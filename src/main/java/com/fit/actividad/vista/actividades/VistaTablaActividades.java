package com.fit.actividad.vista.actividades;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.fit.actividad.controlador.ControladorActividad;
import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.vista.ModeloActividadObserver;
import com.fit.util.Pantalla;

public class VistaTablaActividades extends JFrame implements ActionListener, ModeloActividadObserver {

	private static final long serialVersionUID = 1L;
	
	private static final String BOTON_REGISTRAR = "Registrar";
	
	private static final String BOTON_ACTUALIZAR = "Actualizar";

	private DefaultTableModel modeloTablaActividades;

	private JTable tablaActividades;
	
	private ControladorActividad controlador;

	public VistaTablaActividades(ControladorActividad controlador) {
		
		this.controlador = controlador;
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(Pantalla.ancho / 3, Pantalla.alto / 4);
		
		controlador.agregarObservadorAlModeloActividad(this);
		
		inicializarTabla();
		actualizarListaActividades(controlador.leerListaActividades());
		
		inicializarPanelBotones();
		
		pack();
		setResizable(false);
		setVisible(true);
	}

	private void inicializarTabla() {
		modeloTablaActividades = getModeloTabla();
		tablaActividades = new JTable(modeloTablaActividades);
		tablaActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(this.tablaActividades), BorderLayout.CENTER);
		
	}

	private DefaultTableModel getModeloTabla() {
		DefaultTableModel modeloTablaActividades = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modeloTablaActividades.addColumn("Actividad");
		modeloTablaActividades.addColumn("Fecha y hora");
		modeloTablaActividades.addColumn("Duracion");
		modeloTablaActividades.addColumn("Ubicación");
		return modeloTablaActividades;
	}
	
	private void actualizarListaActividades(List<Actividad> listaActividades) {
		if(listaActividades == null) return;
		this.modeloTablaActividades.setRowCount(0);
		for (Actividad actividad : listaActividades)
			this.modeloTablaActividades.addRow(formatearActividad(actividad));
		this.modeloTablaActividades.fireTableDataChanged();
	}
	
	private Object[] formatearActividad(Actividad actividad) {
		return new Object[] {
				actividad.getNombreTipoActividad(), 
				formatearFechaHora(actividad.getFechaHora()),
				formatearDuracion(actividad.getDuracion()),
				actividad.getUbicacion()
				};
	}
	
	private String formatearFechaHora(Timestamp fechaHora) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(fechaHora.getTime());
		return String.format("%04d-%02d-%02d %02d:%02d", 
				calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) + 1, 
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.HOUR_OF_DAY), 
				calendar.get(Calendar.MINUTE));
	}
	
	private String formatearDuracion(Time duracion) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(duracion.getTime());
		int horas = calendar.get(Calendar.HOUR_OF_DAY);
		int minutos = calendar.get(Calendar.MINUTE);
		String horasString = (horas != 0)?(horas + " hora" + ((horas == 1)? "" : "s")) : "" ;
		String minutosString = (minutos != 0)? (minutos + " minuto" + ((minutos == 1)? "" : "s" )) : "";
		return (horasString.isEmpty() && minutosString.isEmpty())? "sin duración" 
				: (horasString + ((!horasString.isEmpty() && !minutosString.isEmpty())? " y " : "" ) + minutosString);
	}
	
	private void inicializarPanelBotones() {
		JPanel panelBotones = new JPanel();
		
		JButton botonRegistrar = new JButton(BOTON_REGISTRAR);
		botonRegistrar.addActionListener(this);
		panelBotones.add(botonRegistrar);
		
		JButton botonActualizar = new JButton(BOTON_ACTUALIZAR);
		botonActualizar.addActionListener(this);
		panelBotones.add(botonActualizar);
		
		add(panelBotones, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source instanceof JButton) {
			JButton boton = (JButton) source;
			if(boton.getText().equals(BOTON_REGISTRAR)) {
				new SeleccionTipoActividadCrear(controlador);
			}else if(boton.getText().equals(BOTON_ACTUALIZAR)) {
				System.out.println("Actualizar: " + tablaActividades.getSelectedRow());
			}
		}
	}

	@Override
	public void actualizar(List<Actividad> actividades) {
		actualizarListaActividades(actividades);
	}
}