package com.fit.actividad.modelo;

import java.sql.Time;
import java.sql.Timestamp;

public class Estiramientos extends Actividad {
	
	public static final int TAMANIO_MAXIMO_TIPO_SESION = 50;

	public static final int TAMANIO_MAXIMO_NIVEL_DIFICULTAD = 20;

	public static final String MENSAJE_TAMANIO_MAXIMO_TIPO_SESION = "Máximo " + TAMANIO_MAXIMO_TIPO_SESION
			+ " caracteres";

	public static final String MENSAJE_TAMANIO_MAXIMO_NIVEL_DIFICULTAD = "Máximo " + TAMANIO_MAXIMO_NIVEL_DIFICULTAD
			+ " caracteres";

	private String tipoSesion;

	private String nivelDificultad;
	
	public Estiramientos(int id, int userId, Timestamp fechaHora, Time duracion, String ubicacion, 
			String tipoSesion, String nivelDificultad) {
		super(id, userId, fechaHora, duracion, ubicacion);
		this.tipoSesion = tipoSesion;
		this.nivelDificultad = nivelDificultad;
	}

	public String getTipoSesion() {
		return tipoSesion;
	}

	public void setTipoSesion(String tipoSesion) {
		this.tipoSesion = tipoSesion;
	}

	public String getNivelDificultad() {
		return nivelDificultad;
	}

	public void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}

	@Override
	public int getTipoActividad() {
		return TipoActividad.ESTIRAMIENTOS.getValor();
	}
	
	@Override
	public String toString() {
		return super.toString() + "Estiramientos [tipoSesion=" + tipoSesion + ", nivelDificultad=" + nivelDificultad + "]";
	}

	@Override
	public boolean atributosIguales(Actividad otraActividad) {
		if(! super.atributosIguales(otraActividad)) return false;
		if(!(otraActividad instanceof Estiramientos)) return false;
		Estiramientos estiramientos = (Estiramientos) otraActividad;
		return this.tipoSesion.equals(estiramientos.getTipoSesion()) &&
				this.nivelDificultad.equals(estiramientos.getNivelDificultad());
	}
	
	public static class EstiramientosBuilder extends ActividadBuilder{

		private String tipoSesion;

		private String nivelDificultad;

		public EstiramientosBuilder setTipoSesion(String tipoSesion) {
			this.tipoSesion = tipoSesion;
			return this;
		}
		
		public EstiramientosBuilder setNivelDificultad(String nivelDificultad) {
			this.nivelDificultad = nivelDificultad;
			return this;
		}
		
		@Override
		public Actividad build() {
			return new Estiramientos(id, userId, fechaHora, duracion, ubicacion, tipoSesion, nivelDificultad);
		}
	}
}
