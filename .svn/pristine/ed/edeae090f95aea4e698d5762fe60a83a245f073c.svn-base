package com.searchitemsapp.util;

//PMD.jllgalvez

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @version 1.0.0
 * @author Architect: Getronics
 * @author <br>
 *         Designer: Getronics
 * @author <br>
 *         Developer: Getronics
 *
 * Clase que proporciona funciones de utilidad sobre objetos de tipo Date
 * @modelguid {A7D8D125-64CA-40EF-86B0-D6D223E1C324}
 */
public final class FechaUtils extends AbstractFechas implements IFUtils {
	
    /**
     * Campo que representa un dia dentro de un mes (1 a 31).
     */
	private static final int DIA = Calendar.DAY_OF_MONTH;

	/**
     * Campo que representa un mes dentro del ano (1 a 12).
     */
	private static final int MES = Calendar.MONTH;

	/**
     * Campo que representa un ano.
     */
	private static final int ANO = Calendar.YEAR;
	
	private FechaUtils() {
		super();
	}

	/**
	 * Metodo estatico que nos devuelve la fecha actual en formato aaaammdd
	 *
	 * String fecha = FechaUtil.fechaEntero(); // aaaammdd
	 *
	 * @return la fecha del sistema
	 */
	public static int fechaEntero(){
		final java.util.Calendar fecha = java.util.Calendar.getInstance();
		final int mes = fecha.get(java.util.Calendar.MONTH) + 1;
		final int dia = fecha.get(java.util.Calendar.DATE);
		final int anyo = fecha.get(java.util.Calendar.YEAR);
		return anyo * 10000 + mes * 100 + dia;
	}

	public static int fechaDateToEntero(Date fechaDate){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String output = formatter.format(fechaDate);
		if (output!= null)
		{
			return Integer.parseInt(output);
		}
		return 0;
	}
	
	/**
	 * Metodo estatico que nos devuelve la fecha actual en formato DD/MM/AAAA
	 *
	 * String fecha = FechaUtil.fecha(); // DD/MM/AAAA
	 *
	 * @return la fecha del sistema
	 */
	public static String fecha(){
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}
	/**
	 * Metodo estatico que nos devuelve la fecha actual en formato D/MM/AAAA
	 *
	 * String fecha = FechaUtil.fechaCorta(); // D/MM/AAAA
	 *
	 * @return la fecha del sistema
	 */
	public static String fechaCorta(){
		return new SimpleDateFormat("d/MM/yyyy").format(new Date());
	}
	/**
	 * Metodo que devuelve la fecha actual como un tipo java.sql.Date
	 * @return fechaActual
	*/
	public static java.sql.Date fechaActualDate() {
	    return new java.sql.Date(new java.util.Date().getTime());
	}
	
	/**
	 * ilahhit CR-5876 Metodo que devuelve la fecha actual como un tipo long
	 * @return fechaLong
	 */
	public static long getFechaActualEnLong(){
		return new java.util.Date().getTime();
	}

