package com.searchitemsapp.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searchitemsapp.commons.CommonsPorperties;

 /**
  * Esta clase establece el proxy, a través del cual,
  * se realizara el rastreo de las paginas web de las 
  * que se extraerán los datos.
  * 
  * @author Felix Marin Ramirez
  *
  */
public class ProxyConnection {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProxyConnection.class); 
	
	/*
	 * Constantes Globales
	 */
	private static final String GET = "GET";
	private static final String ACCEPT = "Accept";
	private static final String ACCEPT_VALUE = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
	
	/*
	 * Constructor
	 */
	public ProxyConnection() {
		super();
	}

	/**
	 * Método que establece una conexion con el proxy indicado
	 * en el fichero de propiedades.
	 * 
	 */
	public void establecerProxy() {
		
		HttpURLConnection conn = null;
		String[] arStrIpPort = null;
		String output;
		
		try {
			URL url = new URL(CommonsPorperties.getValue("flow.value.url.ws.proxy"));
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(GET);
			conn.setRequestProperty(ACCEPT, ACCEPT_VALUE);

			if (conn.getResponseCode() != 200) {
				throw new ConnectException("HTTP error code : " + conn.getResponseCode());
			}
			
			StringBuilder stringBuilder = new StringBuilder(1);
			try (BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {
				
				while (Objects.nonNull(output = br.readLine())) {
					stringBuilder.append(output);
				}
			}
			
			if(stringBuilder.length() > 0) {
				arStrIpPort = stringBuilder.toString().split(":");
			} 
		} catch (IOException e) {
			if(LOGGER.isErrorEnabled()) {
				LOGGER.error(Thread.currentThread().getStackTrace()[1].toString(),e);
			}
		} finally  {
			if(Objects.nonNull(conn)) {
				conn.disconnect();
			}
		}
		
		if(Objects.isNull(arStrIpPort)) {
			arStrIpPort = new String[2];
			arStrIpPort[0] = CommonsPorperties.getValue("flow.value.valor.ip"); 
			arStrIpPort[1] = CommonsPorperties.getValue("flow.value.valor.port");
		} 

		System.setProperty("https.proxyHost",arStrIpPort[0]);
		System.setProperty("https.proxyPort",arStrIpPort[1]);		
		System.setProperty("java.net.useSystemProxies", "true");
	}
}
