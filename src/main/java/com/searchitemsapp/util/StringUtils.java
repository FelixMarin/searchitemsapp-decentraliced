package com.searchitemsapp.util;

import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.searchitemsapp.commons.CommonsPorperties;

/**
 * @version 1.0.0
 * @author Architect: Getronics
 * @author <br>
 *         Designer: Getronics
 * @author <br>
 *         Developer: Getronics
 *
 *         Clase que proporciona funciones de utilidad sobre elementos de tipo
 *         String
 * @modelguid {2969363B-099C-43ED-BFF2-C955BBB3B4D3}
 */
public class StringUtils  implements IFUtils {

	public static final String OPERACION_NO_SOPORTADA = "Operación no soportada";
	public static final String GET = "GET";
	public static final String POST = "POST";
	public static final String BR_TAG_STRING = "<br>";
	public static final String SALTO_DE_LINEA= "\n";
	public static final String COMMA_STRING = ",";
	public static final String DOT_STRING = ".";
	public static final String ALMOADILLA = "#";
	public static final String NULL_STRING = null;
	public static final String EMPTY_STRING = "";
	public static final String SPACE_STRING = " ";
	public static final String NULL_STRING_FORMAT = "null";
	public static final String PLUS_STRING = "+";
	public static final String DEFAULT_STR_PRICE = "1000.00";
	public static final String WILDCARD = "{1}";
	public static final String REGEX_WILDCARD = "([{1}])+";
	public static final String AGENT_MOZILLA = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
	public static final String AGENT_ALL = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36";
	public static final String REFFERER_GOOGLE = "http://www.google.com";
	public static final String HTTP_STATUS_CODE = "HTTP Status Code: ";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String APPLICATION_XHTML_XML = "application/xhtml+xml";
	public static final String CONNECTION = "Connection";
	public static final String X_REQUESTED_WITH = "X-Requested-With";
	public static final String XML_HTTP_REQUEST = "XMLHttpRequest";
	public static final String BASIC = "Basic ";
	public static final String AUTHORIZATION = "Authorization";
	public static final String KEEP_ALIVE = "keep-alive";
	public static final String SEPARADOR_URL = "%20";
	public static final String ERROR_STRING = "Error";
	public static final String PIPE = "|";
	public static final String BARRA = "/";
	public static final String DOS_PUNTOS = ":";
	public static final String DOBLE_BARRA = "//";
	public static final String PROTOCOL_ACCESSOR ="://";
	public static final String HTTPS = "https:";
	public static final String EURO = "€";
	public static final String LITERAL_EURO = "EUR";
	public static final String ACCEPT_VALUE = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
	public static final String ACEPT_VALUE_JSON = "application/json";
	public static final String CABECERA_XML = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
	public static final String ACCEPT = "Accept";
	public static final String GZIP_DEFLATE_SDCH = "gzip, deflate, sdch";
	public static final String ACCEPT_ENCODING = "Accept-Encoding";	
	public static final String ES_ES = "es-ES,es;q=0.8";
	public static final String ACCEPT_LANGUAGE = "Accept-Language";	
	public static final String ACTION_LOGIN = "ActionLogin";
	public static final String LOGIN = "Login";
	public static final String  NO_HAY_RESULTADOS= "No hay resultados";
	public static final String LEFT_PARENTHESIS_0 = " (";
	public static final String BARRA_KILO_GRAM = "/KG.";
	public static final String KILO_GRAM = "kg";
	public static final String DECIMALES = ".00";
	public static final String REGEX_NUMERO_DECIMAL = "\\d*[,][0-9]*";
	public static final String REGEX_NUMERO_ENTERO = "\\d*";
	public static final char CHAR_ENIE_COD = '\u00f1';
	public static final String STRING_ENIE_MIN = "ñ";
	public static final String STRING_ENIE_MAY = "Ñ";
	public static final String UNICODE_ENIE = "u00f1";
	public static final String UNICODE_FEMALE_A = "u00aa";
	public static final String UNICODE_EURO = "u20ac";
	public static final String DICCIONARIO = "DICCIONARIO";
	public static final String UTF_8 = "UTF-8";
	public static final String ENIE_EROSKI = "$00f1";
	public static final String ENIE_URL = "%F1";
	public static final String REGEX_PERCENT_DOLAR = "(\\%|\\$00)";
	public static final String REGEX_DECIMAL = "[,][0-9]{2}";
	public static final String REGEX_INTEGER = "\\d+";
	public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	public static final String ZERO_STRING = "0";
	
	public static final String EROSKI = CommonsPorperties.getValue("flow.value.nom.empresa.eroski");
	public static final String HIPERCOR = CommonsPorperties.getValue("flow.value.nom.empresa.hipercor");
	public static final String ELCORTEINGLES = CommonsPorperties.getValue("flow.value.nom.empresa.eci");
	public static final String DIA = CommonsPorperties.getValue("flow.value.nom.empresa.dia");
	public static final String SIMPLY = CommonsPorperties.getValue("flow.value.nom.empresa.simply");
	public static final String CONDIS = CommonsPorperties.getValue("flow.value.nom.empresa.condis");
	public static final String ULABOX = CommonsPorperties.getValue("flow.value.nom.empresa.ulabox");
	public static final String CARREFOUR = CommonsPorperties.getValue("flow.value.nom.empresa.carrefour");
	public static final String ALCAMPO = CommonsPorperties.getValue("flow.value.nom.empresa.alcampo");
	public static final String CAPRABO = CommonsPorperties.getValue("flow.value.nom.empresa.caprabo");
	public static final String MERCADONA = CommonsPorperties.getValue("flow.value.nom.empresa.mercadona");
	public static final String CONSUM = CommonsPorperties.getValue("flow.value.nom.empresa.consum");
	
	public static final String ID_EROSKI = CommonsPorperties.getValue("flow.value.did.empresa.eroski");
	public static final String ID_LIDL = CommonsPorperties.getValue("flow.value.did.empresa.lidl");
	public static final String ID_HIPERCOR = CommonsPorperties.getValue("flow.value.did.empresa.hipercor");
	public static final String ID_ELCORTEINGLES = CommonsPorperties.getValue("flow.value.did.empresa.eci");
	public static final String ID_DIA = CommonsPorperties.getValue("flow.value.did.empresa.dia");
	public static final String ID_SIMPLY = CommonsPorperties.getValue("flow.value.did.empresa.simply");
	public static final String ID_CONDIS = CommonsPorperties.getValue("flow.value.did.empresa.condis");
	public static final String ID_ULABOX = CommonsPorperties.getValue("flow.value.did.empresa.ulabox");
	public static final String ID_CARREFOUR = CommonsPorperties.getValue("flow.value.did.empresa.carrefour");
	public static final String ID_ALCAMPO = CommonsPorperties.getValue("flow.value.did.empresa.alcampo");
	public static final String ID_CAPRABO = CommonsPorperties.getValue("flow.value.did.empresa.caprabo");
	public static final String ID_MERCADONA = CommonsPorperties.getValue("flow.value.did.empresa.mercadona");
	public static final String ID_CONSUM = CommonsPorperties.getValue("flow.value.did.empresa.consum");	
	public static final String ID_DICCIONARIO = CommonsPorperties.getValue("flow.value.empresa.did.diccionario");
		
