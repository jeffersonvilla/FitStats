package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class PanelFomularioEntrenamientoGimnasio extends PanelFormulario {

	private static final long serialVersionUID = 1L;

	private JTextArea textAreaEjerciciosRealizados;

	private JTextField textFieldDescansosEntreEjercicios;

	private JLabel labelErrorDescansosEntreEjercicios;

	private JTextField textFieldDescansosEntreSeries;

	private JLabel labelErrorDescansosEntreSeries;
	
	private String ejerciciosRealizados;
	
	private String descansoEjercicios;
	
	private String descansoSeries;

	public PanelFomularioEntrenamientoGimnasio() {
		super();

		inicializar();
	}
	
	public PanelFomularioEntrenamientoGimnasio(String ejerciciosRealizados, String descansoEjercicios,
			String descansoSeries) {
		super();
		this.ejerciciosRealizados = ejerciciosRealizados;
		this.descansoEjercicios = descansoEjercicios;
		this.descansoSeries = descansoSeries;
		
		inicializar();
	}

	private void inicializar() {
		ajustarConstraints(0, 0, 1, 1);
		add(new JLabel("Ejercicios realizados"), constraints);

		ajustarConstraints(1, 0, 1, 1);
		this.textAreaEjerciciosRealizados = new JTextArea(3, 10);
		if(this.ejerciciosRealizados != null) this.textAreaEjerciciosRealizados.setText(this.ejerciciosRealizados);
		add(this.textAreaEjerciciosRealizados, constraints);

		ajustarConstraints(0, 1, 1, 1);
		add(new JLabel("Descansos entre ejercicios"), constraints);

		ajustarConstraints(1, 1, 1, 1);
		this.textFieldDescansosEntreEjercicios = new JTextField(10);
		if(this.descansoEjercicios != null) this.textFieldDescansosEntreEjercicios.setText(this.descansoEjercicios);
		add(this.textFieldDescansosEntreEjercicios, constraints);

		ajustarConstraints(0, 2, 2, 1);
		this.labelErrorDescansosEntreEjercicios = getLabelError();
		add(this.labelErrorDescansosEntreEjercicios, constraints);

		ajustarConstraints(0, 3, 1, 1);
		add(new JLabel("Descansos entre series"), constraints);

		ajustarConstraints(1, 3, 1, 1);
		this.textFieldDescansosEntreSeries = new JTextField(10);
		if(this.descansoSeries != null) this.textFieldDescansosEntreSeries.setText(this.descansoSeries);
		add(this.textFieldDescansosEntreSeries, constraints);

		ajustarConstraints(0, 4, 2, 1);
		this.labelErrorDescansosEntreSeries = getLabelError();
		add(this.labelErrorDescansosEntreSeries, constraints);
	}

	@Override
	public void limpiarCamposTexto() {
		this.textAreaEjerciciosRealizados.setText("");
		this.textFieldDescansosEntreEjercicios.setText("");
		this.textFieldDescansosEntreSeries.setText("");
	}

	@Override
	public void limpiarCamposError() {
		this.labelErrorDescansosEntreEjercicios.setText(" ");
		this.textFieldDescansosEntreEjercicios.setBorder(UIManager.getBorder(textField_border_key));
		this.labelErrorDescansosEntreSeries.setText(" ");
		this.textFieldDescansosEntreSeries.setBorder(UIManager.getBorder(textField_border_key));
	}

	public String getEjerciciosRealizados() {
		return this.textAreaEjerciciosRealizados.getText();
	}

	public String getDescansosEntreEjercicios() {
		return this.textFieldDescansosEntreEjercicios.getText();
	}

	public String getDescansosEntreSeries() {
		return this.textFieldDescansosEntreSeries.getText();
	}

	public void mostrarErrorCampoDescansoEntreEjercicios(String mensajeError) {
		this.labelErrorDescansosEntreEjercicios.setText(mensajeError);
		this.textFieldDescansosEntreEjercicios.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	public void mostrarErrorCampoDescansosEntreSeries(String mensajeError) {
		this.labelErrorDescansosEntreSeries.setText(mensajeError);
		this.textFieldDescansosEntreSeries.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
}
