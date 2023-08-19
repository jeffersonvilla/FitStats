package com.fit.actividad.vista.panelesPrincipales;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.fit.actividad.ControladorActividad;
import com.fit.actividad.vista.VentanaActividades;
import com.fit.actividad.vista.panelFormulario.FormularioEntrenamientoGimnasio;
import com.fit.actividad.vista.panelFormulario.PanelFormulario;
import com.fit.actividad.vista.panelFormulario.FormularioActividad;
import com.fit.actividad.vista.panelFormulario.FormularioCaminata;
import com.fit.actividad.vista.panelFormulario.FormularioCiclismo;
import com.fit.actividad.vista.panelFormulario.FormularioDeporteEquipo;
import com.fit.actividad.vista.panelFormulario.FormularioEstiramientos;
import com.fit.actividad.vista.panelFormulario.FormularioNatacion;
import com.fit.actividad.vista.panelFormulario.FormularioOtraActividad;
import com.fit.util.Constraints;

public class PanelBotonesRegistroActualizacionActividad extends JPanel {

	private static final long serialVersionUID = 1L;

	private VentanaActividades ventana;

	private ControladorActividad controlador;

	public PanelBotonesRegistroActualizacionActividad(VentanaActividades ventana) {
		super(new GridBagLayout());

		this.ventana = ventana;
		this.controlador = ventana.getControlador();

		GridBagConstraints constraints = Constraints.getGridBagConstraints();

		constraints.gridy = 0;
		add(getBotonGuardarActividad(), constraints);

		constraints.gridy = 1;
		add(getBotonCancelarRegistroActividad(), constraints);
	}

	private JButton getBotonGuardarActividad() {
		JButton botonGuardar = new JButton("Guardar");
		botonGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int actividadSelecionada = ventana.getTipoActividadSelecionada();

				ventana.limpiarCamposError(actividadSelecionada);
				
				Component card = ventana.getPanelCardFormularioDetalleActividad();
				
				Component contenido = ventana.getPanelContenidoDetalleActividad();
				
				Component componente = (contenido == null)? 
						((PanelCardFormularioDetalleActividad) card).getComponent(actividadSelecionada) 
						:(PanelFormulario) contenido;	
				
				FormularioActividad panelActividad = ventana.getPanelFormularioActividad();

//				if (componente instanceof PanelFormularioCaminata) {
//					controlador.registrarCaminata(ventana.getActividadSeleccionadaEnTabla(), panelActividad.getFecha(),
//							panelActividad.getDuracion(), panelActividad.getUbicacion(),
//							((PanelFormularioCaminata) componente).getDistancia());
//				}
//				if (componente instanceof PanelFormularioCiclismo) {
//					PanelFormularioCiclismo panelCiclismo = (PanelFormularioCiclismo) componente;
//					controlador.registrarCiclismo(ventana.getActividadSeleccionadaEnTabla(), panelActividad.getFecha(),
//							panelActividad.getDuracion(), panelActividad.getUbicacion(), panelCiclismo.getDistancia(),
//							panelCiclismo.getTipoBicicleta());
//				}
//				if (componente instanceof PanelFormularioNatacion) {
//					PanelFormularioNatacion panelNatacion = (PanelFormularioNatacion) componente;
//					controlador.registrarNatacion(ventana.getActividadSeleccionadaEnTabla(), panelActividad.getFecha(),
//							panelActividad.getDuracion(), panelActividad.getUbicacion(), panelNatacion.getDistancia(),
//							panelNatacion.getEstiloNatacion());
//				}
//				if (componente instanceof PanelFormularioDeporteEquipo) {
//					PanelFormularioDeporteEquipo panelDeporteEquipo = (PanelFormularioDeporteEquipo) componente;
//					controlador.registrarDeporteEquipo(ventana.getActividadSeleccionadaEnTabla(),
//							panelActividad.getFecha(), panelActividad.getDuracion(), panelActividad.getUbicacion(),
//							panelDeporteEquipo.getNombreDeporte(), panelDeporteEquipo.getNombreEquipos(),
//							panelDeporteEquipo.getResultadoDelPartido());
//				}
//				if (componente instanceof PanelFomularioEntrenamientoGimnasio) {
//					PanelFomularioEntrenamientoGimnasio panelEntrenamiento = (PanelFomularioEntrenamientoGimnasio) componente;
//					controlador.registrarEntrenamientoGimnasio(ventana.getActividadSeleccionadaEnTabla(),
//							panelActividad.getFecha(), panelActividad.getDuracion(), panelActividad.getUbicacion(),
//							panelEntrenamiento.getEjerciciosRealizados(),
//							panelEntrenamiento.getDescansosEntreEjercicios(),
//							panelEntrenamiento.getDescansosEntreSeries());
//				}
//				if (componente instanceof PanelFormularioEstiramientos) {
//					PanelFormularioEstiramientos panelEstiramientos = (PanelFormularioEstiramientos) componente;
//					controlador.registrarEstiramientos(ventana.getActividadSeleccionadaEnTabla(),
//							panelActividad.getFecha(), panelActividad.getDuracion(), panelActividad.getUbicacion(),
//							panelEstiramientos.getTipoSesion(), panelEstiramientos.getNivelDificultad());
//				}
//				if (componente instanceof PanelFormularioOtraActividad) {
//					PanelFormularioOtraActividad panelOtraActividad = (PanelFormularioOtraActividad) componente;
//					controlador.registrarOtraActividad(ventana.getActividadSeleccionadaEnTabla(),
//							panelActividad.getFecha(), panelActividad.getDuracion(), panelActividad.getUbicacion(),
//							panelOtraActividad.getDescripcion());
//				}
		}
		});
		return botonGuardar;
	}

	private JButton getBotonCancelarRegistroActividad() {
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.verListaActividades();
			}
		});
		return botonCancelar;
	}
}