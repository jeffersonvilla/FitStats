package com.fit.actividad.vista.detalles;

import javax.swing.JLabel;

import com.fit.actividad.modelo.DeporteEquipo;

public class DetallesDeporteEquipo extends PanelDetalles {

	private static final long serialVersionUID = 1L;

	public DetallesDeporteEquipo(DeporteEquipo deporteEquipo) {
		super(deporteEquipo);

		add(new JLabel("Nombre del deporte:"));
		add(new JLabel(deporteEquipo.getNombreDeporte()), "wrap");
		
		add(new JLabel("Nombre de los equipos:"));
		add(new JLabel(deporteEquipo.getNombreEquipos()), "wrap");
		
		add(new JLabel("Resultado del partido:"));
		add(new JLabel(deporteEquipo.getResultadoDelPartido()), "wrap");
	}
}
