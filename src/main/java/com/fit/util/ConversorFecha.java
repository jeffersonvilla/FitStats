package com.fit.util;

import java.util.GregorianCalendar;

public class ConversorFecha {

	public static String convertirGregorianCalendarEnString(GregorianCalendar fechaHora) {
		if(fechaHora == null) return "null";
		return String.format("%04d-%02d-%02d %02d:%02d:%02d", 
				fechaHora.get(GregorianCalendar.YEAR),
				fechaHora.get(GregorianCalendar.MONTH + 1),
				fechaHora.get(GregorianCalendar.DAY_OF_MONTH),
				fechaHora.get(GregorianCalendar.HOUR_OF_DAY),
				fechaHora.get(GregorianCalendar.MINUTE),
				fechaHora.get(GregorianCalendar.SECOND));
	}
}
