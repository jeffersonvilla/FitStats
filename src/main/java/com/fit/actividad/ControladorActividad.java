package com.fit.actividad;

import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.fit.actividad.dao.DaoActividad;
import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.Registro;
import com.fit.actividad.modelo.detalle_actividad.Caminata;
import com.fit.actividad.modelo.detalle_actividad.Carrera;
import com.fit.actividad.modelo.detalle_actividad.Ciclismo;
import com.fit.actividad.modelo.detalle_actividad.DeporteEquipo;
import com.fit.actividad.modelo.detalle_actividad.Natacion;
import com.fit.actividad.vista.interfaces.VistaActividades;
import com.fit.principal.ControladorPrincipal;
import com.fit.usuario.login.Sesion;

public class ControladorActividad {
	
	private ControladorPrincipal controladorPrincipal;
	
	private VistaActividades vista;

	private DaoActividad daoActividad;
	
	private Sesion sesion;
	
	private String[] actividades;
	
	private final String FORMATO_DISTANCIA = "^(?=\\d{1,10}(\\.\\d{0,5})?$)\\d+(\\.\\d+)?$";
	
	private final String FORMATO_RITMO_PROMEDIO = "^(?=\\d{0,5}(\\.\\d{0,5})?$)\\d*(\\.\\d*)?$";
	
	private final String MENSAJE_VALIDACION_DISTANCIA = "Solo valores numéricos, máximo 10 digitos";
	
	private final String MENSAJE_VALIDACION_RITMO_PROMEDIO = "Solo valores numéricos, máximo 5 digitos";
	
	private final String MENSAJE_VALIDACION_CAMPO_VACIO = "Debe llenar este campo";
	
	public ControladorActividad(ControladorPrincipal controladorPrincipal, Sesion sesion) {
		this.controladorPrincipal = controladorPrincipal;
		this.sesion = sesion;
		
		this.daoActividad = new DaoActividad();
	}
	
	public void setVista(VistaActividades vista) {
		this.vista = vista;
	}
	
	public void registrarCaminata(String distanciaString) {
		if(validarDistancia(distanciaString)) 
			registrarActividad(new Caminata(getActividadSelecionada() + 1, Float.parseFloat(distanciaString)));
	}
	
	public void registrarCarrera(String distanciaString, String ritmoString) {
		if(validarDatosCarrera(distanciaString, ritmoString)) {
			float distancia = Float.parseFloat(distanciaString);
			float ritmo = Float.parseFloat(ritmoString);
			registrarActividad(new Carrera(getActividadSelecionada() + 1, distancia, ritmo));
		}
	}

	public void registrarCiclismo(String distanciaString, String tipoBicicleta) {
		if(validarDatosCiclismo(distanciaString, tipoBicicleta)) {
			float distancia = Float.parseFloat(distanciaString);
			registrarActividad(new Ciclismo(getActividadSelecionada() + 1, distancia, tipoBicicleta));
		}
	}
	
	public void registrarNatacion(String distanciaString, String estiloNatacion) {
		if(validarDatosNatacion(distanciaString, estiloNatacion)) {
			float distancia = Float.parseFloat(distanciaString);
			registrarActividad(new Natacion(getActividadSelecionada() + 1, distancia, estiloNatacion));
		}
	}
	
	public void registrarDeporteEquipo(String nombreDeporte, String resultadoDelPartido, String duracionDelPartido) {
		if(validarDatosDeporteEquipo(nombreDeporte, resultadoDelPartido, duracionDelPartido))
			registrarActividad(new DeporteEquipo(getActividadSelecionada(), nombreDeporte, resultadoDelPartido, Integer.parseInt(duracionDelPartido)));
	}

	private boolean validarDatosCarrera(String distancia, String ritmo) {
		boolean distanciaValida = validarDistancia(distancia);
		boolean ritmoValido = validarRitmo(ritmo);
		return distanciaValida && ritmoValido;
	}
	
	private boolean validarDatosCiclismo(String distanciaString, String tipoBicicleta) {
		boolean distanciaValida = validarDistancia(distanciaString);
		boolean tipoBiciletaValido = validarTipoBicicleta(tipoBicicleta);
		return distanciaValida && tipoBiciletaValido;
	}
	
	private boolean validarDatosNatacion(String distanciaString, String estiloNatacion) {
		boolean distanciaValida = validarDistancia(distanciaString);
		boolean estiloNatacionValido = validarEstiloNatacion(estiloNatacion);
		return distanciaValida && estiloNatacionValido;
	}
	
