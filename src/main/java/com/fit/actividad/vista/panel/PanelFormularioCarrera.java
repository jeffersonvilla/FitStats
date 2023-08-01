package com.fit.actividad.vista.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.fit.actividad.ControladorActividad;
import com.fit.actividad.vista.interfaces.ValidadorCampoDistancia;
import com.fit.actividad.vista.interfaces.ValidadorCampoRitmoPromedio;

public class PanelFormularioCarrera extends PanelFormulario implements ValidadorCampoDistancia, ValidadorCampoRitmoPromedio{

	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldDistancia;
	
	private JLabel labelErrorDistancia;
	
	private JTextField textFieldRitmoPromedio;
	
	private JLabel labelErrorRitmoPromedio;
	
	public PanelFormularioCarrera(final ControladorActividad controlador) {
		super();
		
		ajustarConstraints(0, 0, 1, 1);
		add(new JLabel("Distancia (km):"), constraints);
		
		ajustarConstraints(1, 0, 1, 1);
		this.textFieldDistancia = new JTextField(15);
		add(this.textFieldDistancia, constraints);
		
		ajustarConstraints(0, 1, 2, 1);
		
		this.labelErrorDistancia = getLabelError();
		add(this.labelErrorDistancia, constraints);
		
		ajustarConstraints(0, 2, 1, 1);
		add(new JLabel("Ritmo promedio (minutos por km):"), constraints);
		
		ajustarConstraints(1, 2, 1, 1);
		this.textFieldRitmoPromedio = new JTextField(15);
		add(this.textFieldRitmoPromedio, constraints);
		
		ajustarConstraints(0, 3, 2, 1);
		this.labelErrorRitmoPromedio = getLabelError();
		add(this.labelErrorRitmoPromedio, constraints);
		
		ajustarConstraints(0, 4, 2, 1);
		JButton botonGuardarCarrera = new JButton("Guardar");
		add(botonGuardarCarrera, constraints);
		botonGuardarCarrera.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarCamposError();
				controlador.registrarCarrera(textFieldDistancia.getText(), textFieldRitmoPromedio.getText());;
			}
		});
	}
	
	@Override
	public void limpiarCamposTexto() {
		this.textFieldDistancia.setText("");
		this.textFieldRitmoPromedio.setText("");
	}
	
	@Override
	public void limpiarCamposError() {
		limpiarCampoErrorDistancia();
		limpiarCampoErrorRitmoPromedio();
	}
	
	@Override
	public void mostrarErrorCampoDistancia(String mensajeError) {
		this.labelErrorDistancia.setText(mensajeError);
		this.textFieldDistancia.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	@Override
	public void mostrarErrorCampoRitmoPromedio(String mensajeError) {
		this.labelErrorRitmoPromedio.setText(mensajeError);
		this.textFieldRitmoPromedio.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	private void limpiarCampoErrorDistancia() {
		this.labelErrorDistancia.setText(" ");
		this.textFieldDistancia.setBorder(UIManager.getBorder("TextField.border"));
	}
	
	private void limpiarCampoErrorRitmoPromedio() {
		this.labelErrorRitmoPromedio.setText(" ");
		this.textFieldRitmoPromedio.setBorder(UIManager.getBorder("TextField.border"));
	}
}