package com.fit.actividad.modelo;

public class Ciclismo extends DetalleActividad {

	public static final int TAMANIO_MAXIMO_TIPO_BICICLETA = 50;

	public static final String MENSAJE_TAMANIO_MAXIMO_TIPO_BICICLETA = "Máximo " + TAMANIO_MAXIMO_TIPO_BICICLETA
			+ " caracteres";

	private float distancia;

	private String tipo_bicicleta;

	public Ciclismo(int id, float distancia, String tipo_bicicleta) {
		super(id);
		this.distancia = distancia;
		this.tipo_bicicleta = tipo_bicicleta;
	}

	public Ciclismo(float distancia, String tipo_bicicleta) {
		super();
		this.distancia = distancia;
		this.tipo_bicicleta = tipo_bicicleta;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	public String getTipo_bicicleta() {
		return tipo_bicicleta;
	}

	public void setTipo_bicicleta(String tipo_bicicleta) {
		this.tipo_bicicleta = tipo_bicicleta;
	}

	@Override
	public String toString() {
		return "Ciclismo [distancia=" + distancia + ", tipo_bicicleta=" + tipo_bicicleta + ", id=" + super.getId()
				+ "]";
	}

	@Override
	public int getTipoActividad() {
		return TipoActividad.CICLISMO.getValor();
	}

	@Override
	public boolean atributosIguales(DetalleActividad detalle) {
		if(!(detalle instanceof Ciclismo)) return false;
		Ciclismo ciclismo = (Ciclismo) detalle;
		return this.distancia == ciclismo.getDistancia() &&
				this.tipo_bicicleta.equals(ciclismo.getTipo_bicicleta());
	}
}
