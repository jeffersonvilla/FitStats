package com.fit.actividad.modelo;

import java.sql.Time;
import java.sql.Timestamp;

public class OtraActividad extends Actividad {

	public static final int TAMANIO_MAXIMO_DESCRIPCION = 150;

	public static final String MENSAJE_TAMANIO_MAXIMO_DESCRIPCION = "Máximo " + TAMANIO_MAXIMO_DESCRIPCION
			+ " caracteres";

	private String descripcion;

	public OtraActividad(int id, int userId, Timestamp fechaHora, Time duracion, String ubicacion, String descripcion) {
		super(id, userId, fechaHora, duracion, ubicacion);
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int getTipoActividad() {
		return TipoActividad.OTRA_ACTIVIDAD.getValor();
	}

	@Override
	public String getNombreTipoActividad() {
		return TipoActividad.OTRA_ACTIVIDAD.getNombre();
	}

	@Override
	public String toString() {
		return super.toString() + "OtraActividad [descripcion=" + descripcion + "]";
	}

	@Override
	public boolean atributosIguales(Actividad detalle) {
		if(! super.atributosIguales(detalle)) return false;
		if(!(detalle instanceof OtraActividad)) return false;
		OtraActividad otraActividad = (OtraActividad) detalle;
		return this.descripcion.equals(otraActividad.getDescripcion());
	}
	
	public static class OtraActividadBuilder extends ActividadBuilder{

		private String descripcion;

		public OtraActividadBuilder setDescripcion(String descripcion) {
			this.descripcion = descripcion;
			return this;
		}
		
		@Override
		public Actividad build() {
			return new OtraActividad(id, userId, fechaHora, duracion, ubicacion, descripcion);
		}
	}
}
