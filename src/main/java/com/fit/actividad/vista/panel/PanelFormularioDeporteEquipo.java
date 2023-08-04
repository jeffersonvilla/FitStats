package com.fit.actividad.vista.panel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.fit.actividad.ControladorActividad;

public class PanelFormularioDeporteEquipo extends PanelFormulario {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldNombreDeporte;
	
	private JLabel labelErrorNombreDeporte;
	
	private JTextField textFieldNombreEquipos;
	
	private JLabel labelErrorNombreEquipos;
	
	private JTextField textFieldResultadoDelPartido;
	
	private JLabel labelErrorResultadoDelPartido;
	
	public PanelFormularioDeporteEquipo(final ControladorActividad controlador) {
		super();
		
		ajustarConstraints(0, 0, 1, 1);
		add(new JLabel("Nombre del deporte"), constraints);
		
		ajustarConstraints(1, 0, 1, 1);
		this.textFieldNombreDeporte = new JTextField(15);
		add(this.textFieldNombreDeporte, constraints);
		
		ajustarConstraints(0, 1, 2, 1);
		this.labelErrorNombreDeporte = getLabelError();
		add(this.labelErrorNombreDeporte, constraints);

		ajustarConstraints(0, 2, 1, 1);
		add(new JLabel("Nombre de los equipos"), constraints);
		
		ajustarConstraints(1, 2, 1, 1);
		this.textFieldNombreEquipos = new JTextField(15);
		add(this.textFieldNombreEquipos, constraints);
		
		ajustarConstraints(0, 3, 2, 1);
		this.labelErrorNombreEquipos = getLabelError();
		add(this.labelErrorNombreEquipos, constraints);
		
		ajustarConstraints(0, 4, 1, 1);
		add(new JLabel("Resultado del partido"), constraints);
		
		ajustarConstraints(1, 4, 1, 1);
		this.textFieldResultadoDelPartido = new JTextField(15);
		add(this.textFieldResultadoDelPartido, constraints);
		
		ajustarConstraints(0, 5, 2, 1);
		this.labelErrorResultadoDelPartido = getLabelError();
		add(this.labelErrorResultadoDelPartido, constraints);
	}

	@Override
	public void limpiarCamposTexto() {
		this.textFieldNombreDeporte.setText("");
		this.textFieldNombreEquipos.setText("");
		this.textFieldResultadoDelPartido.setText("");
	}

	@Override
	public void limpiarCamposError() {
		limpiarCampoErrorNombreDeporte();
		limpiarCampoErrorNombreEquipos();
		limpiarCampoErrorResultadoDelPartido();
	}

	public void mostrarErrorCampoNombreDeporte(String mensajeError) {
		this.labelErrorNombreDeporte.setText(mensajeError);
		this.textFieldNombreDeporte.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	public void mostrarErrorNombreEquipos(String mensajeError) {
		this.labelErrorNombreEquipos.setText(mensajeError);
		this.textFieldNombreEquipos.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	public void mostrarErrorCampoResultadoDelPartido(String mensajeError) {
		this.labelErrorResultadoDelPartido.setText(mensajeError);
		this.textFieldResultadoDelPartido.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	private void limpiarCampoErrorNombreDeporte() {
		this.labelErrorNombreDeporte.setText(" ");
		this.textFieldNombreDeporte.setBorder(UIManager.getBorder("TextField.border"));
	}

	private void limpiarCampoErrorNombreEquipos() {
		this.labelErrorNombreEquipos.setText(" ");
		this.textFieldNombreEquipos.setBorder(UIManager.getBorder("TextField.border"));
	}
	
	private void limpiarCampoErrorResultadoDelPartido() {
		this.labelErrorResultadoDelPartido.setText(" ");
		this.textFieldResultadoDelPartido.setBorder(UIManager.getBorder("TextField.border"));
	}

	public String getNombreDeporte() {
		return this.textFieldNombreDeporte.getText();
	}

	public String getNombreEquipos() {
		return this.textFieldNombreEquipos.getText();
	}

	public String getResultadoDelPartido() {
		return this.textFieldResultadoDelPartido.getText();
	}
}
