package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;
import java.sql.Time;
import java.sql.Timestamp;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class FormularioCaminata extends FormularioActividad {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldDistancia;

	private JLabel labelErrorDistancia;
	
	private String distancia;

	public FormularioCaminata() {
		super();

		inicializar();
	}
	
	public FormularioCaminata(Timestamp fechaHora, Time duracion, String ubicacion, String distancia) {
		super(fechaHora, duracion, ubicacion);
		this.distancia = distancia;
		
		inicializar();
	}

	public void inicializar() {
		add(new JLabel("Distancia"));
		this.textFieldDistancia = new JTextField(15);
		this.textFieldDistancia.setText((distancia != null)? distancia :"0.0");
		add(this.textFieldDistancia, "span, grow, wrap");
		this.labelErrorDistancia = getLabelError();
		add(this.labelErrorDistancia, "span, grow, wrap");
	
	}

	public String getDistancia() {
		return this.textFieldDistancia.getText();
	}

	@Override
	public void limpiarCamposTexto() {
		this.textFieldDistancia.setText("0.0");
	}

	@Override
	public void limpiarCamposError() {
		super.limpiarCamposError();
		limpiarCampoErrorDistancia();
	}

	public void mostrarErrorCampoDistancia(String mensajeError) {
		this.labelErrorDistancia.setText(mensajeError);
		this.textFieldDistancia.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	private void limpiarCampoErrorDistancia() {
		this.labelErrorDistancia.setText(" ");
		this.textFieldDistancia.setBorder(UIManager.getBorder(textField_border_key));
	}
}