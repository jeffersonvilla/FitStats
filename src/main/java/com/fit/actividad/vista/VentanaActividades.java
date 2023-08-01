package com.fit.actividad.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.fit.actividad.ControladorActividad;
import com.fit.actividad.vista.interfaces.LimpiadorCamposError;
import com.fit.actividad.vista.interfaces.LimpiadorCamposTexto;
import com.fit.actividad.vista.interfaces.ValidadorCampoDistancia;
import com.fit.actividad.vista.interfaces.ValidadorCampoRitmoPromedio;
import com.fit.actividad.vista.interfaces.VistaActividades;
import com.fit.actividad.vista.panel.PanelFormularioCaminata;
import com.fit.actividad.vista.panel.PanelFormularioCarrera;
import com.fit.actividad.vista.panel.PanelFormularioCiclismo;
import com.fit.actividad.vista.panel.PanelFormularioNatacion;
import com.fit.util.Pantalla;

public class VentanaActividades extends JFrame implements VistaActividades{
	
	private static final long serialVersionUID = 1L;
	
	private ControladorActividad controlador;
	
	private JMenuBar barraMenu;
	
	private JPanel panelActividad;
	
	private JPanel panelSeleccionActividad;
	
	private JPanel panelFormularioActividad;
	
	private CardLayout cardLayout;
	
	private String[] opcionesActividad;

	private int actividadSelecionada;
	
	public VentanaActividades(final ControladorActividad controlador) {
		this.controlador = controlador;
		
		this.opcionesActividad = controlador.opcionesActividades();
		
		setSize(Pantalla.ancho/2, Pantalla.alto/2);
		setLocation(Pantalla.ancho/4, Pantalla.alto/4);
		
		this.barraMenu = getBarraMenu();
		add(this.barraMenu, BorderLayout.NORTH);
		
		this.cardLayout = new CardLayout();
		
		this.panelActividad = getPanelActividad();
		add(this.panelActividad, BorderLayout.CENTER);
		
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
	
	private JPanel getPanelActividad() {
		JPanel panelActividad = new JPanel(new BorderLayout());
		
		this.panelSeleccionActividad = getPanelSeleccionActividad();
		panelActividad.add(this.panelSeleccionActividad, BorderLayout.NORTH);
		
		this.panelFormularioActividad = getPanelFormularioActividad();
		panelActividad.add(this.panelFormularioActividad, BorderLayout.CENTER);
		
		return panelActividad;
	}
	
	private JPanel getPanelSeleccionActividad(){		
		JPanel panelSeleccionActividades = new JPanel();
		JComboBox<String> listaOpcionesActividad = new JComboBox<String>(opcionesActividad);
		listaOpcionesActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox opciones = (JComboBox) e.getSource();
				actividadSelecionada = opciones.getSelectedIndex();
				cardLayout.show(panelFormularioActividad, opcionesActividad[opciones.getSelectedIndex()]);
			}
		});	
		panelSeleccionActividades.add(listaOpcionesActividad);
		return panelSeleccionActividades;
	}

	private JPanel getPanelFormularioActividad() {
		JPanel panelFormularioActividad = new JPanel(this.cardLayout);
		panelFormularioActividad.add(new PanelFormularioCaminata(this.controlador), this.opcionesActividad[0]);
		panelFormularioActividad.add(new PanelFormularioCarrera(this.controlador), this.opcionesActividad[1]);
		panelFormularioActividad.add(new PanelFormularioCiclismo(this.controlador), this.opcionesActividad[2]);
		panelFormularioActividad.add(new PanelFormularioNatacion(this.controlador), this.opcionesActividad[3]);		
		return panelFormularioActividad;
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
		if(componente instanceof LimpiadorCamposTexto)
			((LimpiadorCamposTexto) componente).limpiarCamposTexto();
	}

	@Override
	public void limpiarCamposError(int actividad) {
		Component componente = this.panelFormularioActividad.getComponent(actividad); 
		if(componente instanceof LimpiadorCamposError)
			((LimpiadorCamposError) componente).limpiarCamposError();
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
}




