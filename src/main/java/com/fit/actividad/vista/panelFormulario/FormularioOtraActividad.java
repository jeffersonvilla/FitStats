package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;
import java.sql.Time;
import java.sql.Timestamp;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class FormularioOtraActividad extends FormularioActividad {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldDescripcion;

	private JLabel labelErrorDescripcion;
	
	private String descripcion;

	public FormularioOtraActividad() {
		super();

		inicializar();
	}

	public FormularioOtraActividad(Timestamp fechaHora, Time duracion, String ubicacion, String descripcion) {
		super(fechaHora, duracion, ubicacion);
		this.descripcion = descripcion;
		
		inicializar();
	}

	private void inicializar() {
		add(new JLabel("Descripcion"));
		this.textFieldDescripcion = new JTextField(15);
		if(this.descripcion != null) this.textFieldDescripcion.setText(this.descripcion);
		add(this.textFieldDescripcion, "span, grow, wrap");
		this.labelErrorDescripcion = getLabelError();
		add(this.labelErrorDescripcion, "span, grow, wrap");
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
