package com.fit.controlador;

import com.fit.modelo.Sesion;
import com.fit.vista.VentanaActividades;

public class ControladorActividades {

	private ControladorPrincipal controladorPrincipal;
	
	private VentanaActividades vista;
	
	private Sesion sesion;
	
	public ControladorActividades(ControladorPrincipal controladorPrincipal, Sesion sesion) {
		this.controladorPrincipal = controladorPrincipal;
		this.sesion = sesion;
	}
	
	public void setVista(VentanaActividades vista) {
		this.vista = vista;
	}
	
	public void cerrarSesion() {
		this.controladorPrincipal.cerrarSesion(this.sesion);
	}
}
