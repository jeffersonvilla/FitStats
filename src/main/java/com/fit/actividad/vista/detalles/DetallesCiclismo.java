package com.fit.actividad.vista.detalles;

import javax.swing.JLabel;

import com.fit.actividad.modelo.Ciclismo;

public class DetallesCiclismo extends PanelDetalles{

	private static final long serialVersionUID = 1L;

	public DetallesCiclismo(Ciclismo ciclismo) {
		super(ciclismo);
		
		add(new JLabel("Distancia:"));
		add(new JLabel(Float.toString(ciclismo.getDistancia())), "wrap");
		
		add(new JLabel("Tipo de bicicleta:"));
		add(new JLabel(ciclismo.getTipo_bicicleta()), "wrap");
	}

	
}
