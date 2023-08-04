package com.fit.actividad;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.fit.actividad.dao.DaoActividad;
import com.fit.actividad.dao.DaoTipoActividad;
import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.Caminata;
import com.fit.actividad.modelo.Ciclismo;
import com.fit.actividad.modelo.DeporteEquipo;
import com.fit.actividad.modelo.DetalleActividad;
import com.fit.actividad.modelo.Natacion;
import com.fit.actividad.vista.interfaces.VistaActividades;
import com.fit.principal.ControladorPrincipal;
import com.fit.usuario.login.Sesion;

public class ControladorActividad {
	
	private ControladorPrincipal controladorPrincipal;
	
	private VistaActividades vista;

	private DaoTipoActividad daoTipoActividad;
	
	private DaoActividad daoActividad;
	
	private Sesion sesion;
	
	private String[] tipoActividades;
	
	private final String FORMATO_DISTANCIA = "^(?=\\d{1,10}(\\.\\d{0,5})?$)\\d+(\\.\\d+)?$";
	
	private final String MENSAJE_VALIDACION_DISTANCIA = "Solo valores numéricos, máximo 10 digitos";
	
	private final String MENSAJE_VALIDACION_CAMPO_VACIO = "Debe llenar este campo";
	
	public ControladorActividad(ControladorPrincipal controladorPrincipal, Sesion sesion) {
		this.controladorPrincipal = controladorPrincipal;
		this.sesion = sesion;
		
		this.daoTipoActividad = new DaoTipoActividad();
		this.daoActividad = new DaoActividad();
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
	
	public void cerrarSesion() {
		this.controladorPrincipal.cerrarSesion(this.sesion);
	}

	public String[] getOpcionesTipoActividad() {
		this.tipoActividades = this.daoTipoActividad.getListaTipoActividades();
		return this.tipoActividades;
	}
	
	public List<Object[]> getListaActividades() {
		List<Actividad> actividades = daoActividad.leerListaActividadesPorUsuarioId(sesion.getIdUsuario());
		List<Object[]> actividadesObjectos = new ArrayList<>();
		if(actividades != null) {
			for(Actividad actividad: actividades) {
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
		return this.vista.getActividadSelecionada();
	}
}
