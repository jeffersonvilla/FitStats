package com.fit.actividad;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.fit.actividad.dao.DaoActividad;
import com.fit.actividad.dao.DaoDetalleActividad;
import com.fit.actividad.dao.DaoTipoActividad;
import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.Caminata;
import com.fit.actividad.modelo.Ciclismo;
import com.fit.actividad.modelo.DeporteEquipo;
import com.fit.actividad.modelo.DetalleActividad;
import com.fit.actividad.modelo.EntrenamientoGimnasio;
import com.fit.actividad.modelo.Estiramientos;
import com.fit.actividad.modelo.Natacion;
import com.fit.actividad.modelo.OtraActividad;
import com.fit.actividad.vista.frameDetalles.VentanaDetallesCaminata;
import com.fit.actividad.vista.frameDetalles.VentanaDetallesCiclismo;
import com.fit.actividad.vista.frameDetalles.VentanaDetallesNatacion;
import com.fit.actividad.vista.frameDetalles.VentanaDetallesOtraActividad;
import com.fit.actividad.vista.frameDetalles.VentanaDetallesDeporteEquipo;
import com.fit.actividad.vista.frameDetalles.VentanaDetallesEntrenamientoGimnasio;
import com.fit.actividad.vista.frameDetalles.VentanaDetallesEstiramientos;
import com.fit.actividad.vista.interfaces.VistaActividades;
import com.fit.actividad.vista.panelFormulario.PanelFormularioActividad;
import com.fit.principal.ControladorPrincipal;
import com.fit.usuario.login.Sesion;

public class ControladorActividad {
	
	private ControladorPrincipal controladorPrincipal;
	
	private VistaActividades vista;

	private DaoTipoActividad daoTipoActividad;
	
	private DaoDetalleActividad daoDetalleActividad;
	
	private DaoActividad daoActividad;
	
	private Sesion sesion;
	
	private String[] tipoActividades;
	
	private List<Actividad> actividades;
	
	private final String FORMATO_DISTANCIA = "^(?=\\d{1,10}(\\.\\d{0,5})?$)\\d+(\\.\\d+)?$";
	
	private final String MENSAJE_VALIDACION_DISTANCIA = "Solo valores numéricos, máximo 10 digitos";
	
	private final String MENSAJE_VALIDACION_CAMPO_VACIO = "Debe llenar este campo";
	
	public ControladorActividad(ControladorPrincipal controladorPrincipal, Sesion sesion) {
		this.controladorPrincipal = controladorPrincipal;
		this.sesion = sesion;
		
		this.daoTipoActividad = new DaoTipoActividad();
		this.daoActividad = new DaoActividad();
		this.daoDetalleActividad = new DaoDetalleActividad();
	}
	
	public void setVista(VistaActividades vista) {
		this.vista = vista;
	}
	
	public void registrarCaminata(Timestamp fecha, Time duracion, String ubicacion, String distancia) {
		if(validarFecha(fecha)) {			
			if(validarDistancia(distancia))
				registrarActividad(
						getActividad(fecha, duracion, ubicacion),
						new Caminata(getActividadSelecionada() + 1, Float.parseFloat(distancia))
						);
		}
	}

	public void registrarCiclismo(Timestamp fecha, Time duracion, String  ubicacion, String distancia, String tipoBicicleta) {
		if(validarFecha(fecha)) {			
			if(validarDatosCiclismo(distancia, tipoBicicleta)) {
				registrarActividad(
						getActividad(fecha, duracion, ubicacion),
						new Ciclismo(getActividadSelecionada() + 1, Float.parseFloat(distancia), tipoBicicleta)
						);
			}
		}
	}
	
	public void registrarNatacion(Timestamp fecha, Time duracion, String  ubicacion, String distancia, String estiloNatacion) {
		if(validarFecha(fecha)) {			
			if(validarDatosNatacion(distancia, estiloNatacion)) {
				registrarActividad(
						getActividad(fecha, duracion, ubicacion),
						new Natacion(getActividadSelecionada() + 1, Float.parseFloat(distancia), estiloNatacion)
						);
			}
		}
	}

	public void registrarDeporteEquipo(Timestamp fecha, Time duracion, String  ubicacion, String nombreDeporte, String nombreEquipos, String resultadoDelPartido) {
		if(validarFecha(fecha)) {
			if(validarDatosDeporteEquipo(nombreDeporte, nombreEquipos, resultadoDelPartido))
				registrarActividad(
						getActividad(fecha, duracion, ubicacion),
						new DeporteEquipo(getActividadSelecionada(), nombreDeporte, nombreEquipos, resultadoDelPartido)
						);
		}
	}
	
