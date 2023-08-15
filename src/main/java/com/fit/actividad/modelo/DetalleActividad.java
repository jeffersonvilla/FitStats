package com.fit.actividad.modelo;

import com.fit.actividad.dao.DaoDetalleActividad;

public abstract class DetalleActividad {

	protected DaoDetalleActividad daoDetalleActividad;

	private int id;
	
	public DetalleActividad() {}

	public DetalleActividad(int id) {
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
	
	public abstract int getTipoActividad();
	
	public abstract boolean atributosIguales (DetalleActividad detalle);
}
