package com.fit.actividad.modelo;

import java.util.List;

import com.fit.actividad.dao.DaoActividad;

public class ModeloActividad {

	private DaoActividad daoActividad;
	
	public ModeloActividad(DaoActividad daoActividad) {
		this.daoActividad = daoActividad;
	}
	
	public boolean guardarActividad(Actividad actividad) {
		return daoActividad.guardarActividad(actividad);
	}
	
	public List<Actividad> obtenerActividadesDelUsuario(int userId){
		return daoActividad.leerListaActividadesPorUsuarioId(userId);
	}
	
	public boolean actualizarActividad(Actividad actividad) {
		return daoActividad.actualizarActividad(actividad);
	}
	
	public boolean eliminarActividad(int idActividad) {
		return daoActividad.eliminarActividadPorId(idActividad);
	}
}