	public void registrarEntrenamientoGimnasio(Timestamp fecha, Time duracion, String  ubicacion, String ejerciciosRealizados, String descansosEntreEjercicios, String descansosEntreSeries) {
		if(validarFecha(fecha)) {
			if(validarDatosEntrenamientoGimnasio(descansosEntreEjercicios, descansosEntreSeries)) {				
				registrarActividad(
						getActividad(fecha, duracion, ubicacion), 
						new EntrenamientoGimnasio(getActividadSelecionada() + 1, ejerciciosRealizados, descansosEntreEjercicios, descansosEntreSeries)
						);
			}
		}
	}
	
	public void registrarEstiramientos(Timestamp fecha, Time duracion, String  ubicacion, String tipoSesion, String nivelDificultad) {
		if(validarFecha(fecha)) {
			if(validarDatosEstiramientos(tipoSesion, nivelDificultad)) {
				registrarActividad(
						getActividad(fecha, duracion, ubicacion), 
						new Estiramientos(getActividadSelecionada() + 1, tipoSesion, nivelDificultad)
						);
			}
		}
	}
	
	public void registrarOtraActividad(Timestamp fecha, Time duracion, String  ubicacion, String descripcion) {
		if(validarFecha(fecha)) {
			if(validarDescripcion(descripcion)) {
				registrarActividad(
						getActividad(fecha, duracion, ubicacion), 
						new OtraActividad(getActividadSelecionada() + 1, descripcion)
						);
			}
		}
	}

	private Actividad getActividad(Timestamp fecha, Time duracion, String  ubicacion) {
		return new Actividad
				.ActividadBuilder()
				.setUserId(this.sesion.getIdUsuario())
				.setTipoActividadId(getActividadSelecionada() + 1)
				.setFechaHora(fecha)
				.setDuracion(duracion)
				.setUbicación(ubicacion)
				.build();
	}
	
	private void registrarActividad(Actividad actividad, DetalleActividad detalleActividad) {
		int idActividad = actividad.guardarEnDB();
		if(idActividad != -1 && detalleActividad.guardarEnDB_AsociadoConRegistro(idActividad)) {
			vista.limpiarCamposTexto(getActividadSelecionada());
			vista.actualizarListaActividades(getListaActividades());
			vista.verListaActividades();
			JOptionPane.showMessageDialog(null, "Registrado con exito!");
		}else JOptionPane.showMessageDialog(null, "Ha ocurrido un error guardando.");	
	}

	private boolean validarFecha(Timestamp fecha) {
		if(fecha != null) return true;
		vista.validarFechaActividad(MENSAJE_VALIDACION_CAMPO_VACIO);
		return false;
	}
	
	private boolean validarDistancia(String distancia) {
		if(!distancia.isEmpty() && Pattern.matches(FORMATO_DISTANCIA, distancia)) return true;
		vista.validarDistancia(getActividadSelecionada(), MENSAJE_VALIDACION_DISTANCIA);
		return false;
	}
	
	private boolean validarDatosCiclismo(String distanciaString, String tipoBicicleta) {
		boolean distanciaValida = validarDistancia(distanciaString);
		boolean tipoBiciletaValido = validarTipoBicicleta(tipoBicicleta);
		return distanciaValida && tipoBiciletaValido;
	}
	
	private boolean validarTipoBicicleta(String tipoBicicleta) {
		if(tipoBicicleta.length() <= Ciclismo.TAMANIO_MAXIMO_TIPO_BICICLETA) return true;
		vista.validarTipoBicicleta(getActividadSelecionada(), Ciclismo.MENSAJE_TAMANIO_MAXIMO_TIPO_BICICLETA);
		return false;
	}
	
	private boolean validarDatosNatacion(String distanciaString, String estiloNatacion) {
		boolean distanciaValida = validarDistancia(distanciaString);
		boolean estiloNatacionValido = validarEstiloNatacion(estiloNatacion);
		return distanciaValida && estiloNatacionValido;
	}
	
	private boolean validarEstiloNatacion(String estiloNatacion) {
		if(estiloNatacion.length() <= Natacion.TAMANIO_MAXIMO_ESTILO_NATACION) return true;
		vista.validarEstilosNatacion(getActividadSelecionada(), Natacion.MENSAJE_TAMANIO_MAXIMO_ESTILO_NATACION);
		return false;
	}
	
