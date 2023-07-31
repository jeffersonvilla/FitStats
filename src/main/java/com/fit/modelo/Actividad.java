package com.fit.modelo;

import com.fit.dao.DaoDetalleActividad;

public abstract class Actividad {

	protected DaoDetalleActividad daoDetalleActividad;
	
	private int id;

	public Actividad(int id) {
		super();
		this.id = id;
		this.daoDetalleActividad = new DaoDetalleActividad();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Actividad [id=" + id + "]";
	}

	public abstract boolean guardarEnDB_AsociadoConRegistro(int registroId);	
}
