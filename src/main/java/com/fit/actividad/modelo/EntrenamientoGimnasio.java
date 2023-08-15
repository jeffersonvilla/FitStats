package com.fit.actividad.modelo;

public class EntrenamientoGimnasio extends DetalleActividad {

	private String ejerciciosRealizados;

	private String descansoEntreEjercicios;

	private String descansoEntreSeries;

	public static final int TAMANIO_MAXIMO_DESCANSO_ENTRE_EJERCICIOS = 50;

	public static final int TAMANIO_MAXIMO_DESCANSO_ENTRE_SERIES = 50;

	public static final String MENSAJE_TAMANIO_MAXIMO_DESCANSO_EJERCICIOS = "Máximo "
			+ TAMANIO_MAXIMO_DESCANSO_ENTRE_EJERCICIOS + " caracteres";

	public static final String MENSAJE_TAMANIO_MAXIMO_DESCANSO_SERIES = "Máximo " + TAMANIO_MAXIMO_DESCANSO_ENTRE_SERIES
			+ " caracteres";

	public EntrenamientoGimnasio(int id, String ejerciciosRealizados, String descansoEntreEjercicios,
			String descansoEntreSeries) {
		super(id);
		this.ejerciciosRealizados = ejerciciosRealizados;
		this.descansoEntreEjercicios = descansoEntreEjercicios;
		this.descansoEntreSeries = descansoEntreSeries;
	}

	public EntrenamientoGimnasio(String ejerciciosRealizados, String descansoEntreEjercicios,
			String descansoEntreSeries) {
		super();
		this.ejerciciosRealizados = ejerciciosRealizados;
		this.descansoEntreEjercicios = descansoEntreEjercicios;
		this.descansoEntreSeries = descansoEntreSeries;
	}

	public String getEjerciciosRealizados() {
		return ejerciciosRealizados;
	}

	public void setEjerciciosRealizados(String ejerciciosRealizados) {
		this.ejerciciosRealizados = ejerciciosRealizados;
	}

	public String getDescansoEntreEjercicios() {
		return descansoEntreEjercicios;
	}

	public void setDescansoEntreEjercicios(String descansoEntreEjercicios) {
		this.descansoEntreEjercicios = descansoEntreEjercicios;
	}

	public String getDescansoEntreSeries() {
		return descansoEntreSeries;
	}

	public void setDescansoEntreSeries(String descansoEntreSeries) {
		this.descansoEntreSeries = descansoEntreSeries;
	}

	@Override
	public String toString() {
		return "EntrenamientoGimnasio [ejerciciosRealizados=" + ejerciciosRealizados + ", descansoEntreEjercicios="
				+ descansoEntreEjercicios + ", descansoEntreSeries=" + descansoEntreSeries + " id=" + super.getId()
				+ "]";
	}

	@Override
	public int getTipoActividad() {
		return TipoActividad.ENTRENAMIENTO_GIMNASIO.getValor();
	}

	@Override
	public boolean atributosIguales(DetalleActividad detalle) {
		if(!(detalle instanceof EntrenamientoGimnasio)) return false;
		EntrenamientoGimnasio entrenamiento = (EntrenamientoGimnasio) detalle;
		return this.ejerciciosRealizados.equals(entrenamiento.getEjerciciosRealizados()) &&
				this.descansoEntreEjercicios.equals(entrenamiento.getDescansoEntreEjercicios()) &&
				this.descansoEntreSeries.equals(entrenamiento.getDescansoEntreSeries());
	}
}
