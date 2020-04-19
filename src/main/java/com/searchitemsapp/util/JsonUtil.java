package com.searchitemsapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;

import com.searchitemsapp.commons.CommonsPorperties;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class JsonUtil implements IFUtils {

	private JsonUtil() {
		super();
	}

	public static String toJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(obj);

			// si detecta una propiedad que es un string json lo combierte a map
			Map objMap = mapper.readValue(json, Map.class);

			boolean change = false;
			for (Object objProperty : objMap.keySet()) {
				String property = (String) objProperty;
				change = toJsonAux(property, objMap, mapper);
			}
			if (change) {
				json = mapper.writeValueAsString(objMap);
			}
			return json;
		} catch (IOException e) {
			LogsUtils.escribeLogError("Mensaje: ".concat(e.getMessage()), JsonUtil.class, e);
		}
		return StringUtils.NULL_STRING;
	}

	private static boolean toJsonAux(String property, Map objMap, ObjectMapper mapper) throws IOException {
		boolean change = Boolean.FALSE;
		if (property.toLowerCase().contains("json")) {
			Object objValue = objMap.get(property);
			if (objValue instanceof String && !ClaseUtils.isNullObject(objValue)) {
				String value = (String) objValue;
				if (!value.isEmpty() && value.startsWith("{")) {
					Map mapDetected = mapper.readValue(value, Map.class);
					objMap.put(property, mapDetected);
					change = Boolean.TRUE;
				}
			}
		}
		return change;
	}

	public static <T> T toBean(String json, Class<T> clazz) {

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object[]> implementationClassMap = new HashMap<>(ClaseUtils.DEFAULT_INT_VALUE);
		String property;

		// si detecta una propiedad que es un json como map lo combierte a string
		try {
			Map objMap = mapper.readValue(json, Map.class);

			Object instance = clazz.newInstance();

			Set<Object> propertykeys = new HashSet<>(objMap.keySet());
			boolean change = false;
			for (Object objProperty : propertykeys) {
				property = (String) objProperty;
				change = toBeanAux1(instance, property, objMap, mapper, implementationClassMap);
			}
			if (change) {
				json = mapper.writeValueAsString(objMap);
			}

		} catch (IOException | InstantiationException | IllegalAccessException e1) {
			LogsUtils.escribeLogError("Error al Parsear Objeto JSON", JsonUtil.class, e1);
		}

		try {
			T instance = mapper.readValue(json, clazz);
			for (Map.Entry<String, Object[]> entry : implementationClassMap.entrySet()) {
				property = entry.getKey();
				Object[] classAndValue = implementationClassMap.get(property);
				Class<?> implementationClass = (Class<?>) classAndValue[0];
				String value = (String) classAndValue[1];
				Object ojbProperty = mapper.readValue(value, implementationClass);
				putPropertyValue(instance, property, ojbProperty);
			}
			return instance;
		} catch (IOException e) {
			LogsUtils.escribeLogError("Error al Parsear Objeto JSON", JsonUtil.class, e);
			return (T) ClaseUtils.NULL_OBJECT;
		}
	}

	private static boolean toBeanAux1(Object instance, String property, Map objMap, ObjectMapper mapper,
			Map<String, Object[]> implementationClassMap) throws IOException {
		boolean change = Boolean.FALSE;
		if (property.toLowerCase().contains("json")) {
			Object objValue = objMap.get(property);
			if (objValue instanceof Map) {
				Map value = (Map) objValue;
				String textDetected = mapper.writeValueAsString(value);
				objMap.put(property, textDetected);
				change = true;
			}
		} else {
			// si detecta un interfaz que no sabra instanciar jackson
			// lo guarda en implementationClassMap para hacerlo aparte
			Object ojbProperty = objMap.get(property);
			if (ojbProperty != null) {
				Class<?> implementationClass = getImplementationClassOfInterface(instance, property);
				if (implementationClass != null) {
					implementationClassMap.put(property,
							new Object[] { implementationClass, mapper.writeValueAsString(ojbProperty) });
					objMap.remove(property);
					change = true;
				}
			}
		}
		return change;
	}

	private static void putPropertyValue(Object instance, String property, Object ojbProperty) {
		String methodName = "set" + property.substring(0, 1).toUpperCase() + property.substring(1);
		for (Method method : instance.getClass().getMethods()) {
			if (method.getName().equals(methodName)) {
				try {
					method.invoke(instance, ojbProperty);
					break;
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					LogsUtils.escribeLogError("Error JSON.putPropertyValue", JsonUtil.class, e);
				}
			}
		}

	}

	private static Class<?> getImplementationClassOfInterface(Object instance, String property) {
		Class<?> implementationClass = null;
		try {
			String methodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
			Class<?> clazz = instance.getClass();
			Method method = clazz.getMethod(methodName, clazz);
			Class<?> returnType = method.getReturnType();
			if (returnType.isInterface()) {
				Object defaultImplementation = method.invoke(instance, new Object());
				if (defaultImplementation != null) {
					implementationClass = defaultImplementation.getClass();
				}
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			LogsUtils.escribeLogError("Error en JSON.getImplementationClassOfInterface", JsonUtil.class, e);
		}
		return implementationClass;
	}

	public static String toArrayJson(String jsonSinTratar) {

		if (StringUtils.validateNull(jsonSinTratar)) {
			return StringUtils.NULL_STRING;
		}

		String resultado = jsonSinTratar.replaceAll("\\}\\{", "\\},\\{");
		resultado = "{[".concat(resultado).concat("]}");
		return resultado.replace("{[", "{\"resultado\":[");
	}
	
	public static void establecerProxy() {
		
		StringBuilder sbResultado = new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
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
			LogsUtils.escribeLogError("Error en JsonUtil.getProxyIPfromWebService()", JsonUtil.class, e);
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
