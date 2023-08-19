package com.fit.actividad.modelo;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public abstract class Actividad {

	private int id;
	
	private int userId;
	
	private Timestamp fechaHora;
	
	private Time duracion;
	
	private String ubicacion;

	public Actividad() {}

	public Actividad(int id, int userId, Timestamp fechaHora, Time duracion, String ubicacion) {
		this.id = id;
		this.userId = userId;
		this.fechaHora = fechaHora;
		this.duracion = duracion;
		this.ubicacion = ubicacion;
	}

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

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public abstract int getTipoActividad();
	
    @Override
	public String toString() {
		return "Actividad [id=" + id + ", userId=" + userId + ", fechaHora=" + fechaHora + ", duracion=" + duracion
				+ ", ubicacion=" + ubicacion + "]";
	}

	public boolean atributosIguales(Actividad actividad) {
    	return  this.fechaHora.equals(actividad.getFechaHora()) &&
                compararDuracion(actividad) &&
                this.ubicacion.equals(actividad.getUbicacion());
    }
  
    private boolean compararDuracion(Actividad actividad) {
    	Calendar calendarDB = Calendar.getInstance();
    	calendarDB.setTimeInMillis(this.duracion.getTime());
    	Calendar calendarVista = Calendar.getInstance();
    	calendarVista.setTimeInMillis(actividad.getDuracion().getTime());
    	return calendarDB.get(Calendar.HOUR_OF_DAY) == calendarVista.get(Calendar.HOUR_OF_DAY) &&
    			calendarDB.get(Calendar.MINUTE) == calendarVista.get(Calendar.MINUTE);
    }
  
	public static abstract class ActividadBuilder {
		protected int id;
		protected int userId;
		protected Timestamp fechaHora;
		protected Time duracion;
		protected String ubicacion;

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

		public ActividadBuilder setFechaHora(Timestamp fechaHora) {
			this.fechaHora = fechaHora;
			return this;
		}

		public ActividadBuilder setDuracion(Time duracion) {
			this.duracion = duracion;
			return this;
		}

		public ActividadBuilder setUbicacion(String ubicacion) {
			this.ubicacion = ubicacion;
			return this;
		}

		public abstract Actividad build();
	}
	
}
