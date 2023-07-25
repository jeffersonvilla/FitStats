package com.fit.modelo;

public abstract class Actividad {

	private int id;

	public Actividad(int id) {
		super();
		this.id = id;
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
	
}
