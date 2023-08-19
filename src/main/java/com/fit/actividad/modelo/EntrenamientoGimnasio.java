package com.fit.actividad.modelo;

import java.sql.Time;
import java.sql.Timestamp;

public class EntrenamientoGimnasio extends Actividad {

	public static final int TAMANIO_MAXIMO_DESCANSO_ENTRE_EJERCICIOS = 50;

	public static final int TAMANIO_MAXIMO_DESCANSO_ENTRE_SERIES = 50;

	public static final String MENSAJE_TAMANIO_MAXIMO_DESCANSO_EJERCICIOS = "Máximo "
			+ TAMANIO_MAXIMO_DESCANSO_ENTRE_EJERCICIOS + " caracteres";

	public static final String MENSAJE_TAMANIO_MAXIMO_DESCANSO_SERIES = "Máximo " + TAMANIO_MAXIMO_DESCANSO_ENTRE_SERIES
			+ " caracteres";
	
	private String ejerciciosRealizados;

	private String descansoEntreEjercicios;

	private String descansoEntreSeries;
	
	public EntrenamientoGimnasio(int id, int userId, Timestamp fechaHora, Time duracion, String ubicacion, 
			String ejerciciosRealizados, String descansoEntreEjercicios, String descansoEntreSeries) {
		super(id, userId, fechaHora, duracion, ubicacion);
		this.ejerciciosRealizados = ejerciciosRealizados;
		this.descansoEntreEjercicios = descansoEntreEjercicios;
		this.descansoEntreSeries = descansoEntreSeries;
	}

	public String getEjerciciosRealizados() {
		return ejerciciosRealizados;
	}

	public void setEjerciciosRealizados(String ejerciciosRealizados) {
		this.ejerciciosRealizados = ejerciciosRealizados;
	}

	public String getDescansoEntreEjercicios() {
		return descansoEntreEjercicios;
	}

	public void setDescansoEntreEjercicios(String descansoEntreEjercicios) {
		this.descansoEntreEjercicios = descansoEntreEjercicios;
	}

	public String getDescansoEntreSeries() {
		return descansoEntreSeries;
	}

	public void setDescansoEntreSeries(String descansoEntreSeries) {
		this.descansoEntreSeries = descansoEntreSeries;
	}

	@Override
	public int getTipoActividad() {
		return TipoActividad.ENTRENAMIENTO_GIMNASIO.getValor();
	}
	
	@Override
	public String toString() {
		return super.toString() + "EntrenamientoGimnasio [ejerciciosRealizados=" + ejerciciosRealizados
				+ ", descansoEntreEjercicios=" + descansoEntreEjercicios + ", descansoEntreSeries="
				+ descansoEntreSeries + "]";
	}

	@Override
	public boolean atributosIguales(Actividad otraActividad) {
		if(! super.atributosIguales(otraActividad)) return false;
		if (!(otraActividad instanceof EntrenamientoGimnasio)) return false;
		EntrenamientoGimnasio entrenamiento = (EntrenamientoGimnasio) otraActividad;
		return this.ejerciciosRealizados.equals(entrenamiento.getEjerciciosRealizados())
				&& this.descansoEntreEjercicios.equals(entrenamiento.getDescansoEntreEjercicios())
				&& this.descansoEntreSeries.equals(entrenamiento.getDescansoEntreSeries());
	}
	
	public static class EntrenamientoGimnasioBuilder extends ActividadBuilder{
		
		private String ejerciciosRealizados;

		private String descansoEntreEjercicios;

		private String descansoEntreSeries;

		public EntrenamientoGimnasioBuilder setEjerciciosRealizados(String ejerciciosRealizados) {
			this.ejerciciosRealizados = ejerciciosRealizados;
			return this;
		}
		
		public EntrenamientoGimnasioBuilder setDescansoEntreEjercicios(String descansoEntreEjercicios) {
			this.descansoEntreEjercicios = descansoEntreEjercicios;
			return this;
		}
		
		public EntrenamientoGimnasioBuilder setDescansoEntreSeries(String descansoEntreSeries) {
			this.descansoEntreSeries = descansoEntreSeries;
			return this;
		}
		
		@Override
		public Actividad build() {
			return new EntrenamientoGimnasio(id, userId, fechaHora, duracion, ubicacion, 
					ejerciciosRealizados, descansoEntreEjercicios, descansoEntreSeries);
		}
	}
}
