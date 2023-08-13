package com.fit.actividad.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.fit.actividad.ControladorActividad;
import com.fit.actividad.vista.interfaces.*;
import com.fit.actividad.vista.panelFormulario.*;
import com.fit.actividad.vista.panelesPrincipales.*;
import com.fit.util.Pantalla;

public class VentanaActividades extends JFrame implements VistaActividades, ActionListener {

	private static final long serialVersionUID = 1L;

	private ControladorActividad controlador;

	private String[] opcionesTipoActividad;

	private CardLayout cardLayout;

	private PanelContenedor panelContenedor;

	private PanelBotonesCrudActividad panelBotonesCrudActividad;

	private PanelVisualizacionActividades panelVisualizacionActividades;

	private PanelCardFormularioDetalleActividad panelCardFormularioDetalleActividad;

	private PanelContenedorRegistroActualizacionActividad panelContenedorRegistroActualizacionActividad;

	private PanelFormularioActividad panelFormularioActividad;

	private int tipoActividadSelecionada;

	public VentanaActividades(final ControladorActividad controlador) {
		this.controlador = controlador;
		this.opcionesTipoActividad = controlador.getOpcionesTipoActividad();
		this.cardLayout = new CardLayout();
		this.panelFormularioActividad = new PanelFormularioActividad();

		agregarBarraMenu();
		add(new PanelPrincipalActividades(this), BorderLayout.CENTER);
		ajustesVentana();
	}

