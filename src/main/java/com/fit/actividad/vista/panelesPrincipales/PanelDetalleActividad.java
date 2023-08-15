package com.fit.actividad.vista.panelesPrincipales;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.fit.actividad.vista.VentanaActividades;
import com.fit.actividad.vista.panelFormulario.PanelFormulario;
import com.fit.util.Constraints;

public class PanelDetalleActividad extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private VentanaActividades ventana;
	
	public PanelDetalleActividad(VentanaActividades ventana) {
		super(new GridBagLayout());

		this.ventana = ventana;
		
		ventana.setPanelDetalleActividad(this);
		
		inicializarElementos();
	}
		
	private void inicializarElementos() {
		GridBagConstraints constraints = Constraints.getGridBagConstraints();

		add(new PanelSeleccionActividad(ventana), constraints);

		constraints.gridx = 1;
		add(new PanelCardFormularioDetalleActividad(ventana), constraints);
	}

	private void inicializarElementos(PanelFormulario panel) {
		GridBagConstraints constraints = Constraints.getGridBagConstraints();

		add(new PanelSeleccionActividad(ventana), constraints);

		constraints.gridx = 1;
		add(panel, constraints);
	}
	
	public void setPanelFormDetalleActividad(PanelFormulario panelFormDetalleActividad) {
		removeAll();
		inicializarElementos(panelFormDetalleActividad);
		revalidate();
		repaint();
	}
	
	public void refrescarElementos() {
		removeAll();
		inicializarElementos();
		revalidate();
		repaint();
	}
}