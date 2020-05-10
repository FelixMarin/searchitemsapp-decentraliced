package com.searchitemsapp.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.persistence.NoResultException;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.EmpresaDTO;
import com.searchitemsapp.dto.ResultadoDTO;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.impl.IFImplementacion;

public class ListaProductosUtils {
	
	public ListaProductosUtils() {
		super();
	}

	public static void logInicioDebugMessage(final String didCategoria) {
		StringBuilder debugMessage = StringUtils.getNewStringBuilder();
		debugMessage.append(CommonsPorperties.getValue("flow.value.categoria.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(didCategoria);
		LogsUtils.escribeLogDebug(debugMessage.toString(),ListaProductosUtils.class);
	}
	
	public static String errorJsonResponseNoResultException(final String linea, final String mensaje) {
		return StringUtils.getErrorJsonResponse(linea
				.concat(new NoResultException(mensaje).toString()),Thread.currentThread().getId()); 		
	}
		
	public static List<SelectoresCssDTO> fillSelectoresCss( 
			IFImplementacion<SelectoresCssDTO, EmpresaDTO> selectoresCssImpl) 
					throws IOException {		
			return selectoresCssImpl.findAll();
	}	
	
	public static boolean validaCamposEntrada(final String didCategoria, 
			final String producto, final String didPais, 
			final String ordenacion, final String empresas) {
		
		return StringUtils.validateNull(didCategoria) || 
				StringUtils.validateNull(producto) ||
				StringUtils.validateNull(ordenacion) ||
				StringUtils.validateNull(didPais) ||
				StringUtils.isEmpty(empresas);
	}
	
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
	public static List<ResultadoDTO> executeFuture(final List<Future<List<ResultadoDTO>>> resultList) 
			throws InterruptedException, ExecutionException {
		
		List<ResultadoDTO> listResultFinal = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
		
		for(Future<List<ResultadoDTO>> future : resultList) {
			
			try {
				if(ClaseUtils.isNullObject(future.get())) {
					continue;
				}
				
				listResultFinal.addAll(future.get(5, TimeUnit.MINUTES));				
				LogsUtils.escribeLogDebug(future.get().toString().concat(StringUtils.SPACE_STRING)
						.concat(String.valueOf(future.isDone())),ListaProductosUtils.class);
			
			}catch(TimeoutException e) {
				LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),ListaProductosUtils.class,e);
			}
		}
		
		return listResultFinal;
	}	
	
	public static String errorJsonResponse(final String mensaje, long id) {
		return StringUtils.getErrorJsonResponse(mensaje, id);
	}	
}
