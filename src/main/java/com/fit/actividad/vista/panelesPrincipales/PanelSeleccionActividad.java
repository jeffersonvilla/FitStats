package com.fit.actividad.vista.panelesPrincipales;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fit.actividad.vista.VentanaActividades;
import com.fit.util.Constraints;

public class PanelSeleccionActividad extends JPanel{

	private static final long serialVersionUID = 1L;

	public PanelSeleccionActividad(VentanaActividades ventana) {
		
		super(new GridBagLayout());
		
		GridBagConstraints constraints = Constraints.getGridBagConstraints();
		
		add(new JLabel("Tipo actividad"), constraints);
		
		constraints.gridx = 1;
		JComboBox<String> listaOpcionesTipoActividad = new JComboBox<String>(ventana.getOpcionesTipoActividad());
		listaOpcionesTipoActividad.addActionListener(ventana);	
		add(listaOpcionesTipoActividad, constraints);
	}
}
