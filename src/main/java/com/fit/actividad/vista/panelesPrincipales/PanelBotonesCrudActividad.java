package com.fit.actividad.vista.panelesPrincipales;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.fit.actividad.vista.VentanaActividades;
import com.fit.util.Constraints;

public class PanelBotonesCrudActividad extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton botonRegistrarActividad;

	private JButton botonVerDetallesActividad;

	private JButton botonActualizarActividad;

	private JButton botonEliminarActividad;

	public PanelBotonesCrudActividad(VentanaActividades ventana) {
		super(new GridBagLayout());

		ventana.setPanelBotonesCrudActividad(this);

		GridBagConstraints constraints = Constraints.getGridBagConstraints();

		// TODO: que pasa si dan clic en los botones del crud sin seleccionar una
		// actividad en la tabla?

		botonRegistrarActividad = new JButton("Nueva");
		botonRegistrarActividad.addActionListener(ventana);
		add(botonRegistrarActividad, constraints);

		constraints.gridx = 1;
		botonVerDetallesActividad = new JButton("Ver detalles");
		botonVerDetallesActividad.addActionListener(ventana);
		add(botonVerDetallesActividad, constraints);

		constraints.gridx = 2;
		botonActualizarActividad = new JButton("Actualizar");
		botonActualizarActividad.addActionListener(ventana);
		add(botonActualizarActividad, constraints);

		constraints.gridx = 3;
		botonEliminarActividad = new JButton("Eliminar");
		botonEliminarActividad.addActionListener(ventana);
		add(botonEliminarActividad, constraints);
	}

	public void setVisibilidadBotonesCrudActividades(boolean visibilidad) {
		botonRegistrarActividad.setVisible(visibilidad);
		botonVerDetallesActividad.setVisible(visibilidad);
		botonActualizarActividad.setVisible(visibilidad);
		botonEliminarActividad.setVisible(visibilidad);
	}
}