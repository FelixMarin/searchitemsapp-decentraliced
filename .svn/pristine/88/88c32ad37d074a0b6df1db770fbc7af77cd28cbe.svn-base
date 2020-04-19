package com.searchitemsapp.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searchitemsapp.services.IFService;
import com.searchitemsapp.services.ListadoProductosService;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;

@SuppressWarnings("unchecked")
@Component
public class ServiceFactory {
	
	private static final String LISTA_PRODUCTOS = "LISTA_PRODUCTOS";

	public ServiceFactory() {
		super();
	}
	
	@Autowired
	private ListadoProductosService listadoProductosService;
	
	public IFService<String,String> getService(final String nomService) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ServiceFactory.class);
		
		if(ClaseUtils.isNullObject(nomService)) {
			return (IFService<String,String>) ClaseUtils.NULL_OBJECT;
		}
		
		if(nomService.equals(LISTA_PRODUCTOS)) {
			return listadoProductosService;
		}
		
		return (IFService<String,String>) ClaseUtils.NULL_OBJECT;
	}

}
