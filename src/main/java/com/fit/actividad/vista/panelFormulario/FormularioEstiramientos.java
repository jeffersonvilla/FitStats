package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;

import javax.management.RuntimeErrorException;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.Estiramientos;
import com.fit.actividad.modelo.TipoActividad;

public class FormularioEstiramientos extends FormularioActividad {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldTipoSesion;

	private JLabel labelErrorTipoSesion;

	private JTextField textFieldNivelDificultad;

	private JLabel labelErrorNivelDificultad;

	private boolean tipoSesionValido = true;
	
	private boolean nivelDificultadValido = true;

	public FormularioEstiramientos() {
		super();

		inicializar();
	}
	
	public FormularioEstiramientos(Estiramientos estiramientos) {
		super(estiramientos);
		
		inicializar();
	}

	private void inicializar() {
		inicializarCamposTipoSesion();
		inicializarCamposNivelDificultad();
	}
	
	private void inicializarCamposTipoSesion() {
		add(new JLabel("Tipo de sesion"));
		this.textFieldTipoSesion = new JTextField(15);
		if(actividad != null) this.textFieldTipoSesion.setText(((Estiramientos) actividad).getTipoSesion());
		add(this.textFieldTipoSesion, "span, grow, wrap");
		this.labelErrorTipoSesion = getLabelError();
		add(this.labelErrorTipoSesion,  "span, grow, wrap");
		
		agregarListenerTipoSesion();
	}
	
	private void agregarListenerTipoSesion() {
	
		this.textFieldTipoSesion.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validarTipoSesion();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validarTipoSesion();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) { }
		});
	}
	
	private void inicializarCamposNivelDificultad() {
		add(new JLabel("Nivel de dificultad"));
		this.textFieldNivelDificultad = new JTextField(15);
		if(actividad != null) this.textFieldNivelDificultad.setText(((Estiramientos) actividad).getNivelDificultad());
		add(this.textFieldNivelDificultad, "span, grow, wrap");
		this.labelErrorNivelDificultad = getLabelError();
		add(this.labelErrorNivelDificultad, "span, grow, wrap");
		
		agregarListenerNivelDificultad();
	}
	
	private void agregarListenerNivelDificultad() {
	
		this.textFieldNivelDificultad.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validarNivelDificultad();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validarNivelDificultad();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) { }
		});
	}
	
	@Override
	public void limpiarCamposTexto() {
		this.textFieldTipoSesion.setText("");
		this.textFieldNivelDificultad.setText("");
	}

	@Override
	public void limpiarCamposError() {
		limpiarCampoErrorTipoSesion();
		limpiarCampoErrorNivelDificultad();
	}
	
	private void limpiarCampoErrorTipoSesion() {
		this.labelErrorTipoSesion.setText(" ");
		this.textFieldTipoSesion.setBorder(UIManager.getBorder(textField_border_key));	}
	
	private void limpiarCampoErrorNivelDificultad() {
		this.labelErrorNivelDificultad.setText(" ");
		this.textFieldNivelDificultad.setBorder(UIManager.getBorder(textField_border_key));
	}

	public String getTipoSesion() {
		return this.textFieldTipoSesion.getText();
	}

	public String getNivelDificultad() {
		return this.textFieldNivelDificultad.getText();
	}

	public void mostrarErrorCampoTipoSesion(String mensajeError) {
		this.labelErrorTipoSesion.setText(mensajeError);
		this.textFieldTipoSesion.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	public void mostrarErrorCampoNivelDificultad(String mensajeError) {
		this.labelErrorNivelDificultad.setText(mensajeError);
		this.textFieldNivelDificultad.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	private void validarTipoSesion() {
		limpiarCampoErrorTipoSesion();
		tipoSesionValido = true;
		if(getTipoSesion().length() > Estiramientos.TAMANIO_MAXIMO_TIPO_SESION) {
			mostrarErrorCampoTipoSesion(Estiramientos.MENSAJE_TAMANIO_MAXIMO_TIPO_SESION);
			tipoSesionValido = false;
		}

		validarInputs();
	}
	
	private void validarNivelDificultad() {
		limpiarCampoErrorNivelDificultad();
		nivelDificultadValido = true;
		if(getNivelDificultad().length() > Estiramientos.TAMANIO_MAXIMO_NIVEL_DIFICULTAD) {
			mostrarErrorCampoNivelDificultad(Estiramientos.MENSAJE_TAMANIO_MAXIMO_NIVEL_DIFICULTAD);
			nivelDificultadValido = false;
		}
		
		validarInputs();
	}
	
	@Override
	public Actividad getActividad() {
		Estiramientos estiramientos;
		if(actividad != null) estiramientos = (Estiramientos) actividad;
		else estiramientos = new Estiramientos();
		estiramientos.setFechaHora(getFecha());
		estiramientos.setDuracion(getDuracion());
		estiramientos.setUbicacion(getUbicacion());
		estiramientos.setTipoSesion(getTipoSesion());
		estiramientos.setNivelDificultad(getNivelDificultad());
		return estiramientos;
	}

	@Override
	protected void validarInputs() {
		if(observadorInputs != null) 
			observadorInputs.update((fechaValida() && tipoSesionValido && nivelDificultadValido)? true : false);
		else throw new RuntimeErrorException(new Error("Se debe agregar un InputsValidosObserver al FormularioEstiramientos"));
	}

	@Override
	public String getTitulo() {
		return TipoActividad.ESTIRAMIENTOS.getNombre();
	}
}
