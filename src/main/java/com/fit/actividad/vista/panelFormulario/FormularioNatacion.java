package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;
import java.sql.Time;
import java.sql.Timestamp;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class FormularioNatacion extends FormularioActividad {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldDistancia;

	private JLabel labelErrorDistancia;

	private JTextField textFieldEstiloNatacion;

	private JLabel labelErrorEstiloNatacion;

	private String distancia;
	
	private String estiloNatacion;
	
	public FormularioNatacion() {
		super();

		inicializar();
	}
	
	public FormularioNatacion(Timestamp fechaHora, Time duracion, String ubicacion,
			String distancia, String estiloNatacion) {
		super(fechaHora, duracion, ubicacion);
		this.distancia = distancia;
		this.estiloNatacion = estiloNatacion;
		
		inicializar();
	}

	private void inicializar() {
		inicializarCamposDistancia();
		inicializarCamposEstilosNatacion();
	}

	private void inicializarCamposDistancia() {
		add(new JLabel("Distancia"));
		this.textFieldDistancia = new JTextField(15);
		this.textFieldDistancia.setText((this.distancia != null) ? this.distancia : "0.0");
		add(this.textFieldDistancia, "span, grow, wrap");
		this.labelErrorDistancia = getLabelError();
		add(this.labelErrorDistancia, "span, grow, wrap");
	}
	
	private void inicializarCamposEstilosNatacion() {
		add(new JLabel("Estilos natación"));
		this.textFieldEstiloNatacion = new JTextField(15);
		if(this.estiloNatacion != null) this.textFieldEstiloNatacion.setText(this.estiloNatacion);
		add(this.textFieldEstiloNatacion, "span, grow, wrap");
		this.labelErrorEstiloNatacion = getLabelError();
		add(this.labelErrorEstiloNatacion, "span, grow, wrap");
	}
	
	@Override
	public void limpiarCamposTexto() {
		this.textFieldDistancia.setText("0.0");
		this.textFieldEstiloNatacion.setText("");
	}

	@Override
	public void limpiarCamposError() {
		limpiarCampoErrorDistancia();
		limpiarCampoErrorEstiloNatacion();
	}

	public void mostrarErrorCampoDistancia(String mensajeError) {
		this.labelErrorDistancia.setText(mensajeError);
		this.textFieldDistancia.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	public void mostrarErrorCampoEstilosNatacion(String mensajeError) {
		this.labelErrorEstiloNatacion.setText(mensajeError);
		this.textFieldEstiloNatacion.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	private void limpiarCampoErrorDistancia() {
		this.labelErrorDistancia.setText(" ");
		this.textFieldDistancia.setBorder(UIManager.getBorder(textField_border_key));
	}

	private void limpiarCampoErrorEstiloNatacion() {
		this.labelErrorEstiloNatacion.setText(" ");
		this.textFieldEstiloNatacion.setBorder(UIManager.getBorder(textField_border_key));
	}

	public String getDistancia() {
		return this.textFieldDistancia.getText();
	}

	public String getEstiloNatacion() {
		return this.textFieldEstiloNatacion.getText();
	}
}
