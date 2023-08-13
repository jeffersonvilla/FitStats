package com.fit.actividad.vista.panelesPrincipales;

import javax.swing.JPanel;

import com.fit.actividad.vista.VentanaActividades;

public class PanelContenedor extends JPanel{

	private static final long serialVersionUID = 1L;

	public static final String PANEL_VISUALIZACION_ACTIVIDADES = "visualizacion actividades";
	
	public static final String PANEL_REGISTRO_ACTUALIZACION_ACTIVIDAD = "registro actualizacion actividad";
	
	public PanelContenedor(VentanaActividades ventana) {
		super(ventana.getCardLayout());
		ventana.setPanelContenedor(this);
		add(new PanelVisualizacionActividades(ventana), PANEL_VISUALIZACION_ACTIVIDADES);
		add(new PanelRegistroActualizacionActividad(ventana), PANEL_REGISTRO_ACTUALIZACION_ACTIVIDAD);
	}
}