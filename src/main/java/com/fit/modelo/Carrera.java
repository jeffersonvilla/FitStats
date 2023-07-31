package com.fit.modelo;

public class Carrera extends Actividad {

	private float distancia;
	
	private float ritmo_promedio;

	public Carrera(int id, float distancia, float ritmo_promedio) {
		super(id);
		this.distancia = distancia;
		this.ritmo_promedio = ritmo_promedio;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	public float getRitmo_promedio() {
		return ritmo_promedio;
	}

	public void setRitmo_promedio(float ritmo_promedio) {
		this.ritmo_promedio = ritmo_promedio;
	}

	@Override
	public String toString() {
		return "Carrera [distancia=" + distancia + ", ritmo_promedio=" + ritmo_promedio + ", getId()=" + getId()
				+ ", toString()=" + super.toString() + "]";
	}

	@Override
	public boolean guardarEnDB_AsociadoConRegistro(int registroId) {
		return super.daoDetalleActividad.guardarCarrera(registroId, this);
	}	
}
