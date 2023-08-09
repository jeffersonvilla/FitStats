package com.fit.actividad.vista.frameDetalles;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.fit.util.Constraints;
import com.fit.util.Pantalla;

public class VentanaDetallesOtraActividad extends JFrame {

	private static final long serialVersionUID = 1L;

	public VentanaDetallesOtraActividad(String descripcion) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setLocation(Pantalla.ancho/3, Pantalla.alto/3);
		
		JPanel panelDetalles = new JPanel(new GridBagLayout());
		
		GridBagConstraints constraints = Constraints.getGridBagConstraints();
		
		panelDetalles.add(new JLabel("Descripcion: "), constraints);
		constraints.gridx = 1;
		JTextArea textAreaDescripcion = new JTextArea(descripcion);
		textAreaDescripcion.setColumns(20);
		textAreaDescripcion.setEditable(false);
		panelDetalles.add(textAreaDescripcion, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		JButton botonCerrar = new JButton("Cerrar");
		botonCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panelDetalles.add(botonCerrar, constraints);
		
		add(panelDetalles);
		
		pack();
		
		setVisible(true);
	}
}
