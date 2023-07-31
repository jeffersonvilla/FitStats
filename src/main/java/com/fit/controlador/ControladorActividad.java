package com.fit.controlador;

import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.fit.dao.DaoActividad;
import com.fit.modelo.Actividad;
import com.fit.modelo.Caminata;
import com.fit.modelo.Carrera;
import com.fit.modelo.Ciclismo;
import com.fit.modelo.Registro;
import com.fit.modelo.Sesion;
import com.fit.vista.VistaActividades;

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
	
	private final String MENSAJE_VALIDACION_TIPO_BICICLETA = "Máximo 49 caractéres";
	
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
		if(ValidarDatosCiclismo(distanciaString, tipoBicicleta)) {
			float distancia = Float.parseFloat(distanciaString);
			registrarActividad(new Ciclismo(getActividadSelecionada() + 1, distancia, tipoBicicleta));
		}
	}

	private boolean validarDatosCarrera(String distancia, String ritmo) {
		boolean distanciaValida = validarDistancia(distancia);
		boolean ritmoValido = validarRitmo(ritmo);
		return distanciaValida && ritmoValido;
	}
	
	private boolean ValidarDatosCiclismo(String distanciaString, String tipoBicicleta) {
		boolean distanciaValida = validarDistancia(distanciaString);
		boolean tipoBiciletaValido = validarTipoBicicleta(tipoBicicleta);
		return distanciaValida && tipoBiciletaValido;
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
		if(!validarCampoNoVacio(tipoBicicleta)) return false;
		if(tipoBicicleta.length() < 50) return true;
		vista.validarTipoBicicleta(getActividadSelecionada(), MENSAJE_VALIDACION_TIPO_BICICLETA);
		return false;
	}

	private boolean validarCampoNoVacio(String campo) {
		if(!campo.isEmpty()) return true;
		vista.validarTipoBicicleta(getActividadSelecionada(), MENSAJE_VALIDACION_CAMPO_VACIO);
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
