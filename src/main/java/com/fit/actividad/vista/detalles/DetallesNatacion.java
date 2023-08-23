package com.fit.actividad.vista.detalles;

import javax.swing.JLabel;

import com.fit.actividad.modelo.Natacion;

public class DetallesNatacion extends PanelDetalles {

	private static final long serialVersionUID = 1L;

	public DetallesNatacion(Natacion natacion) {
		super(natacion);
	
		add(new JLabel("Distancia:"));
		add(new JLabel(Float.toString(natacion.getDistancia())), "wrap");
		
		add(new JLabel("Estilos de natación:"));
		add(new JLabel(natacion.getEstilosNatacion()), "wrap");
	}
}