	private void ajustesVentana() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				controlador.cerrarSesion();
			}
		});
		setSize(Pantalla.ancho / 2, Pantalla.alto / 2);
		setLocation(Pantalla.ancho / 4, Pantalla.alto / 4);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void agregarBarraMenu() {
		JMenuBar barraMenu = new JMenuBar();
		JMenu menuSesion = new JMenu("Usuario");
		JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesion");

		itemCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cerrarSesion();
			}
		});

		menuSesion.add(itemCerrarSesion);
		barraMenu.add(Box.createHorizontalGlue());
		barraMenu.add(menuSesion);
		add(barraMenu, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source instanceof JButton) {
			JButton boton = (JButton) source;

			if (boton.getText().equals("Nueva")) {
				this.panelFormularioActividad = new PanelFormularioActividad();
				this.panelContenedorRegistroActualizacionActividad.refrescarElementos();
				cardLayout.show(panelContenedor, PanelContenedor.PANEL_REGISTRO_ACTUALIZACION_ACTIVIDAD);
				panelBotonesCrudActividad.setVisibilidadBotonesCrudActividades(false);
			} else if (boton.getText().equals("Ver detalles")) {
				controlador.verDetallesActividadSeleccionada(getActividadSeleccionadaEnTabla());
			} else if (boton.getText().equals("Actualizar")) {
				controlador.actualizarActividad(getActividadSeleccionadaEnTabla());
			} else if (boton.getText().equals("Eliminar")) {
				controlador.eliminarActividad(getActividadSeleccionadaEnTabla());
			}
		}

		if (source instanceof JComboBox) {
			JComboBox opciones = (JComboBox) e.getSource();
			tipoActividadSelecionada = opciones.getSelectedIndex();
			cardLayout.show(panelCardFormularioDetalleActividad, opcionesTipoActividad[opciones.getSelectedIndex()]);
		}
	}

	public ControladorActividad getControlador() {
		return controlador;
	}

	public String[] getOpcionesTipoActividad() {
		return opcionesTipoActividad;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setPanelContenedor(PanelContenedor panelContenedor) {
		this.panelContenedor = panelContenedor;
	}

	public void setPanelBotonesCrudActividad(PanelBotonesCrudActividad panelBotonesCrudActividad) {
		this.panelBotonesCrudActividad = panelBotonesCrudActividad;
	}

	public void setPanelVisualizacionActividades(PanelVisualizacionActividades panelVisualizacionActividades) {
		this.panelVisualizacionActividades = panelVisualizacionActividades;
	}

	public PanelCardFormularioDetalleActividad getPanelCardFormularioDetalleActividad() {
		return panelCardFormularioDetalleActividad;
	}

	public void setPanelCardFormularioDetalleActividad(
			PanelCardFormularioDetalleActividad panelCardFormularioDetalleActividad) {
		this.panelCardFormularioDetalleActividad = panelCardFormularioDetalleActividad;
	}

	public PanelFormularioActividad getPanelFormularioActividad() {
		return panelFormularioActividad;
	}

	public void setPanelFormularioActividad(PanelFormularioActividad panelFormularioActividad) {
		this.panelFormularioActividad = panelFormularioActividad;
	}

	public void setPanelContenedorRegistroActualizacionActividad(
			PanelContenedorRegistroActualizacionActividad panelContenedorRegistroActualizacionActividad) {
		this.panelContenedorRegistroActualizacionActividad = panelContenedorRegistroActualizacionActividad;
	}

	private int getActividadSeleccionadaEnTabla() {
		return panelVisualizacionActividades.getTablaActividades().getSelectedRow();
	}

	@Override
	public int getTipoActividadSelecionada() {
		return this.tipoActividadSelecionada;
	}

	@Override
	public void limpiarCamposTexto(int actividad) {
		Component componente = this.panelCardFormularioDetalleActividad.getComponent(actividad);
		if (componente instanceof PanelFormulario)
			((PanelFormulario) componente).limpiarCamposTexto();
	}

	@Override
	public void limpiarCamposError(int actividad) {
		Component componente = this.panelCardFormularioDetalleActividad.getComponent(actividad);
		if (componente instanceof PanelFormulario)
			((PanelFormulario) componente).limpiarCamposError();
		this.panelFormularioActividad.limpiarCamposError();
	}

	@Override
	public void validarFechaActividad(String mensajeError) {
		this.panelFormularioActividad.mostrarErrorCampoFecha(mensajeError);
	}

	@Override
	public void validarDistancia(int actividad, String mensajeError) {
		Component componente = this.panelCardFormularioDetalleActividad.getComponent(actividad);
		if (componente instanceof ValidadorCampoDistancia)
			((ValidadorCampoDistancia) componente).mostrarErrorCampoDistancia(mensajeError);
	}

	@Override
	public void validarTipoBicicleta(int actividad, String mensajeError) {
		Component componente = this.panelCardFormularioDetalleActividad.getComponent(actividad);
		if (componente instanceof PanelFormularioCiclismo)
			((PanelFormularioCiclismo) componente).mostrarErrorCampoTipoBicicleta(mensajeError);
	}

	@Override
	public void validarEstilosNatacion(int actividad, String mensajeError) {
		Component componente = this.panelCardFormularioDetalleActividad.getComponent(actividad);
		if (componente instanceof PanelFormularioNatacion)
			((PanelFormularioNatacion) componente).mostrarErrorCampoEstilosNatacion(mensajeError);
	}

	@Override
	public void validarNombreDeporte(int actividad, String mensajeError) {
		Component componente = this.panelCardFormularioDetalleActividad.getComponent(actividad);
		if (componente instanceof PanelFormularioDeporteEquipo)
			((PanelFormularioDeporteEquipo) componente).mostrarErrorCampoNombreDeporte(mensajeError);
	}

	@Override
	public void validarNombreEquipos(int actividad, String mensajeError) {
		Component componente = this.panelCardFormularioDetalleActividad.getComponent(actividad);
		if (componente instanceof PanelFormularioDeporteEquipo)
			((PanelFormularioDeporteEquipo) componente).mostrarErrorNombreEquipos(mensajeError);
	}

	@Override
	public void validarResultadoDelPartido(int actividad, String mensajeError) {
		Component componente = this.panelCardFormularioDetalleActividad.getComponent(actividad);
		if (componente instanceof PanelFormularioDeporteEquipo)
			((PanelFormularioDeporteEquipo) componente).mostrarErrorCampoResultadoDelPartido(mensajeError);
	}

	@Override
	public void validarDescansosEntreEjercicios(int actividad, String mensajeError) {
		Component componente = this.panelCardFormularioDetalleActividad.getComponent(actividad);
		if (componente instanceof PanelFomularioEntrenamientoGimnasio)
			((PanelFomularioEntrenamientoGimnasio) componente).mostrarErrorCampoDescansoEntreEjercicios(mensajeError);
	}

	@Override
	public void validarDescansosEntreSeries(int actividad, String mensajeError) {
		Component componente = this.panelCardFormularioDetalleActividad.getComponent(actividad);
		if (componente instanceof PanelFomularioEntrenamientoGimnasio)
			((PanelFomularioEntrenamientoGimnasio) componente).mostrarErrorCampoDescansosEntreSeries(mensajeError);
	}

	@Override
	public void validarTipoSesionEstiramientos(int actividad, String mensajeError) {
		Component componente = this.panelCardFormularioDetalleActividad.getComponent(actividad);
		if (componente instanceof PanelFormularioEstiramientos)
			((PanelFormularioEstiramientos) componente).mostrarErrorCampoTipoSesion(mensajeError);
	}

	@Override
	public void validarNivelDificultadEstiramientos(int actividad, String mensajeError) {
		Component componente = this.panelCardFormularioDetalleActividad.getComponent(actividad);
		if (componente instanceof PanelFormularioEstiramientos)
			((PanelFormularioEstiramientos) componente).mostrarErrorCampoNivelDificultad(mensajeError);
	}

	@Override
	public void validarDescripcionOtraActividad(int actividad, String mensajeError) {
		Component componente = this.panelCardFormularioDetalleActividad.getComponent(actividad);
		if (componente instanceof PanelFormularioOtraActividad)
			((PanelFormularioOtraActividad) componente).mostrarErrorCampoDescripcion(mensajeError);
	}

	@Override
	public void verListaActividades() {
		cardLayout.show(panelContenedor, PanelContenedor.PANEL_VISUALIZACION_ACTIVIDADES);
		panelBotonesCrudActividad.setVisibilidadBotonesCrudActividades(true);
	}

	@Override
	public void mostrarPanelActualizacionActividad(PanelFormularioActividad panelActividad) {
		this.panelFormularioActividad = panelActividad;
		panelContenedorRegistroActualizacionActividad.refrescarElementos();
		cardLayout.show(panelContenedor, PanelContenedor.PANEL_REGISTRO_ACTUALIZACION_ACTIVIDAD);
		panelBotonesCrudActividad.setVisibilidadBotonesCrudActividades(false);
	}

	@Override
	public void actualizarListaActividades(List<Object[]> listaActividades) {
		panelVisualizacionActividades.actualizarListaActividades(listaActividades);
	}
}