package com.searchitemsapp.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.diccionario.Diccionario;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.dto.ResultadoDTO;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.impl.IFImplementacion;
import com.searchitemsapp.scraping.ScrapingUnit;
import com.searchitemsapp.scraping.UrlTreatment;

/**
 * @author Felix Marin Ramirez
 * 
 * Servicio que contiene la lógica para obtener listados 
 * de productos ordenados de los distintos supermercados.
 * 
 */
@Service("listadoProductosService")
public class ListadoProductosService implements IFService<String,String> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ListadoProductosService.class);  
	
	/*
	 * Constantes Globales
	 */
	private static final String SPACE_STRING = " ";
	
	/*
	 * Variables Globales
	 */
	private List<SelectoresCssDTO> listTodosSelectoresCss;
	
	@Autowired
	private IFImplementacion<SelectoresCssDTO, EmpresaDTO> selectoresCssImpl;
	
	@Autowired
	private UrlTreatment urlTreatment;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private Diccionario diccionario;

	/*
	 * Constructor
	 */
	public ListadoProductosService() {
		super();
	}
	
	
	/**
	 * Método principal de servicio web.  Este método contiene 
	 * toda la lógica de negocio del servicio. {@link ScrapingUnit}
	 */
	public String service(final String... str) {

		/**
		 * Esto configurará log4j para que saque los mensajes de log por la 
		 * estándar out (la pantalla habitualmente) con un formato concreto.
		 */
		org.apache.log4j.BasicConfigurator.configure();
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * En este punto se recogen los parametros de entrada
		 * en variables para manejarlos mejor.
		 */
		String didPais = str[0];
		String didCategoria = str[1];
		String ordenacion = str[2];
		String producto = str[3];
		String empresas = str[4];

		/**
		 * Se declaran las variables que serán utilizadasa en el
		 * proceso de ejecución del programa.
		 */
		StringBuilder strJsonResult;
		ScrapingUnit scrapingUnit;
		int contador = 0;

		/**
		 * Validación de las variables de entrada.
		 */
		if (validaCamposEntrada(didCategoria, producto, didPais, ordenacion, empresas))  {		
			return getErrorJsonResponse(Thread.currentThread().getStackTrace().toString(),
					Thread.currentThread().getId());
		}
		
		/**
		 * Crea un grupo de subprocesos que crea nuevos subprocesos según 
		 * sea necesario, pero reutilizará los subprocesos construidos 
		 * previamente cuando estén disponibles. Mejora el rendimiento de 
		 * aplicaciones que ejecutan muchas tareas asincronas de corta duración.
		 */
		ExecutorService executorService = Executors.newCachedThreadPool();

		try {
			
			/**
			 * Se traza el log con el identificador de la categoría.
			 */
			StringBuilder debugMessage = new StringBuilder(10);
			debugMessage.append(CommonsPorperties.getValue("flow.value.categoria.did.txt"));
			debugMessage.append(SPACE_STRING);
			debugMessage.append(didCategoria);

			if(LOGGER.isInfoEnabled()) {
				LOGGER.info(debugMessage.toString(), this.getClass());
			}
			
			/**
			 * El listado de selectores se rellena la primera vez que
			 * se ejecuta la aplicación. Si es nulo se relealiza una
			 * consulta a bbdd para traer los selectores css que se
			 * utilizarán para extraer la información de la páginas
			 * web revisadas. 
			 */
			if(Objects.isNull(listTodosSelectoresCss)) {
				listTodosSelectoresCss = selectoresCssImpl.findAll();
			}
			
			/**
			 * Si en este punto el listado de selectores es nulo, 
			 * significa ha habido un problema en la conexión a
			 * bbdd. Si eso sucede se devuelve un error.
			 */
			if(Objects.isNull(listTodosSelectoresCss)) {
				return getErrorJsonResponse(Thread.currentThread().getStackTrace().toString(),
						Thread.currentThread().getId());
			}			
			
			/**
			 * Se comprueba que el nombre del producto introducido 
			 * está bien escrito. Si el nombre es una palabra que 
			 * no existe, la aprilcación devolverá un error.
			 */			
			String productoAux = diccionario.corregirCaracter(producto, 
					urlTreatment, 
					listTodosSelectoresCss, 
					applicationContext);
			
			/**
			 * En este punto, la aplicación compone las URLs de los supermercados
			 * indicados en la request. Se reemplaza el patron '{1}' por el nombre 
			 * del producto a buscar.
			 */
			Collection<UrlDTO> lResultDtoUrlsTratado = urlTreatment.replaceWildcardCharacter(didPais, 
					didCategoria, productoAux, empresas, listTodosSelectoresCss);

			/**
			 * ArrayList que contiene un objeto encargado de scrapear el producto
			 * en cada uno de los supermercados. Habrá un objeto por cada 
			 * supermercado a rastrear.
			 */
			Collection<ScrapingUnit> callablesScrapingUnit = new ArrayList<>(10);

			/**
			 * Habrá tantas iteraciones como URLs contenga cada supermercado.
			 * En cada iteración se crea una instancia de la calse ScrapingUnit,
			 * a la que se le pasarán los parametros de la request. Cada objeto
			 * de tipo ScrapingUnit se añadirá a la lista.
			 */
			for (UrlDTO urlDto : lResultDtoUrlsTratado) {
				
				scrapingUnit = applicationContext
						.getBean(ScrapingUnit.class, urlDto, productoAux, 
								didPais, didCategoria, ordenacion);
	
				callablesScrapingUnit.add(scrapingUnit);	
			}
			
			/**
			 * Ejecuta las tareas dadas, devolviendo una lista de Futuros con su 
			 * estado y resultados cuando todo esté completo. Future.isDone() es 
			 * verdadero para cada elemento de la lista devuelta.
			 */
			List<Future<List<ResultadoDTO>>> listFutureListResDto = executorService.invokeAll(callablesScrapingUnit);
			List<ResultadoDTO> listResultDtoFinal = executeFuture(listFutureListResDto);
			
			/**
			 * Si la lista de resultados está vacía (no ha habido resultados)
			 * la aplicación devolverá un mensaje notificando el suceso.
			 */
            if(listResultDtoFinal.isEmpty()) {
    			return getErrorJsonResponse(Thread.currentThread().getStackTrace().toString()
    					.concat(new NoResultException(NO_HAY_RESULTADOS).toString()),
    					Thread.currentThread().getId());
            }

            /**
             * Ordenación de los resultados obtenidos. El método 
             * sort ha sido modificado para usar un algoritmo especifico 
             * que ordena los objetos por precio o por precio/kilo, según 
             * se haya indicado en la solicitud del servicio.
             */
            Collections.sort(listResultDtoFinal,new ResultadoDTO());

            /**
             * Se crea un objeto StringBuilider en el que se concatenarán todos
             * los productos formateados en JSON. 
             */
            strJsonResult = new StringBuilder(10);
            
            /**
             * Se itera sobre los resultados para formar un string con formato
             * JSON. Cada iteración corresponde a un producto.
             */
			for (ResultadoDTO resultadoDTO : listResultDtoFinal) {
				strJsonResult.append(toJson(resultadoDTO,contador++));
			}

		}catch(IOException | NoResultException | InterruptedException | ExecutionException | URISyntaxException e) {			
			
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}		
			
			Thread.currentThread().interrupt();		
			
			return getErrorJsonResponse(e.toString(),Thread.currentThread().getId());
		} finally {
			
			/**
			 * Se deshabilita el executor hasta la próxima solicitud de servicio.
			 */
			executorService.shutdown();
		}

		/**
		 * Si el objeto que contiene el resultado en JSON está vacío
		 * (No ha habido resultados) la aplicación devolverá un mensaje
		 * indicando el suceso.
		 */
		if(EMPTY_STRING.contentEquals(strJsonResult.toString())) {
			return  getErrorJsonResponse(Thread.currentThread().getStackTrace().toString()
					.concat(new NoResultException(NO_HAY_RESULTADOS).toString()),
					Thread.currentThread().getId());
		}
		
		/**
		 * Como resultado, la aplicación devuelve una cadena en formato
		 * JSON y que contiene todos los productos ordenados por precio o
		 * por precio/kilo.
		 */
		return toArrayJson(strJsonResult.toString());
	}
	
	/*
	 * Métodos privados
	 */
	
	/**
	 * La clase Future representa un resultado futuro de un cálculo 
	 * asincrónico, un resultado que finalmente aparecerá en el Futuro 
	 * después de que se complete el procesamiento.
	 * 
	 * @param resultList Una lista de listas de resultados.
	 * @return List<ResultadoDTO>
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	private List<ResultadoDTO> executeFuture(final List<Future<List<ResultadoDTO>>> resultList) 
			throws InterruptedException, ExecutionException {
		
		List<ResultadoDTO> listResultFinal = new ArrayList<>(10);
		
		/**
		 * Se itera sobre la lista de futuros. Cada ejecución
		 * de futuro tiene como máximo 5 segundos para ser
		 * ejecutado, en otro caso, continuará con el siguiente.
		 */
		for(Future<List<ResultadoDTO>> future : resultList) {
			
			try {
				/**
				 * Si el futuro de la posición actual es nulo
				 * se continua con la siguente ejecución
				 */
				if(Objects.isNull(future.get())) {
					continue;
				}
				
				/**
				 * El resultado de la ejecución del objeto futuro es 
				 * una lista de Objetos de tipo ResultadoDTO. Todos  
				 * los resultados se unen en una sola lista. 
				 */
				listResultFinal.addAll(future.get(5, TimeUnit.SECONDS));
				
				/**
				 * Se escribe una traza de log indicado el resultado de la ejecución
				 */
				if(LOGGER.isInfoEnabled()) {
					LOGGER.info(future.get().toString().concat(SPACE_STRING)
							.concat(String.valueOf(future.isDone())));
				}
				
			}catch(TimeoutException e) {
				if(LOGGER.isErrorEnabled()) {
					LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
				}
			}
		}
		
		return listResultFinal;
	}	
	
	/**
	 * Valida los campos recibidos en la solicitud de servicio.
	 * 
	 * @param didCategoria
	 * @param producto
	 * @param didPais
	 * @param ordenacion
	 * @param empresas
	 * @return boolean
	 */
	private boolean validaCamposEntrada(final String didCategoria, 
			final String producto, final String didPais, 
			final String ordenacion, final String empresas) {
		
		return Objects.isNull(didCategoria) || 
				Objects.isNull(producto) ||
				Objects.isNull(ordenacion) ||
				Objects.isNull(didPais) ||
				EMPTY_STRING.contentEquals(empresas);
	}
	
	/**
	 * Crea un arrego JSON en una variable de tipo String.
	 * 
	 * @param String
	 * @return String
	 */
	private String toArrayJson(String jsonSinTratar) {

		if (Objects.isNull(jsonSinTratar)) {
			return "null";
		}

		String resultado = jsonSinTratar.replaceAll("\\}\\{", "\\},\\{");
		resultado = "{[".concat(resultado).concat("]}");
		return resultado.replace("{[", "{\"resultado\":[");
	}
	
	private String getErrorJsonResponse(String error, long id) {
		return "{\"id\":\"" + id +"\",\"descripcion\":\"" + error + "\"}";
	}
	
	private String toJson(ResultadoDTO resultadoDTO, int contador) {
		return "{\"identificador\":\"" + ++contador + "\","
				+ "\"nomProducto\":\"" + resultadoDTO.getNomProducto() + "\","
				+ "\"didEmpresa\":\"" + resultadoDTO.getDidEmpresa() + "\","
				+ "\"nomEmpresa\":\"" + resultadoDTO.getNomEmpresa() +  "\","
				+ "\"imagen\":\"" + resultadoDTO.getImagen() +  "\","
				+ "\"nomUrl\":\"" + resultadoDTO.getNomUrl() +  "\","
				+ "\"precio\":\"" + resultadoDTO.getPrecio() + "\","
				+ "\"precioKilo\":\"" + resultadoDTO.getPrecioKilo() + "\"}";
	}
}