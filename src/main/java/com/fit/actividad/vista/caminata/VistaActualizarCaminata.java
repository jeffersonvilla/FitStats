package com.fit.actividad.vista.caminata;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.fit.actividad.AbstractFactory.VistaFormularioActualizar;
import com.fit.actividad.controlador.ControladorActividad;
import com.fit.actividad.vista.panelFormulario.FormularioCaminata;

public class VistaActualizarCaminata extends VentanaFormularioCaminata implements VistaFormularioActualizar, ActionListener{

	private static final long serialVersionUID = 1L;
	
	private static final String BOTON_ACTUALIZAR = "Actualizar";
	
	public VistaActualizarCaminata(ControladorActividad controlador, FormularioCaminata formulario) {
		super(controlador, formulario);
		init();
	}
	
	private void init() {
		JButton botonActualizar = new JButton(BOTON_ACTUALIZAR);
		botonActualizar.addActionListener(this);
		add(botonActualizar, BorderLayout.SOUTH);
		
		pack();
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		formulario.limpiarCamposError();
		
		Object source = e.getSource();
		if(source instanceof JButton) {
			JButton boton = (JButton) source;
			switch(boton.getText()) {
				case BOTON_ACTUALIZAR -> {
					controlador.actualizarActividad(formulario.getActividad());
					dispose();
				}
				default -> {System.out.println("Sin funcionalidad para el boton elegido");}
			}
		}
	}
}