package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fit.actividad.modelo.Caminata;
import com.fit.util.MensajesValidacion;
import com.fit.util.Validador;

public class FormularioCaminata extends FormularioActividad {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(FormularioCaminata.class);

	private JTextField textFieldDistancia;

	private JLabel labelErrorDistancia;

	public FormularioCaminata() {
		super();

		inicializar();
	}
	
	public FormularioCaminata(Caminata caminata) {
		super(caminata);
		
		logger.debug("En constructor {}", actividad);
		
		inicializar();
	}

	private void inicializar() {
		add(new JLabel("Distancia"));
		this.textFieldDistancia = new JTextField(15);
		this.textFieldDistancia.setText((actividad != null)? Float.toString(((Caminata) actividad).getDistancia()) :"0.0");
		add(this.textFieldDistancia, "span, grow, wrap");
		this.labelErrorDistancia = getLabelError();
		add(this.labelErrorDistancia, "span, grow, wrap");
		agregarListeners();
	}
	
	private void agregarListeners() {
		
		this.textFieldDistancia.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				logger.info("msg");
				System.out.println("eliminado");
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				logger.info("msg");
				System.out.println("agregado");
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	} 

	@Override
	public Caminata getActividad() {
		Caminata caminata;
		if(actividad != null) caminata = (Caminata) actividad;
		else caminata = new Caminata();
		caminata.setFechaHora(getFecha());
		caminata.setDuracion(getDuracion());
		caminata.setUbicacion(getUbicacion());
		caminata.setDistancia(getDistancia());
		logger.debug("{}", caminata);
		return caminata;
	}
	
	private float getDistancia() {
		logger.debug("distancia de textfield {}", this.textFieldDistancia.getText());
		
		return Float.parseFloat(this.textFieldDistancia.getText());
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

	private void validarDistancia() {
		if(Validador.validarDistancia(this.textFieldDistancia.getText())) limpiarCampoErrorDistancia();
		mostrarErrorCampoDistancia(MensajesValidacion.MENSAJE_VALIDACION_DISTANCIA);
	}
	
	private void mostrarErrorCampoDistancia(String mensajeError) {
		this.labelErrorDistancia.setText(mensajeError);
		this.textFieldDistancia.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	private void limpiarCampoErrorDistancia() {
		this.labelErrorDistancia.setText(" ");
		this.textFieldDistancia.setBorder(UIManager.getBorder(textField_border_key));
	}
}