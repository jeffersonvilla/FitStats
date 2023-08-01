package com.fit.usuario.registro;

import java.util.regex.Pattern;

import com.fit.principal.ControladorPrincipal;
import com.fit.usuario.DaoUsuario;
import com.fit.usuario.Usuario;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;

public class ControladorRegistroUsuario {
	
	private ControladorPrincipal controladorPrincipal;
	
	private VistaRegistroUsuario vista;
	
	private DaoUsuario dao;
	
	public ControladorRegistroUsuario(ControladorPrincipal controladorPrincipal) {
		this.controladorPrincipal = controladorPrincipal;
		this.dao = new DaoUsuario();
	}
	
	public void setVista(VistaRegistroUsuario vista) {
		this.vista = vista;
	}
	
	public void registrarUsuario(String nombre, String email, char[] password) {
		if(validarDatosUsuario(nombre, email, password)) {
			if(dao.validarEmailDisponible(email)) {
				if(dao.crearUsuario(new Usuario(nombre, email, BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, password)))) {
					vista.usuarioRegistradoCorrectamente(Usuario.MENSAJE_USUARIO_REGISTRADO_CORRECTAMENTE);
					this.controladorPrincipal.cerrarVentanaRegistroUsuario();
				}else vista.problemasRegistrandoUsuario(Usuario.MENSAJE_PROBLEMA_REGISTRANDO_USUARIO);
			}else vista.validarEmail(Usuario.MENSAJE_EMAIL_EN_USO);
		}
	}
	
	private boolean validarDatosUsuario(String nombre, String email, char[] password) {
		boolean nombreValido = validarNombre(nombre);
		boolean emailValido = validarEmail(email);
		boolean passwordValido = validarPassword(password);
		return nombreValido && emailValido && passwordValido;
	}

	
	private boolean validarNombre(String nombre) {
		if(nombreTieneFormatoValido(nombre)) return true;
		vista.validarNombre(Usuario.MENSAJE_NOMBRE_NO_VALIDO);
		return false;
	}
	
	private boolean validarEmail(String email) {
		if(emailTieneFormatoValido(email)) return true;
		vista.validarEmail(Usuario.MENSAJE_EMAIL_NO_VALIDO);
		return false;
	}
	
	private boolean validarPassword(char[] password) {
		if(passwordTieneFormatoValido(password)) return true;
		vista.validarPassword(Usuario.MENSAJE_PASSWORD_NO_VALIDO);
		return false;
	}
	
	private boolean nombreTieneFormatoValido(String nombre) {
		return Pattern.compile(Usuario.FORMATO_NOMBRE).matcher(nombre).matches();
	}
	
	private boolean emailTieneFormatoValido(String email) {
		try {
			(new InternetAddress(email)).validate();
			return true;
		} catch (AddressException e1) {
			return false;
		}
	}
	
	private boolean passwordTieneFormatoValido(char[] password) {
		return Pattern.compile(Usuario.FORMATO_PASSWORD).matcher(new String(password)).matches();
	}
	
	public void cancelarRegistroUsuario() {
		this.controladorPrincipal.cerrarVentanaRegistroUsuario();	
	}
}
