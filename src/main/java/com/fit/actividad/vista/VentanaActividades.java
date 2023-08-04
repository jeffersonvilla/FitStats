package com.fit.actividad.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
import javax.swing.table.DefaultTableModel;

import com.fit.actividad.ControladorActividad;
import com.fit.actividad.vista.interfaces.ValidadorCampoDistancia;
import com.fit.actividad.vista.interfaces.ValidadorCampoRitmoPromedio;
import com.fit.actividad.vista.interfaces.VistaActividades;
import com.fit.actividad.vista.panel.PanelFomularioEntrenamientoGimnasio;
import com.fit.actividad.vista.panel.PanelFormulario;
import com.fit.actividad.vista.panel.PanelFormularioActividad;
import com.fit.actividad.vista.panel.PanelFormularioCaminata;
import com.fit.actividad.vista.panel.PanelFormularioCiclismo;
import com.fit.actividad.vista.panel.PanelFormularioDeporteEquipo;
import com.fit.actividad.vista.panel.PanelFormularioNatacion;
import com.fit.actividad.vista.panel.PanelFormularioOtraActividad;
import com.fit.actividad.vista.panel.PanelFormularioYoga;
import com.fit.util.Pantalla;

public class VentanaActividades extends JFrame implements VistaActividades{
	
	private static final long serialVersionUID = 1L;
	
	private ControladorActividad controlador;
	
	private JMenuBar barraMenu;
	
	private JPanel panelCrudActividades;
	
	private JPanel panelContenedor;
	
	private JPanel panelRegistroActividad;
	
	private JPanel panelSeleccionActividad;
	
	private JPanel panelFormularioActividad;
	
	private JButton botonRegistrarActividad;
	
	private CardLayout cardLayout;
	
	private String[] opcionesActividad;
	
	private DefaultTableModel modeloTablaActividades;

	private int actividadSelecionada;
	
	private final String PANEL_VISUALIZACION_ACTIVIDADES = "visualizacion actividades";
	
	private final String PANEL_REGISTRO_ACTIVIDAD = "registro actividad";
	
