package com.fit.actividad.vista;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fit.actividad.controlador.ControladorActividad;
import com.fit.util.OpcionesTipoActividad;
import com.fit.util.Pantalla;

public class SeleccionTipoActividadCrear extends JFrame implements Cerrable{

	private static final long serialVersionUID = 1L;
	
	private ControladorActividad controlador;
	
	public SeleccionTipoActividadCrear(ControladorActividad controlador) {
		
		this.controlador = controlador;
		
		controlador.addCerrable(this);
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(Pantalla.ancho / 3, Pantalla.alto / 3);
		
		inicializarPanelContenedor();
		
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	private void inicializarPanelContenedor() {
		JPanel panelContenedor = new JPanel();
		
		panelContenedor.add(new JLabel("Tipo actividad"));

		JComboBox<String> listaOpcionesTipoActividad = new JComboBox<String>(OpcionesTipoActividad.getOpciones());
		listaOpcionesTipoActividad.addActionListener(controlador);
		panelContenedor.add(listaOpcionesTipoActividad);
		
		add(panelContenedor, BorderLayout.CENTER);
	}

	@Override
	public void cerrar() {
		dispose();
	}
	
}
