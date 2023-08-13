package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.fit.actividad.vista.interfaces.ValidadorCampoDistancia;

public class PanelFormularioCiclismo extends PanelFormulario implements ValidadorCampoDistancia {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldDistancia;

	private JLabel labelErrorDistancia;

	private JTextField textFieldTipoBicicleta;

	private JLabel labelErrorTipoBicicleta;

	public PanelFormularioCiclismo() {
		super();

		ajustarConstraints(0, 0, 1, 1);
		add(new JLabel("Distancia"), constraints);

		ajustarConstraints(1, 0, 1, 1);
		this.textFieldDistancia = new JTextField(15);
		this.textFieldDistancia.setText("0.0");
		add(this.textFieldDistancia, constraints);

		ajustarConstraints(0, 1, 2, 1);
		this.labelErrorDistancia = getLabelError();
		add(this.labelErrorDistancia, constraints);

		ajustarConstraints(0, 2, 1, 1);
		add(new JLabel("Tipo bicicleta"), constraints);

		ajustarConstraints(1, 2, 1, 1);
		this.textFieldTipoBicicleta = new JTextField(15);
		add(this.textFieldTipoBicicleta, constraints);

		ajustarConstraints(0, 3, 2, 1);
		this.labelErrorTipoBicicleta = getLabelError();
		add(this.labelErrorTipoBicicleta, constraints);
	}

	@Override
	public void limpiarCamposTexto() {
		this.textFieldDistancia.setText("0.0");
		this.textFieldTipoBicicleta.setText("");
	}

	@Override
	public void limpiarCamposError() {
		limpiarCampoErrorDistancia();
		limpiarCampoErrorTipoBicicleta();
	}

	@Override
	public void mostrarErrorCampoDistancia(String mensajeError) {
		this.labelErrorDistancia.setText(mensajeError);
		this.textFieldDistancia.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	public void mostrarErrorCampoTipoBicicleta(String mensajeError) {
		this.labelErrorTipoBicicleta.setText(mensajeError);
		this.textFieldTipoBicicleta.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	private void limpiarCampoErrorDistancia() {
		this.labelErrorDistancia.setText(" ");
		this.textFieldDistancia.setBorder(UIManager.getBorder(textField_border_key));
	}

	private void limpiarCampoErrorTipoBicicleta() {
		this.labelErrorTipoBicicleta.setText(" ");
		this.textFieldTipoBicicleta.setBorder(UIManager.getBorder(textField_border_key));
	}

	public String getDistancia() {
		return this.textFieldDistancia.getText();
	}

	public String getTipoBicicleta() {
		return this.textFieldTipoBicicleta.getText();
	}
}