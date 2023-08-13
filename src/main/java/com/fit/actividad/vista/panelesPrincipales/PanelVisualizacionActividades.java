package com.fit.actividad.vista.panelesPrincipales;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.fit.actividad.vista.VentanaActividades;

public class PanelVisualizacionActividades extends JPanel {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel modeloTablaActividades;

	private JTable tablaActividades;

	public PanelVisualizacionActividades(VentanaActividades ventana) {
		super(new BorderLayout());
		ventana.setPanelVisualizacionActividades(this);
		modeloTablaActividades = getModeloTabla();
		actualizarListaActividades(ventana.getControlador().getListaActividades());
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
		modeloTablaActividades.addColumn("Tipo de actividad");
		modeloTablaActividades.addColumn("Fecha y hora de inicio");
		modeloTablaActividades.addColumn("Duracion (horas:minutos:segundos)");
		modeloTablaActividades.addColumn("Ubicación");
		return modeloTablaActividades;
	}

	public void actualizarListaActividades(List<Object[]> listaActividades) {
		this.modeloTablaActividades.setRowCount(0);
		for (Object[] actividad : listaActividades)
			this.modeloTablaActividades.addRow(actividad);
		this.modeloTablaActividades.fireTableDataChanged();
	}

	public JTable getTablaActividades() {
		return tablaActividades;
	}
}
