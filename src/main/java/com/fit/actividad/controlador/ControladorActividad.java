package com.fit.actividad.controlador;

import java.util.List;

import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.ModeloActividad;

public class ControladorActividad {

	private ModeloActividad modelo;

	public ControladorActividad(ModeloActividad modelo) {
		this.modelo = modelo;
	}
	
	//TODO: puede cambiar a void ?
	public List<Actividad> leerListaActividades() {
		return modelo.obtenerActividadesDelUsuario(27);
	}
	
	public void eliminarActividad(int idActividad) {
		modelo.eliminarActividad(idActividad);
	}
	
}
