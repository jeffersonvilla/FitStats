package com.fit.actividad.vista.detalles;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fit.actividad.modelo.Actividad;
import com.fit.util.Formato;

import net.miginfocom.swing.MigLayout;

public abstract class PanelDetalles extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private Actividad actividad;

	public PanelDetalles(Actividad actividad) {
		super(new MigLayout());
		
		this.actividad = actividad;
		
		add(new JLabel("Fecha y hora:"));
		add(new JLabel(Formato.formatearFechaHora(actividad.getFechaHora())), "wrap");
		
		add(new JLabel("Duración:"));
		add(new JLabel(Formato.formatearDuracion(actividad.getDuracion())), "wrap");
		
		add(new JLabel("Ubicación:"));
		add(new JLabel(actividad.getUbicacion()), "wrap");
	}
	
	public String getTitulo() {
		return actividad.getNombreTipoActividad();
	}
}
