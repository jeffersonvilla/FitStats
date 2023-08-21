package com.fit.actividad.vista.caminata;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.sql.Timestamp;

import javax.swing.JButton;

import com.fit.actividad.controlador.ControladorCaminata;
import com.fit.actividad.vista.AbstracFactory.VistaFormularioCrear;
import com.fit.actividad.vista.panelFormulario.FormularioCaminata;

public class VistaCrearCaminata extends VentanaFormularioCaminata implements VistaFormularioCrear, ActionListener{

	private static final long serialVersionUID = 1L;
	
	private static final String BOTON_REGISTRAR = "Registrar";

	public VistaCrearCaminata(ControladorCaminata controlador, FormularioCaminata formulario) {
		super(controlador, formulario);
		
		init();
	}
	
	private void init() {
		JButton botonRegistrar = new JButton(BOTON_REGISTRAR);
		botonRegistrar.addActionListener(this);
		add(botonRegistrar, BorderLayout.SOUTH);
		
		pack();
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		formulario.limpiarCamposError();
		
		Object source = e.getSource();
		if(source instanceof JButton) {
			JButton boton = (JButton) source;
			switch(boton.getText()) {
				case BOTON_REGISTRAR -> {
					Timestamp fecha = formulario.getFecha();
					Time duracion = formulario.getDuracion();
					String ubicacion = formulario.getUbicacion();
					String distancia = formulario.getDistancia();
					
					boolean fechaValida = validarFecha(fecha);
					boolean distanciaValida = validarDistancia(distancia);
					
					if(fechaValida && distanciaValida) {					
						System.out.println(
								"Registrando caminata" + "\n" +
										"Fecha: " + fecha + "\n" +
										"Duracion: " + duracion + "\n" + 
										"Ubicacion: " + ubicacion + "\n" +
										"Distancia: " + distancia
								);
						this.controlador.registrar(fecha, duracion, ubicacion, Float.parseFloat(distancia));
						this.dispose();
					}
				}
				default -> {System.out.println("Sin funcionalidad para el boton elegido");}
			}
		}
	}
}
