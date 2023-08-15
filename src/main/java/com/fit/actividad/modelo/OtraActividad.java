package com.fit.actividad.modelo;

public class OtraActividad extends DetalleActividad {

	private String descripcion;

	public static final int TAMANIO_MAXIMO_DESCRIPCION = 150;

	public static final String MENSAJE_TAMANIO_MAXIMO_DESCRIPCION = "Máximo " + TAMANIO_MAXIMO_DESCRIPCION
			+ " caracteres";

	public OtraActividad(int id, String descripcion) {
		super(id);
		this.descripcion = descripcion;
	}

	public OtraActividad(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "OtraActividad [descripcion=" + descripcion + " id=" + super.getId() + "]";
	}

	@Override
	public int getTipoActividad() {
		return TipoActividad.OTRA_ACTIVIDAD.getValor();
	}
}
