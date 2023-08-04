package com.fit.actividad.modelo;

import java.sql.Time;
import java.sql.Timestamp;

import com.fit.actividad.dao.DaoActividad;

public class Actividad {

	private int id;
	private int userId;
	private int tipoActividadId;
	private Timestamp fechaHora;
	private Time duracion;
	private String ubicación;
	
	private DaoActividad daoActividad;
	
	public Actividad() {
		this.daoActividad = new DaoActividad();
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

	public int getTipoActividadId() {
		return tipoActividadId;
	}

	public void setTipoActividadId(int tipoActividadId) {
		this.tipoActividadId = tipoActividadId;
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
	
	@Override
	public String toString() {
		return "Actividad [id=" + id + ", userId=" + userId + ", tipoActividadId=" + tipoActividadId + ", fecha="
				+ fechaHora + ", duracion=" + duracion + ", ubicación=" + ubicación + "]";
	}

	public int guardarEnDB() {
		this.id = this.daoActividad.guardarActividad(this);
		return this.id;
	}
	
	public static class ActividadBuilder {
        private int id;
        private int userId;
        private int tipoActividadId;
        private Timestamp fechaHora;
        private Time duracion;
        private String ubicación;

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

        public ActividadBuilder setTipoActividadId(int tipoActividadId) {
            this.tipoActividadId = tipoActividadId;
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

        public Actividad build() {
            Actividad actividad = new Actividad();
            actividad.setId(this.id);
            actividad.setUserId(this.userId);
            actividad.setTipoActividadId(this.tipoActividadId);
            actividad.setFechaHora(this.fechaHora);
            actividad.setDuracion(this.duracion);
            actividad.setUbicación(this.ubicación);
            return actividad;
        }
	}
}
