package com.searchitemsapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.searchitemsapp.services.ListadoProductosService;
import com.searchitemsapp.services.ServiceFactory;

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
	@GetMapping(value = "/search/{didPais}//{didCategoria}/{ordenacion}/{producto}/{empresas}", 
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public String listaProductos( @PathVariable("didPais") String didPais, @PathVariable("didCategoria") 
	String didCategoria,@PathVariable("ordenacion") String ordenacion, 
	@PathVariable("producto") String producto, @PathVariable("empresas") String empresas) {
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(Thread.currentThread().getStackTrace()[1].toString());
		}

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
				.service(didPais, didCategoria, ordenacion, producto, empresas);
	}
}
