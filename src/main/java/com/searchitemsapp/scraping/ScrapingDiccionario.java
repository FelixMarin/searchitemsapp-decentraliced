package com.searchitemsapp.scraping;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.searchitemsapp.dto.SelectoresCssDTO;
import com.searchitemsapp.dto.UrlDTO;
import com.searchitemsapp.model.TbSiaSelectoresCss;
import com.searchitemsapp.util.ClaseUtils;
import com.searchitemsapp.util.LogsUtils;
import com.searchitemsapp.util.StringUtils;

@SuppressWarnings("unchecked")
public class ScrapingDiccionario extends Scraping {

    private UrlDTO urlDto;
    private String producto;
	
	public ScrapingDiccionario(UrlDTO urlDto, String producto) {
		super();
		this.urlDto = urlDto;
		this.producto = producto;
	}
	
	public String checkingHtmlDocument() throws IOException, URISyntaxException, InterruptedException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),this.getClass());
		
		if(validarParametros()) {
			return (String) ClaseUtils.NULL_OBJECT;
		}
		
		super.staticData();
		super.cargarTodasLasMarcas();
		super.setTbSiaSelectoresCss(urlDto);
		String strProductoCorregido;
		StringBuilder palabrasResultado = StringUtils.getNewStringBuilder();
				
		Element elem = (Element) ClaseUtils.NULL_OBJECT;
				
		Document document = getHtmlDocument(urlDto, (Map<String, String>) ClaseUtils.NULL_OBJECT,producto,
						(SelectoresCssDTO) ClaseUtils.NULL_OBJECT).get(ClaseUtils.ZERO_INT);
			
		 if(!ClaseUtils.isNullObject(document)) {
            TbSiaSelectoresCss selectorCss = urlDto
            		.getTbSiaSelectoresCsses().get(ClaseUtils.ZERO_INT);
            
        Elements entrada = selectScrapPattern(document,selectorCss.getScrapPattern(), selectorCss.getScrapNoPattern());
            
            if(!entrada.isEmpty()) {
            	elem = entrada.get(ClaseUtils.ZERO_INT);
            }
            
            if(!ClaseUtils.isNullObject(elem)) {
            	strProductoCorregido = elementoPorCssSelector(elem, 
            			selectorCss.getSelProducto(),
            			urlDto);
            	if(!StringUtils.isEmpty(strProductoCorregido)) {
            		palabrasResultado.append(strProductoCorregido.concat(StringUtils.SPACE_STRING));
            	}else {
            		return StringUtils.NULL_STRING;
            	}
            }
		 }
		
		return palabrasResultado.toString().trim();
	}
	
	public boolean validarParametros() {
		return StringUtils.isEmpty(urlDto.getNomUrl()) ||
				StringUtils.isEmpty(producto);
	}
}
