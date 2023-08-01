package com.fit.actividad.vista.panel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.fit.actividad.ControladorActividad;
import com.fit.actividad.vista.interfaces.ValidadorCampoDistancia;

public class PanelFormularioCiclismo extends PanelFormulario implements ValidadorCampoDistancia{

	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldDistancia;
	
	private JLabel labelErrorDistancia;
	
	private JTextField textFieldTipoBicicleta;
	
	private JLabel labelErrorTipoBicicleta;
	
	public PanelFormularioCiclismo(final ControladorActividad controlador) {
		super();
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		
		ajustarConstraints(0, 0, 1, 1);
		add(new JLabel("Distancia (km):"), constraints);
		
		ajustarConstraints(1, 0, 1, 1);
		this.textFieldDistancia = new JTextField(15);
		add(this.textFieldDistancia, constraints);
		
		ajustarConstraints(0, 1, 2, 1);
		this.labelErrorDistancia = getLabelError();
		add(this.labelErrorDistancia, constraints);
		
		ajustarConstraints(0, 2, 1, 1);
		add(new JLabel("Tipo bicicleta: "), constraints);
		
		ajustarConstraints(1, 2, 1, 1);
		this.textFieldTipoBicicleta = new JTextField(15);
		add(this.textFieldTipoBicicleta, constraints);
		
		ajustarConstraints(0, 3, 2, 1);
		this.labelErrorTipoBicicleta = getLabelError();
		add(this.labelErrorTipoBicicleta, constraints);
		
		ajustarConstraints(0, 4, 2, 1);
		JButton botonGuardarCiclismo = new JButton("Guardar");
		add(botonGuardarCiclismo, constraints);
		botonGuardarCiclismo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarCamposError();
				controlador.registrarCiclismo(textFieldDistancia.getText(), textFieldTipoBicicleta.getText());
			}
		});
	}

	@Override
	public void limpiarCamposTexto() {
		this.textFieldDistancia.setText("");
		this.textFieldTipoBicicleta.setText("");
	}
	
	@Override
	public void limpiarCamposError() {
		limpiarCampoErrorDistancia();
		limpiarCampoErrorTipoBicicleta();
	}

	@Override
	public void mostrarErrorCampoDistancia(String mensajeError) {
		this.labelErrorDistancia.setText(mensajeError);
		this.textFieldDistancia.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	public void mostrarErrorCampoTipoBicicleta(String mensajeError) {
		this.labelErrorTipoBicicleta.setText(mensajeError);
		this.textFieldTipoBicicleta.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	private void limpiarCampoErrorDistancia() {
		this.labelErrorDistancia.setText(" ");
		this.textFieldDistancia.setBorder(UIManager.getBorder("TextField.border"));
	}
	
	private void limpiarCampoErrorTipoBicicleta() {
		this.labelErrorTipoBicicleta.setText(" ");
		this.textFieldTipoBicicleta.setBorder(UIManager.getBorder("TextField.border"));
	}
}