package com.fit.controlador;

import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import com.fit.dao.DaoSesion;
import com.fit.dao.DaoUsuario;
import com.fit.modelo.Sesion;
import com.fit.modelo.Usuario;
import com.fit.vista.VistaInicioSesion;

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
		if(emailTieneFormatoValido(email)) {			
			String passwordEncriptado =  daoUsuario.leerPassUsuarioPorEmail(email);
			if(passwordEncriptado != null) {
				if(!BCrypt.verifyer().verify(password,passwordEncriptado).verified) vista.mostrarErrorValidarCredenciales();
				else {
					JOptionPane.showMessageDialog(null, "Inicio correcto!");
					crearSesion(email);
					this.controladorPrincipal.abrirVentanaActividades(this.sesionUsuario);
				}
			}else vista.mostrarErrorEmailNoRegistrado();
		}else vista.validarFormatoEmail();
		
	}
	
	private void crearSesion(String email) {
		Usuario usuario = daoUsuario.leerUsuarioPorEmail(email);
		this.sesionUsuario = daoSesion.sesionAbiertaPorIdUsuario(usuario.getId());
		if(this.sesionUsuario == null) {			
			daoSesion.crearSesion(new Sesion(new GregorianCalendar(), usuario.getId()));
			this.sesionUsuario = daoSesion.sesionAbiertaPorIdUsuario(usuario.getId());
		}
	}
	
	private boolean emailTieneFormatoValido(String email) {
		try {
			(new InternetAddress(email)).validate();
			return true;
		} catch (AddressException e1) {
			return false;
		}
	}

	public void cancelarInicioSesion() {
		this.controladorPrincipal.cerrarVentanaInicioSesion();
	}
	
	
	
}
