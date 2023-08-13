package com.fit.actividad.vista.panelesPrincipales;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.fit.actividad.vista.VentanaActividades;
import com.fit.util.Constraints;

public class PanelDetalleActividad extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelDetalleActividad(VentanaActividades ventana) {
		super(new GridBagLayout());

		GridBagConstraints constraints = Constraints.getGridBagConstraints();

		add(new PanelSeleccionActividad(ventana), constraints);

		constraints.gridx = 1;
		add(new PanelCardFormularioDetalleActividad(ventana), constraints);
	}
}