	public VentanaActividades(final ControladorActividad controlador) {
		this.controlador = controlador;
		
		this.opcionesActividad = controlador.opcionesActividades();
		
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
		JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesion");
		menuSesion.add(itemCerrarSesion);
		barraMenu.add(Box.createHorizontalGlue());
		barraMenu.add(menuSesion);
		add(barraMenu, BorderLayout.NORTH);
		
		itemCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cerrarSesion();
			}
		});
		return barraMenu;
	}
	
	private JPanel getPanelCrudActividades() {
		JPanel panelCrud = new JPanel(new BorderLayout());
		
		this.panelContenedor = getPanelContenedor();
		panelCrud.add(this.panelContenedor, BorderLayout.CENTER);
		
		this.botonRegistrarActividad = new JButton("Registrar actividad");
		this.botonRegistrarActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelContenedor, PANEL_REGISTRO_ACTIVIDAD);
				botonRegistrarActividad.setVisible(false);
			}
		});
		panelCrud.add(this.botonRegistrarActividad, BorderLayout.SOUTH);
		return panelCrud;
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
		JTable tablaActividades = new JTable(this.modeloTablaActividades);
		panelVisualizacionActividades.add(new JScrollPane(tablaActividades), BorderLayout.CENTER);
		return panelVisualizacionActividades;
	}
	
	private DefaultTableModel getModeloTabla() {
		DefaultTableModel modeloTablaActividades = new DefaultTableModel();
		modeloTablaActividades.addColumn("Tipo actividad");
		modeloTablaActividades.addColumn("Fecha y hora");
		modeloTablaActividades.addColumn("Duracion");
		modeloTablaActividades.addColumn("Ubicación");
		return modeloTablaActividades;
	}

	private JPanel getPanelRegistroActividad() {
		JPanel panelRegsitroActividad = new JPanel(new BorderLayout());
		
		JPanel contenedorRegistroActividad = new JPanel(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;

		PanelFormularioActividad panelActividad = new PanelFormularioActividad(); 
		contenedorRegistroActividad.add(panelActividad, constraints);
		
		constraints.gridy = 1;
		JPanel panelDetalleActividad = getPanelDetalleActividad();
		contenedorRegistroActividad.add(panelDetalleActividad, constraints);
		
		panelRegsitroActividad.add(contenedorRegistroActividad, BorderLayout.CENTER);
		
		JPanel panelBotones = new JPanel(new GridBagLayout());
		
		constraints.gridy = 0;
		JButton botonGuardar = new JButton("Guardar");
		botonGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Component componente = panelFormularioActividad.getComponent(getActividadSelecionada()); 
				
				if(componente instanceof PanelFormularioCaminata) {	
					limpiarCamposError(actividadSelecionada);
					controlador.registrarCaminata(
							panelActividad.getFecha(), 
							panelActividad.getDuracion(), 
							panelActividad.getUbicacion(), 
							((PanelFormularioCaminata) componente).getDistancia());
				}
				
			}
		});
		panelBotones.add(botonGuardar, constraints);
		
		constraints.gridy = 1;
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				verListaActividades();
			}
		});
		
		panelBotones.add(botonCancelar, constraints);
		
		panelRegsitroActividad.add(panelBotones, BorderLayout.SOUTH);
		
		return panelRegsitroActividad;
	}
	
	private JPanel getPanelDetalleActividad() {
		JPanel panelDetalleActividad = new JPanel(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		this.panelSeleccionActividad = getPanelSeleccionActividad();
		panelDetalleActividad.add(this.panelSeleccionActividad, constraints);
		
		constraints.gridy = 1;
		panelDetalleActividad.add(new JLabel(" "), constraints);
		
		constraints.gridy = 2;
		this.panelFormularioActividad = getPanelFormularioActividad();
		panelDetalleActividad.add(this.panelFormularioActividad, constraints);
		
		return panelDetalleActividad;
	}
	
	private JPanel getPanelSeleccionActividad(){		
		JPanel panelSeleccionActividades = new JPanel(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		panelSeleccionActividades.add(new JLabel("Tipo actividad"), constraints);
		
		constraints.gridx = 1;
		JComboBox<String> listaOpcionesActividad = new JComboBox<String>(opcionesActividad);
		listaOpcionesActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox opciones = (JComboBox) e.getSource();
				actividadSelecionada = opciones.getSelectedIndex();
				cardLayout.show(panelFormularioActividad, opcionesActividad[opciones.getSelectedIndex()]);
			}
		});	
		panelSeleccionActividades.add(listaOpcionesActividad, constraints);
		return panelSeleccionActividades;
	}

	private JPanel getPanelFormularioActividad() {
		JPanel panelFormularioActividad = new JPanel(this.cardLayout);
		panelFormularioActividad.add(new PanelFormularioCaminata(this.controlador), this.opcionesActividad[0]);
		/*panelFormularioActividad.add(new PanelFormularioCiclismo(this.controlador), this.opcionesActividad[1]);
		panelFormularioActividad.add(new PanelFormularioNatacion(this.controlador), this.opcionesActividad[2]);	
		panelFormularioActividad.add(new PanelFomularioEntrenamientoGimnasio(this.controlador), this.opcionesActividad[3]);
		panelFormularioActividad.add(new PanelFormularioYoga(this.controlador), this.opcionesActividad[4]);
		panelFormularioActividad.add(new PanelFormularioDeporteEquipo(this.controlador), this.opcionesActividad[5]);
		panelFormularioActividad.add(new PanelFormularioOtraActividad(this.controlador), this.opcionesActividad[6]);
		*/return panelFormularioActividad;
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
	}

	@Override
	public void validarDistancia(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad); 
		if(componente instanceof ValidadorCampoDistancia)
			((ValidadorCampoDistancia) componente).mostrarErrorCampoDistancia(mensajeError);
	}

	@Override
	public void validarRitmoPromedio(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad); 
		if (componente instanceof ValidadorCampoRitmoPromedio)
			((ValidadorCampoRitmoPromedio) componente).mostrarErrorCampoRitmoPromedio(mensajeError);
	}
	
	@Override
	public void validarTipoBicicleta(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad);
		if(componente instanceof PanelFormularioCiclismo)
			((PanelFormularioCiclismo) componente).mostrarErrorCampoTipoBicicleta(mensajeError);
	}
	
	@Override
	public void validarEstiloNatacion(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad);
		if(componente instanceof PanelFormularioNatacion)
			((PanelFormularioNatacion) componente).mostrarErrorCampoEstiloNatacion(mensajeError);
	}

	@Override
	public void validarNombreDeporte(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad);
		if(componente instanceof PanelFormularioDeporteEquipo)
			((PanelFormularioDeporteEquipo) componente).mostrarErrorCampoNombreDeporte(mensajeError);
	}

	@Override
	public void validarResultadoDelPartido(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad);
		if(componente instanceof PanelFormularioDeporteEquipo)
			((PanelFormularioDeporteEquipo) componente).mostrarErrorCampoResultadoDelPartido(mensajeError);
	}

	@Override
	public void validarDuracionDelPartido(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad);
		if(componente instanceof PanelFormularioDeporteEquipo)
			((PanelFormularioDeporteEquipo) componente).mostrarErrorCampoDuracionDelPartido(mensajeError);
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
	}	
}