package com.fit.actividad.vista.actividades;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.fit.actividad.controlador.ControladorActividad;
import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.vista.ModeloActividadObserver;
import com.fit.util.Formato;
import com.fit.util.Pantalla;

public class VistaTablaActividades extends JFrame implements ActionListener, ModeloActividadObserver {

	private static final long serialVersionUID = 1L;
	
	private static final String BOTON_REGISTRAR = "Registrar";
	
	private static final String BOTON_DETALLES = "Ver detalles";
	
	private static final String BOTON_ACTUALIZAR = "Actualizar";
	
	private static final String BOTON_ELIMINAR = "Eliminar";

	private DefaultTableModel modeloTablaActividades;

	private JTable tablaActividades;
	
	private JButton botonDetalles;
	
	private JButton botonActualizar;
	
	private JButton botonEliminar;
	
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
		agregarSelectionListenerEnTabla();
		add(new JScrollPane(this.tablaActividades), BorderLayout.CENTER);
		
	}
	
	private void agregarSelectionListenerEnTabla() {
		tablaActividades.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        boolean isRowSelected = tablaActividades.getSelectedRow() != -1;
		        botonDetalles.setEnabled(isRowSelected);
		        botonActualizar.setEnabled(isRowSelected);
		        botonEliminar.setEnabled(isRowSelected);
		    }
		});
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
				Formato.formatearFechaHora(actividad.getFechaHora()),
				Formato.formatearDuracion(actividad.getDuracion()),
				actividad.getUbicacion()
				};
	}
	
	private void inicializarPanelBotones() {
		JPanel panelBotones = new JPanel();
		
		botonDetalles = new JButton(BOTON_DETALLES);
		botonDetalles.addActionListener(this);
		botonDetalles.setEnabled(false);
		panelBotones.add(botonDetalles);
		
		JButton botonRegistrar = new JButton(BOTON_REGISTRAR);
		botonRegistrar.addActionListener(this);
		panelBotones.add(botonRegistrar);
		
		botonActualizar = new JButton(BOTON_ACTUALIZAR);
		botonActualizar.addActionListener(this);
		botonActualizar.setEnabled(false);
		panelBotones.add(botonActualizar);
		
		botonEliminar = new JButton(BOTON_ELIMINAR);
		botonEliminar.addActionListener(this);
		botonEliminar.setEnabled(false);
		panelBotones.add(botonEliminar);
		
		add(panelBotones, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source instanceof JButton) {
			JButton boton = (JButton) source;
			String textoBoton = boton.getText();
			if(textoBoton.equals(BOTON_DETALLES)) {
				controlador.abrirVentanaDetalles(tablaActividades.getSelectedRow());
			}else if(textoBoton.equals(BOTON_REGISTRAR)) {
				new SeleccionTipoActividadCrear(controlador);
			}else if(textoBoton.equals(BOTON_ACTUALIZAR)) {
				controlador.abrirVentanaActualizarActividad(tablaActividades.getSelectedRow());
			}else if(textoBoton.equals(BOTON_ELIMINAR)) {
				controlador.eliminarActividad(tablaActividades.getSelectedRow());
			}
		}
	}

	@Override
	public void actualizar(List<Actividad> actividades) {
		actualizarListaActividades(actividades);
	}
}
