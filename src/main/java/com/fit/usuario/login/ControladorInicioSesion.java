package com.fit.usuario.login;

import java.util.GregorianCalendar;

import com.fit.principal.ControladorPrincipal;
import com.fit.usuario.DaoUsuario;
import com.fit.usuario.Usuario;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;

public class ControladorInicioSesion {
	
	private ControladorPrincipal controladorPrincipal;

	private VistaInicioSesion vista;
	
	private DaoUsuario daoUsuario;
	
	private DaoSesion daoSesion;
	
	private Sesion sesionUsuario;
	
	public ControladorInicioSesion(ControladorPrincipal controladorPrincipal) {
		this.controladorPrincipal = controladorPrincipal;
		this.daoUsuario = new DaoUsuario();
		this.daoSesion = new DaoSesion();
	}
	
	public void setVista(VistaInicioSesion vista) {
		this.vista = vista;
	}
	
	public void iniciarSesion(String email, char[] password) {
		if(validarDatos(email, password)) {
			String passwordEncriptado =  daoUsuario.leerPassUsuarioPorEmail(email);
			if(passwordEncriptado != null) {
				if(!BCrypt.verifyer().verify(password,passwordEncriptado).verified) 
					vista.validarPassword(Usuario.MENSAJE_PASSWORD_INCORRECTO);
				else {
					crearSesion(email);
					this.controladorPrincipal.abrirVentanaActividades(this.sesionUsuario);
				}
			}else vista.validarEmail(Usuario.MENSAJE_EMAIL_NO_REGISTRADO);
		}
	}
	
	private boolean validarDatos(String email, char[] password) {
		boolean emailValido = validarEmail(email);
		boolean passwordValido = validarPassword(password);
		return emailValido && passwordValido;
	}

	private boolean validarEmail(String email) {
		if(emailTieneFormatoValido(email)) return true;
		vista.validarEmail(Usuario.MENSAJE_EMAIL_NO_VALIDO);
		return false;
	}

	private boolean validarPassword(char[] password) {
		if(passwordNoEstaVacio(password)) return true;
		vista.validarPassword(Usuario.MENSAJE_CAMPO_VACIO);
		return false;
	}

	private boolean emailTieneFormatoValido(String email) {
		try {
			(new InternetAddress(email)).validate();
			return true;
		} catch (AddressException e1) {
			return false;
		}
	}
	
	private boolean passwordNoEstaVacio(char[] password) {
		return password.length != 0;
	}

	private void crearSesion(String email) {
		Usuario usuario = daoUsuario.leerUsuarioPorEmail(email);
		this.sesionUsuario = daoSesion.sesionAbiertaPorIdUsuario(usuario.getId());
		if(this.sesionUsuario == null) {			
			daoSesion.crearSesion(new Sesion(new GregorianCalendar(), usuario.getId()));
			this.sesionUsuario = daoSesion.sesionAbiertaPorIdUsuario(usuario.getId());
		}
	}

	public void cancelarInicioSesion() {
		this.controladorPrincipal.cerrarVentanaInicioSesion();
	}
}
