package com.fit.vista;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.fit.controlador.ControladorPrincipal;

public class VentanaPrincipal extends JFrame{

	private ControladorPrincipal controlador;
	
	private PanelPrincipal panel;
	
	public VentanaPrincipal(ControladorPrincipal controlador) {
		
		this.controlador = controlador;
		
		ajustarVentana();		
		agregarPanel();
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void ajustarVentana() {
		setSize(Pantalla.ancho/2, Pantalla.alto/2);
		setLocation(Pantalla.ancho/4, Pantalla.alto/4);
	}
	
	private void agregarPanel() {
		panel = new PanelPrincipal(this.controlador);
		add(panel, BorderLayout.CENTER);
	}
	
	
	
	
	
}

class PanelPrincipal extends JPanel{
	
	private GridBagConstraints constraints;
	
	public PanelPrincipal(final ControladorPrincipal controlador) {
		
		setLayout(new GridBagLayout());
		
		JButton botonIniciarSesion = new JButton("Iniciar sesion");
		JButton botonRegistrarUsuario = new JButton("Registrarse");
		
		this.constraints = new GridBagConstraints();
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		
		ajustarConstraints(0, 0, 2, 1, GridBagConstraints.CENTER);
		add(botonIniciarSesion, this.constraints);
		
		ajustarConstraints(0, 1, 2, 1, GridBagConstraints.CENTER);
		add(botonRegistrarUsuario, this.constraints);
		
		botonIniciarSesion.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
		
				controlador.abrirVentanaInicioSesion();
			}
		});
		
		botonRegistrarUsuario.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
		
				controlador.abrirVentanaRegistroUsuario();
			}
		});
	}
	
	private void ajustarConstraints(int x , int y, int w, int h, int a) {
		this.constraints.gridx = x;
		this.constraints.gridy = y;
		this.constraints.gridwidth = w;
		this.constraints.gridheight = h;
		if(a != -1) this.constraints.anchor = a;
	}
	
}
