package com.searchitemsapp.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searchitemsapp.services.IFService;
import com.searchitemsapp.services.ListadoProductosService;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;

/**
 * 
 * @author Felix Marin Ramirez
 * 
 * Clase Factory encargada de gestionar la creación de 
 * objetos de tipo service. Las peticiones a los services 
 * pasarán siempre por esta clase.
 *
 */
@SuppressWarnings("unchecked")
@Component
public class ServiceFactory {
	
	/*
	 * Variables 
	 */
	private static final String LISTA_PRODUCTOS = "LISTA_PRODUCTOS";

	@Autowired
	private ListadoProductosService listadoProductosService;
	
	/*
	 * Constructor
	 */
	public ServiceFactory() {
		super();
	}
	
	/**
	 * Metodo a través del cual se obtiene un 
	 * objeto de tipo service. Como parámetro
	 * recibe el nombre del objeto service 
	 * solicitado, en otro caso null.
	 * 
	 * @param String
	 * @return IFService<String,String>
	 */
	public IFService<String,String> getService(final String nomService) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ServiceFactory.class);

		if(nomService.equals(LISTA_PRODUCTOS)) {
			return listadoProductosService;
		}
		
		return (IFService<String,String>) ClaseUtils.NULL_OBJECT;
	}

}
