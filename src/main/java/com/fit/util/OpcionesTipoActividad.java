package com.fit.util;

import com.fit.actividad.modelo.TipoActividad;

public class OpcionesTipoActividad {

	public static String[] getOpciones() {
		TipoActividad[] tiposActividad = TipoActividad.values();
		String[] opcionesTipoActividad = new String[tiposActividad.length];
		for(int i = 0; i < tiposActividad.length; i++) opcionesTipoActividad[i] = tiposActividad[i].getNombre();
		return opcionesTipoActividad;
	}
}
