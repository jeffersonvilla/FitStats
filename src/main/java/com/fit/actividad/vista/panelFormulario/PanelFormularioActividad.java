package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;
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

public class PanelFormularioActividad extends PanelFormulario {

	private static final long serialVersionUID = 1L;

	private JDatePickerImpl selectorFecha;

	private JLabel labelErrorFecha;

	private JSpinner selectorHora;

	private JLabel labelErrorHora;

	private JSpinner duracionHoras;

	private JLabel labelErrorDuracionHora;

	private JSpinner duracionMinutos;

	private JLabel labelErrorDuracionMinuto;

	private JTextField textFieldUbicacion;

	private JLabel labelErrorUbicacion;

	private Border borderDefault;

	private Timestamp fechaHora;
	
	private int horasDuracion = -1;
	
	private int minutosDuracion = -1;
	
	private String ubicacion;

	public PanelFormularioActividad() {
		super();

		inicializarCampos();
	}

	public PanelFormularioActividad(Timestamp fechaHora, Time duracion, String ubicacion) {
		super();
		this.fechaHora = fechaHora;
		if(duracion != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(duracion.getTime());
			this.horasDuracion = calendar.get(Calendar.HOUR_OF_DAY);
			this.minutosDuracion = calendar.get(Calendar.MINUTE);
		}
		this.ubicacion = ubicacion;
		inicializarCampos();
	}

	private void inicializarCampos() {
		ajustarConstraints(0, 0, 1, 1);
		add(new JLabel("Fecha"), constraints);

		ajustarConstraints(1, 0, 1, 1);
		this.selectorFecha = getSelectorFecha();
		this.borderDefault = this.selectorFecha.getBorder();
		add(this.selectorFecha, constraints);

		ajustarConstraints(0, 1, 2, 1);
		this.labelErrorFecha = getLabelError();
		add(this.labelErrorFecha, constraints);

		ajustarConstraints(0, 2, 1, 1);
		add(new JLabel("Hora"), constraints);

		ajustarConstraints(1, 2, 1, 1);
		this.selectorHora = getSelectorHoraIncio();
		add(this.selectorHora, constraints);

		ajustarConstraints(0, 3, 2, 1);
		this.labelErrorHora = getLabelError();
		add(this.labelErrorHora, constraints);

		ajustarConstraints(2, 0, 1, 1);
		add(new JLabel("Duracion"), constraints);

		ajustarConstraints(3, 0, 1, 1);
		add(new JLabel("hora(s):"), constraints);

		ajustarConstraints(4, 0, 1, 1);
		this.duracionHoras = getSelectorDuracion(23, horasDuracion);
		add(this.duracionHoras, constraints);

		ajustarConstraints(5, 0, 1, 1);
		add(new JLabel("minutos"), constraints);

		ajustarConstraints(6, 0, 1, 1);
		this.duracionMinutos = getSelectorDuracion(59, minutosDuracion);
		add(this.duracionMinutos, constraints);

		ajustarConstraints(1, 1, 3, 1);
		this.labelErrorDuracionHora = getLabelError();
		add(this.labelErrorDuracionHora, constraints);

		ajustarConstraints(2, 1, 2, 1);
		this.labelErrorDuracionMinuto = getLabelError();
		add(this.labelErrorDuracionMinuto, constraints);

		ajustarConstraints(2, 2, 1, 1);
		add(new JLabel("Ubicacion"), constraints);

		ajustarConstraints(3, 2, 4, 1);
		this.textFieldUbicacion = new JTextField(10);
		if(this.ubicacion != null) this.textFieldUbicacion.setText(ubicacion);
		add(this.textFieldUbicacion, constraints);

		ajustarConstraints(0, 3, 6, 1);
		this.labelErrorUbicacion = getLabelError();
		add(this.labelErrorUbicacion, constraints);
	}

	private JDatePickerImpl getSelectorFecha() {
		UtilDateModel model = new UtilDateModel();
		Calendar calendar = Calendar.getInstance();
		if (this.fechaHora != null)
			calendar.setTimeInMillis(this.fechaHora.getTime());
		model.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		model.setSelected(true);
		return new JDatePickerImpl(new JDatePanelImpl(model, new Properties()), new DateLabelFormatter());
	}

	private JSpinner getSelectorHoraIncio() {
		SpinnerDateModel modelo = new SpinnerDateModel();
		JSpinner selectorHora = new JSpinner(modelo);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(selectorHora, "HH:mm");
		selectorHora.setEditor(editor);
		modelo.setValue((this.fechaHora != null) ? new Date(this.fechaHora.getTime()): new Date());
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

	public Timestamp getFecha() {
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

	public Time getDuracion() {
		int horas = (int) this.duracionHoras.getModel().getValue();
		int horaDefault = 19;
		int minutos = (int) this.duracionMinutos.getModel().getValue();
		return new Time((horas - horaDefault) * 3600000L + minutos * 60000L);
	}

	public String getUbicacion() {
		return this.textFieldUbicacion.getText();
	}

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
}