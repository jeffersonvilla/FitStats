package com.fit.vista;

public interface VistaUsuario {

	void validarNombre(String mensajeNombreNoValido);

	void validarEmail(String mensajeEmailNoValido);

	void validarPassword(String mensajePasswordNoValido);

	void usuarioRegistradoCorrectamente(String mensajeUsuarioRegistradoCorrectamente);

	void problemasRegistrandoUsuario(String mensajeProblemaRegistrandoUsuario);

}
