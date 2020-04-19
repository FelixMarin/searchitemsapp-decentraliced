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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.persistence.NoResultException;

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
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.JsonUtil;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

@Service("listadoProductosService")
public class ListadoProductosService implements IFService<String,String> {
	
	private static List<SelectoresCssDTO> listTodosSelectoresCss;
	
	@Autowired
	private IFImplementacion<SelectoresCssDTO, EmpresaDTO> selectoresCssImpl;
	
	@Autowired
	private UrlTreatment urlTreatment;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private Diccionario diccionario;

	public ListadoProductosService() {
		super();
	}
	
	public String service(final String... str) {

		org.apache.log4j.BasicConfigurator.configure();
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		String didPais = str[0];
		String didCategoria = str[1];
		String ordenacion = str[2];
		String producto = str[3];
		String empresas = str[4];

		Collection<UrlDTO> lResultDtoUrlsTratado;
		List<ResultadoDTO> listResultDtoFinal;
		List<Future<List<ResultadoDTO>>> listFutureListResDto;
		Collection<ScrapingUnit> callablesScrapingUnit;
		ExecutorService executorService;
		StringBuilder strJsonResult;
		ScrapingUnit scrapingUnit;

		if (validaCamposEntrada(didCategoria, producto, didPais, ordenacion, empresas))  {		
			return errorJsonResponse(Thread.currentThread().getStackTrace()[1].toString());
		}
		
		executorService = Executors.newCachedThreadPool();

		try {
			logInicioDebugMessage(didCategoria);
			fillSelectoresCss();
			
			if(ClaseUtils.isNullObject(listTodosSelectoresCss)) {
				return errorJsonResponse(Thread.currentThread().getStackTrace()[1].toString());
			}

			strJsonResult = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
			String productoAux = diccionario.corregirCaracter(producto, 
					urlTreatment, 
					listTodosSelectoresCss, 
					applicationContext);
			
			lResultDtoUrlsTratado = urlTreatment.replaceWildcardCharacter(didPais, 
					didCategoria, productoAux, empresas, listTodosSelectoresCss);

			callablesScrapingUnit = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);

			for (UrlDTO urlDto : lResultDtoUrlsTratado) {
				
				scrapingUnit = applicationContext
						.getBean(ScrapingUnit.class, urlDto, productoAux, 
								didPais, didCategoria, ordenacion);
	
				callablesScrapingUnit.add(scrapingUnit);	
			}
			
			listFutureListResDto = executorService.invokeAll(callablesScrapingUnit);
			listResultDtoFinal = executeFuture(listFutureListResDto);
			
            if(listResultDtoFinal.isEmpty()) {
    			return errorJsonResponseNoResultException(
    					Thread.currentThread().getStackTrace()[1].toString(),
    					StringUtils.NO_HAY_RESULTADOS);          	
            }

            Collections.sort(listResultDtoFinal,new ResultadoDTO());
            
            for (int i = ClaseUtils.ZERO_INT; i < listResultDtoFinal.size(); i++) {
            	listResultDtoFinal.get(i).setIdentificador(i+ClaseUtils.ONE_INT);
			}

			for (ResultadoDTO resultadoDTO : listResultDtoFinal) {
				strJsonResult.append(JsonUtil.toJson(resultadoDTO));
			}

		}catch(IOException | NoResultException | InterruptedException | ExecutionException | URISyntaxException e) {			
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);			
			Thread.currentThread().interrupt();			
			return StringUtils.getErrorJsonResponse(e.toString());
		} finally {
			executorService.shutdown();
		}
		

		if(StringUtils.isEmpty(strJsonResult.toString())) {
			return errorJsonResponseNoResultException(
					Thread.currentThread().getStackTrace()[1].toString(),
					StringUtils.NO_HAY_RESULTADOS); 
		}
		
		return JsonUtil.toArrayJson(strJsonResult.toString());
	}

	private void logInicioDebugMessage(final String didCategoria) {
		StringBuilder debugMessage = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
		debugMessage.append(CommonsPorperties.getValue("flow.value.categoria.did.txt"));
		debugMessage.append(StringUtils.SPACE_STRING);
		debugMessage.append(didCategoria);
		LogsUtils.escribeLogDebug(debugMessage.toString(),this.getClass());
	}
	
	private String errorJsonResponseNoResultException(final String linea, final String mensaje) {
		return StringUtils.getErrorJsonResponse(linea
				.concat(new NoResultException(mensaje).toString())); 		
	}
		
	private void fillSelectoresCss() throws IOException {
		if(ClaseUtils.isNullObject(listTodosSelectoresCss)) {
			setListTodosSelectoresCss(selectoresCssImpl.findAll());
		}
	}	
	
	private boolean validaCamposEntrada(final String didCategoria, 
			final String producto, final String didPais, 
			final String ordenacion, final String empresas) {
		
		return StringUtils.validateNull(didCategoria) || 
				StringUtils.validateNull(producto) ||
				StringUtils.validateNull(ordenacion) ||
				StringUtils.validateNull(didPais) ||
				StringUtils.isEmpty(empresas);
	}
	
	private List<ResultadoDTO> executeFuture(final List<Future<List<ResultadoDTO>>> resultList) 
			throws InterruptedException, ExecutionException {
		
		List<ResultadoDTO> listResultFinal = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
		
		for(Future<List<ResultadoDTO>> future : resultList) {
			
			try {
				if(ClaseUtils.isNullObject(future.get())) {
					continue;
				}
				
				listResultFinal.addAll(future.get(5, TimeUnit.MINUTES));				
				LogsUtils.escribeLogDebug(future.get().toString().concat(StringUtils.SPACE_STRING)
						.concat(String.valueOf(future.isDone())),this.getClass());
			
			}catch(TimeoutException e) {
				LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);
			}
		}
		
		return listResultFinal;
	}	
	
	private String errorJsonResponse(final String mensaje) {
		return StringUtils.getErrorJsonResponse(mensaje);
	}
	
	private static void setListTodosSelectoresCss(List<SelectoresCssDTO> listTodosSelectoresCss) {
		ListadoProductosService.listTodosSelectoresCss = listTodosSelectoresCss;
	}	
}