package com.fit.actividad.controlador;


import java.sql.Time;
import java.sql.Timestamp;

import com.fit.actividad.modelo.Caminata;
import com.fit.actividad.modelo.ModeloActividad;

public class ControladorCaminata{

	private ModeloActividad modelo;
	
	public ControladorCaminata(ModeloActividad modelo) {
		this.modelo = modelo;
	}

	public void registrarCaminata(Timestamp fechaHora, Time duracion, String ubicacion, float distancia) {
		modelo.guardarActividad(new Caminata.CaminataBuilder()
				.setDistancia(distancia)
				.setFechaHora(fechaHora)
				.setDuracion(duracion)
				.setUbicacion(ubicacion)
				.build());
	}
	
	//TODO: de donde sale el id de la actividad?
	public void actualizarCaminata(Timestamp fechaHora, Time duracion, String ubicacion, float distancia) {
		modelo.actualizarActividad(new Caminata.CaminataBuilder()
				.setDistancia(distancia)
				.setFechaHora(fechaHora)
				.setDuracion(duracion)
				.setUbicacion(ubicacion)
				.build());
	}
}
