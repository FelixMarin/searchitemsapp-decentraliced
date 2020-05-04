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

import com.mchange.util.StringObjectMap;
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

		if (ListaProductosUtils.validaCamposEntrada(didCategoria, producto, didPais, ordenacion, empresas))  {		
			return ListaProductosUtils.errorJsonResponse(Thread.currentThread().getStackTrace()[1].toString(),
					Thread.currentThread().getId());
		}
		
		executorService = Executors.newCachedThreadPool();

		try {
			ListaProductosUtils.logInicioDebugMessage(didCategoria);
			
			if(ClaseUtils.isNullObject(listTodosSelectoresCss)) {
				listTodosSelectoresCss = ListaProductosUtils.fillSelectoresCss(selectoresCssImpl);
			}
			
			if(ClaseUtils.isNullObject(listTodosSelectoresCss)) {
				return ListaProductosUtils.errorJsonResponse(Thread.currentThread().getStackTrace()[1].toString(),
						Thread.currentThread().getId());
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
			listResultDtoFinal = ListaProductosUtils.executeFuture(listFutureListResDto);
			
            if(listResultDtoFinal.isEmpty()) {
    			return ListaProductosUtils.errorJsonResponseNoResultException(
    					Thread.currentThread().getStackTrace()[1].toString(),
    					StringUtils.NO_HAY_RESULTADOS);          	
            }

            Collections.sort(listResultDtoFinal,new ResultadoDTO());
            
            for (int i = ClaseUtils.ZERO_INT; i < listResultDtoFinal.size(); i++) {
            	listResultDtoFinal.get(i).setIdentificador(i+ClaseUtils.ONE_INT);
			}

			for (ResultadoDTO resultadoDTO : listResultDtoFinal) {
								
				strJsonResult.append("{" +
						"\"identificador\":\"" + resultadoDTO.getIdentificador() + "\"," +
						"\"nomProducto\":\"" + resultadoDTO.getNomProducto() + "\"," +
						"\"didEmpresa\":\"" + resultadoDTO.getDidEmpresa() + "\"," +
						"\"nomEmpresa\":\"" + resultadoDTO.getTbSiaEmpresa().getNomEmpresa() + "\"," +
						"\"imagen\":\"" + resultadoDTO.getImagen() + "\"," +
						"\"nomUrl\":\"" + resultadoDTO.getNomUrl() + "\"," +
						"\"precio\":\"" + resultadoDTO.getPrecio() + "\"," +
						"\"precioKilo\":\"" + resultadoDTO.getPrecioKilo() + "\"}");
			}

		}catch(IOException | NoResultException | InterruptedException | ExecutionException | URISyntaxException e) {			
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(),this.getClass(),e);			
			Thread.currentThread().interrupt();			
			return StringUtils.getErrorJsonResponse(e.toString(),Thread.currentThread().getId());
		} finally {
			executorService.shutdown();
		}
		

		if(StringUtils.isEmpty(strJsonResult.toString())) {
			return ListaProductosUtils.errorJsonResponseNoResultException(
					Thread.currentThread().getStackTrace()[1].toString(),
					StringUtils.NO_HAY_RESULTADOS); 
		}
		
		return JsonUtil.toArrayJson(strJsonResult.toString());
	}
}