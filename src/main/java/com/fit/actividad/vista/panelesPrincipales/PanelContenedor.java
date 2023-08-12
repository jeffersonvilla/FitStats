package com.fit.actividad.vista.panelesPrincipales;

import javax.swing.JPanel;

import com.fit.actividad.ControladorActividad;
import com.fit.actividad.vista.VentanaActividades;

public class PanelContenedor extends JPanel{

	private static final long serialVersionUID = 1L;

	public static final String PANEL_VISUALIZACION_ACTIVIDADES = "visualizacion actividades";
	
	public static final String PANEL_REGISTRO_ACTIVIDAD = "registro actividad";
	
	//private final String PANEL_ACTUALIZACION_ACTIVIDAD = "actualizacion actividad";
	
	public PanelContenedor(VentanaActividades ventana, ControladorActividad controlador) {
		super(ventana.getCardLayout());
		ventana.setPanelContenedor(this);
		add(new PanelVisualizacionActividades(ventana, controlador), PANEL_VISUALIZACION_ACTIVIDADES);
		add(new PanelRegistroActividad(ventana, controlador), PANEL_REGISTRO_ACTIVIDAD);
	}
}