package com.fit.vista;

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

import com.fit.controlador.ControladorActividad;

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
		
		PanelFormularioCaminata panelFormCaminata = new PanelFormularioCaminata(this.controlador);
		panelFormularioActividad.add(panelFormCaminata, this.opcionesActividad[0]);
		
		PanelFormularioCarrera panelFormCarrera = new PanelFormularioCarrera(this.controlador);
		panelFormularioActividad.add(panelFormCarrera, this.opcionesActividad[1]);
		
		PanelFormularioCiclismo panelFormCiclismo = new PanelFormularioCiclismo(this.controlador);
		panelFormularioActividad.add(panelFormCiclismo, this.opcionesActividad[2]);
		
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
	public void limpiarCamposError(int actividad) {
		Component componente = this.panelFormularioActividad.getComponent(actividad); 
		if(componente instanceof PanelFormularioCaminata)
			((PanelFormularioCaminata) componente).limpiarCamposError();
		else if (componente instanceof PanelFormularioCarrera)
			((PanelFormularioCarrera) componente).limpiarCamposError();
	}

	@Override
	public void validarDistancia(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad); 
		if(componente instanceof PanelFormularioCaminata)
			((PanelFormularioCaminata) componente).mostrarErrorCampoDistancia(mensajeError);
		else if (componente instanceof PanelFormularioCarrera)
			((PanelFormularioCarrera) componente).mostrarErrorCampoDistancia(mensajeError);
		else if(componente instanceof PanelFormularioCiclismo)
			((PanelFormularioCiclismo) componente).mostrarErrorCampoDistancia(mensajeError);
	}

	@Override
	public void validarRitmoPromedio(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad); 
		if (componente instanceof PanelFormularioCarrera)
			((PanelFormularioCarrera) componente).mostrarErrorCampoRitmoPromedio(mensajeError);
	}
	
	@Override
	public void validarTipoBicicleta(int actividad, String mensajeError) {
		Component componente = this.panelFormularioActividad.getComponent(actividad);
		if(componente instanceof PanelFormularioCiclismo)
			((PanelFormularioCiclismo) componente).mostrarErrorCampoTipoBicicleta(mensajeError);
	}

	@Override
	public void limpiarCamposTexto(int actividad) {
		Component componente = this.panelFormularioActividad.getComponent(actividad); 
		if(componente instanceof PanelFormularioCaminata)
			((PanelFormularioCaminata) componente).limpiarCamposTexto();
		else if (componente instanceof PanelFormularioCarrera)
			((PanelFormularioCarrera) componente).limpiarCamposTexto();
		else if(componente instanceof PanelFormularioCiclismo)
			((PanelFormularioCiclismo) componente).limpiarCamposTexto();
	}		
}

class PanelFormularioCaminata extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldDistancia;
	
	private JLabel labelErrorDistancia;
	
	private GridBagConstraints constraints;
	
	public PanelFormularioCaminata(final ControladorActividad controlador) {
		setLayout(new GridBagLayout());
		
		this.constraints = new GridBagConstraints();
		this.constraints.anchor = GridBagConstraints.WEST;
		
		ajustarConstraints(0, 0, 1, 1);
		add(new JLabel("Distancia (km):"), constraints);
		
		ajustarConstraints(1, 0, 1, 1);
		this.textFieldDistancia = new JTextField(15);
		add(this.textFieldDistancia, constraints);
		
		ajustarConstraints(0, 1, 2, 1);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.labelErrorDistancia = getLabelError();
		add(this.labelErrorDistancia, constraints);
		
		ajustarConstraints(0, 2, 2, 1);
		JButton botonGuardarCarrera = new JButton("Guardar");
		add(botonGuardarCarrera, constraints);
		botonGuardarCarrera.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarCamposError();
				controlador.registrarCaminata(textFieldDistancia.getText());
			}
		});
	
	}

	private JLabel getLabelError() {
		JLabel labelError = new JLabel(" ");
		labelError.setForeground(Color.RED);
		return labelError;
	}
	
	private void ajustarConstraints(int x , int y, int w, int h) {
		this.constraints.gridx = x;
		this.constraints.gridy = y;
		this.constraints.gridwidth = w;
		this.constraints.gridheight = h;
	}
	
	public void limpiarCamposError() {
		limpiarCampoErrorDistancia();
	}
	
	public void limpiarCamposTexto() {
		this.textFieldDistancia.setText("");
	}
	
	public void mostrarErrorCampoDistancia(String mensajeError) {
		this.labelErrorDistancia.setText(mensajeError);
		this.textFieldDistancia.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	private void limpiarCampoErrorDistancia() {
		this.labelErrorDistancia.setText(" ");
		this.textFieldDistancia.setBorder(UIManager.getBorder("TextField.border"));
	}
}

