package com.fit.actividad.vista.detalles;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.fit.util.Pantalla;

public class DetallesActividad extends JFrame {

	private static final long serialVersionUID = 1L;

	public DetallesActividad(PanelDetalles panel) {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(Pantalla.ancho / 3, Pantalla.alto / 3);
		
		setTitle(panel.getTitulo());
		
		add(panel, BorderLayout.CENTER);
		
		pack();
		setResizable(false);
		setVisible(true);
	}
	
}
