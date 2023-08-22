package com.fit.actividad.AbstractFactory;

import java.util.List;

import com.fit.actividad.modelo.Actividad;

public interface Controlador {

	public void registrarActividad(Actividad actividad);
	
	public List<Actividad> leerListaActividades();
	
	public void actualizarActividad(Actividad actividad);
	
	public void eliminarActividad(int index);
}
