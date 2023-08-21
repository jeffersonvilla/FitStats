package com.fit.actividad.vista.actividades;

import java.sql.Time;
import java.sql.Timestamp;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fit.util.Formato;

import net.miginfocom.swing.MigLayout;

public abstract class PanelDetalles extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public PanelDetalles(Timestamp fechaHora, Time duracion, String ubicacion) {
		super(new MigLayout());
		
		add(new JLabel("Fecha y hora:"));
		add(new JLabel(Formato.formatearFechaHora(fechaHora)), "wrap");
		
		add(new JLabel("Duración:"));
		add(new JLabel(Formato.formatearDuracion(duracion)), "wrap");
		
		add(new JLabel("Ubicación:"));
		add(new JLabel(ubicacion), "wrap");
	}
}
