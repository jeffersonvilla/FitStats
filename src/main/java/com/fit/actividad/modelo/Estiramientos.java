package com.fit.actividad.modelo;

public class Estiramientos extends DetalleActividad {

	private String tipoSesion;

	private String nivelDificultad;

	public static final int TAMANIO_MAXIMO_TIPO_SESION = 50;

	public static final int TAMANIO_MAXIMO_NIVEL_DIFICULTAD = 20;

	public static final String MENSAJE_TAMANIO_MAXIMO_TIPO_SESION = "Máximo " + TAMANIO_MAXIMO_TIPO_SESION
			+ " caracteres";

	public static final String MENSAJE_TAMANIO_MAXIMO_NIVEL_DIFICULTAD = "Máximo " + TAMANIO_MAXIMO_NIVEL_DIFICULTAD
			+ " caracteres";

	public Estiramientos(int id, String tipoSesion, String nivelDificultad) {
		super(id);
		this.tipoSesion = tipoSesion;
		this.nivelDificultad = nivelDificultad;
	}

	public Estiramientos(String tipoSesion, String nivelDificultad) {
		super();
		this.tipoSesion = tipoSesion;
		this.nivelDificultad = nivelDificultad;
	}

	public String getTipoSesion() {
		return tipoSesion;
	}

	public void setTipoSesion(String tipoSesion) {
		this.tipoSesion = tipoSesion;
	}

	public String getNivelDificultad() {
		return nivelDificultad;
	}

	public void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}

	@Override
	public String toString() {
		return "Estiramientos [tipoSesion=" + tipoSesion + ", nivelDificultad=" + nivelDificultad + " id="
				+ super.getId() + "]";
	}

	@Override
	public int getTipoActividad() {
		return TipoActividad.ESTIRAMIENTOS.getValor();
	}

	@Override
	public boolean atributosIguales(DetalleActividad detalle) {
		if(!(detalle instanceof Estiramientos)) return false;
		Estiramientos estiramientos = (Estiramientos) detalle;
		return this.tipoSesion.equals(estiramientos.getTipoSesion()) &&
				this.nivelDificultad.equals(estiramientos.getNivelDificultad());
	}
}
