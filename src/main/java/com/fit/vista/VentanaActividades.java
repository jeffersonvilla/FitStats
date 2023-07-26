package com.fit.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.fit.controlador.ControladorActividades;

public class VentanaActividades extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private ControladorActividades controlador;
	
	private JMenuBar barraMenu;
	
	private JPanel panelActividad;
	
	private JPanel panelSeleccionActividad;
	
	private JPanel panelFormularioActividad;
	
	private CardLayout cardLayout;
	
	private String[] opcionesActividad;
	
	private JTextField textFieldDistancia;
	
	private JLabel labelErrorDistancia;
	
	private JTextField textFieldRitmoPromedio;
	
	private JLabel labelErrorRitmoPromedio;
	
	private int actividadSelecionada;
	
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
		
		JPanel panelFormCaminata = getPanelFormularioCaminata();
		panelFormularioActividad.add(panelFormCaminata, this.opcionesActividad[0]);
		
		JPanel panelFormCarrera = getPanelFormularioCarrera();
		panelFormularioActividad.add(panelFormCarrera, this.opcionesActividad[1]);
		
		return panelFormularioActividad;
	}
	
	private JPanel getPanelFormularioCaminata() {
		JPanel panelFormularioCaminata = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		ajustarConstraints(constraints, 0, 0, 1, 1, GridBagConstraints.WEST);
		panelFormularioCaminata.add(new JLabel("Distancia (km):"), constraints);
		
		ajustarConstraints(constraints, 1, 0, 1, 1, GridBagConstraints.WEST);
		this.textFieldDistancia = new JTextField(15);
		panelFormularioCaminata.add(this.textFieldDistancia, constraints);
		
		ajustarConstraints(constraints, 0, 1, 2, 1, GridBagConstraints.WEST);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.labelErrorDistancia = getLabelError();
		panelFormularioCaminata.add(this.labelErrorDistancia, constraints);
		
		ajustarConstraints(constraints, 0, 3, 2, 1, GridBagConstraints.WEST);
		JButton botonGuardarCaminata = new JButton("Guardar");
		panelFormularioCaminata.add(botonGuardarCaminata, constraints);
		botonGuardarCaminata.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.registrarCaminata(actividadSelecionada,textFieldDistancia.getText());;
			}
		});
		
		return panelFormularioCaminata;
	}
	
	private JLabel getLabelError() {
		JLabel labelError = new JLabel(" ");
		labelError.setForeground(Color.RED);
		return labelError;
	}
	
	private JPanel getPanelFormularioCarrera() {
		JPanel panelFormularioCarrera = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		ajustarConstraints(constraints, 0, 0, 1, 1, GridBagConstraints.WEST);
		panelFormularioCarrera.add(new JLabel("Distancia (km):"), constraints);
		
		ajustarConstraints(constraints, 1, 0, 1, 1, GridBagConstraints.WEST);
		this.textFieldDistancia = new JTextField(15);
		panelFormularioCarrera.add(this.textFieldDistancia, constraints);
		
		ajustarConstraints(constraints, 0, 1, 2, 1, GridBagConstraints.WEST);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.labelErrorDistancia = getLabelError();
		panelFormularioCarrera.add(this.labelErrorDistancia, constraints);
		
		ajustarConstraints(constraints, 0, 2, 1, 1, GridBagConstraints.WEST);
		panelFormularioCarrera.add(new JLabel("Ritmo promedio (min por km):"), constraints);
		
		ajustarConstraints(constraints, 1, 2, 1, 1, GridBagConstraints.WEST);
		this.textFieldRitmoPromedio = new JTextField(15);
		panelFormularioCarrera.add(this.textFieldRitmoPromedio, constraints);
		
		ajustarConstraints(constraints, 0, 3, 2, 1, GridBagConstraints.WEST);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.labelErrorRitmoPromedio = getLabelError();
		panelFormularioCarrera.add(this.labelErrorRitmoPromedio, constraints);
		
		ajustarConstraints(constraints, 0, 4, 2, 1, GridBagConstraints.WEST);
		JButton botonGuardarCarrera = new JButton("Guardar");
		panelFormularioCarrera.add(botonGuardarCarrera, constraints);
		botonGuardarCarrera.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.registrarCarrera(actividadSelecionada, textFieldDistancia.getText(), textFieldRitmoPromedio.getText());;
			}
		});
		return panelFormularioCarrera;
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
	
	private void ajustarConstraints(GridBagConstraints constraints, int x , int y, int w, int h, int a) {
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		if(a != -1) constraints.anchor = a;
	}

	public void mostrarErrorCampoDistancia(String mensajeError) {
		this.labelErrorDistancia.setText(mensajeError);
		this.textFieldDistancia.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	private void limpiarCampoErrorDistancia() {
		this.labelErrorDistancia.setText(" ");
		this.textFieldDistancia.setBorder(UIManager.getBorder("TextField.border"));
	}
	
	public void mostrarErrorCampoRitmoPromedio(String mensajeError) {
		this.labelErrorRitmoPromedio.setText(mensajeError);
		this.textFieldRitmoPromedio.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	private void limpiarCampoErrorRitmoPromedio() {
		this.labelErrorRitmoPromedio.setText(" ");
		this.textFieldRitmoPromedio.setBorder(UIManager.getBorder("TextField.border"));
	}
	
	public void limpiarCamposError() {
		limpiarCampoErrorDistancia();
		limpiarCampoErrorRitmoPromedio();
	}
	
	public void limpiarCamposTexto() {
		this.textFieldDistancia.setText("");
		this.textFieldRitmoPromedio.setText("");
	}
	
	
}

class PanelFormularioCaminata extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldDistancia;
	
	private JLabel labelErrorDistancia;
	
	private GridBagConstraints constraints;
	
	public PanelFormularioCaminata(final ControladorActividades controlador) {
		setLayout(new GridBagLayout());
		
		
	}
	
}

class PanelFormularioCarrera extends JPanel{

	private static final long serialVersionUID = 1L;
	
	
}


