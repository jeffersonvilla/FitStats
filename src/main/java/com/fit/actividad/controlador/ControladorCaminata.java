package com.fit.actividad.controlador;

import java.sql.Time;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fit.actividad.modelo.Caminata;
import com.fit.actividad.modelo.ModeloActividad;

public class ControladorCaminata implements Controlador{
	
	private static final Logger logger = LoggerFactory.getLogger(ControladorCaminata.class);
	
	private final ModeloActividad modelo;
	
	public ControladorCaminata(ModeloActividad modelo) {
		this.modelo = modelo;
	}

	@Override
	public void registrar(Timestamp fechaHora, Time duracion, String ubicacion, Object... detallesCaminata) {
		modelo.guardarActividad(new Caminata.CaminataBuilder()
				.setDistancia(getDistancia(detallesCaminata))
				.setFechaHora(fechaHora)
				.setDuracion(duracion)
				.setUbicacion(ubicacion)
				.build());
	}
	
	//TODO: de donde sale el id de la actividad?
	@Override
	public void actualizar(Timestamp fechaHora, Time duracion, String ubicacion, Object... detallesCaminata) {
		modelo.actualizarActividad(new Caminata.CaminataBuilder()
				.setDistancia(getDistancia(detallesCaminata))
				.setFechaHora(fechaHora)
				.setDuracion(duracion)
				.setUbicacion(ubicacion)
				.build());
	} 
	
	private float getDistancia(Object[] args) {
		if(args[0] instanceof Float)
			return (Float) args[0];
		logger.error("Cuarto argumento debe ser de tipo Float");
		return -1; 
	}
}
