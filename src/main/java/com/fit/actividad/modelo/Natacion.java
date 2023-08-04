package com.fit.actividad.modelo;

public class Natacion extends DetalleActividad {

	public static final int TAMANIO_MAXIMO_ESTILO_NATACION = 50;
	
	public static final String MENSAJE_TAMANIO_MAXIMO_ESTILO_NATACION = "Máximo " + TAMANIO_MAXIMO_ESTILO_NATACION + " caracteres";

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
