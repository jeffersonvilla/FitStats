package com.fit.actividad.vista.detalles;

import javax.swing.JLabel;

import com.fit.actividad.modelo.Caminata;

public class DetallesCaminata extends PanelDetalles {

	private static final long serialVersionUID = 1L;
	
	public DetallesCaminata(Caminata caminata) {
		super(caminata);
		
		add(new JLabel("Distancia:"));
		add(new JLabel(Float.toString(caminata.getDistancia())));
	}
}
