package com.fit.actividad.controlador;

import java.sql.Time;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fit.actividad.modelo.Ciclismo;
import com.fit.actividad.modelo.ModeloActividad;

public class ControladorCiclismo implements Controlador {
	
	private static final Logger logger = LoggerFactory.getLogger(ControladorCiclismo.class);

	private final ModeloActividad modelo;
	
	public ControladorCiclismo(ModeloActividad modelo) {
		this.modelo = modelo;
	}
	
	@Override
	public void registrar(Timestamp fechaHora, Time duracion, String ubicacion, Object... args) {
		
		if(args.length < 2) {
			logger.error("Se deben pasar 5 argumentos al metodo registrar");
			return;
		}
		
		modelo.guardarActividad(new Ciclismo.CiclismoBuilder()
				.setDistancia(getDistancia(args))
				.setTipoBicicleta(getTipoBicicleta(args))
				.setFechaHora(fechaHora)
				.setDuracion(duracion)
				.setUbicacion(ubicacion)
				.build());
	}


	//TODO: de donde sale el id de la actividad?
	@Override
	public void actualizar(Timestamp fechaHora, Time duracion, String ubicacion, Object... args) {
		modelo.actualizarActividad(new Ciclismo.CiclismoBuilder()
				.setDistancia(getDistancia(args))
				.setTipoBicicleta(getTipoBicicleta(args))
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
	
	private String getTipoBicicleta(Object[] args) {
		if(args[1] instanceof String) return (String) args[1];
		logger.error("Quinto argumento debe ser de tipo String");
		return "";
	}
}
