package com.searchitemsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.searchitemsapp.factory.ServiceFactory;
import com.searchitemsapp.util.LogsUtils;

/**
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
	 * Este método es el que recibe los parametros 
	 * de entrada e inicializa el proceso de la petición.
	 * 
	 * 
	 * @param didPais
	 * @param didCategoria
	 * @param ordenacion
	 * @param producto
	 * @param empresas
	 * @return Un JSON con el listado de productos de menor a mayor. 
	 * La respuesta es en formato JSON.
	 */
	@GetMapping(value = "/search/{didPais}//{didCategoria}/{ordenacion}/{producto}/{empresas}", 
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public String listaProductos( @PathVariable("didPais") String didPais, @PathVariable("didCategoria") 
	String didCategoria,@PathVariable("ordenacion") String ordenacion, 
	@PathVariable("producto") String producto, @PathVariable("empresas") String empresas) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),
				ListaProductosController.class);

		/**
		 *   // para inicializar el proxy, descomenta esta línea 
		 *   // JsonUtil.establecerProxy();
		 */
		
		return serviceFactory
				.getService(LISTA_PRODUCTOS)
				.service(didPais, didCategoria, ordenacion, producto, empresas);
	}
}
