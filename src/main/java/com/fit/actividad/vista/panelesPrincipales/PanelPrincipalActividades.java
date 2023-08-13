package com.fit.actividad.vista.panelesPrincipales;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.fit.actividad.vista.VentanaActividades;

public class PanelPrincipalActividades extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelPrincipalActividades(VentanaActividades ventana) {
		super(new BorderLayout());
		add(new PanelContenedor(ventana), BorderLayout.CENTER);
		add(new PanelBotonesCrudActividad(ventana), BorderLayout.SOUTH);
	}
}