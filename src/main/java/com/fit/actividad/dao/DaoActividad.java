package com.fit.actividad.dao;

import java.util.List;

import com.fit.actividad.modelo.Actividad;

public interface DaoActividad {

	public boolean guardarActividad(Actividad actividad);
	
	public Actividad leerActividadPorId(int idActividad);
	
	public boolean actualizarActividad(Actividad actividad);
	
	public boolean eliminarActividadPorId(int idActividad);
	
	public List<Actividad> leerListaActividadesPorUsuarioId(int usuarioId);
}
