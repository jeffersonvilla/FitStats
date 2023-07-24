package com.fit.controlador;

import java.util.regex.Pattern;

import com.fit.dao.DaoUsuario;
import com.fit.modelo.Usuario;
import com.fit.vista.VistaRegistroUsuario;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;

public class ControladorRegistroUsuario {
	
	private ControladorPrincipal controladorPrincipal;
	
	private VistaRegistroUsuario vista;
	
	private DaoUsuario dao = new DaoUsuario();
	
	private static final String FORMATO_NOMBRE = "^[A-Za-z]+(?:\\s[A-Za-z]+)+$";
	
	private static final String FORMATO_PASSWORD = "^(?!.*[\\s\\r\\n\\t]).{8,}$";//"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\W]{8,}$";
	
	private static final String MENSAJE_NOMBRE_NO_VALIDO = "Nombres y apellidos separados por espacio";
	
	private static final String MENSAJE_EMAIL_NO_VALIDO = "Formato 'ejemplo@mail'";
	
	private static final String MENSAJE_PASSWORD_NO_VALIDO = "Minimo 8 caracteres";

	private static final String MENSAJE_USUARIO_REGISTRADO_CORRECTAMENTE = "Usuario registrado con exito";
	
	private static final String MENSAJE_PROBLEMA_REGISTRANDO_USUARIO = "Hubo un problema registrando el usuario";
	
	private static final String MENSAJE_EMAIL_EN_USO = "Este email ya está en uso";
	
	public ControladorRegistroUsuario(ControladorPrincipal controladorPrincipal) {
		this.controladorPrincipal = controladorPrincipal;
	}
	
	public void setVista(VistaRegistroUsuario vista) {
		this.vista = vista;
	}
	
	public void registrarUsuario(String nombre, String email, char[] password) {
		if(validarDatosUsuario(nombre, email, password)) {
			if(dao.validarEmailDisponible(email)) {
				if(dao.crearUsuario(new Usuario(nombre, email, BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, password)))) {
					vista.usuarioRegistradoCorrectamente(MENSAJE_USUARIO_REGISTRADO_CORRECTAMENTE);
					this.controladorPrincipal.cerrarVentanaRegistroUsuario();					
				}
				else
					vista.problemasRegistrandoUsuario(MENSAJE_PROBLEMA_REGISTRANDO_USUARIO);
			}else {
				vista.validarEmail(MENSAJE_EMAIL_EN_USO);
			}
				
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
		vista.validarNombre(MENSAJE_NOMBRE_NO_VALIDO);
		return false;
	}
	
	private boolean validarEmail(String email) {
		if(emailTieneFormatoValido(email)) return true;
		vista.validarEmail(MENSAJE_EMAIL_NO_VALIDO);
		return false;
	}
	
	private boolean validarPassword(char[] password) {
		if(passwordTieneFormatoValido(password)) return true;
		vista.validarPassword(MENSAJE_PASSWORD_NO_VALIDO);
		return false;
	}

	private boolean nombreTieneFormatoValido(String nombre) {
        return Pattern.compile(FORMATO_NOMBRE).matcher(nombre).matches();
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
        return Pattern.compile(FORMATO_PASSWORD).matcher(new String(password)).matches();
	}

	public void cancelarRegistroUsuario() {
		this.controladorPrincipal.cerrarVentanaRegistroUsuario();
		
	}
}
