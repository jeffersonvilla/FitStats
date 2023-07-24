package com.fit.modelo;

import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

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
		return convertirFechaEnString(this.fechaInicio);
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
		return convertirFechaEnString(this.fechaFin);
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	private String convertirFechaEnString(GregorianCalendar fechaHora) {
		if(fechaHora == null) return "null";
		return String.format("%04d-%02d-%02d %02d:%02d:%02d", 
				fechaHora.get(GregorianCalendar.YEAR),
				fechaHora.get(GregorianCalendar.MONTH),
				fechaHora.get(GregorianCalendar.DAY_OF_MONTH),
				fechaHora.get(GregorianCalendar.HOUR_OF_DAY),
				fechaHora.get(GregorianCalendar.MINUTE),
				fechaHora.get(GregorianCalendar.SECOND));
	}

	@Override
	public String toString() {
		return "Sesion [idSesion=" + idSesion 
				+ ", fechaInicio=" + convertirFechaEnString(fechaInicio) 
				+ ", fechaFin=" + convertirFechaEnString(fechaFin)
				+ ", idUsuario=" + idUsuario + "]";
	}
}