	private boolean validarDatosDeporteEquipo(String nombreDeporte, String nombreEquipos, String resultadoDelPartido) {
		boolean nombreDeporteValido = validarNombreDeporte(nombreDeporte);
		boolean nombreEquiposValido = validarNombreEquipos(nombreEquipos);
		boolean resultadoDelPartidoValido = validarResultadoDelPartido(resultadoDelPartido);
		return nombreDeporteValido && nombreEquiposValido && resultadoDelPartidoValido;
	} 

	private boolean validarNombreDeporte(String nombreDeporte) {
		if(nombreDeporte.isEmpty()) {
			vista.validarNombreDeporte(getActividadSelecionada(), MENSAJE_VALIDACION_CAMPO_VACIO);
			return false;
		} else if(nombreDeporte.length() <= DeporteEquipo.TAMANIO_MAXIMO_NOMBRE_DEPORTE) return true;
		vista.validarNombreDeporte(getActividadSelecionada(), DeporteEquipo.MENSAJE_TAMANIO_MAXIMO_NOMBRE_DEPORTE);
		return false;
	}

	private boolean validarNombreEquipos(String duracionDelPartido) {
		if(duracionDelPartido.length() <= DeporteEquipo.TAMANIO_MAXIMO_NOMBRE_EQUIPOS) return true;
		vista.validarNombreDeporte(getActividadSelecionada(), DeporteEquipo.MENSAJE_TAMANIO_MAXIMO_NOMBRE_EQUIPOS);
		return false;
	}

	private boolean validarResultadoDelPartido(String resultadoDelPartido) {
		if(resultadoDelPartido.length() <= DeporteEquipo.TAMANIO_MAXIMO_RESULTADO_PARTIDO) return true;
		vista.validarResultadoDelPartido(getActividadSelecionada(), DeporteEquipo.MENSAJE_TAMANIO_MAXIMO_RESULTADO_PARTIDO);
		return false;
	}
	
	private boolean validarDatosEntrenamientoGimnasio(String descansosEntreEjercicios, String descansosEntreSeries) {
		boolean ejerciciosValido = validarDescansosEjercicios(descansosEntreEjercicios);
		boolean seriesValido = validarDescansosSeries(descansosEntreSeries);
		return ejerciciosValido && seriesValido;
	}
	
	private boolean validarDescansosEjercicios(String descansosEntreEjercicios) {
		if(descansosEntreEjercicios.length() <= EntrenamientoGimnasio.TAMANIO_MAXIMO_DESCANSO_ENTRE_EJERCICIOS) return true;
		vista.validarDescansosEntreEjercicios(getActividadSelecionada(), EntrenamientoGimnasio.MENSAJE_TAMANIO_MAXIMO_DESCANSO_EJERCICIOS);
		return false;
	}

	private boolean validarDescansosSeries(String descansosEntreSeries) {
		if(descansosEntreSeries.length() <= EntrenamientoGimnasio.TAMANIO_MAXIMO_DESCANSO_ENTRE_SERIES) return true;
		vista.validarDescansosEntreSeries(getActividadSelecionada(), EntrenamientoGimnasio.MENSAJE_TAMANIO_MAXIMO_DESCANSO_SERIES);
		return false;
	}
	
	private boolean validarDatosEstiramientos(String tipoSesion, String nivelDificultad) {
		boolean tipoSesionValida = validarTipoSesion(tipoSesion);
		boolean nivelDificultadValida = validarNivelDificultad(nivelDificultad);
		return tipoSesionValida && nivelDificultadValida;
	}

	private boolean validarTipoSesion(String tipoSesion) {
		if(tipoSesion.isEmpty()) {
			vista.validarTipoSesionEstiramientos(getActividadSelecionada(), MENSAJE_VALIDACION_CAMPO_VACIO);
			return false;
		} else if(tipoSesion.length() <= Estiramientos.TAMANIO_MAXIMO_TIPO_SESION) return true;
		vista.validarTipoSesionEstiramientos(getActividadSelecionada(), Estiramientos.MENSAJE_TAMANIO_MAXIMO_TIPO_SESION);
		return false;
	}

	private boolean validarNivelDificultad(String nivelDificultad) {
		if(nivelDificultad.length() <= Estiramientos.TAMANIO_MAXIMO_TIPO_SESION) return true;
		vista.validarNivelDificultadEstiramientos(getActividadSelecionada(), Estiramientos.MENSAJE_TAMANIO_MAXIMO_NIVEL_DIFICULTAD);
		return false;
	}
	
