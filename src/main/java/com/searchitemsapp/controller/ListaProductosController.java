package com.searchitemsapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.searchitemsapp.services.ListadoProductosService;
import com.searchitemsapp.services.ServiceFactory;
import com.searchitemsapp.validator.ListaProductosValidator;


/**
 *  Controlador principal de la aplicación. Contiene
 *  todos los servicios que pueden ser invocados 
 *  mediate la URL del servicio. Proporciona 
 *  asignaciones entre rutas de solicitud y métodos 
 *  de controlador.
 *   
 * @author Felix Marin Ramirez
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RestController
public class ListaProductosController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ListaProductosController.class);     

	private static final String LISTA_PRODUCTOS = "LISTA_PRODUCTOS";
	
	@Autowired
	private ServiceFactory serviceFactory;
	
	@Autowired
	@Qualifier("listaProductosValidator")
	private ListaProductosValidator validator;
	
	//@Autowired
	//private ProxyConnection proxyConnection;
	
	/**
	 * @GetMapping para asignar solicitudes HTTP GET a métodos de 
	 * controlador específicos. Se indica mediate notación, la ruta y 
	 * los parametros que debe tener la solicitud de servicio.
	 * {@link ListadoProductosService} 
	 * 
	 * @param didPais String
	 * @param didCategoria String
	 * @param ordenacion String
	 * @param producto String
	 * @param empresas String
	 * @return String
	 */
	@GetMapping(value = "/search", produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody String listaProductos(@RequestBody 
				@RequestParam(value = "pais", defaultValue = "101") String didPais,
				@RequestParam(value = "categoria", defaultValue = "101") String didCategoria,
				@RequestParam(value = "ordenacion", defaultValue = "1") String ordenacion, 
				@RequestParam(value = "producto") @Validated String producto, 
				@RequestParam(value = "empresas") @Validated String empresas,
				Model modelo) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		/**
		 * Validación de los parametros de entrada.
		 */
		boolean isParams = validator.isEmpresa(empresas) &&	
		validator.isOrdenacion(ordenacion) &&	
		validator.isNumeric(didPais, didCategoria) &&
		validator.isParams(didPais, didCategoria, ordenacion, producto, empresas);
		
		/**
		 * Si los parámetros de entrada no superan la validación 
		 * termina el proceso y devuelve um mensaje descriptivo
		 * en formato JSON.
		 */
		if(isParams) {
			
			/**
			 * En este punto se establece la dirección del proxy
			 * que utiliza la apicación para realizar el ratreo
			 * de páginas web. En cada petición de servicio 
			 * se solicita una nueva IP de proxy a una API REST
			 * externa.
			 */
			 //proxyConnection.establecerProxy();
			
			 /**
			  * Llamada al servicio a través del service factory.
			  * 
			  */
			return serviceFactory
					.getService(LISTA_PRODUCTOS)
					.service(didPais, didCategoria, 
							ordenacion, producto, empresas);
		} else {
			
			/**
			 * En el caso de no superar las validaciones, 
			 * la apliación retornará un mensaje indicando
			 * el motivo.
			 */
			return "[{\"request\": \"Error\", "
					+ "\"id\" : \"-1\", "
					+ "\"description\": \"Invalid Input Data\"}]";
		}
	}
}
