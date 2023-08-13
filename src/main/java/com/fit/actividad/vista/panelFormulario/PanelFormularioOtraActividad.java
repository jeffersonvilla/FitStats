package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class PanelFormularioOtraActividad extends PanelFormulario {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldDescripcion;

	private JLabel labelErrorDescripcion;

	public PanelFormularioOtraActividad() {
		super();

		ajustarConstraints(0, 0, 1, 1);
		add(new JLabel("Descripcion"), constraints);

		ajustarConstraints(1, 0, 1, 1);
		this.textFieldDescripcion = new JTextField(15);
		add(this.textFieldDescripcion, constraints);

		ajustarConstraints(0, 1, 2, 1);
		this.labelErrorDescripcion = getLabelError();
		add(this.labelErrorDescripcion, constraints);
	}

	@Override
	public void limpiarCamposTexto() {
		this.textFieldDescripcion.setText("");
	}

	@Override
	public void limpiarCamposError() {
		this.labelErrorDescripcion.setText(" ");
		this.textFieldDescripcion.setBorder(UIManager.getBorder(textField_border_key));
	}

	public String getDescripcion() {
		return this.textFieldDescripcion.getText();
	}

	public void mostrarErrorCampoDescripcion(String mensajeError) {
		this.labelErrorDescripcion.setText(mensajeError);
		this.textFieldDescripcion.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

}
