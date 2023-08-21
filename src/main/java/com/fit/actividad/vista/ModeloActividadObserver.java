package com.fit.actividad.vista;

import java.util.List;

import com.fit.actividad.modelo.Actividad;

public interface ModeloActividadObserver {

	public void actualizar(List<Actividad> actividades);
}