	private boolean validarDescripcion(String descripcion) {
		if(descripcion.isEmpty()) {
			vista.validarDescripcionOtraActividad(getActividadSelecionada(), MENSAJE_VALIDACION_CAMPO_VACIO);
			return false;
		} else if(descripcion.length() <= OtraActividad.TAMANIO_MAXIMO_DESCRIPCION) return true;
		vista.validarDescripcionOtraActividad(getActividadSelecionada(), OtraActividad.MENSAJE_TAMANIO_MAXIMO_DESCRIPCION);
		return false;
	}

	public void verDetallesActividadSeleccionada(int actividadSeleccionada) {
		Actividad actividad = this.actividades.get(actividadSeleccionada);
		switch (actividad.getTipoActividadId()) {
		case 1 -> {
			Caminata caminata = this.daoDetalleActividad.leerDetallesCaminata(actividad.getId());
			new VentanaDetallesCaminata(String.valueOf(caminata.getDistancia()));
		}
		case 2 -> {
			Ciclismo ciclismo = this.daoDetalleActividad.leerDetallesCiclismo(actividad.getId());
			new VentanaDetallesCiclismo(String.valueOf(ciclismo.getDistancia()), ciclismo.getTipo_bicicleta());
		}
		case 3 -> {
			Natacion natacion = this.daoDetalleActividad.leerDetallesNatacion(actividad.getId());
			new VentanaDetallesNatacion(String.valueOf(natacion.getDistancia()), natacion.getEstilosNatacion());
		}
		case 4 -> {
			DeporteEquipo deporteEquipo = this.daoDetalleActividad.leerDetallesDeporteEquipo(actividad.getId());
			new VentanaDetallesDeporteEquipo(deporteEquipo.getNombreDeporte(), deporteEquipo.getNombreEquipos(), deporteEquipo.getResultadoDelPartido());
		}
		case 5 -> {
			EntrenamientoGimnasio entrenamiento = this.daoDetalleActividad.leerDetallesEntrenamientoGimnasio(actividad.getId());
			new VentanaDetallesEntrenamientoGimnasio(entrenamiento.getEjerciciosRealizados(), entrenamiento.getDescansoEntreEjercicios(), entrenamiento.getDescansoEntreSeries());
		}
		case 6 -> {
			Estiramientos estiramientos = this.daoDetalleActividad.leerDetallesEstiramientos(actividad.getId());
			new VentanaDetallesEstiramientos(estiramientos.getTipoSesion(), estiramientos.getNivelDificultad());
		}
		case 7 -> {
			OtraActividad otraActividad = this.daoDetalleActividad.leerDetallesOtraActividad(actividad.getId());
			new VentanaDetallesOtraActividad(otraActividad.getDescripcion());
		}
		default ->
			throw new IllegalArgumentException("Valor inesperado: " + actividadSeleccionada);
		}
	}
	
	public void actualizarActividad(int actividadSeleccionada) {
		Actividad actividad = actividades.get(actividadSeleccionada);
		vista.mostrarPanelActualizacionActividad(new PanelFormularioActividad(actividad.getFechaHora()));
	}
	
	public void eliminarActividad(int actividadSeleccionada) {
		int opcionElegida = JOptionPane.showConfirmDialog(null, "Seguro desea eliminar esta actividad?", "Eliminar actividad", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if(opcionElegida == JOptionPane.YES_OPTION) {
			Actividad actividad = this.actividades.get(actividadSeleccionada);
			if(this.daoActividad.eliminarActividadPorId(actividad.getId())) 
				vista.actualizarListaActividades(getListaActividades());
				JOptionPane.showMessageDialog(null, "Actividad eliminada.");			
		}
 	}
	
	public void cerrarSesion() {
		this.controladorPrincipal.cerrarSesion(this.sesion);
	}

	public String[] getOpcionesTipoActividad() {
		this.tipoActividades = this.daoTipoActividad.getListaTipoActividades();
		return this.tipoActividades;
	}
	
	public List<Object[]> getListaActividades() {
		this.actividades = daoActividad.leerListaActividadesPorUsuarioId(sesion.getIdUsuario());
		List<Object[]> actividadesObjectos = new ArrayList<>();
		if(actividades != null) {
			for(Actividad actividad: this.actividades) {
				actividadesObjectos.add(new Object[] {
						this.tipoActividades[actividad.getTipoActividadId()-1],
						actividad.getFechaHora(),
						actividad.getDuracion(),
						actividad.getUbicación()
				});
			}
		}
		return actividadesObjectos;
	}
	
	private int getActividadSelecionada() {
		return this.vista.getTipoActividadSelecionada();
	}
}
