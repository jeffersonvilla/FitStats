package com.fit.actividad.vista.actividades;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.fit.util.Pantalla;

public class VistaDetalles extends JFrame {

	private static final long serialVersionUID = 1L;

	public VistaDetalles(PanelDetalles panel) {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(Pantalla.ancho / 3, Pantalla.alto / 3);
		
		add(panel, BorderLayout.CENTER);
		
		pack();
		setResizable(false);
		setVisible(true);
	}
	
}
