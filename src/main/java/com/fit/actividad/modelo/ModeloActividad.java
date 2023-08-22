package com.fit.actividad.modelo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fit.actividad.dao.DaoActividad;
import com.fit.actividad.vista.ModeloActividadObserver;

public class ModeloActividad {
	
	private static final Logger logger = LoggerFactory.getLogger(ModeloActividad.class);
	
	private List<ModeloActividadObserver> observadores = new ArrayList<>(); 
	
	private List<Actividad> actividades = new ArrayList<>();

	private DaoActividad daoActividad;
	
	public ModeloActividad(DaoActividad daoActividad) {
		this.daoActividad = daoActividad;
	}
	
	public boolean guardarActividad(Actividad actividad) {
		boolean resultado = daoActividad.guardarActividad(actividad);
		notificarCambioObservadores();
		return resultado;
	}
	
	public List<Actividad> obtenerActividadesDelUsuario(int userId){
		actividades = daoActividad.leerListaActividadesPorUsuarioId(userId);
		return actividades;
	}
	
	public Actividad obtenerActividadPorIndexEnLista(int index) {
		return actividades.get(index);
	}
	
	public boolean actualizarActividad(Actividad actividad) {
		boolean resultado = daoActividad.actualizarActividad(actividad);
		if(resultado) logger.debug("Actualizado con exito {}", actividad);
		else logger.debug("No se ha podido actualizar {}", actividad);
		notificarCambioObservadores();
		return resultado;
	}
	
	public boolean eliminarActividad(int idActividad) {
		boolean resultado = daoActividad.eliminarActividadPorId(idActividad);
		notificarCambioObservadores();
		return resultado;
	}
	
	private void notificarCambioObservadores() {
		//TODO: cambiar 27 por forma dinamica de pasar userId
		logger.debug("nofificando a observadores los cambios en el modelo");
		for(ModeloActividadObserver observador: observadores) observador.actualizar(obtenerActividadesDelUsuario(27));
	}
	
	public void agregarObservador(ModeloActividadObserver observador) {
		observadores.add(observador);
	}
	
	public void eliminarObservador(ModeloActividadObserver observador) {
		observadores.remove(observador);
	}
}
