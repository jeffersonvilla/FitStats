package com.fit.actividad.vista.caminata;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.sql.Timestamp;

import javax.swing.JButton;

import com.fit.actividad.controlador.ControladorCaminata;
import com.fit.actividad.vista.AbstracFactory.VistaFormularioActualizar;
import com.fit.actividad.vista.panelFormulario.FormularioCaminata;

public class VistaActualizarCaminata extends VentanaFormularioCaminata implements VistaFormularioActualizar, ActionListener{

	private static final long serialVersionUID = 1L;
	
	private static final String BOTON_ACTUALIZAR = "Actualizar";
	
	public VistaActualizarCaminata(ControladorCaminata controlador, FormularioCaminata formulario) {
		super(controlador, formulario);
		init();
	}
	
	private void init() {
		JButton botonActualizar = new JButton(BOTON_ACTUALIZAR);
		botonActualizar.addActionListener(this);
		add(botonActualizar, BorderLayout.SOUTH);
		
		pack();
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		formulario.limpiarCamposError();
		
		Object source = e.getSource();
		if(source instanceof JButton) {
			JButton boton = (JButton) source;
			switch(boton.getText()) {
				case BOTON_ACTUALIZAR -> {
					Timestamp fecha = super.formulario.getFecha();
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
						this.controlador.actualizar(fecha, duracion, ubicacion, Float.parseFloat(distancia));
					}
				}
				default -> {System.out.println("Sin funcionalidad para el boton elegido");}
			}
		}
	}
}