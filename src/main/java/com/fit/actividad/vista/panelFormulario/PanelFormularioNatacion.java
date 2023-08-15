package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.fit.actividad.vista.interfaces.ValidadorCampoDistancia;

public class PanelFormularioNatacion extends PanelFormulario implements ValidadorCampoDistancia {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldDistancia;

	private JLabel labelErrorDistancia;

	private JTextField textFieldEstiloNatacion;

	private JLabel labelErrorEstiloNatacion;

	private String distancia;
	
	private String estiloNatacion;
	
	public PanelFormularioNatacion() {
		super();

		inicializar();
	}
	
	public PanelFormularioNatacion(String distancia, String estiloNatacion) {
		super();
		this.distancia = distancia;
		this.estiloNatacion = estiloNatacion;
		
		inicializar();
	}

	private void inicializar() {
		ajustarConstraints(0, 0, 1, 1);
		add(new JLabel("Distancia"), constraints);

		ajustarConstraints(1, 0, 1, 1);
		this.textFieldDistancia = new JTextField(15);
		this.textFieldDistancia.setText((this.distancia != null) ? this.distancia : "0.0");
		add(this.textFieldDistancia, constraints);

		ajustarConstraints(0, 1, 2, 1);
		this.labelErrorDistancia = getLabelError();
		add(this.labelErrorDistancia, constraints);

		ajustarConstraints(0, 2, 1, 1);
		add(new JLabel("Estilos natación"), constraints);

		ajustarConstraints(1, 2, 1, 1);
		this.textFieldEstiloNatacion = new JTextField(15);
		if(this.estiloNatacion != null) this.textFieldEstiloNatacion.setText(this.estiloNatacion);
		add(this.textFieldEstiloNatacion, constraints);

		ajustarConstraints(0, 3, 2, 1);
		this.labelErrorEstiloNatacion = getLabelError();
		add(this.labelErrorEstiloNatacion, constraints);
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

	@Override
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
