package com.fit.controlador;

import java.beans.Encoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fit.dao.DaoUsuario;
import com.fit.modelo.Usuario;
import com.fit.util.FormatoNoValido;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;

public class ControladorUsuario {
	
	private DaoUsuario dao = new DaoUsuario();
	
	private static final String FORMATO_NOMBRE = "^[A-Za-z]+(?:\\s[A-Za-z]+)+$";
	
	private static final String FORMATO_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\W]{8,}$";
	
	public void registrarUsuario(String nombre, String email, char[] password) throws FormatoNoValido {
		//if(!nombreTieneFormatoValido(nombre)) throw new FormatoNoValido("Nombre debe ser minimo un nombre y un apellido, separados por espacio, solo letras");
		//if(!emailTieneFormatoValido(email)) throw new FormatoNoValido("Email debe tener formato 'ejemplo@mail'");
		if(!contradTieneFormatoValido(password)) {
			throw new FormatoNoValido("Contraseña debe tener: \n"
					+"Al menos 8 caracteres de longitud.\n"
					+ "Al menos una letra minúscula.\n"
					+ "Al menos una letra mayúscula.\n"
					+ "Al menos un dígito numérico.\n"
					+ "Puede contener caracteres especiales");
		}
		System.out.println("registrando usuario");
		dao.crearUsuario(new Usuario(nombre, email, BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, password)));
	}

    private boolean nombreTieneFormatoValido(String nombre) {
        Pattern pattern = Pattern.compile(FORMATO_NOMBRE);
        Matcher matcher = pattern.matcher(nombre);
        return matcher.matches();
    }
	
	private boolean emailTieneFormatoValido(String email) {
		try {
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			return true;
		} catch (AddressException e1) {			
			//e1.printStackTrace();
			return false;
		}
	}
	
	private boolean contradTieneFormatoValido(char[] password) {
		Pattern pattern = Pattern.compile(FORMATO_PASSWORD);
        Matcher matcher = pattern.matcher(new String(password));
        return matcher.matches();
	}
}
