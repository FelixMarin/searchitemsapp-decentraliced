package com.searchitemsapp.scraping;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.searchitemsapp.dto.ResultadoDTO;
import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.factory.ParserFactory;
import com.searchitemsapp.model.TbSiaSelectoresCss;
import com.searchitemsapp.parsers.IFParser;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

@SuppressWarnings("unchecked")
public class ScrapingUnit extends Scraping  implements Callable<List<ResultadoDTO>> {
	
	private static Map<Integer, Map<String, String>> mapaCookies = new HashMap<>(ClaseUtils.DEFAULT_INT_VALUE);
	private static final String SELECTORES_PARSER = "SELECTORES_PARSER";
	
	private UrlDTO urlDto; 
	private String producto;
	private String didPais; 
	private String didCategoria;
	private String ordenacion;
	
	public ScrapingUnit(UrlDTO urlDto, String producto, 
			String didPais, String didCategoria, String ordenacion) {
		super();
		this.urlDto = urlDto;
		this.producto = producto;
		this.didPais = didPais;
		this.didCategoria = didCategoria;
		this.ordenacion = ordenacion;
		setTbSiaSelectoresCss(this.urlDto);
	}
	
	
	@Autowired
	private ScrapingLoginUnit scrapingLoginUnit;
	
	@Autowired
	private ParserFactory parserFactory;	
	
	public  List<ResultadoDTO> checkHtmlDocument() throws IOException, URISyntaxException, InterruptedException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(validaUrlDto()) {
			return (List<ResultadoDTO>) ClaseUtils.NULL_OBJECT;
		}
		
		List<ResultadoDTO> lResultadoDto;
		Elements entradas;
		ResultadoDTO resDto;
		SelectoresCssDTO selectorCssDto;
		List<Document> listDocuments;
		
		boolean bStatus = urlDto.getBolStatus();
		String[] arProducto = producto.split(StringUtils.SPACE_STRING);
		int iIdEmpresa = urlDto.getTbSiaEmpresa().getDid();
		Pattern pattern = createPatternProduct(arProducto);
		
		int status = getStatus(bStatus);
		
		if(isNullProducto(arProducto)) {
			return (List<ResultadoDTO>) ClaseUtils.NULL_OBJECT;
		}
		
        if (status == ClaseUtils.STATUS_OK) {
        	
        	selectorCssDto = getParserS().toDTO(urlDto
        			.getTbSiaSelectoresCsses()
        			.get(ClaseUtils.ZERO_INT));
        	
        	listDocuments = getHtmlDocument(urlDto, getCookies(iIdEmpresa), producto, selectorCssDto);
        	
        	lResultadoDto = new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
        	
        	for (Document document : listDocuments) {
        	
	        	if(ClaseUtils.isNullObject(document)) {
	            	return (List<ResultadoDTO>) ClaseUtils.NULL_OBJECT;
	            }
	            
	            if(listDocuments.size() == ClaseUtils.ONE_INT && !validaURL(document.baseUri(),urlDto.getNomUrl()
	            		.replace(StringUtils.SPACE_STRING, StringUtils.SEPARADOR_URL))) {
	            	return (List<ResultadoDTO>) ClaseUtils.NULL_OBJECT;
	            }
	            	        	
	            entradas = selectScrapPattern(document,
	            		selectorCssDto.getScrapPattern(), 
	            		selectorCssDto.getScrapNoPattern());
	
	    		for (Element elem : entradas) {
	    			
	    			if(validaSelector(elem)) {
	    				continue;
	    			}
	    			
	    			resDto = fillDataResultadoDTO(elem, selectorCssDto, urlDto, ordenacion);
	    			
	    			if(validaYCargaResultado(iIdEmpresa, arProducto, resDto,  pattern)) {
	    				lResultadoDto.add(resDto);
	    			}
		        }
        	}	
        } else {
        	return (List<ResultadoDTO>) ClaseUtils.NULL_OBJECT;
        }   
        
        return lResultadoDto;
	}
		
	private int getStatus(final boolean bStatus) {
		return bStatus?
				getStatusConnectionCode(urlDto.getNomUrl()):
					ClaseUtils.STATUS_OK;
	}
	
	private Map<String, String> getCookies(final int iIdEmpresa) throws IOException {
		
		Map<String, String> mapLoginPageCookies = mapaCookies.get(iIdEmpresa);
    	
    	if(ClaseUtils.isNullObject(mapLoginPageCookies)) {
			mapLoginPageCookies = scrapingLoginUnit
					.checkingHtmlLoginDocument(didPais, didCategoria, iIdEmpresa, mapaCookies);
    	}
    	
    	return mapLoginPageCookies;
	}
	
	private boolean validaUrlDto() {
		return ClaseUtils.isNullObject(urlDto) ||
				ClaseUtils.isNullObject(urlDto.getTbSiaEmpresa()) ||
				urlDto.getTbSiaEmpresa().getTbSiaSelectoresCsses().isEmpty();
	}
		
	private boolean validaYCargaResultado(final int iIdEmpresa, 
			final String[] arProducto, 
			final ResultadoDTO resDto, 
			final Pattern pattern) {
		
		if(validateContent(arProducto, resDto.getNomProducto(),iIdEmpresa, pattern) && 
				!ClaseUtils.isNullObject(resDto.getPrecio())) {
			return Boolean.TRUE;
		} else {
			LogsUtils.escribeLogDebug("WARNING: ".concat(resDto.toString()),this.getClass());
			return Boolean.FALSE;
		}
	}
		
	private IFParser<SelectoresCssDTO, TbSiaSelectoresCss> getParserS() {
		return ((IFParser<SelectoresCssDTO, TbSiaSelectoresCss>) parserFactory.getParser(SELECTORES_PARSER));
	}
	
	@Override
	public List<ResultadoDTO> call() throws IOException, URISyntaxException, InterruptedException {
		return checkHtmlDocument();
	}	
}
