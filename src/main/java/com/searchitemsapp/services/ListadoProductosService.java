package com.searchitemsapp.services;

import java.io.IOException;
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

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.ResultadoDTO;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.processdata.ProcessDataModule;
import com.searchitemsapp.processdata.UrlComposer;

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
	private static final String ERROR_RESULT = "[{\"request\": \"Error\", " 
			+ "\"id\" : \"-1\", "
			+ "\"description\": \"No hay resultados\"}]";
	
	/*
	 * Variables Globales
	 */
	@Autowired
	private UrlComposer urlComposer;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	/*
	 * Constructor
	 */
	public ListadoProductosService() {
		super();
	}
	
	
	/**
	 * Método principal de servicio web.  Este método contiene 
	 * toda la lógica de negocio del servicio. {@link ProcessDataModule}
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
		 * Se cargan en cache los parametros usados por 
		 * la aplicación. solo se ejecuta una vez.
		 */
		  urlComposer.applicationData();
		  urlComposer.cargarTodasLasMarcas();
		
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
		List<ResultadoDTO> listResultDtoFinal;
		ProcessDataModule scrapingUnit;
		int contador = 0;
		
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
			 StringBuilder stringBuilder = new StringBuilder(1);
			stringBuilder.append(CommonsPorperties.getValue("flow.value.categoria.did.txt"))
			.append(didCategoria);

			if(LOGGER.isInfoEnabled()) {
				LOGGER.info(stringBuilder.toString(), this.getClass());
			}
			
			/**
			 * En este punto, la aplicación compone las URLs de los supermercados
			 * indicados en la request. Se reemplaza el patron '{1}' por el nombre 
			 * del producto a buscar.
			 */
			List<SelectoresCssDTO> lselectores = urlComposer.listSelectoresCssPorEmpresa(empresas);
			Collection<UrlDTO> lResultDtoUrlsTratado = urlComposer.replaceWildcardCharacter(didPais, 
					didCategoria, producto, empresas, lselectores);

			/**
			 * ArrayList que contiene un objeto encargado de scrapear el producto
			 * en cada uno de los supermercados. Habrá un objeto por cada 
			 * supermercado a rastrear.
			 */
			Collection<ProcessDataModule> callablesScrapingUnit = new ArrayList<>(NumberUtils.INTEGER_ONE);

			/**
			 * Habrá tantas iteraciones como URLs contenga cada supermercado.
			 * En cada iteración se crea una instancia de la calse ScrapingUnit,
			 * a la que se le pasarán los parametros de la request. Cada objeto
			 * de tipo ScrapingUnit se añadirá a la lista.
			 */
			for (UrlDTO urlDto : lResultDtoUrlsTratado) {
				
				scrapingUnit = applicationContext
						.getBean(ProcessDataModule.class, urlDto, producto, 
								didPais, didCategoria, ordenacion);
	
				callablesScrapingUnit.add(scrapingUnit);	
			}
			
			/**
			 * Ejecuta las tareas dadas, devolviendo una lista de Futuros con su 
			 * estado y resultados cuando esté completo. Future.isDone() es 
			 * verdadero para cada elemento de la lista devuelta.
			 */
			List<Future<List<ResultadoDTO>>> listFutureListResDto = executorService.invokeAll(callablesScrapingUnit);
			listResultDtoFinal = executeFuture(listFutureListResDto);
			
			/**
			 * Si la lista de resultados está vacía (no ha habido resultados)
			 * la aplicación devolverá un mensaje notificando el suceso.
			 */
            if(listResultDtoFinal.isEmpty()) {
    			return ERROR_RESULT;
            }

            /**
             * Ordenación de los resultados obtenidos. El método 
             * sort ha sido modificado para usar un algoritmo especifico 
             * que ordena los objetos por precio o por precio/kilo, según 
             * se haya indicado en la solicitud del servicio.
             */
            Collections.sort(listResultDtoFinal, new ResultadoDTO());

         	for (int i = 0; i < listResultDtoFinal.size(); i++) {
				listResultDtoFinal.get(i).setIdentificador(++contador);
			}
			
		}catch(IOException | NoResultException | InterruptedException | ExecutionException e) {			
			
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}		
			
			Thread.currentThread().interrupt();		
			
			return "[{\"request\": \"Error\", "
			+ "\"id\" : \"-1\", "
			+ "\"description\": \"" + e.toString() + "\"}]";
			
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
		if(listResultDtoFinal.isEmpty()) {			
			return  ERROR_RESULT;
		}
		
		/**
		 * Como resultado, la aplicación devuelve una cadena en formato
		 * JSON y que contiene todos los productos ordenados por precio o
		 * por precio/kilo.
		 */
		return new Gson().toJson(listResultDtoFinal);
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
		
		List<ResultadoDTO> listResultFinal = new ArrayList<>(NumberUtils.INTEGER_ONE);
		
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
					LOGGER.info(future.get().toString());
					LOGGER.info(String.valueOf(future.isDone()));
				}
				
			}catch(TimeoutException e) {
				if(LOGGER.isErrorEnabled()) {
					LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
				}
			}
		}
		
		return listResultFinal;
	}
}