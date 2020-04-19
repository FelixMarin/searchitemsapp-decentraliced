package com.searchitemsapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.SortedMap;
import java.util.TreeMap;


public class FicherosUtil  implements IFUtils {
	
	
	private FicherosUtil() {
		super();
	}

	/**
	 * Lee un fichero de Texto y devuelve cada una de sus líneas en un MAP, ordenado y quitando repetidos.
	 * Se puede filtrar por líneas, para excluir aquellas que tengan mas de X caracteres.
	 * Se puede indicar si se desea filtrar las líneas por una Expresión regular.
	 * El Map contiene el String como Key y el nº de línea en que se encuentra en el fichero.
	 * @param iStream - Stream que contiene los datos.
	 * @param iNumCarac - Numero de caracteres para filtrar. 0 Para que no filtre.
	 * @param bPater - Poner a True o False si se desea que se compare cada linea con un Pattern.
	 * @param patern - Pattern para el que se desea comprobar las líneas.
	 * @return
	 * @throws Exception
	 */
	public static SortedMap<String,Integer> inputStream2SortedMap(InputStream iStream, int iNumCarac, boolean bPater) throws IOException {
		
		LogsUtils.escribeLogDebug(Thread.currentThread().getStackTrace()[1].toString(),FicherosUtil.class);
		
		SortedMap<String,Integer> lSalida = new TreeMap<>();
		int i = 1;
		BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
		String sAux;
		while (!ClaseUtils.isNullObject((sAux = reader.readLine()))) { 
			if( (iNumCarac != 0) && (sAux.length() < iNumCarac) && !bPater) {
				lSalida.put(sAux, i);
			}
			i++;
        }   
		iStream.close();
		return lSalida;
	}
}
