package com.searchitemsapp.util;

import java.io.IOException;
import java.io.StringReader;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class ParserXmlJaxb extends DefaultHandler  implements IFUtils {

	private final XMLReader xr;  

	//Atributo del xml a leer
	private String attribute = "";
	
	//Valor del atributo a leer
	private String value = "";
	
	//Nodo del xml que contine dicho atributo a leer
	private String node = "";
	
	
	String contenido="";
    /** Indicador de inicio de etiqueta */
    private boolean bStart = false;

    /** tag actual del parseador */
    private String  tag = null; 

	// Constructor
	public ParserXmlJaxb() throws SAXException {  
		xr = XMLReaderFactory.createXMLReader();  
		xr.setContentHandler(this);  
		xr.setErrorHandler(this);
	}  
	
	/**
	 * Metodo encargado de obtener el valor de un campo dentro de xml.
	 * @param firma
	 * @param tagInput
	 * @return value
	 * @throws IOException
	 * @throws SAXException
	 */
	public String leer(String xml, String sNode, String sAttribute) throws IOException, SAXException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ParserXmlJaxb.class);
		
		this.attribute = sAttribute;
		this.node = sNode;
		xr.parse(new InputSource(new StringReader(xml))); 
		return value;
	}

	/**
	 * Contenido de un nodo divido.
	 */
	@Override
	public void characters(char[] buf, int offset, int len)
    throws SAXException
    {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ParserXmlJaxb.class);
		
        if (bStart && tag != null) {
            contenido = contenido + String.valueOf(buf, offset, len);
        } else {
            contenido = "";
        }

    }
	
	/**
	 * Parseo de elementos nodo a nodo.
	 */
	@Override
	public void endElement(String uri, String localName, String qName) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ParserXmlJaxb.class);
		
		bStart = false;
        tag = qName;
		if ( ClaseUtils.isNullObject(this.attribute)&& qName.trim().equals(this.node)){
			value = contenido;
		}	
	}
	
	
	/**
	 * Parseo de elementos nodo a nodo. Attributes 
	 * contiene los atributos asociados a ese nodo.
	 */
	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),ParserXmlJaxb.class);
		
		bStart = true;
        tag = name;
        contenido = "";
        
        if(node.equals(name) && attributes.getValue(this.attribute)!= null && !"".equals(attributes.getValue(this.attribute))){
	        value = attributes.getValue(this.attribute);
        }
	}
	
	
}
