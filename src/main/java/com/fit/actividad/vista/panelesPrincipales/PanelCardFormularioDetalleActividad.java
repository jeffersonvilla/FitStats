package com.fit.actividad.vista.panelesPrincipales;

import javax.swing.JPanel;

import com.fit.actividad.vista.VentanaActividades;
import com.fit.actividad.vista.panelFormulario.FormularioEntrenamientoGimnasio;
import com.fit.actividad.vista.panelFormulario.FormularioCaminata;
import com.fit.actividad.vista.panelFormulario.FormularioCiclismo;
import com.fit.actividad.vista.panelFormulario.FormularioDeporteEquipo;
import com.fit.actividad.vista.panelFormulario.FormularioEstiramientos;
import com.fit.actividad.vista.panelFormulario.FormularioNatacion;
import com.fit.actividad.vista.panelFormulario.FormularioOtraActividad;

public class PanelCardFormularioDetalleActividad extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelCardFormularioDetalleActividad(VentanaActividades ventana) {
		super(ventana.getCardLayout());

		ventana.setPanelCardFormularioDetalleActividad(this);

		String[] tipoActividad = ventana.getOpcionesTipoActividad();

		add(new FormularioCaminata(), tipoActividad[0]);
		add(new FormularioCiclismo(), tipoActividad[1]);
		add(new FormularioNatacion(), tipoActividad[2]);
		add(new FormularioDeporteEquipo(), tipoActividad[3]);
		add(new FormularioEntrenamientoGimnasio(), tipoActividad[4]);
		add(new FormularioEstiramientos(), tipoActividad[5]);
		add(new FormularioOtraActividad(), tipoActividad[6]);
	}
}