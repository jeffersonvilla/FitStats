package com.fit.actividad.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.fit.actividad.ControladorActividad;
import com.fit.actividad.vista.interfaces.ValidadorCampoDistancia;
import com.fit.actividad.vista.interfaces.VistaActividades;
import com.fit.actividad.vista.panelFormulario.PanelFomularioEntrenamientoGimnasio;
import com.fit.actividad.vista.panelFormulario.PanelFormulario;
import com.fit.actividad.vista.panelFormulario.PanelFormularioActividad;
import com.fit.actividad.vista.panelFormulario.PanelFormularioCaminata;
import com.fit.actividad.vista.panelFormulario.PanelFormularioCiclismo;
import com.fit.actividad.vista.panelFormulario.PanelFormularioDeporteEquipo;
import com.fit.actividad.vista.panelFormulario.PanelFormularioEstiramientos;
import com.fit.actividad.vista.panelFormulario.PanelFormularioNatacion;
import com.fit.actividad.vista.panelFormulario.PanelFormularioOtraActividad;
import com.fit.util.Pantalla;

public class VentanaActividades extends JFrame implements VistaActividades{
	
	private static final long serialVersionUID = 1L;
	
	private ControladorActividad controlador;
	
	private JMenuBar barraMenu;
	
	private JPanel panelCrudActividades;
	
	private JPanel panelContenedor;
	
	private JTable tablaActividades;
	
	private JPanel panelBotonesCrudActividad;
	
	private JPanel panelSeleccionActividad;

	private PanelFormularioActividad panelActividad;
	
	private JPanel panelFormularioActividad;
	
	private JButton botonRegistrarActividad;
	
	private JButton botonVerDetallesActividad;
	
	private CardLayout cardLayout;
	
	private String[] opcionesTipoActividad;
	
	private DefaultTableModel modeloTablaActividades;

	private int actividadSelecionada;
	
	private final String PANEL_VISUALIZACION_ACTIVIDADES = "visualizacion actividades";
	
	private final String PANEL_REGISTRO_ACTIVIDAD = "registro actividad";
	
