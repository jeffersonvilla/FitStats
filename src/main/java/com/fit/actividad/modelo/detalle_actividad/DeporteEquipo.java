package com.fit.actividad.modelo.detalle_actividad;

import com.fit.actividad.modelo.Actividad;

public class DeporteEquipo extends Actividad{
	
	public static final int TAMANIO_MAXIMO_NOMBRE_DEPORTE = 50;
	
	public static final int TAMANIO_MAXIMO_RESULTADO_PARTIDO = 20;
	
	public static final String FORMATO_DURACION_DEL_PARTIDO = "^[1-9][0-9]*$";

	public static final String MENSAJE_TAMANIO_MAXIMO_NOMBRE_DEPORTE = "Máximo " + TAMANIO_MAXIMO_NOMBRE_DEPORTE + " caracteres";
	
	public static final String MENSAJE_TAMANIO_MAXIMO_RESULTADO_PARTIDO = "Máximo " + TAMANIO_MAXIMO_RESULTADO_PARTIDO + " caracteres";
	
	public static final String MENSAJE_VALIDACION_DURACION_DEL_PARTIDO = "Solo valores numericos mayores a cero";

	private String nombreDeporte;
	
	private String resultadoDelPartido;
	
	private int duracionDelPartido;
	
	public DeporteEquipo(int id, String nombreDeporte, String resultadoDelPartido, int duracionDelPartido) {
		super(id);
		this.nombreDeporte = nombreDeporte;
		this.resultadoDelPartido = resultadoDelPartido;
		this.duracionDelPartido = duracionDelPartido;
	}

	public String getNombreDeporte() {
		return nombreDeporte;
	}

	public void setNombreDeporte(String nombreDeporte) {
		this.nombreDeporte = nombreDeporte;
	}

	public String getResultadoDelPartido() {
		return resultadoDelPartido;
	}

	public void setResultadoDelPartido(String resultadoDelPartido) {
		this.resultadoDelPartido = resultadoDelPartido;
	}

	public int getDuracionDelPartido() {
		return duracionDelPartido;
	}

	public void setDuracionDelPartido(int duracionDelPartido) {
		this.duracionDelPartido = duracionDelPartido;
	}

	@Override
	public String toString() {
		return "DeporteEquipo [nombreDeporte=" + nombreDeporte + ", resultadoDelPartido=" + resultadoDelPartido
				+ ", duracionDelPartido=" + duracionDelPartido + ", id=  " + super.getId() + "]";
	}

	@Override
	public boolean guardarEnDB_AsociadoConRegistro(int registroId) {
		return daoDetalleActividad.guardarDeporteEquipo(registroId, this);
	}
}