	private static final String[] LISTA_DIGITOS = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", 
			"D", "X", "B", "N", "J", "Z", "S", "Q",	"V", "H", "L", "C", "K", "E" };
	private static final String RIGTH_PARENTHESIS = "\\)";
	private static final String LEFT_PARENTHESIS = "\\(";
	private static final String GUION = "-";
	private static final String PATRON_FECHA_MAESTRO = "[\\d]{4}-[\\d]{2}-[\\d]{2}";
	private static final String VIRGULILLA = "~";	
	private static final int AJUSTE_DERECHA = 0;
	private static final String REEMPLAZABLE_TILDES = "[\\p{InCombiningDiacriticalMarks}]";
	private static final String[] NULL_STRING_ARRAY = null;
	
	public static final String FEMALE_A = "ª";
	public static final String U_ACUTE_MAY = "Ú";
	public static final String O_ACUTE_MAY = "Ó";
	public static final String I_ACUTE_MAY = "Í";
	public static final String E_ACUTE_MAY = "É";
	public static final String A_ACUTE_MAY = "Á";
	public static final String U_ACUTE_MIN = "ú";
	public static final String O_ACUTE_MIN = "ó";
	public static final String I_ACUTE_MIN = "í";
	public static final String E_ACUTE_MIN = "é";
	public static final String A_ACUTE_MIN = "á";
	public static final String MAYOR_QUE = ">";
	public static final String MENOR_QUE = "<";
	public static final String LEFT_COMILLA_LLAVE = "\"{";
	public static final String RIGHT_COMILLA_LLAVE = "\"}";
	public static final String RIGHT_CORCHETE = "]";
	public static final String LEFT_CORCHETE = "[";
	public static final String CONTRA_BARRA_SCAPE = "\\";
	public static final String SCAPE_T = "\\t";
	public static final String SCAPE_N = "\\n";
	public static final String GT = "&gt;";
	public static final String LT = "&lt;";
	public static final String ACUTE = "\\&quot;";	
	
	private static final String[][] SANITIZE_STRING = { { "&", "y" }, { "<", StringUtils.SPACE_STRING },
			{ ">", StringUtils.SPACE_STRING }, { "\"", StringUtils.SPACE_STRING }, { "'", StringUtils.SPACE_STRING },
			{ ALMOADILLA, StringUtils.SPACE_STRING }, { "%", StringUtils.SPACE_STRING }, { ";", StringUtils.SPACE_STRING },
			{ "\\+", StringUtils.SPACE_STRING }, { GUION, StringUtils.SPACE_STRING } };
	
	private static final Pattern[] patterns;
	
	static {
		final int length = SANITIZE_STRING.length;
		patterns = new Pattern[length];
		for (int c = 0; c < length; c++) {
			patterns[c] = Pattern.compile(SANITIZE_STRING[c][0]);
		}
	}
	
	private static final int AJUSTE_IZQUIERDA = 1;
	
	private static final String[] ARRAY_TILDES_EROSKI = {"$00e1","$00e9","$00ed","$00f3","$00fa"};
	private static final String[] LATIN_UNICODE_MIN = {"u00e1","u00e9","u00ed","u00f3","u00fa"};
	private static final String[] LATIN_UNICODE_MAY = {"u00c1","u00c9","u00cd","u00d3","u00da"};
	private static final String[] ARRAY_TILDES_NORMALES_MIN = {"á","é","í","ó","ú"};
	private static final String[] ARRAY_VOCALES_MIN = {"a","e","i","o","u"};
	
	/**
	 * Logger de la aplicacion
	 *
	 * @modelguid {3A3993BA-7938-485F-9955-A160F81781D4}
	 */

	private StringUtils() {
		super();
	}

	/**
	 * Obtiene una lista de cadenas, mediante el troceado de una cadena
	 * proporcionada en funcion de un separador
	 *
	 * @param pstrCadena    Cadena
	 * @param pstrSeparador separador
	 * @return lista de cadenas
	 * @modelguid {DA609843-66B1-48DE-96EB-E2523528CF5A}
	 */
	public static String[] getLista(String strCadena, String strSeparador) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		return strCadena.split(strSeparador);
	}

	/**
	 * Realiza el ajuste de una cadena por la derecha, para proporcionarle una
	 * longitud determinada mediante la adicion de un tipo de caracter dado
	 *
	 * @param pstrCadena   cadena
	 * @param piLongitud   Longitud de la cadena
	 * @param pchrCaracter caracter
	 * @return cadena ajustada
	 * @modelguid {96426349-1D0C-49A4-AB02-A1AFC0B874CD}
	 */
	public static String ajustarDerecha(String strCadena, int iLongitud, char chrCaracter) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		return anadirCaracteres(strCadena, chrCaracter, iLongitud, AJUSTE_DERECHA);
	}

	/**
	 * Convierte una fecha en formato yyyy-MM-dd con o sin horas al formato
	 * dd/mm/yyyy sin horas
	 * 
	 * @param fuente
	 * @return
	 */
	public static String formatoFechaMaestro(String fuente) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		Matcher matcher = matcher(PATRON_FECHA_MAESTRO, fuente);
		StringBuilder fechaConvertida = StringUtils.getNewStringBuilder();
		if (matcher.find()) {
			String fecha = matcher.group();
			String[] fechaConvertida1 = fecha.split(GUION);
			fechaConvertida.append(fechaConvertida1[2])
				.append("/")
				.append(fechaConvertida1[1]).append("/")
				.append(fechaConvertida1[0]);
		}
		return fechaConvertida.toString();
	}

	/**
	 * Realiza el ajuste de una cadena por la izquierda, para proporcionarle una
	 * longitud determinada mediante la adicion de un tipo de caracter dado
	 *
	 * @param pstrCadena   cadena
	 * @param piLongitud   Longitud de la cadena
	 * @param pchrCaracter caracter
	 * @return cadena ajustada
	 * @modelguid {6D09AEED-F667-4C5F-A92D-67B73F0585D3}
	 */
	public static String ajustarIzquierda(String strCadena, int iLongitud, char chrCaracter) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		return anadirCaracteres(strCadena, chrCaracter, iLongitud, AJUSTE_IZQUIERDA);
	}

	/**
	 * Realiza la adicion de caracteres a una cadena para proporcionarle una
	 * longitud determinada
	 *
	 * @param pstrCadena   cadena
	 * @param piLongitud   Longitud de la cadena
	 * @param pchrCaracter caracter
	 * @param piTipo       Tipo de adicion (Izquierda o Derecha)
	 * @return cadena ajustada
	 * @modelguid {360E79DB-48FE-4795-9E71-37FCA8F0B22D}
	 */
	private static String anadirCaracteres(String strCadena, char chrCaracter, int iLongitud, int iTipo) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		final StringBuilder sbResultado = StringUtils.getNewStringBuilder();
		if (iTipo == AJUSTE_DERECHA) {
			sbResultado.append(strCadena);
		}
		final int dif = iLongitud - strCadena.length();
		for (int i = 0; i < dif; i++) {
			sbResultado.append(chrCaracter);
		}

		if (iTipo == AJUSTE_IZQUIERDA) {
			sbResultado.append(strCadena);
		}

		return sbResultado.toString();
	}

	/**
	 * Convierte una cadena a tipo Double
	 *
	 * @param pStrCadena
	 * @return
	 */
	public static double desformatearDouble(String pStrCadena) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		double dResultado = 0;
		if (pStrCadena != NULL_STRING && !StringUtils.EMPTY_STRING.equals(pStrCadena)) {
			try {
				dResultado = Double.parseDouble(pStrCadena);
			} catch (NumberFormatException nfe) {
				LogsUtils.escribeLogWarn("Error al desformatear el valor Double. Valor: " + pStrCadena,StringUtils.class,nfe);
			}
		}
		return dResultado;
	}

	/**
	 * Convierte una cadena a tipo int
	 *
	 * @param pStrCadena
	 * @return
	 */
	public static int desformatearEntero(String pStrCadena) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		int iResultado = 0;
		if (!StringUtils.isEmpty(pStrCadena)) {
			try {
				iResultado = Integer.parseInt(pStrCadena);
			} catch (NumberFormatException nfe) {
				LogsUtils.escribeLogWarn("Error al desformatear el valor Integer. Valor: " + pStrCadena,StringUtils.class,nfe);
			}
		}
		return iResultado;
	}

	/**
	 * Desformatear una cadea y la convierte en long
	 * 
	 * @param pstrCadena
	 * @return
	 */
	public static long desformatearLargo(String pstrCadena) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		long iResultado = 0;
		if (pstrCadena != NULL_STRING && !StringUtils.EMPTY_STRING.equals(pstrCadena)) {
			try {
				iResultado = Long.parseLong(pstrCadena);
			} catch (NumberFormatException nfe) {
				LogsUtils.escribeLogWarn("Error al desformatear el valor Long. Valor: " + pstrCadena,StringUtils.class,nfe);
			}
		}
		return iResultado;
	}

	/**
	 * Metodo que devuelve una cadena con el valor del importe con una precision de
	 * 2 digitos fraccionarios.
	 *
	 * @param pdCadena El importe. Tipo double.
	 *
	 * @return la cadena con el valor del importe.
	 *
	 * @author maruizi
	 *
	 */
	public static String formatearMoneda(double pdCadena) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		final NumberFormat nf = getNumberFormatMoneda();
		return nf.format(pdCadena);
	}

	/**
	 * Metodo que devuelve el valor double que corresponde con el importe encontrado
	 * en la cadena.
	 *
	 * @param cadena La cadena con el importe a transformar.
	 *
	 * @return el valor del importe al transformar la cadena. Devuelve 0 si la
	 *         cadena es incorrecta.
	 *
	 * @author maruizi
	 *
	 */
	public static double desformatearMoneda(String cadena) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		double valor = 0;

		final NumberFormat nf = getNumberFormatMoneda();

		try {
			valor = (nf.parse(cadena)).doubleValue();
		} catch (ParseException pe) {
			LogsUtils.escribeLogWarn(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class,pe);
		}

		return valor;
	}

	/**
	 * Metodo que devuelve una cadena con el valor del importe con una precision de
	 * 2 digitos fraccionarios. En ingles.
	 *
	 * @param pDCadena El importe. Tipo double.
	 *
	 * @return la cadena con el valor del importe.
	 *
	 * @author maruizi
	 *
	 */
	public static String formatearMonedaImp(double pDCadena) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		final NumberFormat nf = getNumberFormatMonedaImp();
		return nf.format(pDCadena);
	}

	/**
	 * Metodo que devuelve el valor double que corresponde con el importe encontrado
	 * en la cadena. En ingles.
	 *
	 * @param cadena La cadena con el importe a transformar.
	 *
	 * @return el valor del importe al transformar la cadena. Devuelve 0 si la
	 *         cadena es incorrecta.
	 *
	 * @author maruizi
	 *
	 */
	public static double desformatearMonedaImp(String cadena) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		double valor = 0;

		final NumberFormat nf = getNumberFormatMonedaImp();

		try {
			valor = (nf.parse(cadena)).doubleValue();
		} catch (ParseException pe) {
			LogsUtils.escribeLogWarn(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class,pe);
		}

		return valor;
	}

	/**
	 * Metodo que devuelve una cadena con el valor del importe con una precision de
	 * 2 digitos fraccionarios.
	 *
	 * @param pfCadena El importe. Tipo float.
	 *
	 * @return la cadena con el valor del importe.
	 *
	 * @author maruizi
	 *
	 */
	public static String formatearMoneda(float pfCadena) {

		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		final NumberFormat nf = getNumberFormatMoneda();
		return nf.format(pfCadena);
	}

	/**
	 * Metodo que devuelve el valor double que corresponde con el importe encontrado
	 * en la cadena.
	 *
	 * @param cadena La cadena con el importe a transformar.
	 *
	 * @return el valor del importe al transformar la cadena. Devuelve 0 si la
	 *         cadena es incorrecta.
	 *
	 * @author maruizi
	 *
	 */
	public static float desformatearMonedaFloat(String cadena) {
		float valor = 0;

		final NumberFormat nf = getNumberFormatMoneda();

		try {
			valor = (nf.parse(cadena)).floatValue();
		} catch (ParseException pe) {
			LogsUtils.escribeLogWarn("Error al tranformar la cadena \"" + cadena + "\" en un importe de tipo float.",StringUtils.class,pe);
		}

		return valor;
	}

	/**
	 * Metodo que devuelve una cadena con el valor del importe con una precision de
	 * 2 digitos fraccionarios. En ingles.
	 *
	 * @param pfCadena El importe. Tipo float.
	 *
	 * @return la cadena con el valor del importe.
	 *
	 * @author maruizi
	 *
	 */
	public static String formatearMonedaImp(float pfCadena) {
		final NumberFormat nf = getNumberFormatMonedaImp();
		return nf.format(pfCadena);
	}

	/**
	 * Metodo que devuelve el valor double que corresponde con el importe encontrado
	 * en la cadena. En ingles.
	 *
	 * @param cadena La cadena con el importe a transformar.
	 *
	 * @return el valor del importe al transformar la cadena. Devuelve 0 si la
	 *         cadena es incorrecta.
	 *
	 * @author maruizi
	 *
	 */
	public static float desformatearMonedaFloatImp(String cadena) {
		float valor = 0;

		final NumberFormat nf = getNumberFormatMonedaImp();

		try {
			valor = (nf.parse(cadena)).floatValue();
		} catch (ParseException pe) {
			LogsUtils.escribeLogWarn("Error al tranformar la cadena \"" + cadena + "\" en un importe de tipo float.",StringUtils.class,pe);
		}

		return valor;
	}

	/**
	 * Crea un float a partir de una cadena que tambien en float
	 * 
	 * @param pfCadena
	 * @return
	 */
	public static String formatearFloat(float pfCadena) {
		return Float.toString(pfCadena);
	}

	/**
	 * Crea un float a partir de una cadena
	 * 
	 * @param pstrCadena
	 * @return
	 */
	public static float desformatearFloat(String pstrCadena) {
		float iResultado = 0;
		if (pstrCadena != NULL_STRING && !StringUtils.EMPTY_STRING.equals(pstrCadena)) {
			try {
				iResultado = Float.parseFloat(pstrCadena);
			} catch (NumberFormatException nfe) {
				LogsUtils.escribeLogWarn("Error al desformatear el valor Float. Valor: " + pstrCadena,StringUtils.class,nfe);
			}
		}
		return iResultado;
	}

	/**
	 * Convierte un entero en una cadena
	 *
	 * @param piEntero Entero
	 * @return
	 */
	public static String formatearEntero(int piEntero) {
		return Integer.toString(piEntero);
	}

	/**
	 * Convierte a Date un integer yyyyMMdd
	 * 
	 * @param piFechaEntero
	 * @return
	 */
	public static Date desformatearFecha(int piFechaEntero) {
		final SimpleDateFormat sdfFormato = getSdfYMD();
		Date dtFecha;
		try {
			dtFecha = sdfFormato.parse(Integer.toString(piFechaEntero));
		} catch (ParseException e) {
			LogsUtils.escribeLogWarn("Error al desformatear la fecha. Valor: " + piFechaEntero,StringUtils.class,e);
			dtFecha = new Date();
		}
		return dtFecha;
	}

	/**
	 * Convierte a date una cadena dd/MM/yyyy
	 * 
	 * @param pstrFecha
	 * @return
	 */
	public static Date desformatearFecha(String pstrFecha) {
		final SimpleDateFormat sdfFormato = getSdfNormal();
		Date dtFecha;
		try {
			dtFecha = sdfFormato.parse(pstrFecha);
		} catch (ParseException e) {
			LogsUtils.escribeLogWarn("Error al desformatear la fecha (se esperaba el formato dd/MM/yyyy). Valor: " + pstrFecha,StringUtils.class,e);
			dtFecha = new Date();
		}
		return dtFecha;
	}

	/**
	 * Convierte a date una cadena dd/MM/yyyy HH:mm:ss
	 * 
	 * @param pstrFecha
	 * @return
	 */
	public static Date desformatearFechaDMAHMS(String pstrFecha) {
		String pstrFechaAux = pstrFecha;
		if (pstrFecha.length() == 10) {
			pstrFechaAux = new StringBuffer(pstrFecha)
					.append(" 00:00:00")
					.toString();
		}
		SimpleDateFormat sdfFormato = getSdfDMAHMS();
		Date dtFecha;
		try {
			dtFecha = sdfFormato.parse(pstrFechaAux);
		} catch (ParseException e) {
			LogsUtils.escribeLogWarn("Error al desformatear la fecha (se esperaba el formato dd/MM/yyyy HH:mm:ss). Valor: "
					+ pstrFecha,StringUtils.class,e);
			dtFecha = new Date();
		}
		return dtFecha;
	}

	/***************************************************************************
	 * Convierte un tipo CadenaFecha en tipo entero de fecha
	 *
	 * @param pstrFecha
	 * @return
	 */
	public static int convertirFechaEntero(final String pstrFecha) {
		int iFecha = 0;
		if ("0".equals(pstrFecha)) {
			iFecha = 0;
		} else {
			final Date dFecha = desformatearFecha(pstrFecha);
			final String strFecha = formatearFecha(dFecha);
			try {
				iFecha = Integer.parseInt(strFecha);
			} catch (NumberFormatException nfe) {
				LogsUtils.escribeLogWarn("Error al desformatear el valor Fecha. Valor: " + strFecha,StringUtils.class,nfe);
			}
		}
		return iFecha;
	}

	/***************************************************************************
	 * Convierte un tipo CadenaFecha en tipo entero de fecha
	 *
	 * @param pstrFecha
	 * @return
	 */
	public static int convertirFechaEnteroDMA(final String pstrFecha) {
		int iFecha = 0;
		if ("0".equals(pstrFecha)) {
			iFecha = 0;
		} else {
			final Date dFecha = desformatearFecha(pstrFecha);
			final String strFecha = formatearFechaDMA(dFecha);
			try {
				iFecha = Integer.parseInt(strFecha);
			} catch (NumberFormatException nfe) {
				LogsUtils.escribeLogWarn("Error al desformatear el valor Fecha. Valor: " + strFecha,StringUtils.class,nfe);
			}

		}
		return iFecha;
	}

	/**
	 * Convierte a string un date
	 * 
	 * @param pdtFecha
	 * @return
	 */
	public static String desformatearFecha(Date pdtFecha) {
		final String cadena;
		if (pdtFecha != null) {
			final SimpleDateFormat sdfFormato = getSdfNormal();
			cadena = sdfFormato.format(pdtFecha);
		} else {
			cadena = StringUtils.EMPTY_STRING;
		}

		return cadena;
	}

	// INICIO Incidencia 6. Incidencia_260.

	/**
	 * Convierte a string un date con formato YYYYMMDD
	 * 
	 * @param pdtFecha
	 * @return
	 */
	public static String desformatearFechaYYYYMMDD(Date pdtFecha) {
		final String cadena;
		if (pdtFecha != null) {
			final SimpleDateFormat sdfFormato = getSdfYMD();
			cadena = sdfFormato.format(pdtFecha);
		} else {
			cadena = StringUtils.EMPTY_STRING;
		}

		return cadena;
	}
	// FIN Incidencia 6. Incidencia_260.

	/**
	 * Convierte a Date un long yyyyMMddHHmm
	 * 
	 * @param piTimestampEntero
	 * @return
	 */
	public static Date desformatearTimestamp(long piTimestampEntero) {
		final SimpleDateFormat sdfFormato = getSdfYMDHM();
		Date dtFecha;
		try {
			dtFecha = sdfFormato.parse(Long.toString(piTimestampEntero));
		} catch (ParseException e) {
				LogsUtils.escribeLogWarn("Error al desformatear el valor Timestamp "
						+ "(se esperaba el formato yyyyMMddHHmm). Valor: "
						+ piTimestampEntero,StringUtils.class,e);
			dtFecha = new Date();
		}
		return dtFecha;
	}

	/**
	 * Convierte a date un entero
	 * 
	 * @param piHoraEntero
	 * @return
	 */
	public static Date desformatearHora(int piHoraEntero) {
		final SimpleDateFormat sdfFormato = getSdfHM();
		Date dtFecha;
		try {
			dtFecha = sdfFormato.parse(Integer.toString(piHoraEntero));
		} catch (ParseException e) {
				LogsUtils.escribeLogWarn(
						"Error al desformatear el valor hora "
						+ "(se esperaba el formato HHmm). Valor: " + piHoraEntero,StringUtils.class,e);
			dtFecha = new Date();
		}
		return dtFecha;
	}

	/**
	 * Convierte a String YYYYMMDD un date
	 * 
	 * @param pdtFecha
	 * @return
	 */
	public static String formatearFecha(Date pdtFecha) {
		final String salida;
		if (pdtFecha != null) {
			final SimpleDateFormat sdfFormato = getSdfYMD();
			salida = sdfFormato.format(pdtFecha);
		} else {
			salida = StringUtils.EMPTY_STRING;
		}
		return salida;
	}

	public static String formatearFechaDMAHMS(Date pdtFecha) {
		final String salida;
		if (pdtFecha != null) {
			final SimpleDateFormat sdfFormato = getSdfDMAHMS();
			salida = sdfFormato.format(pdtFecha);
		} else {
			salida = StringUtils.EMPTY_STRING;
		}
		return salida;
	}

	// CR-3561 Incidencia 92 sggarcia 21/04/2010
	/**
	 * Convierte a String yyyy-MM-dd'T'HH:mm:ss un date
	 * 
	 * @param pdtFecha
	 * @return
	 */
	public static String formatearFechaAMDTHMS(Date pdtFecha) {
		final String salida;
		if (pdtFecha != null) {
			final SimpleDateFormat sdfFormato = getSdfYMDTHMS();
			salida = sdfFormato.format(pdtFecha);
		} else {
			salida = StringUtils.EMPTY_STRING;
		}
		return salida;
	}
	// Fin CR-3561 Incidencia 92

	/**
	 * Convierte a String con formato "ddMMyyyy" un date
	 * 
	 * @param pdtFecha
	 * @return
	 */
	public static String formatearFechaDMA(Date pdtFecha) {
		final String salida;
		if (pdtFecha != null) {
			final SimpleDateFormat sdfFormato = getSdfDMY();
			salida = sdfFormato.format(pdtFecha);
		} else {
			salida = StringUtils.EMPTY_STRING;
		}
		return salida;
	}

	/**
	 * Convierte a String con formato DD/MM/YYYY otro string de entrada YYYYMMDD
	 * 
	 * @param pstrFecha
	 * @return
	 */
	public static String formatearFecha(String pstrFecha) {
		final String salida;
		final java.util.Calendar cal = java.util.Calendar.getInstance();
		final int year = Integer.parseInt(pstrFecha.substring(0, 4));
		final int month = Integer.parseInt(pstrFecha.substring(4, 6)) - 1;
		final int day = Integer.parseInt(pstrFecha.substring(6, 8));
		cal.set(year, month, day);
		final java.util.Date date = cal.getTime();
		final SimpleDateFormat sdf = getSdfNormal();
		salida = sdf.format(date);
		return salida;
	}

	/**
	 * Convierte a String DD/MM/YYYY una fecha entero YYYYMMDD. Este es mes
	 * eficiente que formatearFecha(String)
	 * 
	 * @param fecha
	 * @return
	 */
	public static String formatearFecha(int fecha) {
		final String fechaCaducFormat;
		final String strFecha = String.valueOf(fecha);
		if (strFecha.length() == 8) {
			fechaCaducFormat = new StringBuffer(ClaseUtils.DEFAULT_INT_VALUE)
					.append(strFecha.substring(6, 8))
					.append("/")
					.append(strFecha.substring(4, 6))
					.append("/")
					.append(strFecha.substring(0, 4))
					.toString();
		} else {
			fechaCaducFormat = StringUtils.EMPTY_STRING;
			LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);
		}
		return fechaCaducFormat;
	}

	/**
	 * Devuelve la fecha actual en formato "dd/MM/yyyy"
	 * 
	 * @return
	 */
	public static String darFechaActual() {
		final String salida;
		final java.util.Date date = new java.util.Date();
		final SimpleDateFormat sdf = getSdfNormal();
		salida = sdf.format(date);
		return salida;
	}

	/**
	 * Convierte a String un Date con formato "yyyyMMddHHmm"
	 * 
	 * @param pdtTimestamp
	 * @return
	 */
	public static String formatearTimestamp(Date pdtTimestamp) {
		final String salida;
		if (pdtTimestamp != null) {
			final SimpleDateFormat sdfFormato = getSdfYMDHM();
			salida = sdfFormato.format(pdtTimestamp);
		} else {
			salida = StringUtils.EMPTY_STRING;
		}
		return salida;
	}

	/**
	 * Devuelve la hora en forma de string con formato HHmm de una hora tipo date
	 * pasada
	 * 
	 * @param pdtHora
	 * @return
	 */
	public static String formatearHora(Date pdtHora) {
		final String salida;
		if (pdtHora != null) {
			final SimpleDateFormat sdfFormato = getSdfHM();
			salida = sdfFormato.format(pdtHora);
		} else {
			salida = StringUtils.EMPTY_STRING;
		}
		return salida;
	}

	/**
	 * Devuelve la hora en forma de string con formato HH:mm:ss de una hora tipo
	 * date pasada
	 * 
	 * @param pdtHora
	 * @return
	 */
	public static String formatearHoraInforme(Date pdtHora) {
		final String salida;
		if (pdtHora != null) {
			final SimpleDateFormat sdfFormato = getSdfHMS();
			salida = sdfFormato.format(pdtHora);
		} else {
			salida = StringUtils.EMPTY_STRING;
		}
		return salida;
	}

	/**
	 * Formatea un mensaje a partir de una cadena pasada
	 * 
	 * @param cadena
	 * @return
	 */
	public static String formatCadenaMensaje(String cadena) {

			LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		
		final int longitudCadena = cadena.length();
		String ret;

		if (longitudCadena > 80) {
			final StringBuilder cadenaFinal = StringUtils.getNewStringBuilder();
			int longitudLinea = 70;
			int numeroFilas = 0;
			int mod;
			String cadenaAuxiliar = cadena;

			numeroFilas = longitudCadena / longitudLinea;
			mod = longitudCadena % longitudLinea;
			if (mod > 0) {
				numeroFilas++;
			}
			for (int i = 0; i < numeroFilas; i++) {
				numeroFilas = formatCadenaMensajeAux(cadenaFinal, 
						cadenaAuxiliar, longitudLinea, i, numeroFilas);
			}
			ret = cadenaFinal.toString();
		} else {
			ret = cadena;
		}

		return ret;
	}
	
	private static int formatCadenaMensajeAux(StringBuilder cadenaFinal,
			String cadenaAuxiliar, int longitudLinea, int i, int numeroFilas) {
		
		String cadenaAux;
		int ultimoEspacio;
		int longitudAuxiliar = cadenaAuxiliar.length();
		
		if (longitudAuxiliar <= longitudLinea) {
			longitudLinea = longitudAuxiliar;
		}
		cadenaAux = cadenaAuxiliar.substring(0, longitudLinea);
		if (longitudAuxiliar <= longitudLinea) {
			ultimoEspacio = longitudAuxiliar;
		} else {
			final String siguienteCaracter = cadenaAuxiliar.substring(longitudLinea, longitudLinea + 1);
			if (StringUtils.SPACE_STRING.equals(siguienteCaracter) || DOT_STRING.equals(siguienteCaracter)
					|| COMMA_STRING.equals(siguienteCaracter)) {
				ultimoEspacio = longitudLinea + ClaseUtils.ONE_INT;
			} else {
				ultimoEspacio = cadenaAux.lastIndexOf(StringUtils.SPACE_STRING);
			}
		}
		cadenaAux = cadenaAuxiliar.substring(0, ultimoEspacio);
		cadenaAuxiliar = cadenaAuxiliar.substring(ultimoEspacio);
		if (cadenaAux.indexOf(StringUtils.SPACE_STRING) == ClaseUtils.ZERO_INT) {
			cadenaAux = cadenaAux.substring(1);
		}
		cadenaFinal.append(cadenaAux).append(BR_TAG_STRING);
		if (i == numeroFilas - ClaseUtils.ONE_INT && cadenaAuxiliar.length() > ClaseUtils.ZERO_INT) { // NOPMD
			numeroFilas++;
		}
		return numeroFilas;
	}

	/**
	 * extrae un campo de una cadena con separador fijo
	 */
	public static String extraerCampo(String sCadena, int iNumCampo) {

			LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		return extraerCampo(sCadena, iNumCampo, "::");
	}

	/**
	 * Extrae un campo de una cadena
	 * 
	 * @param sCadena
	 * @param iNumCampo
	 * @param separador
	 * @return
	 */
	public static String extraerCampo(String sCadena, int iNumCampo, String separador) {

			LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		String strCampo = NULL_STRING;
		// Si hay dos separadores juntos les pongo un espacio en medio.
		String strCadenaAux = NULL_STRING;
		if (sCadena != NULL_STRING) {
			final String strDobleSeparador = new StringBuffer(ClaseUtils.DEFAULT_INT_VALUE)
					.append(separador)
					.append(separador)
					.toString();
			final String strSeparadorFinal = new StringBuffer(ClaseUtils.DEFAULT_INT_VALUE)
					.append(separador)
					.append(StringUtils.SPACE_STRING)
					.append(separador)
					.toString();

			strCadenaAux = sCadena.trim();
			while (strCadenaAux.indexOf(strDobleSeparador) != -1) {
				strCadenaAux = strCadenaAux.replaceFirst(strDobleSeparador, strSeparadorFinal);
			}
			strCadenaAux = strCadenaAux.replaceAll(separador, VIRGULILLA);
		}
		final StringTokenizer stCadena = new StringTokenizer(strCadenaAux, VIRGULILLA, Boolean.FALSE);
		int i = 1;
		while (stCadena.hasMoreTokens()) {
			strCadenaAux = stCadena.nextToken();
			if (i == iNumCampo) {
				strCampo = strCadenaAux.trim();
				if (StringUtils.EMPTY_STRING.equals(strCampo)) {
					strCampo = NULL_STRING;
				}
				break;
			}
			i++;
		}

		return strCampo;
	}

	/**
	 * @param string
	 * @return salida.toString() cadena formateada con puntos
	 *
	 **/
	public static String formatearEnteroMiles(String string) {

			LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		final String ret;

		final int valorEnt = desformatearEntero(string);

		if (valorEnt != ClaseUtils.ZERO_INT) {
			final StringBuilder salida = StringUtils.getNewStringBuilder();
			final int miLong = string.length();

			for (int i = ClaseUtils.ZERO_INT; i < miLong; i++) {
				if (i % 3 == ClaseUtils.ZERO_INT && i > ClaseUtils.ZERO_INT) {
					salida.append(DOT_STRING);
				}
				salida.append(string.charAt(miLong - i - ClaseUtils.ONE_INT));
			}
			salida.reverse();
			ret = salida.toString();
		} else {
			ret = string;
		}

		return ret;
	}

	/**
	 * Metodo que limpia la cadena que se anade como atributo a un XML. Este metodo
	 * limpia los caracteres que pueden dar problemas.
	 *
	 * @param cadenaXML Cadena a limpiar.
	 * @return String. Cadena limpiada.
	 */
	public static String limpiarCadenaAtributoXML(String cadenaXML) {

			LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);

		final StringBuilder sb = StringUtils.getNewStringBuilder();
		final String[] aCaracteres = new String[] { "\"", "'", "\\" };
		final int miNumCaracteres = aCaracteres.length;
		int miIndice;

		for (int i = 0; i < miNumCaracteres; i++) {
			miIndice = sb.indexOf(aCaracteres[i]);
			while (miIndice != -1) {
				sb.replace(miIndice, miIndice + 1, StringUtils.EMPTY_STRING);
				miIndice = sb.indexOf(aCaracteres[i]);
			}
		}

		return sb.toString();
	}

	/**
	 * Convert a String to an int, returning a default value if the conversion
	 * fails.<br>
	 * See org.apache.commons.lang.math.NumberUtils
	 * 
	 * @param str
	 * @param defaultInt
	 * @return
	 */
	public static int toInt(String str, int defaultInt) {
		int ret;
		if (isEmpty(str)) {
			ret = defaultInt;
		} else {
			try {
				ret = Integer.parseInt(str);
			} catch (NumberFormatException nfe) {
					LogsUtils.escribeLogWarn("Error al desformatear el valor: " + str,StringUtils.class,nfe);
				ret = defaultInt;
			}
		}
		return ret;
	}

	/**
	 * Checks if a String is empty (StringUtils.EMTY_STRING) or null.<br>
	 * See org.apache.commons.lang.StringUtils;
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return  StringUtils.validateNull(str) || str.length() <= ClaseUtils.ZERO_INT;
	}
	
    private static boolean isEmpty(final CharSequence cs) {
        return cs == NULL_STRING || cs.length() == ClaseUtils.ZERO_INT;
}	

	private static NumberFormat getNumberFormatMoneda() {
		NumberFormat numberFormatMoneda = NumberFormat.getInstance();
		numberFormatMoneda.setMinimumFractionDigits(ClaseUtils.TWO_INT);
		numberFormatMoneda.setMaximumFractionDigits(ClaseUtils.TWO_INT);
		return numberFormatMoneda;
	}

	private static NumberFormat getNumberFormatMonedaImp() {
		NumberFormat numberFormatMonedaImp = NumberFormat.getInstance(Locale.ENGLISH);
		numberFormatMonedaImp.setGroupingUsed(Boolean.FALSE);
		numberFormatMonedaImp.setMinimumFractionDigits(ClaseUtils.TWO_INT);
		numberFormatMonedaImp.setMaximumFractionDigits(ClaseUtils.TWO_INT);
		return numberFormatMonedaImp;
	}

	/**
	 * ilahhit CR-7808 07/08/2012: La funcion generica que nos calcula el digito de
	 * control.
	 * 
	 * @param cadena
	 * @return
	 */
	public static String calcularDigitoControl(String cadena) {
		
		
			LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);
		
		String digitoControl;

		int total = 0;
		for (int i = 0; i < cadena.length(); i++) {
			char caracter = cadena.charAt(i);
			int valor = (int) caracter;
			total = valor + valor;
		}
		int resta = total / 23;
		digitoControl = LISTA_DIGITOS[resta];
		return digitoControl;
	}

	/**
	 * ilahhit 11/10/2012: La funcion generica que nos permite remplazar caracteres
	 * extranos.
	 * 
	 * @param cadena
	 * @return
	 */
	public static String remplazarCaracteresRaros(final String cadena) {
		
		
			LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),StringUtils.class);
		
		String cadenaResultado;
		cadenaResultado = cadena;

		if (cadenaResultado != NULL_STRING && !cadenaResultado.equalsIgnoreCase(StringUtils.EMPTY_STRING)) {
			cadenaResultado = cadenaResultado.trim();
			// Reemplazamos el & por y, ya que si modificamos el xsd podemos perder ciertos
			// caracteres validos
			cadenaResultado = cadenaResultado.replaceAll("&", "y");
			// Reemplazamos el & por y, ya que si modificamos el xsd podemos perder ciertos
			// caracteres validos
			cadenaResultado = cadenaResultado.replaceAll("%", StringUtils.SPACE_STRING);
			// Reemplazamos el caracter  por blanco.
			cadenaResultado = cadenaResultado.replaceAll("", StringUtils.SPACE_STRING);
		}

		return cadenaResultado;
	}

	private static SimpleDateFormat getSdfNormal() {
		return new SimpleDateFormat("dd/MM/yyyy");
	}

	private static SimpleDateFormat getSdfDMAHMS() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	private static SimpleDateFormat getSdfYMD() {
		return new SimpleDateFormat("yyyyMMdd");
	}

	private static SimpleDateFormat getSdfDMY() {
		return new SimpleDateFormat("ddMMyyyy");
	}

	private static SimpleDateFormat getSdfYMDHM() {
		return new SimpleDateFormat("yyyyMMddHHmm");
	}

	private static SimpleDateFormat getSdfHM() {
		return new SimpleDateFormat("HHmm");
	}

	private static SimpleDateFormat getSdfHMS() {
		return new SimpleDateFormat("HH:mm:ss");
	}

	private static SimpleDateFormat getSdfYMDTHMS() {
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	}

	// Problemas con & en base de datos
	public static String filtro(final CharSequence charSeq) {
		if (charSeq != NULL_STRING) {
			String str = charSeq.toString();
			final int length = SANITIZE_STRING.length;
			for (int c = 0; c < length; c++) {
				str = patterns[c].matcher(str).replaceAll(SANITIZE_STRING[c][1]);
			}
			return str;
		}
		return NULL_STRING;
	}

	/**
	 * Escapa las comillas simples utilizado para Entradas Almacen, en domicilio de
	 * remitente.
	 *
	 * @param pstrCadena cadena
	 * @return cadena escapada
	 */
	public static String escape(String pstrCadena) {
		return pstrCadena.replaceAll("\'", "&apos;");
	}

	/**
	 * Devuelve true si la cadena evaluada es null
	 * 
	 * @param cadena String 
	 * @return  boolean boolean
	 */
	public static boolean validateNull(String cadena) {
		return Objects.isNull(cadena);
	}
	
	/**
	 * Elimina los parentesis "(" y ")" de una cadena 
	 * 
	 * @param cadena String
	 * @return resultado String
	 */
	public static String replaceParenthesis(String cadena) {

		if(StringUtils.validateNull(cadena)) {
			return NULL_STRING;
		}
		
		String resultado;
		
		resultado = cadena.replaceAll(LEFT_PARENTHESIS, EMPTY_STRING);
		resultado = resultado.replaceAll(RIGTH_PARENTHESIS, EMPTY_STRING);
		
		return resultado;
	}
	
	public static String formatSybol(String cadena) {
		
		String resultado = StringUtils.EMPTY_STRING;
		
		if(StringUtils.validateNull(cadena)) {
			return resultado;
		}
		resultado = cadena.replaceAll("€", "eur");
		resultado = resultado.replaceAll("Kilo", "Kg.");
		resultado = resultado.replaceAll(" / ", "/");
		resultado = resultado.replaceAll(" \"", "\"");
		return resultado;
	}
	
	public static String formatoCaracteres(final String cadena) {
		
		if(validateNull(cadena)) {
			return NULL_STRING;
		}
		
		String resultado = replaceParenthesis(cadena);
		
		if(validateNull(resultado)) {
			return NULL_STRING;
		}
		
		resultado = formatSybol(resultado);
		
		return resultado;
	}
	
	/**
     * <p>Joins the elements of the provided {@code Iterator} into
     * a single String containing the provided elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * A {@code null} separator is the same as an empty String ("").</p>
     *
     * <p>See the examples here: {@link #join(Object[],String)}. </p>
     *
     * @param iterator  the {@code Iterator} of values to join together, may be null
     * @param separator  the separator character to use, null treated as ""
     * @return the joined String, {@code null} if null iterator input
     */
    public static String join(final Iterator<?> iterator, final String separator) {

        // handle null, zero and one elements before building a buffer
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY_STRING;
        }
        final Object first = iterator.next();
        if (!iterator.hasNext()) {
            return Objects.toString(first, "");
        }

        // two or more elements
        final StringBuilder buf = StringUtils.getNewStringBuilder();
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != NULL_STRING) {
                buf.append(separator);
            }
            final Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }
    
    public static boolean isNumeric(final CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(cs.charAt(i))) {
                return false;
            }
        }
        return true;
}    
	
    /**
     * Metodo que contene combinaciones de palabras 
     * no permitidas. Las palabras no permitidas se
     * reemplazan por espacios en blanco.
     * 
     * @param value
     * @return String
     */
	public static String sanitizeValue(String value) {
		
		if(validateNull(value)) {
			return NULL_STRING;
		}
		
		value = value.replaceAll("\\*", EMPTY_STRING);
		value = value.replaceAll("/", EMPTY_STRING);
		value = value.replaceAll("//", EMPTY_STRING);
		value = value.replaceAll("<", EMPTY_STRING).replaceAll(">", EMPTY_STRING);
		value = value.replaceAll("\\{", EMPTY_STRING).replaceAll("\\}", EMPTY_STRING);
		value = value.replaceAll("\\[", EMPTY_STRING).replaceAll("\\]", EMPTY_STRING);
		value = value.replaceAll("\\(", EMPTY_STRING).replaceAll("\\)", EMPTY_STRING);
		value = value.replaceAll("'", EMPTY_STRING);
		value = value.replaceAll("eval\\((.*)\\)", EMPTY_STRING);
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", EMPTY_STRING);
		value = value.replaceAll("script", EMPTY_STRING);
		value = value.replaceAll("select", EMPTY_STRING).replaceAll("SELECT", EMPTY_STRING);
		value = value.replaceAll("insert", EMPTY_STRING).replaceAll("INSERT", EMPTY_STRING);
		value = value.replaceAll("delete", EMPTY_STRING).replaceAll("DELETE", EMPTY_STRING);
		value = value.replaceAll("alter", EMPTY_STRING).replaceAll("ALTER", EMPTY_STRING);
		value = value.replaceAll("drop", EMPTY_STRING).replaceAll("DROP", EMPTY_STRING);

		return value;
	}

	public static String getErrorJsonResponse(String error, long id) {
		return "{\"id\":\"" + id +"\",\"descripcion\":\"" + error + "\"}";
	}
	
	public static String eliminarTildes(final String cadena) {
		
		if(validateNull(cadena)) {
			return NULL_STRING;
		}
		
		if(cadena.indexOf(StringUtils.CHAR_ENIE_COD) != ClaseUtils.ONE_NEGATIVE_INT) {
			return cadena;
		}
		String resultado = cadena.replace(STRING_ENIE_MAY, UNICODE_ENIE);
		resultado = Normalizer.normalize(resultado.toLowerCase(), Normalizer.Form.NFD);
		resultado = resultado.replaceAll(REEMPLAZABLE_TILDES, EMPTY_STRING);
		resultado = resultado.replace(UNICODE_ENIE, STRING_ENIE_MIN);
		return Normalizer.normalize(resultado, Normalizer.Form.NFC);
		
	}
	
	public static String pasarAmayusculas(final String cadena) {
		
		if(validateNull(cadena)) {
			return NULL_STRING;
		}
		
		return cadena.toUpperCase();
	}
	
	public static String[] tokenizeString(final String cadena, final String token) {
		
		StringTokenizer st = new StringTokenizer(cadena, token); 
		
		if(ClaseUtils.isNullObject(st)) {
			return NULL_STRING_ARRAY;
		}
		
		List<String> listaAux = getNewListString();
		
		while (st.hasMoreElements()) {
			listaAux.add((String) st.nextElement());
			
		}
		
		return listaAux.toArray(new String[ClaseUtils.ZERO_INT]);
	}
	
	/**
	 * Metodo que comprueba si un String coincide o no con un Patrón.
	 * @param sAux
	 * @param patern
	 * @return
	 */
	public static boolean bRegExpr(String sCadena, String sExpresion) {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),FicherosUtil.class);
		
		boolean bEqual = false;
		
		Pattern pat = Pattern.compile(sExpresion);
		Matcher match = pat.matcher(sCadena);
		bEqual = match.matches();
		return bEqual;
	}
	
	public static Matcher matcher(final String pattern, final String string) {
		return Pattern.compile(pattern).matcher(string);
	}
	
	public static Matcher matcher(final String pattern, final String string, final int flag) {
		return Pattern.compile(pattern, flag).matcher(string);
	}
	
	public static String[] arrayTildesEroski() {
		return ARRAY_TILDES_EROSKI;
	}
	
	public static String[] arUnicodeLatinMinChars() {
		return LATIN_UNICODE_MIN;
	}
	
	public static String[] arUnicodeLatinMayChars() {
		return LATIN_UNICODE_MAY;
	}	
	
	public static String[] arrayTildesNormales() {
		return ARRAY_TILDES_NORMALES_MIN;
	}
	
	public static String[] arrayVocalesMin() {
		return ARRAY_VOCALES_MIN;
	}
	
	public static List<String> getNewListString() {
		return new ArrayList<>(ClaseUtils.DEFAULT_INT_VALUE);
	}	
	
	public static StringBuilder getNewStringBuilder() {
		return new StringBuilder(ClaseUtils.DEFAULT_INT_VALUE);
	}
}