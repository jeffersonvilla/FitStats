package com.fit.controlador;

import com.fit.modelo.Sesion;
import com.fit.vista.VentanaInicioSesion;
import com.fit.vista.VentanaPrincipal;
import com.fit.vista.VentanaRegistroUsuario;

public class ControladorPrincipal {

	private VentanaPrincipal ventanaPrincipal;
	
	private ControladorInicioSesion controladorInicioSesion;
	private VentanaInicioSesion ventanaInicioSesion;
	
	private ControladorRegistroUsuario controladorRegistroUsuario;
	private VentanaRegistroUsuario ventanaRegistroUsuario;
	
	public ControladorPrincipal() {
		this.ventanaPrincipal = new VentanaPrincipal(this);
	}
	
	public void abrirVentanaInicioSesion() {
		this.controladorInicioSesion = new ControladorInicioSesion(this);
		this.ventanaInicioSesion = new VentanaInicioSesion(this.controladorInicioSesion);
    	this.controladorInicioSesion.setVista(this.ventanaInicioSesion);
    	this.ventanaPrincipal.setVisible(false);
	}
	
	public void cerrarVentanaInicioSesion() {
		if(this.ventanaInicioSesion != null) this.ventanaInicioSesion.dispose();
		this.ventanaPrincipal.setVisible(true);
	}
	
	public void abrirVentanaRegistroUsuario() {
		this.controladorRegistroUsuario = new ControladorRegistroUsuario(this);
		this.ventanaRegistroUsuario = new VentanaRegistroUsuario(this.controladorRegistroUsuario);
		this.controladorRegistroUsuario.setVista(this.ventanaRegistroUsuario);
		this.ventanaPrincipal.setVisible(false);
	}
	
	public void cerrarVentanaRegistroUsuario() {
		if(this.ventanaRegistroUsuario != null) this.ventanaRegistroUsuario.dispose(); 
		this.ventanaPrincipal.setVisible(true);
	}

	public void abrirFuncionalidad(Sesion sesionUsuario) {
		cerrarVentanaInicioSesion();
		System.out.println("Sin implementar");
	}
}
