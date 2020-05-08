package com.searchitemsapp.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.searchitemsapp.diccionario.Diccionario;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.dto.ResultadoDTO;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.impl.IFImplementacion;
import com.searchitemsapp.scraping.ScrapingUnit;
import com.searchitemsapp.scraping.UrlTreatment;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.JsonUtil;
import com.searchitemsapp.util.ListaProductosUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

/**
 * @author Felix Marin Ramirez
 * 
 * Servicio que contiene la lógica para obtener listados 
 * de productos ordenados de los distintos supermercados.
 */
@Service("listadoProductosService")
public class ListadoProductosService implements IFService<String,String> {
	
	/**
	 * Variables
	 */
	private static List<SelectoresCssDTO> listTodosSelectoresCss;
	
	@Autowired
	private IFImplementacion<SelectoresCssDTO, EmpresaDTO> selectoresCssImpl;
	
	@Autowired
	private UrlTreatment urlTreatment;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private Diccionario diccionario;

	/**
	 * Constructor
	 */
	public ListadoProductosService() {
		super();
	}
	
	
	/**
	 * Método principal de servicio web.  Este método contiene 
	 * toda la lógica de negocio del servicio.
	 */
	public String service(final String... str) {

		/**
		 * Esto configurará log4j para que saque los mensajes de log por la 
		 * estándar out (la pantalla habitualmente) con un formato concreto.
		 */
		org.apache.log4j.BasicConfigurator.configure();
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
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
		Collection<UrlDTO> lResultDtoUrlsTratado;
		List<ResultadoDTO> listResultDtoFinal;
		List<Future<List<ResultadoDTO>>> listFutureListResDto;
		Collection<ScrapingUnit> callablesScrapingUnit;
		ExecutorService executorService;
		StringBuilder strJsonResult;
		ScrapingUnit scrapingUnit;
		int contador = 0;

		/**
		 * Validación de las variables de entrada.
		 */
		if (ListaProductosUtils.validaCamposEntrada(didCategoria, producto, didPais, ordenacion, empresas))  {		
			return ListaProductosUtils.errorJsonResponse(Thread.currentThread().getStackTrace()[1].toString(),
					Thread.currentThread().getId());
		}
		
		/**
		 * Crea un grupo de subprocesos que crea nuevos subprocesos según 
		 * sea necesario, pero reutilizará los subprocesos construidos 
		 * previamente cuando estén disponibles. Mejora el rendimiento de 
		 * aplicaciones que ejecutan muchas tareas asincronas de corta duración.
		 */
		executorService = Executors.newCachedThreadPool();

		try {
			
			/**
			 * Se traza el log con el identificador de la categoría.
			 */
			ListaProductosUtils.logInicioDebugMessage(didCategoria);
			
			/**
			 * El listado de selectores se rellena la primera vez que
			 * se ejecuta la aplicación. Si es nulo se relealiza una
			 * consulta a bbdd para traer los selectores css que se
			 * utilizarán para extraer la información de la páginas
			 * web revisadas. 
			 */
			if(ClaseUtils.isNullObject(listTodosSelectoresCss)) {
				listTodosSelectoresCss = ListaProductosUtils.fillSelectoresCss(selectoresCssImpl);
			}
			
			/**
			 * Si en este punto el listado de selectores es nulo, 
			 * significa ha habido un problema en la conexión a
			 * bbdd. Si eso sucede se devuelve un error.
			 */
			if(ClaseUtils.isNullObject(listTodosSelectoresCss)) {
				return ListaProductosUtils.errorJsonResponse(Thread.currentThread().getStackTrace()[1].toString(),
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
			lResultDtoUrlsTratado = urlTreatment.replaceWildcardCharacter(didPais, 
					didCategoria, productoAux, empresas, listTodosSelectoresCss);

			/**
			 * ArrayList que contiene un objeto encargado de scrapear el producto
			 * en cada uno de los supermercados. Habrá un objeto por cada 
			 * supermercado a rastrear.
			 */
			callablesScrapingUnit = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);

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
			listFutureListResDto = executorService.invokeAll(callablesScrapingUnit);
			listResultDtoFinal = ListaProductosUtils.executeFuture(listFutureListResDto);
			
			/**
			 * Si la lista de resultados está vacía (no ha habido resultados)
			 * la aplicación devolverá un mensaje notificando el suceso.
			 */
            if(listResultDtoFinal.isEmpty()) {
    			return ListaProductosUtils.errorJsonResponseNoResultException(
    					Thread.currentThread().getStackTrace()[1].toString(),
    					StringUtils.NO_HAY_RESULTADOS);          	
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
            strJsonResult = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
            
            /**
             * Se itera sobre los resultados para formar un string con formato
             * JSON. Cada iteración corresponde a un producto.
             */
			for (ResultadoDTO resultadoDTO : listResultDtoFinal) {
								
				strJsonResult.append("{\"identificador\":\"" + ++contador + "\","
						+ "\"nomProducto\":\"" + resultadoDTO.getNomProducto() + "\","
						+ "\"didEmpresa\":\"" + resultadoDTO.getDidEmpresa() + "\","
						+ "\"nomEmpresa\":\"" + resultadoDTO.getTbSiaEmpresa().getNomEmpresa() +  "\","
						+ "\"imagen\":\"" + resultadoDTO.getImagen() +  "\","
						+ "\"nomUrl\":\"" + resultadoDTO.getNomUrl() +  "\","
						+ "\"precio\":\"" + resultadoDTO.getPrecio() + "\","
						+ "\"precioKilo\":\"" + resultadoDTO.getPrecioKilo() + "\"}");
			}

		}catch(IOException | NoResultException | InterruptedException | ExecutionException | URISyntaxException e) {			
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);			
			Thread.currentThread().interrupt();			
			return StringUtils.getErrorJsonResponse(e.toString(),Thread.currentThread().getId());
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
		if(StringUtils.isEmpty(strJsonResult.toString())) {
			return ListaProductosUtils.errorJsonResponseNoResultException(
					Thread.currentThread().getStackTrace()[1].toString(),
					StringUtils.NO_HAY_RESULTADOS); 
		}
		
		/**
		 * Como resultado, la aplicación devuelve una cadena en formato
		 * JSON y que contiene todos los productos ordenados por precio o
		 * por precio/kilo.
		 */
		return JsonUtil.toArrayJson(strJsonResult.toString());
	}
}