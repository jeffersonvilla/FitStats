package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.management.RuntimeErrorException;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.DeporteEquipo;
import com.fit.actividad.modelo.TipoActividad;

public class FormularioDeporteEquipo extends FormularioActividad {

	private static final long serialVersionUID = 1L;
	
	private static final String EJEMPLO_NOMBRE_DEPORTE = "Ejemplo: Tenis";

	private JTextField textFieldNombreDeporte;

	private JLabel labelErrorNombreDeporte;

	private JTextField textFieldNombreEquipos;

	private JLabel labelErrorNombreEquipos;

	private JTextField textFieldResultadoDelPartido;

	private JLabel labelErrorResultadoDelPartido;
	
	private boolean nombreDeporteValido = true;
	
	private boolean nombreEquiposValido = true;
	
	private boolean resultadoPartidoValido = true;

	public FormularioDeporteEquipo() {
		super();

		inicializar();
	}

	public FormularioDeporteEquipo(DeporteEquipo deporteEquipo) {
		super(deporteEquipo);
		
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
		if(actividad != null) this.textFieldNombreDeporte.setText(((DeporteEquipo) actividad).getNombreDeporte());
		else this.textFieldNombreDeporte.setText(EJEMPLO_NOMBRE_DEPORTE);
		add(this.textFieldNombreDeporte, "span, grow, wrap");
		this.labelErrorNombreDeporte = getLabelError();
		add(this.labelErrorNombreDeporte, "span, grow, wrap");
		
		agregarListenerNombreDeporte();
	}
	
	private void agregarListenerNombreDeporte() {
		
		this.textFieldNombreDeporte.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validarNombreDeporte();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validarNombreDeporte();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) { }
		});
		
		this.textFieldNombreDeporte.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(textFieldNombreDeporte.getText().equals(EJEMPLO_NOMBRE_DEPORTE))
					textFieldNombreDeporte.setText("");
			}
		});
	}
	
	private void inicializarCamposNombreEquipos() {
		add(new JLabel("Nombre de los equipos"));
		this.textFieldNombreEquipos = new JTextField(15);
		if(actividad != null) this.textFieldNombreEquipos.setText(((DeporteEquipo) actividad).getNombreEquipos());
		add(this.textFieldNombreEquipos, "span, grow, wrap");
		this.labelErrorNombreEquipos = getLabelError();
		add(this.labelErrorNombreEquipos, "span, grow, wrap");
		
		agregarListenerNombreEquipos();
	}
	
	private void agregarListenerNombreEquipos() {
		
		this.textFieldNombreEquipos.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validarNombreEquipos();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validarNombreEquipos();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) { }
		});
	}
	
	private void inicializarCamposResultadoPartido() {
		add(new JLabel("Resultado del partido"));
		this.textFieldResultadoDelPartido = new JTextField(15);
		if(actividad != null) this.textFieldResultadoDelPartido.setText(((DeporteEquipo) actividad).getResultadoDelPartido());
		add(this.textFieldResultadoDelPartido, "span, grow, wrap");
		this.labelErrorResultadoDelPartido = getLabelError();
		add(this.labelErrorResultadoDelPartido, "span, grow, wrap");
		
		agregarListenerResultadoPartido();
	}
	
	private void agregarListenerResultadoPartido() {
		
		this.textFieldResultadoDelPartido.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validarResultadoPartido();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validarResultadoPartido();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) { }
		});
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

	public void mostrarErrorCampoNombreEquipos(String mensajeError) {
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

	private void validarNombreDeporte() {
		limpiarCampoErrorNombreDeporte();
		nombreDeporteValido = true;
		
		if(getNombreDeporte().isBlank()) {
			mostrarErrorCampoNombreDeporte(MENSAJE_VALIDACION_CAMPO_VACIO);
			nombreDeporteValido = false;
		}else if (getNombreDeporte().length() > DeporteEquipo.TAMANIO_MAXIMO_NOMBRE_DEPORTE) {
			mostrarErrorCampoNombreDeporte(DeporteEquipo.MENSAJE_TAMANIO_MAXIMO_NOMBRE_DEPORTE);
			nombreDeporteValido = false;
		}
		
		validarInputs();
	}
	
	private void validarNombreEquipos() {
		limpiarCampoErrorNombreEquipos();
		nombreEquiposValido = true;
		if(getNombreEquipos().length() > DeporteEquipo.TAMANIO_MAXIMO_NOMBRE_EQUIPOS) {
			mostrarErrorCampoNombreEquipos(DeporteEquipo.MENSAJE_TAMANIO_MAXIMO_NOMBRE_EQUIPOS);
			nombreEquiposValido = false;
		}
		
		validarInputs();
	}
	
	private void validarResultadoPartido() {
		limpiarCampoErrorResultadoDelPartido();
		resultadoPartidoValido = true;
		if(getResultadoDelPartido().length() > DeporteEquipo.TAMANIO_MAXIMO_RESULTADO_PARTIDO) {
			mostrarErrorCampoResultadoDelPartido(DeporteEquipo.MENSAJE_TAMANIO_MAXIMO_RESULTADO_PARTIDO);
			resultadoPartidoValido = false;
		}
		
		validarInputs();
	}
	
	@Override
	public Actividad getActividad() {
		DeporteEquipo deporteEquipo;
		if(actividad != null) deporteEquipo = (DeporteEquipo) actividad;
		else deporteEquipo = new DeporteEquipo();
		deporteEquipo.setFechaHora(getFecha());
		deporteEquipo.setDuracion(getDuracion());
		deporteEquipo.setUbicacion(getUbicacion());
		deporteEquipo.setNombreDeporte(getNombreDeporte());
		deporteEquipo.setNombreEquipos(getNombreEquipos());
		deporteEquipo.setResultadoDelPartido(getResultadoDelPartido());
		return deporteEquipo;
	}

	@Override
	protected void validarInputs() {
		if(observadorInputs != null) 
			observadorInputs.update((fechaValida() && nombreDeporteValido && 
					nombreEquiposValido && resultadoPartidoValido)? true : false);
		else throw new RuntimeErrorException(new Error("Se debe agregar un InputsValidosObserver al FormularioDeporteEquipo"));
	}

	@Override
	public String getTitulo() {
		return TipoActividad.DEPORTE_EQUIPO.getNombre();
	}
}
