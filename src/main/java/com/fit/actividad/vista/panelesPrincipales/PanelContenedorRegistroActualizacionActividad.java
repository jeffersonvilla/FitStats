package com.fit.actividad.vista.panelesPrincipales;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.fit.actividad.vista.VentanaActividades;
import com.fit.util.Constraints;

public class PanelContenedorRegistroActualizacionActividad extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private VentanaActividades ventana;

	public PanelContenedorRegistroActualizacionActividad(VentanaActividades ventana) {
		super(new GridBagLayout());
		
		this.ventana = ventana;
		
		ventana.setPanelContenedorRegistroActualizacionActividad(this);
		
		inicializarElementos();
	}
	
	private void inicializarElementos() {
		GridBagConstraints constraints = Constraints.getGridBagConstraints();
		
		add(ventana.getPanelFormularioActividad(), constraints);
		
		constraints.gridy = 1;
		add(new PanelDetalleActividad(ventana), constraints);
	}
	
	public void refrescarElementos() {
		removeAll();
		inicializarElementos();
		revalidate();
		repaint();
	}
	
	
}