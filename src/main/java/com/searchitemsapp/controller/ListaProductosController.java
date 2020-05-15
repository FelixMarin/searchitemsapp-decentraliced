package com.searchitemsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.searchitemsapp.factory.ServiceFactory;
import com.searchitemsapp.services.ListadoProductosService;
import com.searchitemsapp.util.LogsUtils;

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

	private static final String LISTA_PRODUCTOS = "LISTA_PRODUCTOS";
	
	@Autowired
	private ServiceFactory serviceFactory;
	
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
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),
				ListaProductosController.class);

		/**
		 * En este punto se establece la dirección del proxy
		 * que utiliza la apicación para realizar el ratreo
		 * de páginas web. En cada petición de servicio 
		 * se solicita una nueva IP de proxy a una API REST
		 * externa.
		 */
		 //ProxyConnection.establecerProxy();
		
		 /**
		  * Llamada al servicio a través del service factory.
		  * 
		  */
		return serviceFactory
				.getService(LISTA_PRODUCTOS)
				.service(didPais, didCategoria, ordenacion, producto, empresas);
	}
}
