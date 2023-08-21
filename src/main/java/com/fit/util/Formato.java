package com.fit.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public class Formato {
	
	public static String formatearFechaHora(Timestamp fechaHora) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(fechaHora.getTime());
		return String.format("%04d-%02d-%02d %02d:%02d", 
				calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) + 1, 
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.HOUR_OF_DAY), 
				calendar.get(Calendar.MINUTE));
	}
	
	public static String formatearDuracion(Time duracion) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(duracion.getTime());
		int horas = calendar.get(Calendar.HOUR_OF_DAY);
		int minutos = calendar.get(Calendar.MINUTE);
		String horasString = (horas != 0)?(horas + " hora" + ((horas == 1)? "" : "s")) : "" ;
		String minutosString = (minutos != 0)? (minutos + " minuto" + ((minutos == 1)? "" : "s" )) : "";
		return (horasString.isEmpty() && minutosString.isEmpty())? "sin duración" 
				: (horasString + ((!horasString.isEmpty() && !minutosString.isEmpty())? " y " : "" ) + minutosString);
	}
}
