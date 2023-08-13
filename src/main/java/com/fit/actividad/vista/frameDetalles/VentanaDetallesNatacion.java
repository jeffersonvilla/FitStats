package com.fit.actividad.vista.frameDetalles;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fit.util.Constraints;
import com.fit.util.Pantalla;

public class VentanaDetallesNatacion extends JFrame {

	private static final long serialVersionUID = 704325185814802227L;

	public VentanaDetallesNatacion(String distancia, String estilosNatacion) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setLocation(Pantalla.ancho / 3, Pantalla.alto / 3);

		JPanel panelDetalles = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = Constraints.getGridBagConstraints();

		panelDetalles.add(new JLabel("Distancia: "), constraints);
		constraints.gridx = 1;
		panelDetalles.add(new JLabel(distancia), constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		panelDetalles.add(new JLabel("Estilos de natación: "), constraints);
		constraints.gridx = 1;
		panelDetalles.add(new JLabel(estilosNatacion), constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
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
