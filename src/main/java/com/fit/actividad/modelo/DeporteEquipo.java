package com.fit.actividad.modelo;

public class DeporteEquipo extends DetalleActividad{
	
	public static final int TAMANIO_MAXIMO_NOMBRE_DEPORTE = 20;
	
	public static final int TAMANIO_MAXIMO_NOMBRE_EQUIPOS = 20;
	
	public static final int TAMANIO_MAXIMO_RESULTADO_PARTIDO = 20;

	public static final String MENSAJE_TAMANIO_MAXIMO_NOMBRE_DEPORTE = "Máximo " + TAMANIO_MAXIMO_NOMBRE_DEPORTE + " caracteres";
	
	public static final String MENSAJE_TAMANIO_MAXIMO_NOMBRE_EQUIPOS = "Máximo " + TAMANIO_MAXIMO_NOMBRE_EQUIPOS+ " caracteres";

	public static final String MENSAJE_TAMANIO_MAXIMO_RESULTADO_PARTIDO = "Máximo " + TAMANIO_MAXIMO_RESULTADO_PARTIDO + " caracteres";
	
	private String nombreDeporte;
	
	private String nombreEquipos;

	private String resultadoDelPartido;
	
	public DeporteEquipo(int id, String nombreDeporte, String nombreEquipos, String resultadoDelPartido) {
		super(id);
		this.nombreDeporte = nombreDeporte;
		this.nombreEquipos = nombreEquipos;
		this.resultadoDelPartido = resultadoDelPartido;
	}

	public String getNombreDeporte() {
		return nombreDeporte;
	}

	public void setNombreDeporte(String nombreDeporte) {
		this.nombreDeporte = nombreDeporte;
	}

	public String getNombreEquipos() {
		return nombreEquipos;
	}

	public void setNombreEquipos(String nombreEquipos) {
		this.nombreEquipos = nombreEquipos;
	}

	public String getResultadoDelPartido() {
		return resultadoDelPartido;
	}

	public void setResultadoDelPartido(String resultadoDelPartido) {
		this.resultadoDelPartido = resultadoDelPartido;
	}

	@Override
	public String toString() {
		return "DeporteEquipo [nombreDeporte=" + nombreDeporte + ", nombreEquipos=" + nombreEquipos
				+ ", resultadoDelPartido=" + resultadoDelPartido + " id=" + super.getId() + "]";
	}

	@Override
	public boolean guardarEnDB_AsociadoConRegistro(int registroId) {
		return daoDetalleActividad.guardarDeporteEquipo(registroId, this);
	}
}