	private boolean validarDatosDeporteEquipo(String nombreDeporte, String resultadoDelPartido, String duracionDelPartido) {
		boolean nombreDeporteValido = validarNombreDeporte(nombreDeporte);
		boolean resultadoDelPartidoValido = validarResultadoDelPartido(resultadoDelPartido);
		boolean duracionDelPartidoValido = validarDuracionDelPartido(duracionDelPartido);
		return nombreDeporteValido && resultadoDelPartidoValido && duracionDelPartidoValido;
	} 

	private boolean validarDistancia(String distancia) {
		if(!distancia.isEmpty() && Pattern.matches(FORMATO_DISTANCIA, distancia)) return true;
		vista.validarDistancia(getActividadSelecionada(), MENSAJE_VALIDACION_DISTANCIA);
		return false;
	}
	
	private boolean validarRitmo(String ritmo) {
		if(!ritmo.isEmpty() && Pattern.matches(FORMATO_RITMO_PROMEDIO, ritmo)) return true;
		vista.validarRitmoPromedio(getActividadSelecionada(), MENSAJE_VALIDACION_RITMO_PROMEDIO);
		return false;
	}
	
	private boolean validarTipoBicicleta(String tipoBicicleta) {
		if(tipoBicicleta.isEmpty()) {
			vista.validarTipoBicicleta(getActividadSelecionada(), MENSAJE_VALIDACION_CAMPO_VACIO);
			return false;
		} else if(tipoBicicleta.length() < Ciclismo.TAMANIO_MAXIMO_TIPO_BICICLETA) return true;
		vista.validarTipoBicicleta(getActividadSelecionada(), Ciclismo.MENSAJE_TAMANIO_MAXIMO_TIPO_BICICLETA);
		return false;
	}
	
	private boolean validarEstiloNatacion(String estiloNatacion) {
		if(estiloNatacion.isEmpty()) {
			vista.validarEstiloNatacion(getActividadSelecionada(), MENSAJE_VALIDACION_CAMPO_VACIO);
			return false;
		} else if(estiloNatacion.length() <= Natacion.TAMANIO_MAXIMO_ESTILO_NATACION) return true;
		vista.validarEstiloNatacion(getActividadSelecionada(), Natacion.MENSAJE_TAMANIO_MAXIMO_ESTILO_NATACION);
		return false;
	}
	
	private boolean validarNombreDeporte(String nombreDeporte) {
		if(nombreDeporte.isEmpty()) {
			vista.validarNombreDeporte(getActividadSelecionada(), MENSAJE_VALIDACION_CAMPO_VACIO);
			return false;
		} else if(nombreDeporte.length() <= DeporteEquipo.TAMANIO_MAXIMO_NOMBRE_DEPORTE) return true;
		vista.validarNombreDeporte(getActividadSelecionada(), DeporteEquipo.MENSAJE_TAMANIO_MAXIMO_NOMBRE_DEPORTE);
		return false;
	}

	private boolean validarResultadoDelPartido(String resultadoDelPartido) {
		if(resultadoDelPartido.isEmpty()) {
			vista.validarResultadoDelPartido(getActividadSelecionada(), MENSAJE_VALIDACION_CAMPO_VACIO);
			return false;
		} else if(resultadoDelPartido.length() <= DeporteEquipo.TAMANIO_MAXIMO_RESULTADO_PARTIDO) return true;
		vista.validarResultadoDelPartido(getActividadSelecionada(), DeporteEquipo.MENSAJE_TAMANIO_MAXIMO_RESULTADO_PARTIDO);
		return false;
	}
	
	private boolean validarDuracionDelPartido(String duracionDelPartido) {
		if(!duracionDelPartido.isEmpty() && Pattern.matches(DeporteEquipo.FORMATO_DURACION_DEL_PARTIDO, duracionDelPartido)) 
			return true;
		vista.validarDuracionDelPartido(getActividadSelecionada(), DeporteEquipo.MENSAJE_VALIDACION_DURACION_DEL_PARTIDO);
		return false;
	}

	private void registrarActividad(Actividad actividad) {
		Registro registro = new Registro(this.sesion.getIdUsuario(),actividad.getId(),new GregorianCalendar(),null);
		int idRegistro = registro.guardarEnDB();
		if(idRegistro != -1 && actividad.guardarEnDB_AsociadoConRegistro(registro.getId())) {
			vista.limpiarCamposTexto(getActividadSelecionada());
			JOptionPane.showMessageDialog(null, "Registrado con exito!");
		}else JOptionPane.showMessageDialog(null, "Ha ocurrido un error guardando.");	
	}
	
	public void cerrarSesion() {
		this.controladorPrincipal.cerrarSesion(this.sesion);
	}

	public String[] opcionesActividades() {
		this.actividades = this.daoActividad.getListaActividades();
		return this.actividades;
	}
	
	private int getActividadSelecionada() {
		return this.vista.getActividadSelecionada();
	}
}
