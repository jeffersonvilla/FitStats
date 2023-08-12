package com.fit.actividad.vista.panelesPrincipales;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.fit.actividad.ControladorActividad;
import com.fit.actividad.vista.VentanaActividades;

public class PanelPrincipalActividades extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public PanelPrincipalActividades(VentanaActividades ventana, ControladorActividad controlador) {
		super(new BorderLayout());
		add(new PanelContenedor(ventana, controlador), BorderLayout.CENTER);
		add(new PanelBotonesCrudActividad(ventana), BorderLayout.SOUTH);
	}	
}