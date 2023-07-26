package com.fit.controlador;

import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import com.fit.dao.DaoActividad;
import com.fit.dao.DaoDetalleActividad;
import com.fit.dao.DaoRegistro;
import com.fit.modelo.Actividad;
import com.fit.modelo.Caminata;
import com.fit.modelo.Carrera;
import com.fit.modelo.Registro;
import com.fit.modelo.Sesion;
import com.fit.vista.VentanaActividades;

public class ControladorActividades {

	private ControladorPrincipal controladorPrincipal;
	
	private VentanaActividades vista;
	
	private Sesion sesion;
	
	private String[] actividades;
	
	private DaoActividad daoActividad;
	
	private DaoDetalleActividad daoDetalleActividad;
	
	private DaoRegistro daoRegistro;
	
	private final String ERROR_CAMPO_VACIO = "Debe llenar este campo.";
	
	private final String ERROR_CAMPO_NUMERICO = "Solo valores numéricos";
	
	public ControladorActividades(ControladorPrincipal controladorPrincipal, Sesion sesion) {
		this.controladorPrincipal = controladorPrincipal;
		this.sesion = sesion;
		this.daoActividad = new DaoActividad();
		this.daoRegistro = new DaoRegistro();
		this.daoDetalleActividad = new DaoDetalleActividad();
	}
	
	public void setVista(VentanaActividades vista) {
		this.vista = vista;
	}
	
	public void registrarCaminata(int index, String distanciaString) {
		vista.limpiarCamposError();
		
		float distancia = -1;
		try {
			distancia = Float.parseFloat(distanciaString);	
		}catch(NumberFormatException nfe) {
			vista.mostrarErrorCampoDistancia(ERROR_CAMPO_NUMERICO);
		}
		
		if(distancia != -1) {
			vista.limpiarCamposError();
			registrarActividad(new Caminata(index + 1, distancia));
		} 
	}
	
	public void registrarCarrera(int index, String distanciaString, String ritmoString) {
		vista.limpiarCamposError();
		
		float distancia = -1;
		try {
			distancia = Float.parseFloat(distanciaString);
		}catch(NumberFormatException nfe) {
			vista.mostrarErrorCampoDistancia(ERROR_CAMPO_NUMERICO);
		}
		
		float ritmo = -1;
		try {
			ritmo = Float.parseFloat(ritmoString);
		}catch(NumberFormatException nfe) {
			vista.mostrarErrorCampoRitmoPromedio(ERROR_CAMPO_NUMERICO);
		}
		
		if(distancia != -1 && ritmo != -1) {
			vista.limpiarCamposError();
			registrarActividad(new Carrera(index + 1, distancia, ritmo));	
		}
		   
	}
	
	private void registrarActividad(Actividad actividad) {
		Registro registro = new Registro(this.sesion.getIdUsuario(),actividad.getId(),new GregorianCalendar(),null);
		if(registro.guardarEnDB()) {
			if(actividad.guardarEnDB_AsociadoConRegistro(registro.getIdFromDB())) {
				vista.limpiarCamposTexto();
				JOptionPane.showMessageDialog(null, "correcto!");
			}else System.out.println("No se ha guardado detalle actividad");
		}else System.out.println("No se ha guardado registro");
		
	}
	
	public void cerrarSesion() {
		this.controladorPrincipal.cerrarSesion(this.sesion);
	}

	public String[] opcionesActividades() {
		this.actividades = this.daoActividad.getListaActividades();
		return this.actividades;
	}
}
