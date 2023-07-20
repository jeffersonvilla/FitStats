package com.fit.util;

public class FormatoNoValido extends IllegalArgumentException{

	private static final long serialVersionUID = 6293404706454565971L;

	public FormatoNoValido(String mensaje) {
		super(mensaje);
	}
}
