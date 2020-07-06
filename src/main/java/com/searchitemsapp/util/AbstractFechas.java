package com.searchitemsapp.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Felix Marin Ramirez
 *
 *         Clase que proporciona funciones de utilidad sobre operaciones sobre
 *         Fechas
 * @modelguid {80259D62-9858-4D0F-B35B-4F7196DDA69B}
 */
public abstract class AbstractFechas  implements IFUtils {
	
	/*
	 * Enumerados Globales
	 */
	public enum MesAnio {
		ENERO, FEBRERO, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SEPTIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE;
	}
	
	/*
	 * Constantes Globales
	 */
	private static final int[] DATE_CONF = {
			ClaseUtils.ONE_NEGATIVE_INT, 
			ClaseUtils.ONE_NEGATIVE_INT, 
			ClaseUtils.ONE_NEGATIVE_INT, 
			ClaseUtils.ZERO_INT, 
			ClaseUtils.ZERO_INT, 
			ClaseUtils.ZERO_INT, 
			ClaseUtils.ZERO_INT };

	/*
	 * Controlador
	 */
	public AbstractFechas()	{
		super();
	}
	
	/**
	 * Calendar cal = Calendar.getInstance(); Integer iMes =
	 * cal.get(Calendar.MONTH); Integer iAnio = cal.get(Calendar.YEAR);
	 * 
	 * descargasBean.setMeses(Fechas.getFechas().getMeses());
	 * descargasBean.setMes(Fechas.getFechas().Mes(iMes));
	 * descargasBean.setAnio(iAnio.toString());
	 */
	public static String mes() {
		String sMes = null;
		Calendar cal = Calendar.getInstance();
		Integer mes = cal.get(Calendar.MONTH);
		for (MesAnio aux : MesAnio.values()) {
			if (aux.ordinal() == mes) {
				sMes = aux.toString();
			}
		}
		return sMes;
	}
    
	/**
	 * Lista en formato cadena con los nombres de los meses. 
	 * 
	 * @return List<String>
	 */
	public static List<String> getMeses() {
		
		List<String> meses = StringUtils.getNewListString();
		
		for (MesAnio aux : MesAnio.values()) {
			meses.add(aux.toString());
		}
		
		return meses;
	}

	public static String getAnio() {
		Calendar cal = Calendar.getInstance();
		Integer anio = cal.get(Calendar.YEAR);
		return anio.toString();
	}

	/**
	 * Funcion que comprueba si una fecha esta dentro de un rango de 12 meses sin
	 * contar el mes actual.
	 * 
	 * @return boolean
	 */
	public static boolean compruebaRango(Date fComprobar, Integer opcion) {
		boolean bValida = true;

		if (opcion == ClaseUtils.ONE_INT) {
			// Esta opcion comprueba que la fecha se encuentre dentro de los doce meses
			// anteriores.
			// Para ello, creamos la fecha.

			GregorianCalendar gFechComp = new GregorianCalendar();
			gFechComp.setTime(fComprobar);
			gFechComp.setTime(recorTaFecha(gFechComp, DATE_CONF).getTime());

			// Establecemos el limite a comprobar.
			GregorianCalendar gFechLimite = new GregorianCalendar();
			gFechLimite.add(GregorianCalendar.YEAR, - ClaseUtils.ONE_INT);

			gFechLimite.setTime(recorTaFecha(gFechLimite, DATE_CONF).getTime());

			if (gFechComp.before(gFechLimite)) {
				bValida = false;
			}
		}

		return bValida;
	}

	/**
	 * Metodo que setea a cero el campo de un gregorian calendar se le indique. Se
	 * deben dejar a -1 aquellos valores que no se quiran modificar.
	 * 
	 * @param gEntrada
	 * @param iAno
	 * @param iMes
	 * @param iDia
	 * @param iHora
	 * @param iMinuto
	 * @param iSegundos
	 * @param iMilisec
	 * @return GregorianCalendar
	 */
	public static GregorianCalendar recorTaFecha(GregorianCalendar gEntrada, int[] dateConf) {

		if (dateConf[0] > -1) {
			gEntrada.set(GregorianCalendar.YEAR, dateConf[0]);
		}
		if (dateConf[1] > -1) {
			gEntrada.set(GregorianCalendar.MONTH, dateConf[1]);
		}
		if (dateConf[2] > -1) {
			gEntrada.set(GregorianCalendar.DAY_OF_MONTH, dateConf[2]);
		}
		if (dateConf[3] > -1) {
			gEntrada.set(GregorianCalendar.HOUR_OF_DAY, dateConf[3]);
		}
		if (dateConf[4] > -1) {
			gEntrada.set(GregorianCalendar.MINUTE, dateConf[4]);
		}
		if (dateConf[5] > -1) {
			gEntrada.set(GregorianCalendar.SECOND, dateConf[5]);
		}
		if (dateConf[6] > -1) {
			gEntrada.set(GregorianCalendar.MILLISECOND, dateConf[6]);
		}

		return gEntrada;
	}
}
