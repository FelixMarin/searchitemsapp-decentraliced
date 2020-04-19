package com.searchitemsapp.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @version 1.0.0
 * @author Architect: Getronics
 * @author <br>
 *         Designer: Getronics
 * @author <br>
 *         Developer: Getronics
 *
 *         Clase que proporciona funciones de utilidad sobre operaciones sobre
 *         objetos
 * @modelguid {80259D62-9858-4D0F-B35B-4F7196DDA69B}
 */
@SuppressWarnings("rawtypes")
public final class ClaseUtils implements IFUtils {

	private static final String ACCESO_ILEGAL_AL_ESTABLECER_VALOR_CLASE = "Acceso ilegal al establecer valor. Clase: ";
	private static final String STR_ATRIBUTO = ". Atributo: ";

	public static final Object NULL_OBJECT = null;
	public static final int DEFAULT_INT_VALUE = 10;
	public static final double DEFAULT_PRICE = 1000.00;
	public static final int TWO_INT = 2;
	public static final int ONE_INT = 1;
	public static final int ZERO_INT = 0;
	public static final int ONE_NEGATIVE_INT = -1;
	public static final int TWO_NEGATIVE_INT = -2;
	public static final int STATUS_OK = 200;
	public static final int TIME_OUT = 100000;
	public static final int THREADS_NUM = 57;

	private ClaseUtils() {
		super();
	}

	/**
	 * Construye un objeto de la clase indicada
	 * 
	 * @param pStrNombreClase Nombre de la clase
	 * @return Objeto creado
	 * @modelguid {6469F7C1-7BB2-4CBE-973A-6DC92AC0A47E}
	 */
	public static Object construirObjeto(String pStrNombreClase) throws InstantiationError {
		return construirObjeto(obtenerClase(pStrNombreClase));
	}

	/**
	 * Construye un objeto de la clase indicada
	 * 
	 * @param clase Clase del objeto
	 * @return Objeto creado
	 */
	public static Object construirObjeto(Class<?> clase) throws InstantiationError {
		final Object oObjeto;

		try {
			oObjeto = clase.newInstance();
		} catch (InstantiationException ie) {
			final String msg = "No se puede instanciar la clase. Clase: " + clase.getName();
			LogsUtils.escribeLogError(msg,ClaseUtils.class,ie);
			throw new InternalError(msg, ie);
		} catch (IllegalAccessException iae) {
			final String msg = ACCESO_ILEGAL_AL_ESTABLECER_VALOR_CLASE + clase.getName();
			LogsUtils.escribeLogError(msg,ClaseUtils.class,iae);
			throw new InternalError(msg, iae);
		}
		return oObjeto;
	}

	/**
	 * Construye un objeto de la clase indicado
	 * 
	 * @param pStrNombreClase Nombre de la clase
	 * @return Objeto creado
	 * @modelguid {6469F7C1-7BB2-4CBE-973A-6DC92AC0A47E}
	 */
	public static Object construirObjeto(String pStrNombreClase, List<?> pAlValoresInicio) throws InstantiationError {
		return construirObjeto(obtenerClase(pStrNombreClase), pAlValoresInicio);
	}

	/**
	 * Construye un objeto de la clase indicado
	 * 
	 * @param pStrNombreClase Nombre de la clase
	 * @return Objeto creado
	 */
	public static Object construirObjeto(Class<?> clase, List<?> pAlValoresInicio) throws InstantiationError {
		Object oObjeto = ClaseUtils.NULL_OBJECT;
		final Constructor<?> cConstructor;
		if (null == pAlValoresInicio) {
			return oObjeto;
		}
		final Class<?>[] aClasesContructor = obtenerClasesLista(pAlValoresInicio);

		try {
			cConstructor = obtenerConstructor(clase, aClasesContructor);
			oObjeto = cConstructor.newInstance(pAlValoresInicio.toArray());

		} catch (InstantiationException ie) {
			final String msg = "No se puede instanciar la clase. Clase: " + clase.getName();
			LogsUtils.escribeLogError(msg,ClaseUtils.class,ie);
			throw new InternalError(msg, ie);

		} catch (IllegalAccessException iae) {
			final String msg = ACCESO_ILEGAL_AL_ESTABLECER_VALOR_CLASE + clase.getName();
			LogsUtils.escribeLogError(msg,ClaseUtils.class,iae);
			throw new InternalError(msg, iae);

		} catch (InstantiationError iae) {
			final String msg = "Parametros incorrectos en la llamada al contructor. Clase: " + clase.getName();
			LogsUtils.escribeLogError(msg,ClaseUtils.class,iae);
			throw new InternalError(msg, iae);

		} catch (InvocationTargetException ite) {
			final String msg = "Error en la llamada al contructor. Clase: " + clase.getName();
			LogsUtils.escribeLogError(msg,ClaseUtils.class,ite);
			throw new InternalError(msg, ite);
		}
		return oObjeto;
	}

