package com.fit.modelo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class ConversorFecha {

	public static String convertirGregorianCalendarEnFormatoDateTimeMySql(GregorianCalendar fechaHora) {
		if(fechaHora == null) return "null";
		return String.format("%04d-%02d-%02d %02d:%02d:%02d", 
				fechaHora.get(GregorianCalendar.YEAR),
				fechaHora.get(GregorianCalendar.MONTH),
				fechaHora.get(GregorianCalendar.DAY_OF_MONTH),
				fechaHora.get(GregorianCalendar.HOUR_OF_DAY),
				fechaHora.get(GregorianCalendar.MINUTE),
				fechaHora.get(GregorianCalendar.SECOND));
	}
	
	public static GregorianCalendar convertirStringFormatoDateTimeMySqlEnGregorianCalendar(String fechaHoraString) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formateador = new SimpleDateFormat(pattern);
		GregorianCalendar fechaFormateada = new GregorianCalendar();
		try {
			fechaFormateada.setTime(formateador.parse(fechaHoraString));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return fechaFormateada;
	}
}
