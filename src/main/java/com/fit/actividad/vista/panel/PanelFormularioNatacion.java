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

public class PanelFormularioNatacion extends JPanel implements LimpiadorCamposTexto, LimpiadorCamposError, ValidadorCampoDistancia{

	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldDistancia;
	
	private JLabel labelErrorDistancia;
	
	private JTextField textFieldEstiloNatacion;
	
	private JLabel labelErrorEstiloNatacion;
	
	private GridBagConstraints constraints;
	
	public PanelFormularioNatacion(final ControladorActividad controlador) {
		setLayout(new GridBagLayout());
		
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		
		ajustarConstraints(0, 0, 1, 1);
		add(new JLabel("Distancia (metros):"), constraints);
		
		ajustarConstraints(1, 0, 1, 1);
		this.textFieldDistancia = new JTextField(15);
		add(this.textFieldDistancia, constraints);
		
		ajustarConstraints(0, 1, 2, 1);
		this.labelErrorDistancia = getLabelError();
		add(this.labelErrorDistancia, constraints);
		
		ajustarConstraints(0, 2, 1, 1);
		add(new JLabel("Estilo natación: "), constraints);
		
		ajustarConstraints(1, 2, 1, 1);
		this.textFieldEstiloNatacion = new JTextField(15);
		add(this.textFieldEstiloNatacion, constraints);
		
		ajustarConstraints(0, 3, 2, 1);
		this.labelErrorEstiloNatacion = getLabelError();
		add(this.labelErrorEstiloNatacion, constraints);
		
		ajustarConstraints(0, 4, 2, 1);
		JButton botonGuardarNatacion = new JButton("Guardar");
		add(botonGuardarNatacion, constraints);
		botonGuardarNatacion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarCamposError();
				controlador.registrarNatacion(textFieldDistancia.getText(), textFieldEstiloNatacion.getText());
			}
		});
	}
	
	@Override
	public void limpiarCamposTexto() {
		this.textFieldDistancia.setText("");
		this.textFieldEstiloNatacion.setText("");
	}
	
	@Override
	public void limpiarCamposError() {
		limpiarCampoErrorDistancia();
		limpiarCampoErrorEstiloNatacion();
	}
	
	@Override
	public void mostrarErrorCampoDistancia(String mensajeError) {
		this.labelErrorDistancia.setText(mensajeError);
		this.textFieldDistancia.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	public void mostrarErrorCampoEstiloNatacion(String mensajeError) {
		this.labelErrorEstiloNatacion.setText(mensajeError);
		this.textFieldEstiloNatacion.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	private void limpiarCampoErrorDistancia() {
		this.labelErrorDistancia.setText(" ");
		this.textFieldDistancia.setBorder(UIManager.getBorder("TextField.border"));
	}
	
	private void limpiarCampoErrorEstiloNatacion() {
		this.labelErrorEstiloNatacion.setText(" ");
		this.textFieldEstiloNatacion.setBorder(UIManager.getBorder("TextField.border"));
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