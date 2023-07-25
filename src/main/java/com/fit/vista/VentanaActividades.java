package com.fit.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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

import com.fit.controlador.ControladorActividades;

public class VentanaActividades extends JFrame implements ActionListener{

	private ControladorActividades controlador;

	private JMenuBar barraMenu;
	
	private JPanel panelActividad;
	
	private JPanel panelSeleccionActividad;
	
	private JPanel panelFormularioActividad;
	
	private CardLayout cardLayout;
	
	private String[] opcionesActividad;
	
	public VentanaActividades(final ControladorActividades controlador) {
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
		listaOpcionesActividad.addActionListener(this);	
		panelSeleccionActividades.add(listaOpcionesActividad);
		return panelSeleccionActividades;
	}
	

	private JPanel getPanelFormularioActividad() {
		JPanel panelFormularioActividad = new JPanel(this.cardLayout);
		
		JPanel panelFormCaminata = getPanelFormularioCaminata();
		JPanel panelFormCarrera = getPanelFormularioCarrera();
		
		panelFormularioActividad.add(panelFormCaminata, this.opcionesActividad[0]);
		panelFormularioActividad.add(panelFormCarrera, this.opcionesActividad[1]);
		
		return panelFormularioActividad;
	}
	
	private JPanel getPanelFormularioCaminata() {
		JPanel panelFormularioCaminata = new JPanel();
		panelFormularioCaminata.setBackground(Color.RED);
		return panelFormularioCaminata;
	}
	
	private JPanel getPanelFormularioCarrera() {
		JPanel panelFormularioCarrera = new JPanel();
		panelFormularioCarrera.setBackground(Color.BLUE);
		return panelFormularioCarrera;
	}
	
	public void actionPerformed(ActionEvent e) {
		JComboBox opciones = (JComboBox) e.getSource();
		this.cardLayout.show(this.panelFormularioActividad, this.opcionesActividad[opciones.getSelectedIndex() ]);
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
	
}