	public VentanaActividades(final ControladorActividad controlador) {
		this.controlador = controlador;
		
		this.opcionesTipoActividad = controlador.getOpcionesTipoActividad();
		
		setSize(Pantalla.ancho/2, Pantalla.alto/2);
		setLocation(Pantalla.ancho/4, Pantalla.alto/4);
		
		this.barraMenu = getBarraMenu();
		add(this.barraMenu, BorderLayout.NORTH);
		
		this.cardLayout = new CardLayout();
		
		this.panelCrudActividades = getPanelCrudActividades();
		add(this.panelCrudActividades, BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		agregarEventoCerrarSesionCuandoCierraVentana();
	} 

	private JMenuBar getBarraMenu() {
		JMenuBar barraMenu = new JMenuBar();
		JMenu menuSesion = new JMenu("Usuario");
		JMenuItem itemCerrarSesion = getItemCerrarSesion();
		
		menuSesion.add(itemCerrarSesion);
		barraMenu.add(Box.createHorizontalGlue());
		barraMenu.add(menuSesion);
		add(barraMenu, BorderLayout.NORTH);
		
		return barraMenu;
	}
	
	private JMenuItem getItemCerrarSesion() {
		JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesion");
		
		itemCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cerrarSesion();
			}
		});
		return itemCerrarSesion;
	}

	private JPanel getPanelCrudActividades() {
		JPanel panelCrud = new JPanel(new BorderLayout());
		
		this.panelBotonesCrudActividad = getPanelBotonesCrudActividad();
		panelCrud.add(this.panelBotonesCrudActividad, BorderLayout.SOUTH);
		
		this.panelContenedor = getPanelContenedor();
		panelCrud.add(this.panelContenedor, BorderLayout.CENTER);
		
		return panelCrud;
	}

	private JPanel getPanelBotonesCrudActividad() {
		JPanel panelBotonesCrud = new JPanel(new GridBagLayout());
		
		GridBagConstraints constraints = getGridBagConstraints();
		
		this.botonRegistrarActividad = getBotonRegistrarActividad();
		panelBotonesCrud.add(this.botonRegistrarActividad, constraints);
		
		constraints.gridx = 1;
		this.botonVerDetallesActividad = getBotonVerDetallesActividad();
		panelBotonesCrud.add(this.botonVerDetallesActividad, constraints);
		
		return panelBotonesCrud;
	}

	private JButton getBotonRegistrarActividad() {
		JButton botonRegistrarActividad = new JButton("Registrar nueva actividad");
		botonRegistrarActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelContenedor, PANEL_REGISTRO_ACTIVIDAD);
				botonRegistrarActividad.setVisible(false);
				botonVerDetallesActividad.setVisible(false);
			}
		});
		return botonRegistrarActividad;
	}
	
	private JButton getBotonVerDetallesActividad() {
		JButton botonVerDetallesActividad = new JButton("Ver detalles actividad seleccionada");
		botonVerDetallesActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.verDetallesActividadSeleccionada(tablaActividades.getSelectedRow());
			}
		});
		return botonVerDetallesActividad;
	}
	
	private JPanel getPanelContenedor() {
		JPanel panelContenedor = new JPanel(this.cardLayout);
		panelContenedor.add(getPanelVisualizacionActividades(), PANEL_VISUALIZACION_ACTIVIDADES);
		panelContenedor.add(getPanelRegistroActividad(), PANEL_REGISTRO_ACTIVIDAD);
		return panelContenedor;
	}
	
	private JPanel getPanelVisualizacionActividades() {
		JPanel panelVisualizacionActividades = new JPanel(new BorderLayout());
		this.modeloTablaActividades = getModeloTabla();
		actualizarListaActividades(controlador.getListaActividades());
		this.tablaActividades = new JTable(this.modeloTablaActividades);
		this.tablaActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelVisualizacionActividades.add(new JScrollPane(this.tablaActividades), BorderLayout.CENTER);
		return panelVisualizacionActividades;
	}
	
	private DefaultTableModel getModeloTabla() {
		DefaultTableModel modeloTablaActividades = new DefaultTableModel() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {				
				return false;
			}
		};
		modeloTablaActividades.addColumn("Tipo de actividad");
		modeloTablaActividades.addColumn("Fecha y hora de inicio");
		modeloTablaActividades.addColumn("Duracion (horas:minutos:segundos)");
		modeloTablaActividades.addColumn("Ubicación");
		return modeloTablaActividades;
	}

	private JPanel getPanelRegistroActividad() {
		JPanel panelRegsitroActividad = new JPanel(new BorderLayout());
		
		GridBagConstraints constraints = getGridBagConstraints();
		
		JPanel contenedorRegistroActividad = getPanelContenedorRegistroActividad(constraints); 
		panelRegsitroActividad.add(contenedorRegistroActividad, BorderLayout.CENTER);
		
		JPanel panelBotones = getPanelBotonesRegistroActividad(constraints);
		panelRegsitroActividad.add(panelBotones, BorderLayout.SOUTH);
		
		return panelRegsitroActividad;
	}

	private GridBagConstraints getGridBagConstraints() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		return constraints;
	}

	private JPanel getPanelContenedorRegistroActividad(GridBagConstraints constraints) {
		JPanel contenedorRegistroActividad = new JPanel(new GridBagLayout());
		
		this.panelActividad = new PanelFormularioActividad();
		contenedorRegistroActividad.add(this.panelActividad, constraints);
		constraints.gridy = 1;
		contenedorRegistroActividad.add(getPanelDetalleActividad(), constraints);
		
		return contenedorRegistroActividad;
	}
	
	
	private JPanel getPanelDetalleActividad() {
		JPanel panelDetalleActividad = new JPanel(new GridBagLayout());
		
		GridBagConstraints constraints = getGridBagConstraints();
		
		this.panelSeleccionActividad = getPanelSeleccionActividad(constraints);
		panelDetalleActividad.add(this.panelSeleccionActividad, constraints);
			
		constraints.gridx = 2;
		this.panelFormularioActividad = getPanelFormularioActividad();
		panelDetalleActividad.add(this.panelFormularioActividad, constraints);
		
		return panelDetalleActividad;
	}
	
	private JPanel getPanelSeleccionActividad(GridBagConstraints constraints){		
		JPanel panelSeleccionActividades = new JPanel(new GridBagLayout());
		
		panelSeleccionActividades.add(new JLabel("Tipo actividad"), constraints);
		
		constraints.gridx = 1;
		JComboBox<String> listaOpcionesTipoActividad = getComboBoxListaOpcionesTipoActividad();
		panelSeleccionActividades.add(listaOpcionesTipoActividad, constraints);
		
		return panelSeleccionActividades;
	}

	private JComboBox<String> getComboBoxListaOpcionesTipoActividad() {
		JComboBox<String> listaOpcionesTipoActividad = new JComboBox<String>(opcionesTipoActividad);
		listaOpcionesTipoActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox opciones = (JComboBox) e.getSource();
				actividadSelecionada = opciones.getSelectedIndex();
				cardLayout.show(panelFormularioActividad, opcionesTipoActividad[opciones.getSelectedIndex()]);
			}
		});	
		return listaOpcionesTipoActividad;
	}

	private JPanel getPanelFormularioActividad() {
		JPanel panelFormularioActividad = new JPanel(this.cardLayout);
		panelFormularioActividad.add(new PanelFormularioCaminata(), this.opcionesTipoActividad[0]);
		panelFormularioActividad.add(new PanelFormularioCiclismo(), this.opcionesTipoActividad[1]);
		panelFormularioActividad.add(new PanelFormularioNatacion(), this.opcionesTipoActividad[2]);	
		panelFormularioActividad.add(new PanelFormularioDeporteEquipo(), this.opcionesTipoActividad[3]);
		panelFormularioActividad.add(new PanelFomularioEntrenamientoGimnasio(), this.opcionesTipoActividad[4]);
		panelFormularioActividad.add(new PanelFormularioEstiramientos(), this.opcionesTipoActividad[5]);
		panelFormularioActividad.add(new PanelFormularioOtraActividad(), this.opcionesTipoActividad[6]);
		return panelFormularioActividad;
	}
	
	private JPanel getPanelBotonesRegistroActividad(GridBagConstraints constraints) {
		JPanel panelBotonesRegistroActividad = new JPanel(new GridBagLayout());
		
		constraints.gridy = 0;
		JButton botonGuardarActividad = getBotonGuardarActividad();
		panelBotonesRegistroActividad.add(botonGuardarActividad, constraints);
		
		constraints.gridy = 1;
		JButton botonCancelarRegistro = getBotonCancelarRegistroActividad();
		panelBotonesRegistroActividad.add(botonCancelarRegistro, constraints);
		
		return panelBotonesRegistroActividad;
	}

	private JButton getBotonGuardarActividad() {
		JButton botonGuardar = new JButton("Guardar");
		botonGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarCamposError(actividadSelecionada);
				
				Component componente = panelFormularioActividad.getComponent(getActividadSelecionada()); 
				
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
				verListaActividades();
			}
		});
		return botonCancelar;
	}

	private void agregarEventoCerrarSesionCuandoCierraVentana() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {				
				super.windowClosing(e);
				controlador.cerrarSesion();
			}
		});
	}

	@Override
	public int getActividadSelecionada() {
		return this.actividadSelecionada;
	}
	
	@Override
	public void limpiarCamposTexto(int actividad) {
		Component componente = this.panelFormularioActividad.getComponent(actividad); 
		if(componente instanceof PanelFormulario)
			((PanelFormulario) componente).limpiarCamposTexto();
	}

	@Override
	public void limpiarCamposError(int actividad) {
		Component componente = this.panelFormularioActividad.getComponent(actividad); 
		if(componente instanceof PanelFormulario)
			((PanelFormulario) componente).limpiarCamposError();
		this.panelActividad.limpiarCamposError();
	}

	@Override
	public void validarFechaActividad(String mensajeError) {
		this.panelActividad.mostrarErrorCampoFecha(mensajeError);
	}
	
	@Override
	public void validarDistancia(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad); 
		if(componente instanceof ValidadorCampoDistancia)
			((ValidadorCampoDistancia) componente).mostrarErrorCampoDistancia(mensajeError);
	}
	
	@Override
	public void validarTipoBicicleta(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad);
		if(componente instanceof PanelFormularioCiclismo)
			((PanelFormularioCiclismo) componente).mostrarErrorCampoTipoBicicleta(mensajeError);
	}
	
	@Override
	public void validarEstilosNatacion(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad);
		if(componente instanceof PanelFormularioNatacion)
			((PanelFormularioNatacion) componente).mostrarErrorCampoEstilosNatacion(mensajeError);
	}

	@Override
	public void validarNombreDeporte(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad);
		if(componente instanceof PanelFormularioDeporteEquipo)
			((PanelFormularioDeporteEquipo) componente).mostrarErrorCampoNombreDeporte(mensajeError);
	}
	
	@Override
	public void validarNombreEquipos(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad);
		if(componente instanceof PanelFormularioDeporteEquipo)
			((PanelFormularioDeporteEquipo) componente).mostrarErrorNombreEquipos(mensajeError);
	}

	@Override
	public void validarResultadoDelPartido(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad);
		if(componente instanceof PanelFormularioDeporteEquipo)
			((PanelFormularioDeporteEquipo) componente).mostrarErrorCampoResultadoDelPartido(mensajeError);
	}

	@Override
	public void validarDescansosEntreEjercicios(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad);
		if(componente instanceof PanelFomularioEntrenamientoGimnasio)
			((PanelFomularioEntrenamientoGimnasio) componente).mostrarErrorCampoDescansoEntreEjercicios(mensajeError);
	}
	
	@Override
	public void validarDescansosEntreSeries(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad);
		if(componente instanceof PanelFomularioEntrenamientoGimnasio)
			((PanelFomularioEntrenamientoGimnasio) componente).mostrarErrorCampoDescansosEntreSeries(mensajeError);
	}	

	@Override
	public void validarTipoSesionEstiramientos(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad);
		if(componente instanceof PanelFormularioEstiramientos)
			((PanelFormularioEstiramientos) componente).mostrarErrorCampoTipoSesion(mensajeError);
	}

	@Override
	public void validarNivelDificultadEstiramientos(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad);
		if(componente instanceof PanelFormularioEstiramientos)
			((PanelFormularioEstiramientos) componente).mostrarErrorCampoNivelDificultad(mensajeError);
	}
	
	@Override
	public void validarDescripcionOtraActividad(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad);
		if(componente instanceof PanelFormularioOtraActividad)
			((PanelFormularioOtraActividad) componente).mostrarErrorCampoDescripcion(mensajeError);
	}
	
	@Override
	public void actualizarListaActividades(List<Object[]> listaActividades) {
		this.modeloTablaActividades.setRowCount(0);
		for(Object[] actividad: listaActividades) this.modeloTablaActividades.addRow(actividad);
		this.modeloTablaActividades.fireTableDataChanged();
	}

	@Override
	public void verListaActividades() {
		cardLayout.show(panelContenedor, PANEL_VISUALIZACION_ACTIVIDADES);
		botonRegistrarActividad.setVisible(true);
		botonVerDetallesActividad.setVisible(true);
	}
}