	/**
	 * Metodo estatico que nos devuelve la hora actual delsistema HH:MM:ss (reloj de 24 h)
	 * @return la hora del sistema.
	 */
	public static String hora(){
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	/**
	 * Metodo estatico que nos devuelve la fecha con la hora actual delsistema HH:MM:ss (reloj de 24 h)
	 * @return la hora del sistema.
	 */
	public static String fechaConHora(){
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
	}
	/**
	 * Metodo estatico que nos devuelve la hora actual del sistema sin segundos HH:mm
	 * @return la hora del sistema.
	 */
	public static String horaCorta(){
		return new SimpleDateFormat("HH:mm").format(new Date());
	}








	/**
	 *  Metodo que devuelve la diferencia en dias que existe entre dos fechas dadas.
	 *
	 *
	 *  @param pdtFechaInicial La fecha inicial.
	 *  @param pdtFechaFinal La fecha final.
	 *
     *  @return la diferencia existente entre las dos fechas expresado en dias.
     *
     * 	@author maruizi
	 *
	 */
	public static int diferenciaDias(Date pdtFechaInicial, Date pdtFechaFinal) {
	    final long resultadoMilisegundos;
	    final int valor;

	    // Realizamos la diferencia de fechas
	    resultadoMilisegundos = pdtFechaInicial.getTime() - pdtFechaFinal.getTime();
	    // Calculamos los dias a la diferencia
	    valor = (int) resultadoMilisegundos / 1000 * 3600 * 24;

	    return valor;
	}

	/**
	 *  Metodo que devuelve una fecha despues deanadirle un cierto numero de dias.
	 *  Si se necesita restarle dias simplemente hay que asignarle un numero negativo
	 *  a piNumDias.
	 *
	 *
	 *  @param pdtFecha La fecha a la cual se le van a anadir o sustraer dias.
	 *  @param piNumDias Los dias a anadir o sustraer.
	 *
     *  @return la fecha resultante.
     *
     * 	@author maruizi
	 *
	 */
	public static Date anadirDias(Date pdtFecha, int piNumDias) {
	    final Calendar cal = Calendar.getInstance();

	    // Asignamos la fecha al calendario
	    cal.setTime(pdtFecha);
	    // Le anadimos o sustraemos el numero de dias indicado con piNumDias
	    cal.add(Calendar.DAY_OF_MONTH, piNumDias);

	    return cal.getTime();
	}

	/**
	 *  Metodo que devuelve una fecha despues deanadirle un cierto numero de meses.
	 *  Si se necesita restarle meses simplemente hay que asignarle un numero negativo
	 *  a piNumMeses.
	 *
	 *
	 *  @param pdtFecha La fecha a la cual se le van a anadir o sustraer meses.
	 *  @param piNumMeses Los meses a anadir o sustraer.
	 *
     *  @return la fecha resultante.
     *
     * 	@author maruizi
	 *
	 */
	public static Date anadirMeses(Date pdtFecha, int piNumMeses) {
	    final Calendar cal = Calendar.getInstance();

	    // Asignamos la fecha al calendario
	    cal.setTime(pdtFecha);
	    // Le anadimos o sustraemos el numero de meses indicado con piNumMeses
	    cal.add(Calendar.MONTH, piNumMeses);

	    return cal.getTime();
	}

	/**
     *  Metodo que devuelve una fecha despues deanadirle un cierto numero de anios.
	 *  Si se necesita restarle anios simplemente hay que asignarle un numero negativo
	 *  a piNumAnos.
	 *
	 *
	 *  @param pdtFecha La fecha a la cual se le van a anadir o sustraemos.
	 *  @param piNumAnos Los anios a anadir o sustraer.
	 *
     *  @return la fecha resultante.
     *
     * 	@author maruizi
	 *
	 */
	public static Date anadirAnos(Date pdtFecha, int piNumAnos) {
	    final Calendar cal = Calendar.getInstance();

	    // Asignamos la fecha al calendario
	    cal.setTime(pdtFecha);
	    // Le anadimos o sustraemos el numero de anios indicado con piNumAnos
	    cal.add(Calendar.YEAR, piNumAnos);

	    return cal.getTime();
	}

	/**
	 *  Metodo que devuelve la diferencia en meses que existe entre dos fechas dadas.
	 *
	 *
	 *  @param pdtFechaInicial La fecha inicial.
	 *  @param pdtFechaFinal La fecha final.
	 *
     *  @return la diferencia existente entre las dos fechas expresado en meses.
     *
     * 	@author maruizi
	 *
	 */
	public static int diferenciaMeses(Date pdtFechaInicial, Date pdtFechaFinal) {
	    long resultadoMilisegundos;
	    int valor = 0;

	    // Realizamos la diferencia de fechas
	    resultadoMilisegundos = pdtFechaInicial.getTime() - pdtFechaFinal.getTime();
	    // Calculamos los meses a la diferencia
	    valor = (int) resultadoMilisegundos / 1000 * 3600 * 24 * 31;

	    return valor;
	}

	/**
	 *  Metodo que devuelve la diferencia en meses que existe entre dos fechas dadas.
	 *
	 *
	 *  @param pdtFechaInicial La fecha inicial.
	 *  @param pdtFechaFinal La fecha final.
	 *
     *  @return la diferencia existente entre las dos fechas expresado en anios.
     *
     * 	@author maruizi
	 *
	 */
	public static int diferenciaAnos(Date pdtFechaInicial, Date pdtFechaFinal) {
	    long resultadoMilisegundos;
	    int valor = 0;

	    // Realizamos la diferencia de fechas
	    resultadoMilisegundos = pdtFechaInicial.getTime() - pdtFechaFinal.getTime();
	    // Calculamos los anios a la diferencia
	    valor = (int) resultadoMilisegundos / 1000 * 3600 * 24 * 31 * 365;

	    return valor;
	}

	/**
	 *  Metodo que devuelve el anio de una fecha.
	 *
	 *
	 *  @param pdtFecha La fecha de la que queremos conseguir el anio.
	 *
     *  @return el anio de esa fecha.
     *
     * 	@author maruizi
	 *
	 */
	public static int getAno(Date pdtFecha) {
	    final Calendar cal = Calendar.getInstance();

	    // Asignamos la fecha al calendario
	    cal.setTime(pdtFecha);

	    return cal.get(Calendar.YEAR);
	}

	/**
	 *  Metodo que devuelve el mes de una fecha.
	 *  Valores devueltos: 1 - Enero, 2 - Febrero, etc...
	 *
	 *  @param pdtFecha La fecha de la que queremos conseguir el mes.
	 *
     *  @return el mes de esa fecha.
     *
     * 	@author maruizi
	 *
	 */
	public static int getMes(Date pdtFecha) {
	    final Calendar cal = Calendar.getInstance();

	    // Asignamos la fecha al calendario
	    cal.setTime(pdtFecha);

	    return cal.get(Calendar.MONTH);
	}

	/**
	 *  Metodo que devuelve el dia del mes de una fecha.
	 *  Valores devueltos: 1, 2, ... 31
	 *
	 *
	 *  @param pdtFecha La fecha de la que queremos conseguir el dia del mes.
	 *
     *  @return el dia del mes de esa fecha.
     *
     * 	@author maruizi
	 *
	 */
	public static int getDia(Date pdtFecha) {
	    final Calendar cal = Calendar.getInstance();

	    // Asignamos la fecha al calendario
	    cal.setTime(pdtFecha);

	    return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 *  Metodo que devuelve el dia de la semana de una fecha.
	 *  Valores devueltos: 1 - Domingo, 2 - Lunes, etc....
	 *
	 *
	 *  @param pdtFecha La fecha de la que queremos conseguir el dia de la semana.
	 *
     *  @return el dia de la semana de esa fecha.
     *
     * 	@author maruizi
	 *
	 */
	public static int getDiaSemana(Date pdtFecha) {
	    final Calendar cal = Calendar.getInstance();

	    // Asignamos la fecha al calendario
	    cal.setTime(pdtFecha);

	    return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
     *  Metodo que devuelve una fecha despues deanadirle un cierto numero de unidades.
	 *  Si se necesita restarle unidades simplemente hay que asignarle un numero negativo
	 *  a iNumUnidades.
	 *
	 *
	 *  @param pdtFecha La fecha a la cual se le van a anadir o sustraemos.
     *  @param iCampo Indica el campo que vamos a modificar. Debe contener el valor de las
     *                  constantes: FechaUtil.DIA, FechaUtil.MES, FechaUtil.ANO
	 *  @param iNumUnidades Las unidades a anadir o sustraer.
	 *
     *  @return la fecha resultante.
     *
     * 	@author maruizi
	 *
	 */
	public static Date anadir(Date pdtFecha, int iCampo, int iNumUnidades) {
	    final Calendar cal = Calendar.getInstance();

	    // Asignamos la fecha al calendario
	    cal.setTime(pdtFecha);
	    // Le anadimos o sustraemos el numero de unidades indicado con iNumUnidades
	    cal.add(iCampo, iNumUnidades);

	    return cal.getTime();
	}
	/**
	 *  Metodo que devuelve la hora de una fecha.
	 *  Valores devueltos: 0, 2, ... 23
	 *
	 *
	 *  @param pdtFecha La fecha de la que queremos conseguir la hora.
	 *
     *  @return la hora de esa fecha.
     *
     * 	@author maruizi
	 *
	 */
	public static int getHora(Date pdtFecha) {
	    final Calendar cal = Calendar.getInstance();

	    // Asignamos la fecha al calendario
	    cal.setTime(pdtFecha);

	    return cal.get(Calendar.HOUR_OF_DAY );
	}
	/**
	 *  Metodo que devuelve los minutos de una fecha.
	 *  Valores devueltos: 0, 2, ... 59
	 *
	 *
	 *  @param pdtFecha La fecha de la que queremos conseguir los minutos.
	 *
     *  @return los minutos de esa fecha.
     *
     * 	@author maruizi
	 *
	 */
	public static int getMinutos(Date pdtFecha) {
	    final Calendar cal = Calendar.getInstance();

	    // Asignamos la fecha al calendario
	    cal.setTime(pdtFecha);

	    return cal.get(Calendar.MINUTE);
	}
	/**
	 *  Metodo que devuelve los segundos de una fecha.
	 *  Valores devueltos: 0, 2, ... 59
	 *
	 *
	 *  @param pdtFecha La fecha de la que queremos conseguir los segundos.
	 *
     *  @return los segundos de esa fecha.
     *
     * 	@author maruizi
	 *
	 */
	public static int getSegundos(Date pdtFecha) {
	    final Calendar cal = Calendar.getInstance();

	    // Asignamos la fecha al calendario
	    cal.setTime(pdtFecha);

	    return cal.get(Calendar.SECOND);
	}
	/**
	 *  Metodo que devuelve la hora actual del sistema.
	 *  Valores devueltos: 0, 2, ... 23
	 *
	 *
     *  @return la hora actual del sistema.
     *
     * 	@author maruizi
	 *
	 */
	public static int getHora(){
	    final Calendar cal = Calendar.getInstance();

	    return cal.get(Calendar.HOUR_OF_DAY );
	}
	/**
	 *  Metodo que devuelve los minutos de la hora actual del sistema.
	 *  Valores devueltos: 0, 2, ... 59
	 *
	 *
     *  @return los minutos de la hora actual del sistema.
     *
     * 	@author maruizi
	 *
	 */
	public static int getMinutos() {
	    final Calendar cal = Calendar.getInstance();

	    return cal.get(Calendar.MINUTE);
	}
	/**
	 *  Metodo que devuelve los segundos de la hora actual del sistema.
	 *  Valores devueltos: 0, 2, ... 59
	 *
	 *
     *  @return los segundos de la hora actual del sistema.
     *
     * 	@author maruizi
	 *
	 */
	public static int getSegundos() {
	    final Calendar cal = Calendar.getInstance();

	    return cal.get(Calendar.SECOND);
	}
	/**
	 * @return Returns the aNO.
	 */
	public static int getYear() {
		return ANO;
	}
	/**
	 * @param ano The aNO to set.
	 */

	/**
	 * @return Returns the dIA.
	 */
	public static int getDay() {
		return DIA;
	}
	/**
	 * @param dia The dIA to set.
	 */

	/**
	 * @return Returns the mES.
	 */
	public static int getMonth() {
		return MES;
	}
	/**
	 * @param mes The mES to set.
	 */
	/**
	 * Metodo estatico que nos devuelve la fecha dada en formato DD/MM/AAAA
	 *
	 * String fecha = FechaUtil.fecha(); // DD/MM/AAAA
	 *
	 * @return la fecha del sistema
	 */
	public static String fecha(Date pFecha){
		return new SimpleDateFormat("dd/MM/yyyy").format(pFecha);
	}
	/**
	 * Metodo estatico que nos devuelve la fecha dada en formato D/MM/AAAA
	 *
	 * String fecha = FechaUtil.fechaCorta(); // D/MM/AAAA
	 *
	 * @return la fecha del sistema
	 */
	public static String fechaCorta(Date pFecha){
		return new SimpleDateFormat("d/MM/yyyy").format(pFecha);
	}
	
	/**
	 * Metodo estatico que nos devuelve la fecha y hora actual en formato String segun patron de fecha:
	 *
	 * String fecha = FechaUtil.dia("DD/MM/AAAA"); // 01/02/2003
	 * String fecha = FechaUtil.dia("DD");         // 01
	 * String fecha = FechaUtil.dia("MM");         //    02
	 * String fecha = FechaUtil.dia("AAAA");       //       2003
	 * String fecha = FechaUtil.dia("AAA");        // 003
	 *
	 * @return la fecha del sistema
	 */
	public static String getFechaHora(){
		final java.util.Calendar fecha = new java.util.GregorianCalendar();
		final int dia = fecha.get(java.util.Calendar.DAY_OF_MONTH);
		final int mes = fecha.get(java.util.Calendar.MONTH)+1;
		final int annio = fecha.get(java.util.Calendar.YEAR);
		
		final int hor = fecha.get(java.util.Calendar.HOUR_OF_DAY);
		final int min = fecha.get(java.util.Calendar.MINUTE);
		final int seg = fecha.get(java.util.Calendar.SECOND);
		
		
		final StringBuilder sbFecha = new StringBuilder(10);
		
				if (dia<10) {
					sbFecha.append("0");
				}
				sbFecha.append(dia+"/");
			
				if (mes<10) {
					sbFecha.append("0");
				}
				sbFecha.append(mes+"/");
			
				sbFecha.append(annio);
				sbFecha.append(System.getProperty("line.separator")); 
				
//				Incluimos la hora
				if (hor<10){
					sbFecha.append("0");
				}
				sbFecha.append(hor+":");
				if (min<10){
					sbFecha.append("0");
				}
				sbFecha.append(min+":");
				if (seg<10){
					sbFecha.append("0");
				}
				sbFecha.append(seg);
	
		return sbFecha.toString();
		
	}
	/**
	 * 
	 * @autor cseguin
	 *
	 * @descripcion: Valida una fecha de acuerdo con el formata que se le pase como parametro
	 * 				tambien valida que la fecha sea legal
	 * @parametros: 
	 * 				String fecha: Fecha a validar
	 * 				String formato: Formato que se quiere validar
	 *
	 * @return true or false
	 */
	public static boolean esFechaValida(String fecha, String formato)
	{
	   
	   SimpleDateFormat sdf = new SimpleDateFormat(formato);
	   
	   Date fechaTest = (Date) ClaseUtils.NULL_OBJECT;
	   boolean resultado = Boolean.TRUE;
	   
	   if(null == fecha || null == formato) {
		   return Boolean.FALSE;
	   }

	   try  {
	     fechaTest = sdf.parse(fecha);
	   }
	   
	   catch (ParseException e) {
		   resultado = false;
	   }

	   if (!sdf.format(fechaTest).equals(fecha))  {
		   resultado = false;
	   }
	  
	   return resultado;
	}
	/**
	 * 
	 * @autor cseguin
	 *
	 * @descripcion: Convierte una String en un Date conforme al formato que se le pase
	 * 	
	 * @parametros: 
	 * 				String fecha: Fecha a convertir
	 * 				String formato: Formato que se quiere validar
	 *
	 * @return Date; la fecha convertida en Date
	 * @throws ParseException 
	 */
	public static Date convertirStringADate(String fecha, String formato) throws ParseException{
		DateFormat df ; 
        Date date = null ;    
	
        df = new SimpleDateFormat(formato);
             date = df.parse(fecha);    
 
    return date;
  } 
	
	/**
	 * Valida si un String tiene formato de fecha.
	 * @param sFecha
	 * @param sFormato
	 * @return
	 */
	public static boolean validaFormatoFecha(String sFecha, String sFormato) {
		boolean valido = false;
		SimpleDateFormat sf = new SimpleDateFormat(sFormato);
		try {
			sf.setLenient(false);
			sf.parse(sFecha);
			valido = true;
		} catch (ParseException e) {
			valido = false;
		}
		return valido;
	}
	
}