package com.fit.modelo;

import java.util.GregorianCalendar;

import com.fit.dao.DaoRegistro;
import com.fit.modelo.util.ConversorFecha;

public class Registro {

	private int id;
	private int userId;
	private int actividadId;
	private GregorianCalendar fechaInicio;
	private GregorianCalendar fechaFin;
	
	private DaoRegistro daoRegistro;
	
	public Registro(int userId, int actividadId, GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
		super();
		this.userId = userId;
		this.actividadId = actividadId;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		
		this.daoRegistro = new DaoRegistro();
	}

	public Registro(int id, int userId, int actividadId, GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
		super();
		this.id = id;
		this.userId = userId;
		this.actividadId = actividadId;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		
		this.daoRegistro = new DaoRegistro();
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

	public int getActividadId() {
		return actividadId;
	}

	public void setActividadId(int actividadId) {
		this.actividadId = actividadId;
	}

	public GregorianCalendar getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(GregorianCalendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public GregorianCalendar getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(GregorianCalendar fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public String getFechaInicioComoString() {
		return ConversorFecha.convertirGregorianCalendarEnFormatoDateTimeMySql(this.fechaInicio);
	}
	
	public String getFechaFinComoString() {
		return ConversorFecha.convertirGregorianCalendarEnFormatoDateTimeMySql(this.fechaFin);
	}

	@Override
	public String toString() {
		return "Registro [id=" + id 
				+ ", userId=" + userId 
				+ ", actividadId=" + actividadId 
				+ ", fechaInicio=" + getFechaInicioComoString()
				+ ", fechaFin=" + getFechaFinComoString()
				+ "]";
	}
	
	public boolean guardarEnDB() {
		return this.daoRegistro.guardarRegistro(this);
	}
	
	public int getIdFromDB() {
		this.id = this.daoRegistro.getRegistroId(this);
		return this.id;
	}
	
}
