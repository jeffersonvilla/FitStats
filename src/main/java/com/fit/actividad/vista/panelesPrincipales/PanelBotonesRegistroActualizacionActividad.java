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
import com.fit.actividad.vista.panelFormulario.PanelFomularioEntrenamientoGimnasio;
import com.fit.actividad.vista.panelFormulario.PanelFormularioActividad;
import com.fit.actividad.vista.panelFormulario.PanelFormularioCaminata;
import com.fit.actividad.vista.panelFormulario.PanelFormularioCiclismo;
import com.fit.actividad.vista.panelFormulario.PanelFormularioDeporteEquipo;
import com.fit.actividad.vista.panelFormulario.PanelFormularioEstiramientos;
import com.fit.actividad.vista.panelFormulario.PanelFormularioNatacion;
import com.fit.actividad.vista.panelFormulario.PanelFormularioOtraActividad;
import com.fit.util.Constraints;

public class PanelBotonesRegistroActualizacionActividad extends JPanel{
	
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
				
				Component componente = ventana.getPanelCardFormularioDetalleActividad().getComponent(actividadSelecionada);
				
				PanelFormularioActividad panelActividad = ventana.getPanelFormularioActividad();
				
				if(componente instanceof PanelFormularioCaminata) {		
					controlador.registrarCaminata(
							panelActividad.getFecha(), 
							panelActividad.getDuracion(), 
							panelActividad.getUbicacion(), 
							((PanelFormularioCaminata) componente).getDistancia());
				}
				if(componente instanceof PanelFormularioCiclismo) {
					PanelFormularioCiclismo panelCiclismo = (PanelFormularioCiclismo) componente;
					controlador.registrarCiclismo(
							panelActividad.getFecha(), 
							panelActividad.getDuracion(), 
							panelActividad.getUbicacion(), 
							panelCiclismo.getDistancia(),
							panelCiclismo.getTipoBicicleta());
				}
				if(componente instanceof PanelFormularioNatacion) {
					PanelFormularioNatacion panelNatacion = (PanelFormularioNatacion) componente;
					controlador.registrarNatacion(
							panelActividad.getFecha(), 
							panelActividad.getDuracion(), 
							panelActividad.getUbicacion(), 
							panelNatacion.getDistancia(),
							panelNatacion.getEstiloNatacion());
				}
				if(componente instanceof PanelFormularioDeporteEquipo) {
					PanelFormularioDeporteEquipo panelDeporteEquipo = (PanelFormularioDeporteEquipo) componente;
					controlador.registrarDeporteEquipo(
							panelActividad.getFecha(), 
							panelActividad.getDuracion(), 
							panelActividad.getUbicacion(), 
							panelDeporteEquipo.getNombreDeporte(),
							panelDeporteEquipo.getNombreEquipos(),
							panelDeporteEquipo.getResultadoDelPartido());
				}
				if(componente instanceof PanelFomularioEntrenamientoGimnasio) {
					PanelFomularioEntrenamientoGimnasio panelEntrenamiento = (PanelFomularioEntrenamientoGimnasio) componente;
					controlador.registrarEntrenamientoGimnasio(
							panelActividad.getFecha(), 
							panelActividad.getDuracion(), 
							panelActividad.getUbicacion(), 
							panelEntrenamiento.getEjerciciosRealizados(),
							panelEntrenamiento.getDescansosEntreEjercicios(),
							panelEntrenamiento.getDescansosEntreSeries());
				}
				if(componente instanceof PanelFormularioEstiramientos) {
					PanelFormularioEstiramientos panelEstiramientos = (PanelFormularioEstiramientos) componente;
					controlador.registrarEstiramientos(
							panelActividad.getFecha(), 
							panelActividad.getDuracion(), 
							panelActividad.getUbicacion(), 
							panelEstiramientos.getTipoSesion(),
							panelEstiramientos.getNivelDificultad());
				}
				if(componente instanceof PanelFormularioOtraActividad) {
					PanelFormularioOtraActividad panelOtraActividad = (PanelFormularioOtraActividad) componente;
					controlador.registrarOtraActividad(
							panelActividad.getFecha(), 
							panelActividad.getDuracion(), 
							panelActividad.getUbicacion(), 
							panelOtraActividad.getDescripcion());
				}
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