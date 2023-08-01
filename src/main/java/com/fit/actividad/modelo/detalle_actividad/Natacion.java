package com.fit.actividad.modelo.detalle_actividad;

import com.fit.actividad.modelo.Actividad;

public class Natacion extends Actividad {

	public static final int TAMANIO_MAXIMO_ESTILO_NATACION = 51;

	private float distancia;
	
	private String estiloNatacion;
	
	public Natacion(int id, float distancia, String estiloNatacion) {
		super(id);
		this.distancia = distancia;
		this.estiloNatacion = estiloNatacion;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	public String getEstiloNatacion() {
		return estiloNatacion;
	}

	public void setEstiloNatacion(String estiloNatacion) {
		this.estiloNatacion = estiloNatacion;
	}

	@Override
	public String toString() {
		return "Natacion [distancia=" + distancia + ", estiloNatacion=" + estiloNatacion + ", idRegistro= " + super.getId() + "]";
	}

	@Override
	public boolean guardarEnDB_AsociadoConRegistro(int registroId) {
		return daoDetalleActividad.guardarNatacion(registroId, this);
	}
}
