package com.fit.actividad.vista.actividades;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.fit.actividad.controlador.ControladorActividad;
import com.fit.actividad.vista.InputsValidosObserver;
import com.fit.actividad.vista.panelFormulario.FormularioActividad;

public class ActualizacionActividad extends VentanaFormularioActividad implements ActionListener, InputsValidosObserver{

	private static final long serialVersionUID = 1L;
	
	private static final String BOTON_ACTUALIZAR = "Actualizar";
	
	private JButton botonActualizar;
	
	public ActualizacionActividad(ControladorActividad controlador, FormularioActividad formulario) {
		super(controlador, formulario);
		
		formulario.setObservadorInputs(this);
		
		init();
	}
	
	private void init() {
		botonActualizar = new JButton(BOTON_ACTUALIZAR);
		botonActualizar.addActionListener(this);
		add(botonActualizar, BorderLayout.SOUTH);
		
		pack();
		setResizable(false);
		setVisible(true);
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

	@Override
	public void update(boolean value) {
		botonActualizar.setEnabled(value);
	}
}