	/**
	 * Establece el valor de un atributo en un objeto
	 * 
	 * @param pObjeto           Objeto
	 * @param strNombreAtributo Nombre del atributo
	 * @param poValor           Valor
	 * @modelguid {6A280520-0395-4D65-B68B-E52C56C73512}
	 */
	public static void setValorAtributo(Object pObjeto, String strNombreAtributo, Object poValor)
			throws InstantiationError {

		Class<?> cClase;

		try {
		if (poValor != NULL_OBJECT) {
			if (strNombreAtributo.indexOf('.') != -1) {
				String[] parts = strNombreAtributo.split("\\.");
				Object obj = pObjeto;
				for (int c = 0; c < parts.length - 1; c++) {
					// CR-3306, Nueva Paqueteria II, jgchacon 05/04/2010
					// Evitamos recursion sobre objetos nulos
					if (obj != NULL_OBJECT) {
						obj = getValorAtributo(obj, parts[c]);
					}
					// Fin CR-3306
				}
				strNombreAtributo = parts[parts.length - 1];
				pObjeto = obj;
			}

			if (pObjeto != NULL_OBJECT) {

				cClase = pObjeto.getClass();
				
					Method setMethod = (Method) ClaseUtils.NULL_OBJECT;
					auxSetValorAtributo(setMethod, pObjeto, strNombreAtributo, poValor);
					auxSetValorAtributo2(cClase, strNombreAtributo, setMethod, pObjeto, poValor);
			}
		}
		} catch (InstantiationError | IllegalAccessException iae) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[2].toString(),ClaseUtils.class,iae);
			throw new InternalError(Thread.currentThread().getStackTrace()[2].toString(),iae);

		}
	}

	public static boolean isNullObject(Object object) {
		return Objects.isNull(object);
	}
	
	private static void auxSetValorAtributo2(Class<?> cClase, String strNombreAtributo, Method setMethod, Object pObjeto, Object poValor) throws InstantiationError, IllegalAccessException {
		if (setMethod != NULL_OBJECT) {
			final Object[] argumentos = new Object[] { poValor };
			auxValorAtributo(setMethod, pObjeto, argumentos);
		} else {
			// Para clases declaradas con atributos publicos sin Getters / Setters
			// Recupera de la clase cClase el atributo llamado strNombreAtributo
			final Field fldAtributo = getAtributoClase(cClase, strNombreAtributo);
			// Escribe en el atributo fldAtributo del objeto pObjeto el valor contenido en
			// poValor
			fldAtributo.set(pObjeto, poValor);
		}
	}

	private static void auxValorAtributo(Method setMethod, Object pObjeto, Object[] argumentos)
			throws IllegalAccessException, InstantiationError {
		try {
			setMethod.invoke(pObjeto, argumentos);
		} catch (InvocationTargetException ite) {
			LogsUtils.escribeLogError("No se encontro el Metodo set para establecer valor",ClaseUtils.class,ite);
		}
	}

	private static Method auxSetValorAtributo(Method setMethod, Object pObjeto, String strNombreAtributo,
			Object poValor) {
		Class<?>[] cType = new Class<?>[] { poValor.getClass() };
		final Class<?> cClase = pObjeto.getClass();
		Method setMethodAux = setMethod;
		try {
			setMethodAux = cClase.getMethod(
					"set" + strNombreAtributo.substring(0, 1).toUpperCase() + strNombreAtributo.substring(1), cType);
		} catch (NoSuchMethodException nsme) {
			// En caso de no encontar el Metodo set con atributos de tipo no primitivos, se
			// recupera la excepcion y se crea una nueva clase cType de tipo primitivo o
			// java.util.Date para establecer el valor
			final Class<?> clase = poValor.getClass();
			auxValorAtributo(clase);
			try { // NO PMD Es necesario para tratar la excepcion capturada
				setMethodAux = cClase.getMethod(
						"set" + strNombreAtributo.substring(0, 1).toUpperCase() + strNombreAtributo.substring(1),
						cType); // NO PMD Es necesario al tener los atributos de los VOs private
			} catch (NoSuchMethodException nse) {
				LogsUtils.escribeLogError("No se encontra el Metodo set para establecer valor. Clase: " + cClase.getName()
					+ STR_ATRIBUTO + strNombreAtributo,ClaseUtils.class,nse);
				setMethodAux = (Method) NULL_OBJECT;
			}
		}
		return setMethodAux;
	}

	private static Class<?>[] auxValorAtributo(Object pObjeto) {

		final Class<?> claseAux = pObjeto.getClass();
		Class<?>[] cTypeAux;

		if (claseAux.equals(java.lang.Integer.class)) {
			cTypeAux = new Class<?>[] { int.class };
		} else if (claseAux.equals(java.lang.Boolean.class)) {
			cTypeAux = new Class<?>[] { boolean.class };
		} else if (claseAux.equals(java.lang.Float.class)) {
			cTypeAux = new Class<?>[] { float.class };
		} else if (claseAux.equals(java.lang.Double.class)) {
			cTypeAux = new Class<?>[] { double.class };
		} else if (claseAux.equals(java.lang.Long.class)) {
			cTypeAux = new Class<?>[] { long.class };
		} else if (claseAux.equals(java.lang.Byte.class)) {
			cTypeAux = new Class<?>[] { byte.class };
		} else if (claseAux.equals(java.lang.Short.class)) {
			cTypeAux = new Class<?>[] { short.class };
		} else if (claseAux.equals(java.lang.Character.class)) {
			cTypeAux = new Class<?>[] { char.class };
		} else if (claseAux.equals(java.sql.Date.class)) {
			cTypeAux = new Class<?>[] { java.util.Date.class };
		} else {
			cTypeAux = (Class<?>[]) NULL_OBJECT;
		}
		return cTypeAux;
	}

	/**
	 * Obtiene el valor de un atributo en un objeto
	 * 
	 * @param pObjeto           Objeto
	 * @param strNombreAtributo Nombre del atributo
	 * @return Valor
	 * @modelguid {B387CB88-5524-46CF-A08B-796D9BB3673B}
	 */
	public static Object getValorAtributo(Object pObjeto, String strNombreAtributo) throws InstantiationError {
		Object oResult = NULL_OBJECT;
		if (strNombreAtributo.indexOf('.') != -1) {
			String[] parts = strNombreAtributo.split("\\.");
			oResult = pObjeto;
			for (int c = 0; c < parts.length; c++) {
				// CR-3306, Nueva Paqueteria II, jgchacon 05/04/2010
				// Evitamos recursion sobre objetos nulos
				if (oResult != NULL_OBJECT) {
					oResult = getValorAtributo(oResult, parts[c]);
				}
				// Fin CR-3306
			}
			return oResult;
		}
		final Class<?> cClase = pObjeto.getClass();
		try {			
			Method getMethod = auxGetAtributoClase(cClase, strNombreAtributo);
			if (getMethod != NULL_OBJECT) {
				final Object[] argumentos = new Object[] {};
				oResult = getMethod.invoke(pObjeto, argumentos);
			}

			if (getMethod == NULL_OBJECT) {
				// Para clases declaradas con atributos publicos sin Getters / Setters
				final Field fldAtributo = getAtributoClase(cClase, strNombreAtributo);
				// Retorna el valor del campo representado por fldAtributo en el objeto pObjeto
				oResult = fldAtributo.get(pObjeto);
			}

		} catch (InstantiationError | IllegalAccessException | InvocationTargetException iae) {
			LogsUtils.escribeLogError(new StringBuffer().append("Argumento ilegal al obtener valor. Clase: ").append(cClase.getName())
					.append(STR_ATRIBUTO).append(strNombreAtributo).toString(),ClaseUtils.class,iae);
			throw new InternalError(new StringBuffer().append("Argumento ilegal al obtener valor. Clase: ")
					.append(cClase.getName()).append(STR_ATRIBUTO).append(strNombreAtributo).toString(), iae);

		}

		return oResult;
	}
	
	private static Method auxGetAtributoClase(final Class<?> cClase, String strNombreAtributo) {
		Method getMethod = (Method) NULL_OBJECT;
		try {
			getMethod = cClase.getMethod(
					"get" + strNombreAtributo.substring(0, 1).toUpperCase() + strNombreAtributo.substring(1),
					(Class<?>) NULL_OBJECT);
		} catch (InstantiationError iae) {
			getMethod = (Method) NULL_OBJECT;
		} catch (NoSuchMethodException nsme) {
			// Si es boolean el atributo se tiene que buscar con is+NombreAtributo
			try { // NOPMD Es necesario
				getMethod = cClase.getMethod(
						"is" + strNombreAtributo.substring(0, 1).toUpperCase() + strNombreAtributo.substring(1),
						(Class<?>) NULL_OBJECT);
			} catch (NoSuchMethodException nse) {
				getMethod = (Method) NULL_OBJECT;
			}
		}
		
		return getMethod;
	}

	/**
	 * Obtiene el atributo de una clase
	 * 
	 * @param pClase            Clase
	 * @param strNombreAtributo Nombre del atributo
	 * @return Atributo
	 * @modelguid {4060528A-D491-4DDE-8B29-9A50E59403C7}
	 */

	private static Field getAtributoClase(Class<?> pClase, String strNombreAtributo) throws InstantiationError {

		Field fldAtributo = (Field) NULL_OBJECT;
		try {
			fldAtributo = pClase.getField(strNombreAtributo); // NO PMD Es necesario al tener los atributos de los VOs
																// private

		} catch (SecurityException se) {
			LogsUtils.escribeLogError(new StringBuffer().append("Atributo no accesible. Clase: ").append(pClase.getName())
					.append(STR_ATRIBUTO).append(strNombreAtributo).toString(),ClaseUtils.class,se);
			throw new InternalError(new StringBuffer().append("Atributo no accesible. Clase: ").append(pClase.getName())
					.append(STR_ATRIBUTO).append(strNombreAtributo).toString(), se);

		} catch (NoSuchFieldException nsfe) {
			LogsUtils.escribeLogError(new StringBuffer().append("Atributo no existe. Clase: ").append(pClase.getName())
					.append(STR_ATRIBUTO).append(strNombreAtributo).toString(),ClaseUtils.class,nsfe);
			throw new InternalError(new StringBuffer().append("Atributo no existe. Clase: ").append(pClase.getName())
					.append(STR_ATRIBUTO).append(strNombreAtributo).toString(), nsfe);
		}
		return fldAtributo;
	}

	/**
	 * Metodo que obtiene el array de clases de los objetos que conforman la
	 * coleccion
	 * 
	 * @param colObjetos coleccion de objetos
	 * @return Array de clases
	 */
	private static Class<?>[] obtenerClasesLista(List<?> colObjetos) {
		final Class<?>[] acResultado;
		if (colObjetos != NULL_OBJECT) {
			final int iNumObjetos = colObjetos.size();

			acResultado = new Class<?>[iNumObjetos];
			for (int i = 0; i < iNumObjetos; i++) {
				final Object oObjeto = colObjetos.get(i);
				acResultado[i] = oObjeto.getClass();
			}
		} else {
			acResultado = (Class<?>[]) NULL_OBJECT;
		}
		return acResultado;
	}

	/**
	 * Metodo que obtiene el constructor de una clase
	 * 
	 * @param strClase Nombre de la clase
	 * @param paClases Clases de los objetos pasados al constructor
	 * @return Constructor<?>
	 * @throws InstantiationError
	 */
	private static Constructor<?> obtenerConstructor(Class<?> strClase, Class<?>[] paClases) throws InstantiationError {
		final Constructor<?> cConstructor;
		try {
			cConstructor = strClase.getConstructor(paClases);

		} catch (SecurityException se) {
			LogsUtils.escribeLogError("No se puede acceder a la clase. Clase: " + strClase,ClaseUtils.class,se);
			throw new InternalError("No se puede acceder a la clase. Clase: " + strClase, se);

		} catch (NoSuchMethodException nsme) {
			LogsUtils.escribeLogError("La clase no contiene el constructor buscado. Clase: " + strClase,ClaseUtils.class,nsme);
			throw new InternalError("La clase no contiene el constructor buscado. Clase: " + strClase, nsme);
		}
		return cConstructor;
	}

	/**
	 * Obtiene una clase a partir del nombre de la misma
	 * 
	 * @param nombreClase
	 * @return
	 * @throws InstantiationError
	 */
	private static Class<?> obtenerClase(String nombreClase) throws InstantiationError {
		final Class<?> clase;
		try {
			clase = Class.forName(nombreClase);

		} catch (ClassNotFoundException cnfe) {
			final String msg = "Clase no encontrada. Clase: " + nombreClase;
			LogsUtils.escribeLogError(msg,ClaseUtils.class,cnfe);
			throw new InternalError(msg, cnfe);
		}
		return clase;
	}

	public static void testProxy(String url, Class clazz) {
		
		try {
			List<Proxy> l = ProxySelector.getDefault().select(new URI(url));
	
			for (Iterator<Proxy> iter = l.iterator(); iter.hasNext();) {
	
				Proxy proxy = iter.next();
				LogsUtils.escribeLogDebug("proxy hostname : " + proxy.type(), clazz);
				InetSocketAddress addr = (InetSocketAddress) proxy.address();
	
				if (ClaseUtils.isNullObject(addr)) {
					LogsUtils.escribeLogDebug("No Proxy", clazz);
				} else {
					LogsUtils.escribeLogDebug("proxy hostname : " + addr.getHostName(), clazz);
					LogsUtils.escribeLogDebug("proxy port : " + addr.getPort(), clazz);
				}
			}
		}catch(URISyntaxException e) {
			LogsUtils.escribeLogError(Thread.currentThread().getStackTrace()[1].toString(), clazz, e);
		}
	}
	
}
