package com.searchitemsapp.diccionario;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.searchitemsapp.commons.CommonsPorperties;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.impl.UrlImpl;
import com.searchitemsapp.model.TbSiaEmpresa;
import com.searchitemsapp.scraping.ScrapingDiccionario;
import com.searchitemsapp.scraping.UrlTreatment;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

public class Diccionario {
	
	private static UrlDTO urlDTODiccionario;
	private static UrlDTO urlDto;

	public Diccionario() {
		super();
	}
	
	@Autowired
	private UrlImpl urlImpl;
	
	@Autowired
	private FillSelectores fillSelectores;

	public String corregirCaracter(final String producto, 
			final UrlTreatment urlTreatment, 
			final List<SelectoresCssDTO> listTodosSelectoresCss,
			final ApplicationContext applicationContext) 
			throws IOException, URISyntaxException, InterruptedException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		ScrapingDiccionario scrapingDiccionario;
		
		String urlAux;
		String strResultadoProducto;
		UrlDTO urlDtoAux;
		StringBuilder strResultado = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
		
		String[] arPalabras = producto.split(StringUtils.SPACE_STRING);
		
		for (int i = 0; i < arPalabras.length; i++) {
			if(ClaseUtils.isNullObject(urlDTODiccionario)) {
				urlDtoAux = new UrlDTO();
				urlDtoAux.setDid(Integer.parseInt(CommonsPorperties.getValue("flow.value.url.did.diccionario")));
				urlDtoAux.setTbSiaEmpresa(new TbSiaEmpresa());
				urlDtoAux.getTbSiaEmpresa().setDid(Integer.parseInt(CommonsPorperties.getValue("flow.value.empresa.did.diccionario")));
				setResultadoDTODiccionario(urlDtoAux);
				setUrlDto(urlImpl.findByDid(urlDtoAux));
				urlDTODiccionario.getTbSiaEmpresa().setDid(urlDtoAux.getTbSiaEmpresa().getDid());
				fillSelectores.fillSelectoresCss(urlDTODiccionario, listTodosSelectoresCss);
			}
			
			urlAux = urlTreatment.replaceWildcardCharacterDiccionario(urlDto.getNomUrl(), arPalabras[i]);
			urlDTODiccionario.setNomUrl(urlAux);
			setTbSiaSelectoresCss(urlDTODiccionario);
			
			scrapingDiccionario =  applicationContext
					.getBean(ScrapingDiccionario.class, urlDTODiccionario, arPalabras[i]);
			
			strResultadoProducto = scrapingDiccionario
					.checkingHtmlDocument();
			
			strResultado.append(StringUtils.NULL_STRING==strResultadoProducto?
					producto:strResultadoProducto)
					.append(StringUtils.SPACE_STRING);
		}
		
		if(StringUtils.isEmpty(strResultado.toString()) ||
				!StringUtils.eliminarTildes(strResultado.toString().trim()).equalsIgnoreCase(producto)) {
			strResultado = new StringBuilder(producto);
		}
		
		return strResultado.toString().trim();
	}
	
	public static void setResultadoDTODiccionario(UrlDTO urlDTODiccionario) {
		Diccionario.urlDTODiccionario = urlDTODiccionario;
	}
	
	public static void setUrlDto(UrlDTO urlDto) {
		Diccionario.urlDto = urlDto;
		setTbSiaSelectoresCss(Diccionario.urlDto);
	}
	
	private static void setTbSiaSelectoresCss(UrlDTO urlDto) {
		if(!ClaseUtils.isNullObject(urlDto) &&
				!ClaseUtils.isNullObject(Diccionario.urlDto.getTbSiaEmpresa())) {
			urlDto.setTbSiaSelectoresCsses(
					Diccionario.urlDto.getTbSiaEmpresa().getTbSiaSelectoresCsses());
		}
	}

}
