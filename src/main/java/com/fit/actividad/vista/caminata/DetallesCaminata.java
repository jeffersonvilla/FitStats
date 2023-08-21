package com.fit.actividad.vista.caminata;

import java.sql.Time;
import java.sql.Timestamp;

import javax.swing.JLabel;

import com.fit.actividad.vista.actividades.PanelDetalles;

public class DetallesCaminata extends PanelDetalles {

	private static final long serialVersionUID = 1L;
	
	public DetallesCaminata(Timestamp fechaHora, Time duracion, String ubicacion, float distancia) {
		super(fechaHora, duracion, ubicacion);
		
		add(new JLabel("Distancia:"));
		add(new JLabel(Float.toString(distancia)));
	}
}
