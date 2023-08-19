package com.fit.actividad.controlador;

import java.util.List;

import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.ModeloActividad;

public class ControladorActividad {

	private ModeloActividad modelo;

	public ControladorActividad(ModeloActividad modelo) {
		this.modelo = modelo;
	}
	
	public void leerListaActividades() {
		List<Actividad> actividades = modelo.obtenerActividadesDelUsuario(27);
		for(Actividad actividad: actividades) System.out.println(actividad);
		System.out.println("Cantidad actividades: " + actividades.size());
	}
	
	public void eliminarActividad(int idActividad) {
		modelo.eliminarActividad(idActividad);
	}
	
}
