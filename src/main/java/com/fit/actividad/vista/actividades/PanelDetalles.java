package com.fit.actividad.vista.actividades;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fit.actividad.modelo.Actividad;
import com.fit.util.Formato;

import net.miginfocom.swing.MigLayout;

/**
 * Panel que muestra la información en comun de las actividades
 * 
 * */
public abstract class PanelDetalles extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public PanelDetalles(Actividad actividad) {
		super(new MigLayout());
		
		add(new JLabel("Fecha y hora:"));
		add(new JLabel(Formato.formatearFechaHora(actividad.getFechaHora())), "wrap");
		
		add(new JLabel("Duración:"));
		add(new JLabel(Formato.formatearDuracion(actividad.getDuracion())), "wrap");
		
		add(new JLabel("Ubicación:"));
		add(new JLabel(actividad.getUbicacion()), "wrap");
	}
}
