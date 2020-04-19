package com.searchitemsapp.scraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.diccionario.FillSelectores;
import com.searchitemsapp.dto.CategoriaDTO;
import com.searchitemsapp.dto.PaisDTO;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.impl.UrlImpl;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

public class UrlTreatment extends Scraping {
	
	
	public UrlTreatment() {
		super();
	}
	
	@Autowired
	private UrlImpl urlImpl;
	
	@Autowired
	private CategoriaDTO categoriaDto;
	
	@Autowired 
	private PaisDTO paisDto;
	
	@Autowired
	private FillSelectores fillSelectores;
	
	public List<UrlDTO> replaceWildcardCharacter(final String didPais, 
			final String didCategoria, 
			final String producto,
			final String empresas,
			final List<SelectoresCssDTO> listTodosSelectoresCss) 
			throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		List<UrlDTO> listUrlDto;
		String productoTratado;	
		String productoTratadoAux;
		String urlAux;
		
		paisDto.setDid(StringUtils.desformatearEntero(didPais));		
		categoriaDto.setDid(StringUtils.desformatearEntero(didCategoria));
		
		List<UrlDTO> pListResultadoDto  = urlImpl.obtenerUrlsPorIdEmpresa(paisDto, categoriaDto, empresas);
		productoTratadoAux= tratarProducto(producto);
		listUrlDto = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
		
		for (UrlDTO urlDto : pListResultadoDto) {
			
			fillSelectores.fillSelectoresCss(urlDto, listTodosSelectoresCss);
			
			if(urlDto.getBolActivo()) {
				if(getMapEmpresas().get(StringUtils.EROSKI) == urlDto.getTbSiaEmpresa().getDid()) {
					productoTratado = reemplazarCaracteresEroski(producto);
					productoTratado = tratarProducto(productoTratado);
				} else if(getMapEmpresas().get(StringUtils.SIMPLY) == urlDto.getTbSiaEmpresa().getDid()) {
					productoTratado = reeplazarCaracteresSimply(producto);
					productoTratado = tratarProducto(productoTratado);
				} else if(getMapEmpresas().get(StringUtils.CONDIS) == urlDto.getTbSiaEmpresa().getDid()) {
					productoTratado = reeplazarTildesCondis(producto);
					productoTratado = reeplazarCaracteresCondis(productoTratado);
					productoTratado = tratarProducto(productoTratado);
				} else {
					productoTratado = productoTratadoAux;
				}
				
				urlAux = urlDto.getNomUrl();
				urlAux = urlAux.replace(StringUtils.WILDCARD, productoTratado);
				urlDto.setNomUrl(urlAux);
				listUrlDto.add(urlDto);
			} else {
				LogsUtils.escribeLogDebug("La URL: "
							.concat(urlDto.getDid().toString())
							.concat(" esta deshabilitada.")
							,this.getClass());
			}
		}
		return listUrlDto;
	}

	public String replaceWildcardCharacterDiccionario(final String url, 
			final String producto) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());

		String productoTratado= tratarProducto(producto);
		String urlAux = url;
		return urlAux.replace(StringUtils.WILDCARD, productoTratado);
	}
}
