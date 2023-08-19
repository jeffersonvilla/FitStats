package com.fit.actividad.modelo;

import java.sql.Time;
import java.sql.Timestamp;

public class DeporteEquipo extends Actividad {

	public static final int TAMANIO_MAXIMO_NOMBRE_DEPORTE = 20;

	public static final int TAMANIO_MAXIMO_NOMBRE_EQUIPOS = 20;

	public static final int TAMANIO_MAXIMO_RESULTADO_PARTIDO = 20;

	public static final String MENSAJE_TAMANIO_MAXIMO_NOMBRE_DEPORTE = "Máximo " + TAMANIO_MAXIMO_NOMBRE_DEPORTE
			+ " caracteres";

	public static final String MENSAJE_TAMANIO_MAXIMO_NOMBRE_EQUIPOS = "Máximo " + TAMANIO_MAXIMO_NOMBRE_EQUIPOS
			+ " caracteres";

	public static final String MENSAJE_TAMANIO_MAXIMO_RESULTADO_PARTIDO = "Máximo " + TAMANIO_MAXIMO_RESULTADO_PARTIDO
			+ " caracteres";

	private String nombreDeporte;

	private String nombreEquipos;

	private String resultadoDelPartido;

	public DeporteEquipo(int id, int userId, Timestamp fechaHora, Time duracion, String ubicacion, String nombreDeporte, String nombreEquipos,
			String resultadoDelPartido) {
		super(id, userId, fechaHora, duracion, ubicacion);
		this.nombreDeporte = nombreDeporte;
		this.nombreEquipos = nombreEquipos;
		this.resultadoDelPartido = resultadoDelPartido;
	}

	public String getNombreDeporte() {
		return nombreDeporte;
	}

	public void setNombreDeporte(String nombreDeporte) {
		this.nombreDeporte = nombreDeporte;
	}

	public String getNombreEquipos() {
		return nombreEquipos;
	}

	public void setNombreEquipos(String nombreEquipos) {
		this.nombreEquipos = nombreEquipos;
	}

	public String getResultadoDelPartido() {
		return resultadoDelPartido;
	}

	public void setResultadoDelPartido(String resultadoDelPartido) {
		this.resultadoDelPartido = resultadoDelPartido;
	}

	@Override
	public int getTipoActividad() {
		return TipoActividad.DEPORTE_EQUIPO.getValor();
	}

	@Override
	public String getNombreTipoActividad() {
		return TipoActividad.DEPORTE_EQUIPO.getNombre();
	}

	@Override
	public String toString() {
		return super.toString() + "DeporteEquipo [nombreDeporte=" + nombreDeporte + ", nombreEquipos=" + nombreEquipos
				+ ", resultadoDelPartido=" + resultadoDelPartido + "]";
	}

	@Override
	public boolean atributosIguales(Actividad otraActividad) {
		if(! super.atributosIguales(otraActividad)) return false;
		if (!(otraActividad instanceof DeporteEquipo)) return false;
		DeporteEquipo deporte = (DeporteEquipo) otraActividad;
		return this.nombreDeporte.equals(deporte.getNombreDeporte())
				&& this.nombreEquipos.equals(deporte.getNombreEquipos())
				&& this.resultadoDelPartido.equals(deporte.getResultadoDelPartido());
	}
	
	public static class DeporteEquipoBuilder extends ActividadBuilder{

		private String nombreDeporte;

		private String nombreEquipos;

		private String resultadoDelPartido;
		
		public DeporteEquipoBuilder setNombreDeporte(String nombreDeporte) {
			this.nombreDeporte = nombreDeporte;
			return this;
		}
		
		public DeporteEquipoBuilder setNombreEquipos(String nombreEquipos) {
			this.nombreEquipos = nombreEquipos;
			return this;
		}
		
		public DeporteEquipoBuilder setResultadoDelPartido(String resultadoDelPartido) {
			this.resultadoDelPartido = resultadoDelPartido;
			return this;
		}
		
		@Override
		public Actividad build() {
			return new DeporteEquipo(id, userId, fechaHora, duracion, ubicacion, nombreDeporte, nombreEquipos, resultadoDelPartido);
		}
	}
}
