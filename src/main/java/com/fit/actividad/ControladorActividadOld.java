package com.fit.actividad;


public class ControladorActividadOld {

	

	/*
	

	

	

	*/
	
//	
//
//	
//	
//
//	private boolean validarDatosCiclismo(String distanciaString, String tipoBicicleta) {
//		boolean distanciaValida = validarDistancia(distanciaString);
//		boolean tipoBiciletaValido = validarTipoBicicleta(tipoBicicleta);
//		return distanciaValida && tipoBiciletaValido;
//	}
//
//	private boolean validarTipoBicicleta(String tipoBicicleta) {
//		if (tipoBicicleta.length() <= Ciclismo.TAMANIO_MAXIMO_TIPO_BICICLETA)
//			return true;
//		vista.validarTipoBicicleta(getActividadSelecionada(), Ciclismo.MENSAJE_TAMANIO_MAXIMO_TIPO_BICICLETA);
//		return false;
//	}
//
//	private boolean validarDatosNatacion(String distanciaString, String estiloNatacion) {
//		boolean distanciaValida = validarDistancia(distanciaString);
//		boolean estiloNatacionValido = validarEstiloNatacion(estiloNatacion);
//		return distanciaValida && estiloNatacionValido;
//	}
//
//	private boolean validarEstiloNatacion(String estiloNatacion) {
//		if (estiloNatacion.length() <= Natacion.TAMANIO_MAXIMO_ESTILO_NATACION)
//			return true;
//		vista.validarEstilosNatacion(getActividadSelecionada(), Natacion.MENSAJE_TAMANIO_MAXIMO_ESTILO_NATACION);
//		return false;
//	}
//
//	private boolean validarDatosDeporteEquipo(String nombreDeporte, String nombreEquipos, String resultadoDelPartido) {
//		boolean nombreDeporteValido = validarNombreDeporte(nombreDeporte);
//		boolean nombreEquiposValido = validarNombreEquipos(nombreEquipos);
//		boolean resultadoDelPartidoValido = validarResultadoDelPartido(resultadoDelPartido);
//		return nombreDeporteValido && nombreEquiposValido && resultadoDelPartidoValido;
//	}
//
//	private boolean validarNombreDeporte(String nombreDeporte) {
//		if (nombreDeporte.isEmpty()) {
//			vista.validarNombreDeporte(getActividadSelecionada(), MENSAJE_VALIDACION_CAMPO_VACIO);
//			return false;
//		} else if (nombreDeporte.length() <= DeporteEquipo.TAMANIO_MAXIMO_NOMBRE_DEPORTE)
//			return true;
//		vista.validarNombreDeporte(getActividadSelecionada(), DeporteEquipo.MENSAJE_TAMANIO_MAXIMO_NOMBRE_DEPORTE);
//		return false;
//	}
//
//	private boolean validarNombreEquipos(String duracionDelPartido) {
//		if (duracionDelPartido.length() <= DeporteEquipo.TAMANIO_MAXIMO_NOMBRE_EQUIPOS)
//			return true;
//		vista.validarNombreDeporte(getActividadSelecionada(), DeporteEquipo.MENSAJE_TAMANIO_MAXIMO_NOMBRE_EQUIPOS);
//		return false;
//	}
//
//	private boolean validarResultadoDelPartido(String resultadoDelPartido) {
//		if (resultadoDelPartido.length() <= DeporteEquipo.TAMANIO_MAXIMO_RESULTADO_PARTIDO)
//			return true;
//		vista.validarResultadoDelPartido(getActividadSelecionada(),
//				DeporteEquipo.MENSAJE_TAMANIO_MAXIMO_RESULTADO_PARTIDO);
//		return false;
//	}
//
//	private boolean validarDatosEntrenamientoGimnasio(String descansosEntreEjercicios, String descansosEntreSeries) {
//		boolean ejerciciosValido = validarDescansosEjercicios(descansosEntreEjercicios);
//		boolean seriesValido = validarDescansosSeries(descansosEntreSeries);
//		return ejerciciosValido && seriesValido;
//	}
//
//	private boolean validarDescansosEjercicios(String descansosEntreEjercicios) {
//		if (descansosEntreEjercicios.length() <= EntrenamientoGimnasio.TAMANIO_MAXIMO_DESCANSO_ENTRE_EJERCICIOS)
//			return true;
//		vista.validarDescansosEntreEjercicios(getActividadSelecionada(),
//				EntrenamientoGimnasio.MENSAJE_TAMANIO_MAXIMO_DESCANSO_EJERCICIOS);
//		return false;
//	}
//
//	private boolean validarDescansosSeries(String descansosEntreSeries) {
//		if (descansosEntreSeries.length() <= EntrenamientoGimnasio.TAMANIO_MAXIMO_DESCANSO_ENTRE_SERIES)
//			return true;
//		vista.validarDescansosEntreSeries(getActividadSelecionada(),
//				EntrenamientoGimnasio.MENSAJE_TAMANIO_MAXIMO_DESCANSO_SERIES);
//		return false;
//	}
//
//	private boolean validarDatosEstiramientos(String tipoSesion, String nivelDificultad) {
//		boolean tipoSesionValida = validarTipoSesion(tipoSesion);
//		boolean nivelDificultadValida = validarNivelDificultad(nivelDificultad);
//		return tipoSesionValida && nivelDificultadValida;
//	}
//
//	private boolean validarTipoSesion(String tipoSesion) {
//		if (tipoSesion.isEmpty()) {
//			vista.validarTipoSesionEstiramientos(getActividadSelecionada(), MENSAJE_VALIDACION_CAMPO_VACIO);
//			return false;
//		} else if (tipoSesion.length() <= Estiramientos.TAMANIO_MAXIMO_TIPO_SESION)
//			return true;
//		vista.validarTipoSesionEstiramientos(getActividadSelecionada(),
//				Estiramientos.MENSAJE_TAMANIO_MAXIMO_TIPO_SESION);
//		return false;
//	}
//
//	private boolean validarNivelDificultad(String nivelDificultad) {
//		if (nivelDificultad.length() <= Estiramientos.TAMANIO_MAXIMO_TIPO_SESION)
//			return true;
//		vista.validarNivelDificultadEstiramientos(getActividadSelecionada(),
//				Estiramientos.MENSAJE_TAMANIO_MAXIMO_NIVEL_DIFICULTAD);
//		return false;
//	}
//
//	private boolean validarDescripcion(String descripcion) {
//		if (descripcion.isEmpty()) {
//			vista.validarDescripcionOtraActividad(getActividadSelecionada(), MENSAJE_VALIDACION_CAMPO_VACIO);
//			return false;
//		} else if (descripcion.length() <= OtraActividad.TAMANIO_MAXIMO_DESCRIPCION)
//			return true;
//		vista.validarDescripcionOtraActividad(getActividadSelecionada(),
//				OtraActividad.MENSAJE_TAMANIO_MAXIMO_DESCRIPCION);
//		return false;
//	}
//
//	public void verDetallesActividadSeleccionada(int actividadSeleccionada) {
//		Actividad actividad = this.actividades.get(actividadSeleccionada);
//		switch (actividad.getTipoActividad()) {
//		case 1 -> {
//			Caminata caminata = this.daoDetalleActividad.leerDetallesCaminata(actividad.getId());
//			new VentanaDetallesCaminata(String.valueOf(caminata.getDistancia()));
//		}
//		case 2 -> {
//			Ciclismo ciclismo = this.daoDetalleActividad.leerDetallesCiclismo(actividad.getId());
//			new VentanaDetallesCiclismo(String.valueOf(ciclismo.getDistancia()), ciclismo.getTipo_bicicleta());
//		}
//		case 3 -> {
//			Natacion natacion = this.daoDetalleActividad.leerDetallesNatacion(actividad.getId());
//			new VentanaDetallesNatacion(String.valueOf(natacion.getDistancia()), natacion.getEstilosNatacion());
//		}
//		case 4 -> {
//			DeporteEquipo deporteEquipo = this.daoDetalleActividad.leerDetallesDeporteEquipo(actividad.getId());
//			new VentanaDetallesDeporteEquipo(deporteEquipo.getNombreDeporte(), deporteEquipo.getNombreEquipos(),
//					deporteEquipo.getResultadoDelPartido());
//		}
//		case 5 -> {
//			EntrenamientoGimnasio entrenamiento = this.daoDetalleActividad
//					.leerDetallesEntrenamientoGimnasio(actividad.getId());
//			new VentanaDetallesEntrenamientoGimnasio(entrenamiento.getEjerciciosRealizados(),
//					entrenamiento.getDescansoEntreEjercicios(), entrenamiento.getDescansoEntreSeries());
//		}
//		case 6 -> {
//			Estiramientos estiramientos = this.daoDetalleActividad.leerDetallesEstiramientos(actividad.getId());
//			new VentanaDetallesEstiramientos(estiramientos.getTipoSesion(), estiramientos.getNivelDificultad());
//		}
//		case 7 -> {
//			OtraActividad otraActividad = this.daoDetalleActividad.leerDetallesOtraActividad(actividad.getId());
//			new VentanaDetallesOtraActividad(otraActividad.getDescripcion());
//		}
//		default -> throw new IllegalArgumentException("Valor inesperado: " + actividadSeleccionada);
//		}
//	}
//
//	public void getInfoActividadActualizar(int actividadSeleccionada) {
//		Actividad actividad = actividades.get(actividadSeleccionada);
//		actividad.setDetalleActividad(daoDetalleActividad.leerDetalleActividad(actividad.getId(), actividad.getTipoActividad()));
//		vista.mostrarPanelActualizacionActividad(
//				new PanelFormularioActividad(actividad.getFechaHora(), actividad.getDuracion(), actividad.getUbicación()),
//				actividad.getTipoActividad(), getPanelFormularioDetalles(actividad.getDetalleActividad()));
//	}
//	

}
