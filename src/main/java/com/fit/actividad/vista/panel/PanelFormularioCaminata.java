package com.fit.actividad.vista.panel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.fit.actividad.ControladorActividad;
import com.fit.actividad.vista.interfaces.LimpiadorCamposError;
import com.fit.actividad.vista.interfaces.LimpiadorCamposTexto;
import com.fit.actividad.vista.interfaces.ValidadorCampoDistancia;

public class PanelFormularioCaminata extends JPanel implements  LimpiadorCamposError, LimpiadorCamposTexto, ValidadorCampoDistancia{

	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldDistancia;
	
	private JLabel labelErrorDistancia;
	
	private GridBagConstraints constraints;
	
	public PanelFormularioCaminata(final ControladorActividad controlador) {
		setLayout(new GridBagLayout());
		
		this.constraints = new GridBagConstraints();
		this.constraints.anchor = GridBagConstraints.WEST;
		
		ajustarConstraints(0, 0, 1, 1);
		add(new JLabel("Distancia (km):"), constraints);
		
		ajustarConstraints(1, 0, 1, 1);
		this.textFieldDistancia = new JTextField(15);
		add(this.textFieldDistancia, constraints);
		
		ajustarConstraints(0, 1, 2, 1);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.labelErrorDistancia = getLabelError();
		add(this.labelErrorDistancia, constraints);
		
		ajustarConstraints(0, 2, 2, 1);
		JButton botonGuardarCarrera = new JButton("Guardar");
		add(botonGuardarCarrera, constraints);
		botonGuardarCarrera.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarCamposError();
				controlador.registrarCaminata(textFieldDistancia.getText());
			}
		});	
	}

	@Override
	public void limpiarCamposError() {
		limpiarCampoErrorDistancia();
	}
	
	@Override
	public void limpiarCamposTexto() {
		this.textFieldDistancia.setText("");
	}
	
	@Override
	public void mostrarErrorCampoDistancia(String mensajeError) {
		this.labelErrorDistancia.setText(mensajeError);
		this.textFieldDistancia.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	private JLabel getLabelError() {
		JLabel labelError = new JLabel(" ");
		labelError.setForeground(Color.RED);
		return labelError;
	}
	
	private void ajustarConstraints(int x , int y, int w, int h) {
		this.constraints.gridx = x;
		this.constraints.gridy = y;
		this.constraints.gridwidth = w;
		this.constraints.gridheight = h;
	}
	
	private void limpiarCampoErrorDistancia() {
		this.labelErrorDistancia.setText(" ");
		this.textFieldDistancia.setBorder(UIManager.getBorder("TextField.border"));
	}	
}