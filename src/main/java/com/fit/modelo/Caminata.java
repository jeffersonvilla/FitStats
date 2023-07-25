package com.fit.modelo;

public class Caminata extends Actividad{

	private float distancia;

	public Caminata(int id, float distancia) {
		super(id);
		this.distancia = distancia;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	@Override
	public String toString() {
		return "Caminata [distancia=" + distancia + ", toString()=" + super.toString() + "]";
	}
	
	
}
