package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;
import java.sql.Time;
import java.sql.Timestamp;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.Ciclismo;

public class FormularioCiclismo extends FormularioActividad{

	private static final long serialVersionUID = 1L;

	private JTextField textFieldDistancia;

	private JLabel labelErrorDistancia;

	private JTextField textFieldTipoBicicleta;

	private JLabel labelErrorTipoBicicleta;
	
	private String distancia;
	
	private String tipoBicicleta;

	public FormularioCiclismo() {
		super();
		
		inicializar();
	}

	public FormularioCiclismo(Ciclismo ciclismo) {
		super(ciclismo);
		this.distancia = distancia;
		this.tipoBicicleta = tipoBicicleta;
		
		inicializar();
	}

	private void inicializar() {
		inicializarCamposDistancia();
		inicializarCamposTipoBicicleta();
	}
	
	private void inicializarCamposDistancia() {
		add(new JLabel("Distancia"));
		this.textFieldDistancia = new JTextField(15);
		this.textFieldDistancia.setText((distancia != null)? distancia :"0.0");
		add(this.textFieldDistancia, "span, grow, wrap");
		this.labelErrorDistancia = getLabelError();
		add(this.labelErrorDistancia, "span, grow, wrap");
	}
	
	private void inicializarCamposTipoBicicleta() {
		add(new JLabel("Tipo bicicleta"));
		this.textFieldTipoBicicleta = new JTextField(15);
		if(tipoBicicleta != null) this.textFieldTipoBicicleta.setText(tipoBicicleta);
		add(this.textFieldTipoBicicleta, "span, grow, wrap");
		this.labelErrorTipoBicicleta = getLabelError();
		add(this.labelErrorTipoBicicleta, "span, grow, wrap");
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

	@Override
	public Actividad getActividad() {
		// TODO Auto-generated method stub
		return null;
	}
}