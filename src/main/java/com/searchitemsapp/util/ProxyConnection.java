package com.searchitemsapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

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

	public static void establecerProxy() {
		
		StringBuilder sbResultado = StringUtils.getNewStringBuilder();
		HttpURLConnection conn = (HttpURLConnection) ClaseUtils.NULL_OBJECT;
		String[] arStrIpPort = (String[]) ClaseUtils.NULL_OBJECT;
		String output;
				
		try {
			URL url = new URL(CommonsPorperties.getValue("flow.value.url.ws.proxy"));
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(StringUtils.GET);
			conn.setRequestProperty(StringUtils.ACCEPT, StringUtils.ACCEPT_VALUE);

			if (conn.getResponseCode() != ClaseUtils.STATUS_OK) {
				throw new ConnectException("HTTP error code : " + conn.getResponseCode());
			}
			
			try (BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {
				
				while ((output = br.readLine()) != null) {
					sbResultado.append(output);
				}
			}
			
			if(sbResultado.length() > 0) {
				arStrIpPort = sbResultado.toString().split(":");
			}
		} catch (IOException e) {
			LogsUtils.escribeLogError("Error en ProxyConnection.getProxyIPfromWebService()", ProxyConnection.class, e);
		} finally  {
			if(!ClaseUtils.isNullObject(conn)) {
				conn.disconnect();
			}
		}
		
		if(ClaseUtils.isNullObject(arStrIpPort)) {
			arStrIpPort = new String[2];
			arStrIpPort[0] = CommonsPorperties.getValue("flow.value.valor.ip"); 
			arStrIpPort[1] = CommonsPorperties.getValue("flow.value.valor.port");
		} 

		System.setProperty("https.proxyHost",arStrIpPort[0]);
		System.setProperty("https.proxyPort",arStrIpPort[1]);
		
		LogsUtils.escribeLogDebug("Proxy IP:" + arStrIpPort[0] + ":" + arStrIpPort[1], JsonUtil.class);
		
		System.setProperty("java.net.useSystemProxies", "true");
	}
}
