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

public class PanelFormularioDeporteEquipo extends PanelFormulario {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldNombreDeporte;
	
	private JLabel labelErrorNombreDeporte;
	
	private JTextField textFieldResultadoDelPartido;
	
	private JLabel labelErrorResultadoDelPartido;
	
	private JTextField textFieldDuracionDelPartido;
	
	private JLabel labelErrorDuracionDelPartido;
	
	public PanelFormularioDeporteEquipo(final ControladorActividad controlador) {
		super();
		
		ajustarConstraints(0, 0, 1, 1);
		add(new JLabel("Nombre del deporte:"), constraints);
		
		ajustarConstraints(1, 0, 1, 1);
		this.textFieldNombreDeporte = new JTextField(15);
		add(this.textFieldNombreDeporte, constraints);
		
		ajustarConstraints(0, 1, 2, 1);
		this.labelErrorNombreDeporte = getLabelError();
		add(this.labelErrorNombreDeporte, constraints);
		
		ajustarConstraints(0, 2, 1, 1);
		add(new JLabel("Resultado del partido:"), constraints);
		
		ajustarConstraints(1, 2, 1, 1);
		this.textFieldResultadoDelPartido = new JTextField(15);
		add(this.textFieldResultadoDelPartido, constraints);
		
		ajustarConstraints(0, 3, 2, 1);
		this.labelErrorResultadoDelPartido = getLabelError();
		add(this.labelErrorResultadoDelPartido, constraints);
		
		ajustarConstraints(0, 4, 1, 1);
		add(new JLabel("Duracion del partido (minutos):"), constraints);
		
		ajustarConstraints(1, 4, 1, 1);
		this.textFieldDuracionDelPartido = new JTextField(15);
		add(this.textFieldDuracionDelPartido, constraints);
		
		ajustarConstraints(0, 5, 2, 1);
		this.labelErrorDuracionDelPartido = getLabelError();
		add(this.labelErrorDuracionDelPartido, constraints);
		
		ajustarConstraints(0, 6, 2, 1);
		JButton botonGuardarDeporteEquipo = new JButton("Guardar");
		add(botonGuardarDeporteEquipo, constraints);
		botonGuardarDeporteEquipo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarCamposError();
				/*controlador.registrarDeporteEquipo(
						textFieldNombreDeporte.getText(),
						textFieldResultadoDelPartido.getText(),
						textFieldDuracionDelPartido.getText()
						);*/
			}
		});
	}

	@Override
	public void limpiarCamposTexto() {
		this.textFieldNombreDeporte.setText("");
		this.textFieldResultadoDelPartido.setText("");
		this.textFieldDuracionDelPartido.setText("");
	}

	@Override
	public void limpiarCamposError() {
		limpiarCampoErrorNombreDeporte();
		limpiarCampoErrorResultadoDelPartido();
		limpiarCampoErrorDuracionDelPartido();
	}

	public void mostrarErrorCampoNombreDeporte(String mensajeError) {
		this.labelErrorNombreDeporte.setText(mensajeError);
		this.textFieldNombreDeporte.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	public void mostrarErrorCampoResultadoDelPartido(String mensajeError) {
		this.labelErrorResultadoDelPartido.setText(mensajeError);
		this.textFieldResultadoDelPartido.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	public void mostrarErrorCampoDuracionDelPartido(String mensajeError) {
		this.labelErrorDuracionDelPartido.setText(mensajeError);
		this.textFieldDuracionDelPartido.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	private void limpiarCampoErrorNombreDeporte() {
		this.labelErrorNombreDeporte.setText(" ");
		this.textFieldNombreDeporte.setBorder(UIManager.getBorder("TextField.border"));
	}
	
	private void limpiarCampoErrorResultadoDelPartido() {
		this.labelErrorResultadoDelPartido.setText(" ");
		this.textFieldResultadoDelPartido.setBorder(UIManager.getBorder("TextField.border"));
	}

	private void limpiarCampoErrorDuracionDelPartido() {
		this.labelErrorDuracionDelPartido.setText(" ");
		this.textFieldDuracionDelPartido.setBorder(UIManager.getBorder("TextField.border"));
	}
}
