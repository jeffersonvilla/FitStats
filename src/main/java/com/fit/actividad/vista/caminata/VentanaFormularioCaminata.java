package com.fit.actividad.vista.caminata;

import java.awt.BorderLayout;
import java.sql.Timestamp;
import java.util.regex.Pattern;

import javax.swing.JFrame;

import com.fit.actividad.controlador.ControladorCaminata;
import com.fit.actividad.vista.panelFormulario.FormularioActividad;
import com.fit.actividad.vista.panelFormulario.FormularioCaminata;
import com.fit.util.Pantalla;

public abstract class VentanaFormularioCaminata extends JFrame {

	private static final long serialVersionUID = 1L;
	
	protected static final String FORMATO_DISTANCIA = "^(?=\\d{1,10}(\\.\\d{0,5})?$)\\d+(\\.\\d+)?$";
	
	protected static final String MENSAJE_VALIDACION_DISTANCIA = "Solo valores numéricos (máximo 8 digitos)";
	
	protected final ControladorCaminata controlador;
	
	protected FormularioCaminata formulario;

	protected VentanaFormularioCaminata(ControladorCaminata controlador, FormularioCaminata formulario) {
		this.controlador = controlador;
		this.formulario = formulario;
		
		init();
	}
	
	private void init() {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(Pantalla.ancho / 3, Pantalla.alto / 3);
		
		add(formulario, BorderLayout.CENTER);
	}

	protected boolean validarFecha(Timestamp fecha) {
		if (fecha != null) return true;
		formulario.mostrarErrorCampoFecha(FormularioActividad.MENSAJE_VALIDACION_CAMPO_VACIO);
		return false;
	}

	protected boolean validarDistancia(String distancia) {
		if (!distancia.isEmpty() && Pattern.matches(FORMATO_DISTANCIA, distancia)) return true;
		formulario.mostrarErrorCampoDistancia(MENSAJE_VALIDACION_DISTANCIA);
		return false;
	}
	
}