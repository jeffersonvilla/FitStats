package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;

import javax.management.RuntimeErrorException;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.EntrenamientoGimnasio;
import com.fit.actividad.modelo.TipoActividad;

public class FormularioEntrenamientoGimnasio extends FormularioActividad {

	private static final long serialVersionUID = 1L;

	private JTextArea textAreaEjerciciosRealizados;
	
	private JLabel labelErrorEjerciciosRealizados;

	private JTextField textFieldDescansosEntreEjercicios;

	private JLabel labelErrorDescansosEntreEjercicios;

	private JTextField textFieldDescansosEntreSeries;

	private JLabel labelErrorDescansosEntreSeries;
	
	private boolean descansoEjerciciosValido = true;
	
	private boolean descansoSeriesValido = true;

	public FormularioEntrenamientoGimnasio() {
		super();

		inicializar();
	}
	
	public FormularioEntrenamientoGimnasio(EntrenamientoGimnasio entrenamiento) {
		super(entrenamiento);
		
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
		if(actividad != null) 
			this.textAreaEjerciciosRealizados.setText(((EntrenamientoGimnasio) actividad).getEjerciciosRealizados());
		add(this.textAreaEjerciciosRealizados, "span, grow, wrap");
		this.labelErrorEjerciciosRealizados = getLabelError();
		add(this.labelErrorEjerciciosRealizados, "span, grow, wrap");
	}
	
	private void inicializarCamposDescansoEjercicios() {
		add(new JLabel("Descansos entre ejercicios"));
		this.textFieldDescansosEntreEjercicios = new JTextField(10);
		if(actividad != null) 
			this.textFieldDescansosEntreEjercicios.setText(((EntrenamientoGimnasio) actividad).getDescansoEntreEjercicios());
		add(this.textFieldDescansosEntreEjercicios, "span, grow, wrap");
		this.labelErrorDescansosEntreEjercicios = getLabelError();
		add(this.labelErrorDescansosEntreEjercicios, "span, grow, wrap");
		
		agregarListenerDescansoEjercicios();
	}
	
	private void agregarListenerDescansoEjercicios() {
	
		this.textFieldDescansosEntreEjercicios.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validarDescansoEjercicios();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validarDescansoEjercicios();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) { }
		});
	}
	
	private void inicializarCamposDescansoSeries() {
		add(new JLabel("Descansos entre series"));
		this.textFieldDescansosEntreSeries = new JTextField(10);
		if(actividad != null) 
			this.textFieldDescansosEntreSeries.setText(((EntrenamientoGimnasio) actividad).getDescansoEntreSeries());
		add(this.textFieldDescansosEntreSeries, "span, grow, wrap");
		this.labelErrorDescansosEntreSeries = getLabelError();
		add(this.labelErrorDescansosEntreSeries, "span, grow, wrap");
		
		agregarListenerDescansoSeries();
	}

	private void agregarListenerDescansoSeries() {
	
		this.textFieldDescansosEntreSeries.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validarDescansoSeries();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validarDescansoSeries();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) { }
		});
	}
	
	@Override
	public void limpiarCamposTexto() {
		this.textAreaEjerciciosRealizados.setText("");
		this.textFieldDescansosEntreEjercicios.setText("");
		this.textFieldDescansosEntreSeries.setText("");
	}

	@Override
	public void limpiarCamposError() {
		limpiarCampoErrorEjerciciosRealizados();
		limpiarCampoErrorDescansoEjercicios();
		limpiarCampoErrorDescansoSeries();
	}
	
	private void limpiarCampoErrorEjerciciosRealizados() {
		this.labelErrorEjerciciosRealizados.setText(" ");
		this.labelErrorEjerciciosRealizados.setBorder(UIManager.getBorder(textField_border_key));
	}
	
	private void limpiarCampoErrorDescansoEjercicios() {
		this.labelErrorDescansosEntreEjercicios.setText(" ");
		this.textFieldDescansosEntreEjercicios.setBorder(UIManager.getBorder(textField_border_key));
	}
	
	private void limpiarCampoErrorDescansoSeries() {
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
	
	private void validarDescansoEjercicios() {
		limpiarCampoErrorDescansoEjercicios();
		descansoEjerciciosValido = true;
		if(getDescansosEntreEjercicios().length() > EntrenamientoGimnasio.TAMANIO_MAXIMO_DESCANSO_ENTRE_EJERCICIOS) {
			mostrarErrorCampoDescansoEntreEjercicios(EntrenamientoGimnasio.MENSAJE_TAMANIO_MAXIMO_DESCANSO_EJERCICIOS);
			descansoEjerciciosValido = false;
		}
		
		validarInputs();
	}
	
	private void validarDescansoSeries() {
		limpiarCampoErrorDescansoSeries();
		descansoSeriesValido = true;
		if(getDescansosEntreSeries().length() > EntrenamientoGimnasio.TAMANIO_MAXIMO_DESCANSO_ENTRE_SERIES) {
			mostrarErrorCampoDescansosEntreSeries(EntrenamientoGimnasio.MENSAJE_TAMANIO_MAXIMO_DESCANSO_SERIES);
			descansoSeriesValido = false;
		}
		
		validarInputs();
	}

	@Override
	public Actividad getActividad() {
		EntrenamientoGimnasio entrenamiento;
		if(actividad != null) entrenamiento = (EntrenamientoGimnasio) actividad;
		else entrenamiento = new EntrenamientoGimnasio();
		entrenamiento.setFechaHora(getFecha());
		entrenamiento.setDuracion(getDuracion());
		entrenamiento.setUbicacion(getUbicacion());
		entrenamiento.setEjerciciosRealizados(getEjerciciosRealizados());
		entrenamiento.setDescansoEntreEjercicios(getDescansosEntreEjercicios());
		entrenamiento.setDescansoEntreSeries(getDescansosEntreSeries());
		return entrenamiento;
	}

	@Override
	protected void validarInputs() {
		if(observadorInputs != null) observadorInputs.update((fechaValida() && 
				descansoEjerciciosValido && descansoSeriesValido)? true : false);
		else throw new RuntimeErrorException(
				new Error("Se debe agregar un InputsValidosObserver al FormularioEntrenamientoGimnasio"));
	}

	@Override
	public String getTitulo() {
		return TipoActividad.ENTRENAMIENTO_GIMNASIO.getNombre();
	}
}
