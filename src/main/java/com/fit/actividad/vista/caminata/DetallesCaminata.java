package com.fit.actividad.vista.caminata;

import javax.swing.JLabel;

import com.fit.actividad.modelo.Caminata;
import com.fit.actividad.vista.actividades.PanelDetalles;

public class DetallesCaminata extends PanelDetalles {

	private static final long serialVersionUID = 1L;
	
	public DetallesCaminata(Caminata caminata) {
		super(caminata);
		
		add(new JLabel("Distancia:"));
		add(new JLabel(Float.toString(caminata.getDistancia())));
	}
}
