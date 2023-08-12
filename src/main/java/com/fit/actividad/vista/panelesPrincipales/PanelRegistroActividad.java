package com.fit.actividad.vista.panelesPrincipales;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.fit.actividad.ControladorActividad;
import com.fit.actividad.vista.VentanaActividades;

public class PanelRegistroActividad extends JPanel{

	private static final long serialVersionUID = 1L;

	public PanelRegistroActividad(VentanaActividades ventana, ControladorActividad controlador) {
		super(new BorderLayout());
		add(new PanelContenedorRegistroActividad(ventana), BorderLayout.CENTER);
		add(new PanelBotonesRegistroActividad(ventana, controlador), BorderLayout.SOUTH);
	}
}
