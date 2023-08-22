package com.fit.actividad.vista.caminata;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.fit.actividad.AbstractFactory.VistaFormularioCrear;
import com.fit.actividad.controlador.ControladorActividad;
import com.fit.actividad.vista.panelFormulario.FormularioCaminata;
import com.fit.actividad.vista.panelFormulario.InputsValidosObserver;

public class VistaCrearCaminata extends VentanaFormularioCaminata 
implements VistaFormularioCrear, ActionListener, InputsValidosObserver{

	private static final long serialVersionUID = 1L;
	
	private static final String BOTON_REGISTRAR = "Registrar";
	
	private JButton botonRegistrar;

	public VistaCrearCaminata(ControladorActividad controlador, FormularioCaminata formulario) {
		super(controlador, formulario);
		
		formulario.setObservadorInputs(this);
		
		init();
	}
	
	private void init() {
		botonRegistrar = new JButton(BOTON_REGISTRAR);
		botonRegistrar.addActionListener(this);
		add(botonRegistrar, BorderLayout.SOUTH);
		
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
				case BOTON_REGISTRAR -> {				
						controlador.registrarActividad(formulario.getActividad());
						this.dispose();
					}
				default -> {System.out.println("Sin funcionalidad para el boton elegido");}
			}
		}
	}

	@Override
	public void update(boolean value) {
		botonRegistrar.setEnabled(value);
	}
}
