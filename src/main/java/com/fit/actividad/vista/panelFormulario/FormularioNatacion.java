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
import com.fit.actividad.modelo.Natacion;
import com.fit.actividad.modelo.TipoActividad;
import com.fit.util.MensajesValidacion;
import com.fit.util.Validador;

public class FormularioNatacion extends FormularioActividad {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldDistancia;

	private JLabel labelErrorDistancia;

	private JTextField textFieldEstiloNatacion;

	private JLabel labelErrorEstiloNatacion;

	private boolean distanciaValida = true;
	
	private boolean estiloNatacionValido = true;
	
	public FormularioNatacion() {
		super();

		inicializar();
	}
	
	public FormularioNatacion(Natacion natacion) {
		super(natacion);

		inicializar();
	}

	private void inicializar() {
		inicializarCamposDistancia();
		inicializarCamposEstilosNatacion();
	}

	private void inicializarCamposDistancia() {
		add(new JLabel("Distancia"));
		this.textFieldDistancia = new JTextField(15);
		this.textFieldDistancia.setText((actividad != null) ? Float.toString(((Natacion) actividad).getDistancia()) : "0.0");
		add(this.textFieldDistancia, "span, grow, wrap");
		this.labelErrorDistancia = getLabelError();
		add(this.labelErrorDistancia, "span, grow, wrap");
		
		agregarListenerDistancia();
	}
	
	private void agregarListenerDistancia() {
		
		this.textFieldDistancia.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validarDistancia();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validarDistancia();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) { }
		});
	}
	
	private void inicializarCamposEstilosNatacion() {
		add(new JLabel("Estilos natación"));
		this.textFieldEstiloNatacion = new JTextField(15);
		if(actividad != null) this.textFieldEstiloNatacion.setText(((Natacion) actividad).getEstilosNatacion());
		add(this.textFieldEstiloNatacion, "span, grow, wrap");
		this.labelErrorEstiloNatacion = getLabelError();
		add(this.labelErrorEstiloNatacion, "span, grow, wrap");
		
		agregarListenerEstilosNatacion();
	}
	
	private void agregarListenerEstilosNatacion() {
	
		this.textFieldEstiloNatacion.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validarEstilosNatacion();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validarEstilosNatacion();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) { }
		});
	}
	
	@Override
	public void limpiarCamposTexto() {
		this.textFieldDistancia.setText("0.0");
		this.textFieldEstiloNatacion.setText("");
	}

	@Override
	public void limpiarCamposError() {
		limpiarCampoErrorDistancia();
		limpiarCampoErrorEstiloNatacion();
	}

	public void mostrarErrorCampoDistancia(String mensajeError) {
		this.labelErrorDistancia.setText(mensajeError);
		this.textFieldDistancia.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	public void mostrarErrorCampoEstilosNatacion(String mensajeError) {
		this.labelErrorEstiloNatacion.setText(mensajeError);
		this.textFieldEstiloNatacion.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	private void limpiarCampoErrorDistancia() {
		this.labelErrorDistancia.setText(" ");
		this.textFieldDistancia.setBorder(UIManager.getBorder(textField_border_key));
	}

	private void limpiarCampoErrorEstiloNatacion() {
		this.labelErrorEstiloNatacion.setText(" ");
		this.textFieldEstiloNatacion.setBorder(UIManager.getBorder(textField_border_key));
	}

	public float getDistancia() {
		return Float.parseFloat(this.textFieldDistancia.getText());
	}

	public String getEstiloNatacion() {
		return this.textFieldEstiloNatacion.getText();
	}
	
	private void validarDistancia() {
		limpiarCampoErrorDistancia();
		distanciaValida = true;
		if(!Validador.validarDistancia(this.textFieldDistancia.getText())) {
			mostrarErrorCampoDistancia(MensajesValidacion.MENSAJE_VALIDACION_DISTANCIA_VALORES_NUMERICOS);
			distanciaValida = false;
		}
		
		validarInputs();
	}
	
	private void validarEstilosNatacion() {
		limpiarCampoErrorEstiloNatacion();
		estiloNatacionValido = true;
		if(getEstiloNatacion().length() > Natacion.TAMANIO_MAXIMO_ESTILO_NATACION) {
			mostrarErrorCampoEstilosNatacion(Natacion.MENSAJE_TAMANIO_MAXIMO_ESTILO_NATACION);
			estiloNatacionValido = false;
		}
		
		validarInputs();
	}

	@Override
	public Actividad getActividad() {
		Natacion natacion;
		if(actividad != null) natacion = (Natacion) actividad;
		else natacion = new Natacion();
		natacion.setFechaHora(getFecha());
		natacion.setDuracion(getDuracion());
		natacion.setUbicacion(getUbicacion());
		natacion.setDistancia(getDistancia());
		natacion.setEstilosNatacion(getEstiloNatacion());
		return natacion;
	}

	@Override
	protected void validarInputs() {
		if(observadorInputs != null) observadorInputs.update(((fechaValida() && distanciaValida) && estiloNatacionValido)? true : false);
		else throw new RuntimeErrorException(new Error("Se debe agregar un InputsValidosObserver al FormularioNatación"));
	}
	
	@Override
	public String getTitulo() {
		return TipoActividad.NATACION.getNombre();
	}
}
