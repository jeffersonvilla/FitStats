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

public class VentanaDetallesDeporteEquipo extends JFrame {

	private static final long serialVersionUID = 1L;

	public VentanaDetallesDeporteEquipo(String nombreDeporte, String nombreEquipo, String resultadoPartido) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setLocation(Pantalla.ancho / 3, Pantalla.alto / 3);

		JPanel panelDetalles = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = Constraints.getGridBagConstraints();

		panelDetalles.add(new JLabel("Nombre del deporte: "), constraints);
		constraints.gridx = 1;
		panelDetalles.add(new JLabel(nombreDeporte), constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		panelDetalles.add(new JLabel("Nombre de los equipos: "), constraints);
		constraints.gridx = 1;
		panelDetalles.add(new JLabel(nombreEquipo), constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		panelDetalles.add(new JLabel("Resultado del partido: "), constraints);
		constraints.gridx = 1;
		panelDetalles.add(new JLabel(resultadoPartido), constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
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
