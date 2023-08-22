package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;
import java.sql.Time;
import java.sql.Timestamp;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.DeporteEquipo;

public class FormularioDeporteEquipo extends FormularioActividad {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldNombreDeporte;

	private JLabel labelErrorNombreDeporte;

	private JTextField textFieldNombreEquipos;

	private JLabel labelErrorNombreEquipos;

	private JTextField textFieldResultadoDelPartido;

	private JLabel labelErrorResultadoDelPartido;
	
	private String nombreDeporte;
	
	private String nombreEquipos;
	
	private String resultadoPartido;

	public FormularioDeporteEquipo() {
		super();

		inicializar();
	}

	public FormularioDeporteEquipo(DeporteEquipo deporteEquipo) {
		super(deporteEquipo);
		this.nombreDeporte = nombreDeporte;
		this.nombreEquipos = nombreEquipos;
		this.resultadoPartido = resultadoPartido;
		
		inicializar();
	}

	private void inicializar() {
		inicializarCamposNombreDeporte();
		inicializarCamposNombreEquipos();
		inicializarCamposResultadoPartido();
	}
	
	private void inicializarCamposNombreDeporte() {
		add(new JLabel("Nombre del deporte"));
		this.textFieldNombreDeporte = new JTextField(15);
		if(this.nombreDeporte != null) this.textFieldNombreDeporte.setText(this.nombreDeporte);
		add(this.textFieldNombreDeporte, "span, grow, wrap");
		this.labelErrorNombreDeporte = getLabelError();
		add(this.labelErrorNombreDeporte, "span, grow, wrap");
	}
	
	private void inicializarCamposNombreEquipos() {
		add(new JLabel("Nombre de los equipos"));
		this.textFieldNombreEquipos = new JTextField(15);
		if(this.nombreEquipos != null) this.textFieldNombreEquipos.setText(this.nombreEquipos);
		add(this.textFieldNombreEquipos, "span, grow, wrap");
		this.labelErrorNombreEquipos = getLabelError();
		add(this.labelErrorNombreEquipos, "span, grow, wrap");
	}
	
	private void inicializarCamposResultadoPartido() {
		add(new JLabel("Resultado del partido"));
		this.textFieldResultadoDelPartido = new JTextField(15);
		if(this.resultadoPartido != null) this.textFieldResultadoDelPartido.setText(this.resultadoPartido);
		add(this.textFieldResultadoDelPartido, "span, grow, wrap");
		this.labelErrorResultadoDelPartido = getLabelError();
		add(this.labelErrorResultadoDelPartido, "span, grow, wrap");
	}
	
	@Override
	public void limpiarCamposTexto() {
		this.textFieldNombreDeporte.setText("");
		this.textFieldNombreEquipos.setText("");
		this.textFieldResultadoDelPartido.setText("");
	}

	@Override
	public void limpiarCamposError() {
		limpiarCampoErrorNombreDeporte();
		limpiarCampoErrorNombreEquipos();
		limpiarCampoErrorResultadoDelPartido();
	}

	public void mostrarErrorCampoNombreDeporte(String mensajeError) {
		this.labelErrorNombreDeporte.setText(mensajeError);
		this.textFieldNombreDeporte.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	public void mostrarErrorNombreEquipos(String mensajeError) {
		this.labelErrorNombreEquipos.setText(mensajeError);
		this.textFieldNombreEquipos.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	public void mostrarErrorCampoResultadoDelPartido(String mensajeError) {
		this.labelErrorResultadoDelPartido.setText(mensajeError);
		this.textFieldResultadoDelPartido.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	private void limpiarCampoErrorNombreDeporte() {
		this.labelErrorNombreDeporte.setText(" ");
		this.textFieldNombreDeporte.setBorder(UIManager.getBorder(textField_border_key));
	}

	private void limpiarCampoErrorNombreEquipos() {
		this.labelErrorNombreEquipos.setText(" ");
		this.textFieldNombreEquipos.setBorder(UIManager.getBorder(textField_border_key));
	}

	private void limpiarCampoErrorResultadoDelPartido() {
		this.labelErrorResultadoDelPartido.setText(" ");
		this.textFieldResultadoDelPartido.setBorder(UIManager.getBorder(textField_border_key));
	}

	public String getNombreDeporte() {
		return this.textFieldNombreDeporte.getText();
	}

	public String getNombreEquipos() {
		return this.textFieldNombreEquipos.getText();
	}

	public String getResultadoDelPartido() {
		return this.textFieldResultadoDelPartido.getText();
	}

	@Override
	public Actividad getActividad() {
		// TODO Auto-generated method stub
		return null;
	}
}
