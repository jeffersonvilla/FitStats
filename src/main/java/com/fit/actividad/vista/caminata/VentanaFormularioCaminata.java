package com.fit.actividad.vista.caminata;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.fit.actividad.controlador.ControladorActividad;
import com.fit.actividad.vista.panelFormulario.FormularioCaminata;
import com.fit.util.Pantalla;

public abstract class VentanaFormularioCaminata extends JFrame {

	private static final long serialVersionUID = 1L;
	
	protected final ControladorActividad controlador;
	
	protected FormularioCaminata formulario;

	protected VentanaFormularioCaminata(ControladorActividad controlador, FormularioCaminata formulario) {
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
}