class PanelFormularioCarrera extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldDistancia;
	
	private JLabel labelErrorDistancia;
	
	private JTextField textFieldRitmoPromedio;
	
	private JLabel labelErrorRitmoPromedio;
	
	private GridBagConstraints constraints;
	
	public PanelFormularioCarrera(final ControladorActividad controlador) {
		setLayout(new GridBagLayout());
		
		this.constraints = new GridBagConstraints();
		this.constraints.anchor = GridBagConstraints.WEST;
		
		ajustarConstraints(0, 0, 1, 1);
		add(new JLabel("Distancia (km):"), constraints);
		
		ajustarConstraints(1, 0, 1, 1);
		this.textFieldDistancia = new JTextField(15);
		add(this.textFieldDistancia, constraints);
		
		ajustarConstraints(0, 1, 2, 1);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.labelErrorDistancia = getLabelError();
		add(this.labelErrorDistancia, constraints);
		
		ajustarConstraints(0, 2, 1, 1);
		add(new JLabel("Ritmo promedio (min por km):"), constraints);
		
		ajustarConstraints(1, 2, 1, 1);
		this.textFieldRitmoPromedio = new JTextField(15);
		add(this.textFieldRitmoPromedio, constraints);
		
		ajustarConstraints(0, 3, 2, 1);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.labelErrorRitmoPromedio = getLabelError();
		add(this.labelErrorRitmoPromedio, constraints);
		
		ajustarConstraints(0, 4, 2, 1);
		JButton botonGuardarCarrera = new JButton("Guardar");
		add(botonGuardarCarrera, constraints);
		botonGuardarCarrera.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarCamposError();
				controlador.registrarCarrera(textFieldDistancia.getText(), textFieldRitmoPromedio.getText());;
			}
		});
	}
	
	private JLabel getLabelError() {
		JLabel labelError = new JLabel(" ");
		labelError.setForeground(Color.RED);
		return labelError;
	}
	
	private void ajustarConstraints(int x , int y, int w, int h) {
		this.constraints.gridx = x;
		this.constraints.gridy = y;
		this.constraints.gridwidth = w;
		this.constraints.gridheight = h;
	}
	
	public void limpiarCamposError() {
		limpiarCampoErrorDistancia();
		limpiarCampoErrorRitmoPromedio();
	}
	
	public void limpiarCamposTexto() {
		this.textFieldDistancia.setText("");
		this.textFieldRitmoPromedio.setText("");
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
}

class PanelFormularioCiclismo extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldDistancia;
	
	private JLabel labelErrorDistancia;
	
	private JTextField textFieldTipoBicicleta;
	
	private JLabel labelErrorTipoBicicleta;
	
	private GridBagConstraints constraints;
	
	public PanelFormularioCiclismo(final ControladorActividad controlador) {
		setLayout(new GridBagLayout());
		
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		
		ajustarConstraints(0, 0, 1, 1);
		add(new JLabel("Distancia (km):"), constraints);
		
		ajustarConstraints(1, 0, 1, 1);
		this.textFieldDistancia = new JTextField(15);
		add(this.textFieldDistancia, constraints);
		
		ajustarConstraints(0, 1, 2, 1);
		//horizontal
		this.labelErrorDistancia = getLabelError();
		add(this.labelErrorDistancia, constraints);
		
		ajustarConstraints(0, 2, 1, 1);
		add(new JLabel("Tipo bicicleta: "), constraints);
		
		ajustarConstraints(1, 2, 1, 1);
		this.textFieldTipoBicicleta = new JTextField(15);
		add(this.textFieldTipoBicicleta, constraints);
		
		ajustarConstraints(0, 3, 2, 1);
		this.labelErrorTipoBicicleta = getLabelError();
		add(this.labelErrorTipoBicicleta, constraints);
		
		ajustarConstraints(0, 4, 2, 1);
		JButton botonGuardarCiclismo = new JButton("Guardar");
		add(botonGuardarCiclismo, constraints);
		botonGuardarCiclismo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarCamposError();
				controlador.registrarCiclismo(textFieldDistancia.getText(), textFieldTipoBicicleta.getText());
			}
		});
	}

	private JLabel getLabelError() {
		JLabel labelError = new JLabel(" ");
		labelError.setForeground(Color.RED);
		return labelError;
	}
	
	private void ajustarConstraints(int x , int y, int w, int h) {
		this.constraints.gridx = x;
		this.constraints.gridy = y;
		this.constraints.gridwidth = w;
		this.constraints.gridheight = h;
	}
	
	public void limpiarCamposError() {
		limpiarCampoErrorDistancia();
		limpiarCampoErrorTipoBicicleta();
	}

	public void limpiarCamposTexto() {
		this.textFieldDistancia.setText("");
		this.textFieldTipoBicicleta.setText("");
	}
	
	public void mostrarErrorCampoDistancia(String mensajeError) {
		this.labelErrorDistancia.setText(mensajeError);
		this.textFieldDistancia.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	private void limpiarCampoErrorDistancia() {
		this.labelErrorDistancia.setText(" ");
		this.textFieldDistancia.setBorder(UIManager.getBorder("TextField.border"));
	}
	
	public void mostrarErrorCampoTipoBicicleta(String mensajeError) {
		this.labelErrorTipoBicicleta.setText(mensajeError);
		this.textFieldTipoBicicleta.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	private void limpiarCampoErrorTipoBicicleta() {
		this.labelErrorTipoBicicleta.setText(" ");
		this.textFieldTipoBicicleta.setBorder(UIManager.getBorder("TextField.border"));
	}
}

