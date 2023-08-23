package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.vista.InputsValidosObserver;
import com.fit.util.Validador;

public abstract class FormularioActividad extends PanelFormulario {

	private static final long serialVersionUID = 1L;
	
	public static final String MENSAJE_VALIDACION_CAMPO_VACIO = "Debe llenar este campo";

	private JDatePickerImpl selectorFecha;

	private JLabel labelErrorFecha;

	private JSpinner selectorHora;

	private JLabel labelErrorHora;

	private JSpinner duracionHoras;

	private JLabel labelErrorDuracion;

	private JSpinner duracionMinutos;

	private JTextField textFieldUbicacion;

	private JLabel labelErrorUbicacion;

	private Border borderDefault;
	
	protected Actividad actividad;
	
	private boolean fechaValida = true;
	
	protected InputsValidosObserver observadorInputs;

	protected FormularioActividad() {
		super();

		inicializarCampos();
	}

	protected FormularioActividad(Actividad actividad) {
		super();
		
		this.actividad = actividad;
		
		inicializarCampos();
	}
	
	private void inicializarCampos() {
		inicializarCamposFecha();		
		inicializarCamposHora();
		inicializarCamposDuracion();
		inicializarCamposUbicacion();
	}
		
	private void inicializarCamposFecha() {
		add(new JLabel("Fecha"));
		this.selectorFecha = getSelectorFecha();
		this.borderDefault = this.selectorFecha.getBorder();
		add(this.selectorFecha, "wrap");
		this.labelErrorFecha = getLabelError();
		add(this.labelErrorFecha, "span, grow");
		
		agregarListenerFecha();
	}
	
	private void inicializarCamposHora() {
		add(new JLabel("Hora inicio"));
		this.selectorHora = getSelectorHoraIncio();
		add(this.selectorHora, "grow, wrap");
		this.labelErrorHora = getLabelError();
		add(this.labelErrorHora,"span, grow, wrap");
	}
	
	private void inicializarCamposDuracion() {
		add(new JLabel("Duracion", JLabel.CENTER), "span, grow, wrap");
		add(new JLabel("Cantidad de horas"));
		this.duracionHoras = getSelectorDuracion(23, getHorasDuracion());
		add(this.duracionHoras, "split 3, grow");
		add(new JLabel("minutos"));
		this.duracionMinutos = getSelectorDuracion(59, getMinutosDuracion());
		add(this.duracionMinutos, "grow, wrap");		
		this.labelErrorDuracion = getLabelError();
		add(this.labelErrorDuracion, "span, grow, wrap");
	}

	public int getHorasDuracion() {	
		if(actividad == null) return 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(actividad.getDuracion().getTime());
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public int getMinutosDuracion() {
		if(actividad == null) return 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(actividad.getDuracion().getTime());
		return calendar.get(Calendar.MINUTE);
	}
	
	private void inicializarCamposUbicacion() {
		add(new JLabel("Ubicacion"));
		this.textFieldUbicacion = new JTextField(10);
		if(this.actividad != null) this.textFieldUbicacion.setText(actividad.getUbicacion());
		add(this.textFieldUbicacion, "span, grow, wrap");
		this.labelErrorUbicacion = getLabelError();
		add(this.labelErrorUbicacion, "span, grow, wrap");
	}
	
	private JDatePickerImpl getSelectorFecha() {
		UtilDateModel model = new UtilDateModel();
		Calendar calendar = Calendar.getInstance();
		if(this.actividad != null) calendar.setTimeInMillis(this.actividad.getFechaHora().getTime());
		model.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		model.setSelected(true);
		return new JDatePickerImpl(new JDatePanelImpl(model, new Properties()), new DateLabelFormatter());	
	}
	
	private void agregarListenerFecha() {
		selectorFecha.getModel().addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("value".equals(evt.getPropertyName())) {
                    validarFecha();
                    validarInputs();
                }
			}
		});
	}

	private void validarFecha() {
		limpiarCamposErrorFecha();
		fechaValida = true;
		if(!Validador.validarFecha(getFecha())) { 
			mostrarErrorCampoFecha(MENSAJE_VALIDACION_CAMPO_VACIO);
			fechaValida = false;
		}
	}
	
	protected abstract void validarInputs();
	
	protected boolean fechaValida() {
		return fechaValida;
	}

	private JSpinner getSelectorHoraIncio() {
		SpinnerDateModel modelo = new SpinnerDateModel();
		JSpinner selectorHora = new JSpinner(modelo);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(selectorHora, "HH:mm");
		selectorHora.setEditor(editor);
		modelo.setValue((this.actividad != null) ? new Date(this.actividad.getFechaHora().getTime()): new Date());
		return selectorHora;
	}

	private JSpinner getSelectorDuracion(int maximo, int value) {
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(((value != -1)? value : 0), 0, maximo, 1));
		return spinner;
	}

	@Override
	public void limpiarCamposTexto() {
		this.textFieldUbicacion.setText("");
	}

	@Override
	public void limpiarCamposError() {
		this.labelErrorFecha.setText(" ");
		this.selectorFecha.setBorder(this.borderDefault);
	}
	
	private void limpiarCamposErrorFecha() {
		this.labelErrorFecha.setText(" ");
		this.selectorFecha.setBorder(this.borderDefault);
	}

	protected Timestamp getFecha() {
		Date fecha = (Date) this.selectorFecha.getModel().getValue();

		if (fecha == null)
			return null;

		Date hora = (Date) this.selectorHora.getModel().getValue();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);

		Calendar timeCalendar = Calendar.getInstance();
		timeCalendar.setTime(hora);
		int hour = timeCalendar.get(Calendar.HOUR_OF_DAY);
		int minute = timeCalendar.get(Calendar.MINUTE);

		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return new Timestamp(calendar.getTimeInMillis());
	}

	protected Time getDuracion() {
		int horas = (int) this.duracionHoras.getModel().getValue();
		int horaDefault = 19;
		int minutos = (int) this.duracionMinutos.getModel().getValue();
		return new Time((horas - horaDefault) * 3600000L + minutos * 60000L);
	}

	protected String getUbicacion() {
		return this.textFieldUbicacion.getText();
	}
	
	public abstract String getTitulo();
	
	public abstract Actividad getActividad();

	private class DateLabelFormatter extends AbstractFormatter {

		private static final long serialVersionUID = 1L;

		private final String datePattern = "yyyy-MM-dd";
		private final java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws ParseException {
			return dateFormatter.parseObject(text);
		}

		@Override
		public String valueToString(Object value) throws ParseException {
			if (value != null) {
				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());
			}
			return "";
		}
	}

	public void mostrarErrorCampoFecha(String mensajeError) {
		this.labelErrorFecha.setText(mensajeError);
		this.selectorFecha.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	public void setObservadorInputs(InputsValidosObserver observadorInputs) {
		this.observadorInputs = observadorInputs;
	}
}