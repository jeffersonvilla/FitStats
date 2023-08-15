package com.fit.actividad.modelo;

public class Caminata extends DetalleActividad {

	private float distancia;

	public Caminata(int id, float distancia) {
		super(id);
		this.distancia = distancia;
	}
	
	public Caminata(float distancia) {
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

	@Override
	public int getTipoActividad() {
		return TipoActividad.CAMINATA.getValor();
	}

	@Override
	public boolean atributosIguales(DetalleActividad detalle) {
		if(!(detalle instanceof Caminata)) return false;
		return this.distancia == ((Caminata) detalle).getDistancia();
	}

}
