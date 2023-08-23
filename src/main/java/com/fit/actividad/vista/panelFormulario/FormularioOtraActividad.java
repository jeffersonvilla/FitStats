package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.management.RuntimeErrorException;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.OtraActividad;
import com.fit.actividad.modelo.TipoActividad;

public class FormularioOtraActividad extends FormularioActividad {

	private static final long serialVersionUID = 1L;
	
	private static final String EJEMPLO_DESCRIPCION = "Ejemplo: Baile";

	private JTextArea textAreaDescripcion;

	private JLabel labelErrorDescripcion;

	private boolean descripcionValida = true;

	public FormularioOtraActividad() {
		super();

		inicializar();
	}

	public FormularioOtraActividad(OtraActividad otraActividad) {
		super(otraActividad);
		
		inicializar();
	}

	private void inicializar() {
		add(new JLabel("Descripcion"));
		this.textAreaDescripcion = new JTextArea(EJEMPLO_DESCRIPCION);
		if(actividad != null) this.textAreaDescripcion.setText(((OtraActividad) actividad).getDescripcion());
		add(this.textAreaDescripcion, "span, grow, wrap");
		this.labelErrorDescripcion = getLabelError();
		add(this.labelErrorDescripcion, "span, grow, wrap");
		
		agregarListenerDescripcion();
	}
	
	private void agregarListenerDescripcion() {
	
		this.textAreaDescripcion.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validarDescripcion();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validarDescripcion();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) { }
		});
		
		this.textAreaDescripcion.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) { }
			
			@Override
			public void focusGained(FocusEvent e) {
				if(textAreaDescripcion.getText().equals(EJEMPLO_DESCRIPCION))
					textAreaDescripcion.setText("");
			}
		});
	}
	
	@Override
	public void limpiarCamposTexto() {
		this.textAreaDescripcion.setText("");
	}

	@Override
	public void limpiarCamposError() {
		limpiarCampoErrorDescripcion();
	}
	
	private void limpiarCampoErrorDescripcion() {
		this.labelErrorDescripcion.setText(" ");
		this.textAreaDescripcion.setBorder(UIManager.getBorder(textField_border_key));
	}

	public String getDescripcion() {
		return this.textAreaDescripcion.getText();
	}

	public void mostrarErrorCampoDescripcion(String mensajeError) {
		this.labelErrorDescripcion.setText(mensajeError);
		this.textAreaDescripcion.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	private void validarDescripcion() {
		limpiarCampoErrorDescripcion();
		descripcionValida = true;
		if(getDescripcion().isBlank()) {
			mostrarErrorCampoDescripcion(MENSAJE_VALIDACION_CAMPO_VACIO);
			descripcionValida = false;
		}else if(getDescripcion().length() > OtraActividad.TAMANIO_MAXIMO_DESCRIPCION) {
			mostrarErrorCampoDescripcion(OtraActividad.MENSAJE_TAMANIO_MAXIMO_DESCRIPCION);
			descripcionValida = false;
		}

		validarInputs();
	}

	@Override
	public Actividad getActividad() {
		OtraActividad otraActividad;
		if(actividad != null) otraActividad = (OtraActividad) actividad;
		else otraActividad = new OtraActividad();
		otraActividad.setFechaHora(getFecha());
		otraActividad.setDuracion(getDuracion());
		otraActividad.setUbicacion(getUbicacion());
		otraActividad.setDescripcion(getDescripcion());
		return otraActividad;
	}

	@Override
	protected void validarInputs() {
		if(observadorInputs != null) observadorInputs.update((fechaValida() && descripcionValida)? true : false);
		else throw new RuntimeErrorException(new Error("Se debe agregar un InputsValidosObserver al FormularioOtraActividad"));
	}

	@Override
	public String getTitulo() {
		return TipoActividad.OTRA_ACTIVIDAD.getNombre();
	}

}
