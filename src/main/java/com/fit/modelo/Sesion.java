package com.fit.modelo;

import java.util.GregorianCalendar;

import com.fit.modelo.util.ConversorFecha;

public class Sesion {

	private int idSesion;
	private GregorianCalendar fechaInicio;
	private GregorianCalendar fechaFin;
	private int idUsuario;
	
	public Sesion(GregorianCalendar fechaInicio, int idUsuario) {
		super();
		this.fechaInicio = fechaInicio;
		this.idUsuario = idUsuario;
	}

	public Sesion(int idSesion, GregorianCalendar fechaInicio, int idUsuario) {
		super();
		this.idSesion = idSesion;
		this.fechaInicio = fechaInicio;
		this.idUsuario = idUsuario;
		this.fechaFin = null;
	}

	public Sesion(int idSesion, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, int idUsuario) {
		super();
		this.idSesion = idSesion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.idUsuario = idUsuario;
	}

	public int getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(int idSesion) {
		this.idSesion = idSesion;
	}

	public GregorianCalendar getFechaInicio() {
		return fechaInicio;
	}
	
	public String getFechaIncioComoString() {
		return ConversorFecha.convertirGregorianCalendarEnFormatoDateTimeMySql(this.fechaInicio);
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
	
	public String getFechaFinComoString() {
		return ConversorFecha.convertirGregorianCalendarEnFormatoDateTimeMySql(this.fechaFin);
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "Sesion [idSesion=" + idSesion 
				+ ", fechaInicio=" + getFechaIncioComoString() 
				+ ", fechaFin=" + getFechaFinComoString()
				+ ", idUsuario=" + idUsuario + "]";
	}
}
