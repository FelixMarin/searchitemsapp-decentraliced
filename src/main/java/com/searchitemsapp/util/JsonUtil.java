package com.searchitemsapp.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Clase con métodos de utilidad para
 * manipular objetos de tipo JSON.
 * 
 * @author Felix Marin Ramirez
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class JsonUtil implements IFUtils {

	/**
	 * Constructor
	 */
	private JsonUtil() {
		super();
	}

	/**
	 * Transforma un objeto con estructura
	 * JSON a otro objeto de tipo Map.
	 *  
	 * @param Object (Map || String)
	 * @return String 
	 */
	public static String toJson(Object obj) {
		
		/**
		 * Este mapeador (o enlazador de datos o códec) 
		 * proporciona funcionalidad para la conversión 
		 * entre objetos Java (las instancias de JDK 
		 * proporcionan clases centrales, beans) y 
		 * construcciones JSON coincidentes.
		 */
		ObjectMapper mapper = new ObjectMapper();
		boolean change = false;
		
		try {
			
			/**
			 * 'writeValueAsString' se utiliza para serializar 
			 * cualquier valor de Java como String.
			 */
			String json = mapper.writeValueAsString(obj);

			/**
			 * Si detecta una propiedad que es un String 
			 * con estructura JSON, lo combierte a otro 
			 * objeto de tipo Map.
			 */
			Map objMap = mapper.readValue(json, Map.class);
			
			/**
			 * Construccion del mapa a partir de String.
			 */
			for (Object objProperty : objMap.keySet()) {
				String property = (String) objProperty;
				change = toJsonAux(property, objMap, mapper);
			}
			
			/**
			 * Si true, convierte un mapa en un String
			 * con formato JSON.
			 */
			if (change) {
				json = mapper.writeValueAsString(objMap);
			}
			
			return json;
			
		} catch (IOException e) {
			LogsUtils.escribeLogError("Mensaje: ".concat(e.getMessage()), JsonUtil.class, e);
		}
		return StringUtils.NULL_STRING;
	}

	/**
	 * Método de validación. Convierte un String 
	 * con formato JSON en un ojeto de tipo Map.
	 * 
	 * @param property
	 * @param objMap
	 * @param mapper
	 * @return boolean
	 * @throws IOException
	 */
	private static boolean toJsonAux(String property, Map objMap, ObjectMapper mapper) throws IOException {
		boolean change = Boolean.FALSE;
		if (property.toLowerCase().contains("json")) {
			Object objValue = objMap.get(property);
			if (!ClaseUtils.isNullObject(objValue) &&
					objValue instanceof String) {
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

	/**
	 * Convierte un String con formato JSON
	 * a un objeto bean previamente definido.
	 * 
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return <T>
	 */
	public static <T> T toBean(String json, Class<T> clazz) {

		Map<String, Object[]> implementationClassMap = new HashMap<>(ClaseUtils.DEFAULT_INT_VALUE);
		String property;
		
		/**
		 * Este mapeador (o enlazador de datos o códec) 
		 * proporciona funcionalidad para la conversión 
		 * entre objetos Java (las instancias de JDK 
		 * proporcionan clases centrales, beans) y 
		 * construcciones JSON coincidentes.
		 */
		ObjectMapper mapper = new ObjectMapper();
		
		/**
		 * Si detecta una propiedad que es un 
		 * String JSON la mapea a objeto Map.
		 */
		try {
			
			/**
			 * Si detecta una propiedad que es un String 
			 * con estructura JSON, lo combierte a otro 
			 * objeto de tipo Map.
			 */
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

	/**
	 * Crea un arrego JSON en una variable de tipo String.
	 * 
	 * @param String
	 * @return String
	 */
	public static String toArrayJson(String jsonSinTratar) {

		if (StringUtils.validateNull(jsonSinTratar)) {
			return StringUtils.NULL_STRING;
		}

		String resultado = jsonSinTratar.replaceAll("\\}\\{", "\\},\\{");
		resultado = "{[".concat(resultado).concat("]}");
		return resultado.replace("{[", "{\"resultado\":[");
	}
}
