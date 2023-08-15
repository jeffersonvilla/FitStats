package com.fit.actividad.vista.panelesPrincipales;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fit.actividad.vista.VentanaActividades;
import com.fit.util.Constraints;

public class PanelSeleccionActividad extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private VentanaActividades ventana;
	
	private JComboBox<String> listaOpcionesTipoActividad;

	public PanelSeleccionActividad(VentanaActividades ventana) {
		super(new GridBagLayout());

		this.ventana = ventana;
		
		ventana.setPanelSeleccionActividades(this);
		
		inicializar();
	}
	
	private void inicializar() {
		GridBagConstraints constraints = Constraints.getGridBagConstraints();

		add(new JLabel("Tipo actividad"), constraints);

		constraints.gridx = 1;
		this.listaOpcionesTipoActividad = new JComboBox<String>(ventana.getOpcionesTipoActividad());
		this.listaOpcionesTipoActividad.addActionListener(ventana);
		add(this.listaOpcionesTipoActividad, constraints);

	}
	
	public void refrescar() {
		removeAll();
		inicializar();
		revalidate();
		repaint();
	}
	
	public void setActividadDefault(int index) {
		this.listaOpcionesTipoActividad.setSelectedIndex(index);
		this.listaOpcionesTipoActividad.setEnabled(false);
		
	}
}
