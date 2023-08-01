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
	
	private final String MENSAJE_MAXIMO_49_CARACTERES = "Máximo 49 caractéres";
	
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
	
	public void registrarNatacion(String distanciaString, String estiloNatacion) {
		if(ValidarDatosNatacion(distanciaString, estiloNatacion)) {
			float distancia = Float.parseFloat(distanciaString);
			registrarActividad(new Natacion(getActividadSelecionada() + 1, distancia, estiloNatacion));
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
	
	private boolean ValidarDatosNatacion(String distanciaString, String estiloNatacion) {
		boolean distanciaValida = validarDistancia(distanciaString);
		boolean estiloNatacionValido = validarEstiloNatacion(estiloNatacion);
		return distanciaValida && estiloNatacionValido;
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
		if(tipoBicicleta.length() < Ciclismo.TAMANIO_MAXIMO_TIPO_BICICLETA) return true;
		vista.validarTipoBicicleta(getActividadSelecionada(), MENSAJE_MAXIMO_49_CARACTERES);
		return false;
	}
	
	private boolean validarEstiloNatacion(String estiloNatacion) {
		if(!validarCampoNoVacio(estiloNatacion)) return false;
		if(estiloNatacion.length() <= Natacion.TAMANIO_MAXIMO_ESTILO_NATACION) return true;
		vista.validarEstiloNatacion(getActividadSelecionada(), MENSAJE_MAXIMO_49_CARACTERES);
		return false;
	}

	private boolean validarCampoNoVacio(String campo) {
		if(!campo.isEmpty()) return true;
		int actividad = getActividadSelecionada();
		switch(actividad) {
			case 2 -> vista.validarTipoBicicleta(actividad, MENSAJE_VALIDACION_CAMPO_VACIO);
			case 3 -> vista.validarEstiloNatacion(actividad, MENSAJE_VALIDACION_CAMPO_VACIO);
			default -> System.out.println("No deberia entrar aqui");
		}		
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
