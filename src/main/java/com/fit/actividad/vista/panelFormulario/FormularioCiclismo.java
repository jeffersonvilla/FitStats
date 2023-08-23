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
import com.fit.actividad.modelo.Ciclismo;
import com.fit.actividad.modelo.TipoActividad;
import com.fit.util.MensajesValidacion;
import com.fit.util.Validador;

public class FormularioCiclismo extends FormularioActividad{

	private static final long serialVersionUID = 1L;

	private JTextField textFieldDistancia;

	private JLabel labelErrorDistancia;

	private JTextField textFieldTipoBicicleta;

	private JLabel labelErrorTipoBicicleta;

	private boolean distanciaValida = true;
	
	private boolean tipoBicicletaValido = true;
	
	public FormularioCiclismo() {
		super();
		
		inicializar();
	}

	public FormularioCiclismo(Ciclismo actividad) {
		super(actividad);
		
		inicializar();
	}

	private void inicializar() {
		inicializarCamposDistancia();
		inicializarCamposTipoBicicleta();
	}
	
	private void inicializarCamposDistancia() {
		add(new JLabel("Distancia"));
		this.textFieldDistancia = new JTextField(15);
		this.textFieldDistancia.setText((actividad != null)? Float.toString(((Ciclismo) actividad).getDistancia()) :"0.0");
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
	
	private void inicializarCamposTipoBicicleta() {
		add(new JLabel("Tipo bicicleta"));
		this.textFieldTipoBicicleta = new JTextField(15);
		if(actividad != null) this.textFieldTipoBicicleta.setText(((Ciclismo) actividad).getTipo_bicicleta());
		add(this.textFieldTipoBicicleta, "span, grow, wrap");
		this.labelErrorTipoBicicleta = getLabelError();
		add(this.labelErrorTipoBicicleta, "span, grow, wrap");
		
		agregarListenerTipoBicicleta();
	}
	
	private void agregarListenerTipoBicicleta() {

		this.textFieldTipoBicicleta.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validarTipoBicicleta();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validarTipoBicicleta();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) { }
		});
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

	public float getDistancia() {
		return Float.parseFloat(this.textFieldDistancia.getText());
	}

	public String getTipoBicicleta() {
		return this.textFieldTipoBicicleta.getText();
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
	
	private void validarTipoBicicleta() {
		limpiarCampoErrorTipoBicicleta();
		tipoBicicletaValido = true;
		if (getTipoBicicleta().length() > Ciclismo.TAMANIO_MAXIMO_TIPO_BICICLETA) {
			mostrarErrorCampoTipoBicicleta(Ciclismo.MENSAJE_TAMANIO_MAXIMO_TIPO_BICICLETA);
			tipoBicicletaValido = false;
		}
		
		validarInputs();
	}

	@Override
	public Actividad getActividad() {
		Ciclismo ciclismo;
		if(actividad != null) ciclismo = (Ciclismo) actividad;
		else ciclismo = new Ciclismo();
		ciclismo.setFechaHora(getFecha());
		ciclismo.setDuracion(getDuracion());
		ciclismo.setUbicacion(getUbicacion());
		ciclismo.setDistancia(getDistancia());
		ciclismo.setTipo_bicicleta(getTipoBicicleta());
		return ciclismo;
	}

	@Override
	protected void validarInputs() {
		if(observadorInputs != null) observadorInputs.update(((fechaValida() && distanciaValida) && tipoBicicletaValido)? true : false);
		else throw new RuntimeErrorException(new Error("Se debe agregar un InputsValidosObserver al FormularioCiclismo"));
	}
	
	@Override
	public String getTitulo() {
		return TipoActividad.CICLISMO.getNombre();
	}
}