package com.fit.actividad.vista.panelesPrincipales;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.fit.actividad.vista.VentanaActividades;

public class PanelRegistroActualizacionActividad extends JPanel{

	private static final long serialVersionUID = 1L;

	public PanelRegistroActualizacionActividad(VentanaActividades ventana) {
		super(new BorderLayout());
		add(new PanelContenedorRegistroActualizacionActividad(ventana), BorderLayout.CENTER);
		add(new PanelBotonesRegistroActualizacionActividad(ventana), BorderLayout.SOUTH);
	}
}
