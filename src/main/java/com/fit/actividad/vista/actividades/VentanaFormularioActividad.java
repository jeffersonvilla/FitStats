package com.fit.actividad.vista.actividades;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.fit.actividad.controlador.ControladorActividad;
import com.fit.actividad.vista.panelFormulario.FormularioActividad;
import com.fit.util.Pantalla;

public abstract class VentanaFormularioActividad extends JFrame {

	private static final long serialVersionUID = 1L;
	
	protected final ControladorActividad controlador;
	
	protected FormularioActividad formulario;

	protected VentanaFormularioActividad(ControladorActividad controlador, FormularioActividad formulario) {
		this.controlador = controlador;
		this.formulario = formulario;
		
		setTitle(formulario.getTitulo());
		
		init();
	}
	
	private void init() {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(Pantalla.ancho / 3, Pantalla.alto / 3);
		
		add(formulario, BorderLayout.CENTER);
	}
}