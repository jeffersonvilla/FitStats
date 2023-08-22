package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;
import java.sql.Time;
import java.sql.Timestamp;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.EntrenamientoGimnasio;

public class FormularioEntrenamientoGimnasio extends FormularioActividad {

	private static final long serialVersionUID = 1L;

	private JTextArea textAreaEjerciciosRealizados;
	
	private JLabel labelErrorEjerciciosRealizados;

	private JTextField textFieldDescansosEntreEjercicios;

	private JLabel labelErrorDescansosEntreEjercicios;

	private JTextField textFieldDescansosEntreSeries;

	private JLabel labelErrorDescansosEntreSeries;
	
	private String ejerciciosRealizados;
	
	private String descansoEjercicios;
	
	private String descansoSeries;

	public FormularioEntrenamientoGimnasio() {
		super();

		inicializar();
	}
	
	public FormularioEntrenamientoGimnasio(EntrenamientoGimnasio entrenamiento) {
		super(entrenamiento);
		this.ejerciciosRealizados = ejerciciosRealizados;
		this.descansoEjercicios = descansoEjercicios;
		this.descansoSeries = descansoSeries;
		
		inicializar();
	}

	private void inicializar() {
		inicializarCamposEjerciciosRealizados();
		inicializarCamposDescansoEjercicios();
		inicializarCamposDescansoSeries();
	}
	
	private void inicializarCamposEjerciciosRealizados() {
		add(new JLabel("Ejercicios realizados"));
		this.textAreaEjerciciosRealizados = new JTextArea(3, 10);
		if(this.ejerciciosRealizados != null) this.textAreaEjerciciosRealizados.setText(this.ejerciciosRealizados);
		add(this.textAreaEjerciciosRealizados, "span, grow, wrap");
		this.labelErrorEjerciciosRealizados = getLabelError();
		add(this.labelErrorEjerciciosRealizados, "span, grow, wrap");
	}
	
	private void inicializarCamposDescansoEjercicios() {
		add(new JLabel("Descansos entre ejercicios"));
		this.textFieldDescansosEntreEjercicios = new JTextField(10);
		if(this.descansoEjercicios != null) this.textFieldDescansosEntreEjercicios.setText(this.descansoEjercicios);
		add(this.textFieldDescansosEntreEjercicios, "span, grow, wrap");
		this.labelErrorDescansosEntreEjercicios = getLabelError();
		add(this.labelErrorDescansosEntreEjercicios, "span, grow, wrap");
	}
	
	private void inicializarCamposDescansoSeries() {
		add(new JLabel("Descansos entre series"));
		this.textFieldDescansosEntreSeries = new JTextField(10);
		if(this.descansoSeries != null) this.textFieldDescansosEntreSeries.setText(this.descansoSeries);
		add(this.textFieldDescansosEntreSeries, "span, grow, wrap");
		this.labelErrorDescansosEntreSeries = getLabelError();
		add(this.labelErrorDescansosEntreSeries, "span, grow, wrap");
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

	@Override
	public Actividad getActividad() {
		// TODO Auto-generated method stub
		return null;
	}
}
