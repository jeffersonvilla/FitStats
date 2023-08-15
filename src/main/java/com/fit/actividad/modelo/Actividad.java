package com.fit.actividad.modelo;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public class Actividad {

	private int id;
	
	private int userId;
	
	private Timestamp fechaHora;
	
	private Time duracion;
	
	private String ubicación;
	
	private DetalleActividad detalleActividad;
	
	private int tipoActividad;

	private Actividad() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Time getDuracion() {
		return duracion;
	}

	public void setDuracion(Time duracion) {
		this.duracion = duracion;
	}

	public String getUbicación() {
		return ubicación;
	}

	public void setUbicación(String ubicación) {
		this.ubicación = ubicación;
	}

	public DetalleActividad getDetalleActividad() {
		return detalleActividad;
	}

	public void setDetalleActividad(DetalleActividad detalleActividad) {
		this.detalleActividad = detalleActividad;
	}

	public int getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(int tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	@Override
	public String toString() {
		return "Actividad [id=" + id + ", userId=" + userId + ", detalleActividadId=" + detalleActividad + ", fecha="
				+ fechaHora + ", duracion=" + duracion + ", ubicación=" + ubicación +", tipoActividad=" + tipoActividad +"]";
	}
	
    public boolean atributosIguales(Actividad actividad) {
    	return  this.fechaHora.equals(actividad.getFechaHora()) &&
                compararDuracion(actividad) &&
                this.ubicación.equals(actividad.getUbicación());
    }
    
    private boolean compararDuracion(Actividad actividad) {
    	Calendar calendarDB = Calendar.getInstance();
    	calendarDB.setTimeInMillis(this.duracion.getTime());
    	Calendar calendarVista = Calendar.getInstance();
    	calendarVista.setTimeInMillis(actividad.getDuracion().getTime());
    	return calendarDB.get(Calendar.HOUR_OF_DAY) == calendarVista.get(Calendar.HOUR_OF_DAY) &&
    			calendarDB.get(Calendar.MINUTE) == calendarVista.get(Calendar.MINUTE);
    }
    
	public static class ActividadBuilder {
		private int id;
		private int userId;
		private Timestamp fechaHora;
		private Time duracion;
		private String ubicación;
		private DetalleActividad detalleActividad;
		private int tipoActividad;

		public ActividadBuilder() {

		}

		public ActividadBuilder setId(int id) {
			this.id = id;
			return this;
		}

		public ActividadBuilder setUserId(int userId) {
			this.userId = userId;
			return this;
		}

		public ActividadBuilder setDetalleActividad(DetalleActividad detalleActividad) {
			this.detalleActividad = detalleActividad;
			return this;
		}

		public ActividadBuilder setFechaHora(Timestamp fechaHora) {
			this.fechaHora = fechaHora;
			return this;
		}

		public ActividadBuilder setDuracion(Time duracion) {
			this.duracion = duracion;
			return this;
		}

		public ActividadBuilder setUbicación(String ubicación) {
			this.ubicación = ubicación;
			return this;
		}

		public ActividadBuilder setTipoActividad(int tipoActividad) {
			this.tipoActividad = tipoActividad;
			return this;
		}
		
		public Actividad build() {
			Actividad actividad = new Actividad();
			actividad.setId(this.id);
			actividad.setUserId(this.userId);
			actividad.setDetalleActividad(this.detalleActividad);
			actividad.setFechaHora(this.fechaHora);
			actividad.setDuracion(this.duracion);
			actividad.setUbicación(this.ubicación);
			actividad.setTipoActividad(this.tipoActividad);
			return actividad;
		}
	}
}
