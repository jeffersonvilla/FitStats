package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;
import java.sql.Time;
import java.sql.Timestamp;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.Estiramientos;

public class FormularioEstiramientos extends FormularioActividad {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldTipoSesion;

	private JLabel labelErrorTipoSesion;

	private JTextField textFieldNivelDificultad;

	private JLabel labelErrorNivelDificultad;
	
	private String tipoSesion;
	
	private String nivelDificultad;

	public FormularioEstiramientos() {
		super();

		inicializar();
	}
	
	public FormularioEstiramientos(Estiramientos estiramientos) {
		super(estiramientos);
		this.tipoSesion = tipoSesion;
		this.nivelDificultad = nivelDificultad;
		
		inicializar();
	}

	private void inicializar() {
		inicializarCamposTipoSesion();
		inicializarCamposNivelDificultad();
	}
	
	private void inicializarCamposTipoSesion() {
		add(new JLabel("Tipo de sesion"));
		this.textFieldTipoSesion = new JTextField(15);
		if(this.tipoSesion != null) this.textFieldTipoSesion.setText(this.tipoSesion);
		add(this.textFieldTipoSesion, "span, grow, wrap");
		this.labelErrorTipoSesion = getLabelError();
		add(this.labelErrorTipoSesion,  "span, grow, wrap");
	}
	
	private void inicializarCamposNivelDificultad() {
		add(new JLabel("Nivel de dificultad"));
		this.textFieldNivelDificultad = new JTextField(15);
		if(this.nivelDificultad != null) this.textFieldNivelDificultad.setText(this.nivelDificultad);
		add(this.textFieldNivelDificultad, "span, grow, wrap");
		this.labelErrorNivelDificultad = getLabelError();
		add(this.labelErrorNivelDificultad, "span, grow, wrap");
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

	@Override
	public Actividad getActividad() {
		// TODO Auto-generated method stub
		return null;
	}
}
