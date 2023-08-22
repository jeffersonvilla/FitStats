package com.fit.util;

import java.sql.Timestamp;
import java.util.regex.Pattern;

public class Validador {
	
	public static boolean validarFecha(Timestamp fecha) {
		return (fecha != null)? true : false ;
	}

	public static boolean validarDistancia(String distancia) {
		return (!distancia.isEmpty() && Pattern.matches(ExpresionesRegulares.FORMATO_DISTANCIA, distancia))? true : false;
	}
	
}
