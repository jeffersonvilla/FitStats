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
import com.fit.actividad.vista.interfaces.ValidadorCampoRitmoPromedio;

public class PanelFormularioCarrera extends JPanel 
	implements LimpiadorCamposTexto, LimpiadorCamposError, ValidadorCampoDistancia, ValidadorCampoRitmoPromedio{

	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldDistancia;
	
	private JLabel labelErrorDistancia;
	
	private JTextField textFieldRitmoPromedio;
	
	private JLabel labelErrorRitmoPromedio;
	
	private GridBagConstraints constraints;
	
	public PanelFormularioCarrera(final ControladorActividad controlador) {
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
		
		ajustarConstraints(0, 2, 1, 1);
		add(new JLabel("Ritmo promedio (min por km):"), constraints);
		
		ajustarConstraints(1, 2, 1, 1);
		this.textFieldRitmoPromedio = new JTextField(15);
		add(this.textFieldRitmoPromedio, constraints);
		
		ajustarConstraints(0, 3, 2, 1);
		constraints.fill = GridBagConstraints.HORIZONTAL;
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
}