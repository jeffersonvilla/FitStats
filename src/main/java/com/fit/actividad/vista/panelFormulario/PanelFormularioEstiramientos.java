package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class PanelFormularioEstiramientos extends PanelFormulario {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldTipoSesion;

	private JLabel labelErrorTipoSesion;

	private JTextField textFieldNivelDificultad;

	private JLabel labelErrorNivelDificultad;
	
	private String tipoSesion;
	
	private String nivelDificultad;

	public PanelFormularioEstiramientos() {
		super();

		inicializar();
	}
	
	public PanelFormularioEstiramientos(String tipoSesion, String nivelDificultad) {
		super();
		this.tipoSesion = tipoSesion;
		this.nivelDificultad = nivelDificultad;
		
		inicializar();
	}

	private void inicializar() {
		ajustarConstraints(0, 0, 1, 1);
		add(new JLabel("Tipo de sesion"), constraints);

		ajustarConstraints(1, 0, 1, 1);
		this.textFieldTipoSesion = new JTextField(15);
		if(this.tipoSesion != null) this.textFieldTipoSesion.setText(this.tipoSesion);
		add(this.textFieldTipoSesion, constraints);

		ajustarConstraints(0, 1, 2, 1);
		this.labelErrorTipoSesion = getLabelError();
		add(this.labelErrorTipoSesion, constraints);

		ajustarConstraints(0, 2, 1, 1);
		add(new JLabel("Nivel de dificultad"), constraints);

		ajustarConstraints(1, 2, 1, 1);
		this.textFieldNivelDificultad = new JTextField(15);
		if(this.nivelDificultad != null) this.textFieldNivelDificultad.setText(this.nivelDificultad);
		add(this.textFieldNivelDificultad, constraints);

		ajustarConstraints(0, 3, 2, 1);
		this.labelErrorNivelDificultad = getLabelError();
		add(this.labelErrorNivelDificultad, constraints);
	}
	
	@Override
	public void limpiarCamposTexto() {
		this.textFieldTipoSesion.setText("");
		this.textFieldNivelDificultad.setText("");
	}

	@Override
	public void limpiarCamposError() {
		this.labelErrorTipoSesion.setText(" ");
		this.textFieldTipoSesion.setBorder(UIManager.getBorder(textField_border_key));
		this.labelErrorNivelDificultad.setText(" ");
		this.textFieldNivelDificultad.setBorder(UIManager.getBorder(textField_border_key));
	}

	public String getTipoSesion() {
		return this.textFieldTipoSesion.getText();
	}

	public String getNivelDificultad() {
		return this.textFieldNivelDificultad.getText();
	}

	public void mostrarErrorCampoTipoSesion(String mensajeError) {
		this.labelErrorTipoSesion.setText(mensajeError);
		this.textFieldTipoSesion.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	public void mostrarErrorCampoNivelDificultad(String mensajeError) {
		this.labelErrorNivelDificultad.setText(mensajeError);
		this.textFieldNivelDificultad.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
}
