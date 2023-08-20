package com.fit.principal;

import java.util.GregorianCalendar;

import com.fit.usuario.login.ControladorInicioSesion;
import com.fit.usuario.login.DaoSesion;
import com.fit.usuario.login.Sesion;
import com.fit.usuario.login.VentanaInicioSesion;
import com.fit.usuario.registro.ControladorRegistroUsuario;
import com.fit.usuario.registro.VentanaRegistroUsuario;

public class ControladorPrincipal {

	private VentanaPrincipal ventanaPrincipal;

	private ControladorInicioSesion controladorInicioSesion;
	private VentanaInicioSesion ventanaInicioSesion;

	private ControladorRegistroUsuario controladorRegistroUsuario;
	private VentanaRegistroUsuario ventanaRegistroUsuario;

	//private ControladorActividad controladorActividades;
	//private VentanaActividades ventanaActividades;

	private DaoSesion daoSesion;

	public ControladorPrincipal() {
		this.daoSesion = new DaoSesion();
		this.ventanaPrincipal = new VentanaPrincipal(this);
	}

	public void abrirVentanaInicioSesion() {
		this.controladorInicioSesion = new ControladorInicioSesion(this);
		this.ventanaInicioSesion = new VentanaInicioSesion(this.controladorInicioSesion);
		this.controladorInicioSesion.setVista(this.ventanaInicioSesion);
		this.ventanaPrincipal.setVisible(false);
	}

	public void cerrarVentanaInicioSesion() {
		if (this.ventanaInicioSesion != null)
			this.ventanaInicioSesion.dispose();
		this.ventanaPrincipal.setVisible(true);
	}

	public void abrirVentanaRegistroUsuario() {
		this.controladorRegistroUsuario = new ControladorRegistroUsuario(this);
		this.ventanaRegistroUsuario = new VentanaRegistroUsuario(this.controladorRegistroUsuario);
		this.controladorRegistroUsuario.setVista(this.ventanaRegistroUsuario);
		this.ventanaPrincipal.setVisible(false);
	}

	public void cerrarVentanaRegistroUsuario() {
		if (this.ventanaRegistroUsuario != null)
			this.ventanaRegistroUsuario.dispose();
		this.ventanaPrincipal.setVisible(true);
	}

	public void abrirVentanaActividades(Sesion sesionUsuario) {
		cerrarVentanaInicioSesion();
//		this.controladorActividades = new ControladorActividad(this, sesionUsuario);
//		this.ventanaActividades = new VentanaActividades(controladorActividades);
//		this.controladorActividades.setVista(ventanaActividades);
//		this.ventanaPrincipal.setVisible(false);
	}

	private void cerrarVentanaActividades() {
//		if (this.ventanaActividades != null)
//			this.ventanaActividades.dispose();
//		this.ventanaPrincipal.setVisible(true);
	}

	public void cerrarSesion(Sesion sesion) {
		sesion.setFechaFin(new GregorianCalendar());
		this.daoSesion.cerrarSesion(sesion);
		cerrarVentanaActividades();
	}

}
