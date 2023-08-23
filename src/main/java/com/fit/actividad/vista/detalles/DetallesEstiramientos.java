package com.fit.actividad.vista.detalles;

import javax.swing.JLabel;

import com.fit.actividad.modelo.Estiramientos;

public class DetallesEstiramientos extends PanelDetalles {

	private static final long serialVersionUID = 1L;

	public DetallesEstiramientos(Estiramientos estiramientos) {
		super(estiramientos);
		
		add(new JLabel("Tipo de sesion:"));
		add(new JLabel(estiramientos.getTipoSesion()), "wrap");
		
		add(new JLabel("Nivel de dificultad:"));
		add(new JLabel(estiramientos.getNivelDificultad()), "wrap");
	}
}
