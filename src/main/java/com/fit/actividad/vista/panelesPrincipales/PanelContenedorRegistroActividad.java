package com.fit.actividad.vista.panelesPrincipales;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.fit.actividad.vista.VentanaActividades;
import com.fit.actividad.vista.panelFormulario.PanelFormularioActividad;
import com.fit.util.Constraints;

public class PanelContenedorRegistroActividad extends JPanel{

	private static final long serialVersionUID = 1L;

	public PanelContenedorRegistroActividad(VentanaActividades ventana) {
		super(new GridBagLayout());
		
		GridBagConstraints constraints = Constraints.getGridBagConstraints();
		
		PanelFormularioActividad panelFormularioActividad = new PanelFormularioActividad();
		
		ventana.setPanelFormularioActividad(panelFormularioActividad);
		add(panelFormularioActividad, constraints);
		
		constraints.gridy = 1;
		add(new PanelDetalleActividad(ventana), constraints);
	}
}