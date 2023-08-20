package com.fit.actividad.controlador;

import java.sql.Time;
import java.sql.Timestamp;

public interface Controlador {

	public void registrar(Timestamp fechaHora, Time duracion, String ubicacion, Object... object);
	
	public void actualizar(Timestamp fechaHora, Time duracion, String ubicacion, Object... object);
}
