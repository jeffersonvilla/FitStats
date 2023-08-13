package com.fit.actividad.vista.panelesPrincipales;

import javax.swing.JPanel;

import com.fit.actividad.vista.VentanaActividades;
import com.fit.actividad.vista.panelFormulario.PanelFomularioEntrenamientoGimnasio;
import com.fit.actividad.vista.panelFormulario.PanelFormularioCaminata;
import com.fit.actividad.vista.panelFormulario.PanelFormularioCiclismo;
import com.fit.actividad.vista.panelFormulario.PanelFormularioDeporteEquipo;
import com.fit.actividad.vista.panelFormulario.PanelFormularioEstiramientos;
import com.fit.actividad.vista.panelFormulario.PanelFormularioNatacion;
import com.fit.actividad.vista.panelFormulario.PanelFormularioOtraActividad;

public class PanelCardFormularioDetalleActividad extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelCardFormularioDetalleActividad(VentanaActividades ventana) {
		super(ventana.getCardLayout());

		ventana.setPanelCardFormularioDetalleActividad(this);

		String[] tipoActividad = ventana.getOpcionesTipoActividad();

		add(new PanelFormularioCaminata(), tipoActividad[0]);
		add(new PanelFormularioCiclismo(), tipoActividad[1]);
		add(new PanelFormularioNatacion(), tipoActividad[2]);
		add(new PanelFormularioDeporteEquipo(), tipoActividad[3]);
		add(new PanelFomularioEntrenamientoGimnasio(), tipoActividad[4]);
		add(new PanelFormularioEstiramientos(), tipoActividad[5]);
		add(new PanelFormularioOtraActividad(), tipoActividad[6]);
	}
}