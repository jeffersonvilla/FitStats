package com.fit.vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.fit.controlador.ControladorActividades;

public class VentanaActividades extends JFrame{

	private ControladorActividades controlador;
	
	public VentanaActividades(final ControladorActividades controlador) {
		this.controlador = controlador;
		
		setSize(Pantalla.ancho/2, Pantalla.alto/2);
		setLocation(Pantalla.ancho/4, Pantalla.alto/4);
		
		JPanel panelMenuCerrarSesion = new JPanel();
		JButton botonCerrarSesion = new JButton("Cerrar sesion");
		
		botonCerrarSesion.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				controlador.cerrarSesion();
			}
		});
		
		panelMenuCerrarSesion.add(botonCerrarSesion);
		add(panelMenuCerrarSesion, BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {				
				super.windowClosing(e);
				
				controlador.cerrarSesion();
			}
		});
	} 
	
	
}


