package com.fit.actividad.vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.fit.actividad.controlador.ControladorCaminata;
import com.fit.actividad.vista.panelFormulario.FormularioActividad;
import com.fit.actividad.vista.panelFormulario.FormularioCaminata;
import com.fit.util.Pantalla;

public class VistaCrearCaminata extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private final ControladorCaminata controlador ;
	
	private FormularioCaminata formulario;
	
	private static final String BOTON_REGISTRAR = "Registrar";
	
	private static final String FORMATO_DISTANCIA = "^(?=\\d{1,10}(\\.\\d{0,5})?$)\\d+(\\.\\d+)?$";
	
	private static final String MENSAJE_VALIDACION_DISTANCIA = "Solo valores numéricos (máximo 8 digitos)";

	public VistaCrearCaminata(ControladorCaminata controlador, FormularioCaminata formulario) {
		this.controlador = controlador;
		this.formulario = formulario;
		
		init();
	}
	
	private void init() {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(Pantalla.ancho / 3, Pantalla.alto / 3);
		
		add(formulario, BorderLayout.CENTER);
	
		JButton botonRegistrar = new JButton(BOTON_REGISTRAR);
		botonRegistrar.addActionListener(this);
		add(botonRegistrar, BorderLayout.SOUTH);
		
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
						this.controlador.registrarCaminata(fecha, duracion, ubicacion, Float.parseFloat(distancia));
					}
					
				}
			default -> {System.out.println("Sin funcionalidad para el boton elegido");}
			}
		}
	}
	
	private boolean validarFecha(Timestamp fecha) {
		if (fecha != null) return true;
		formulario.mostrarErrorCampoFecha(FormularioActividad.MENSAJE_VALIDACION_CAMPO_VACIO);
		return false;
	}

	private boolean validarDistancia(String distancia) {
		if (!distancia.isEmpty() && Pattern.matches(FORMATO_DISTANCIA, distancia)) return true;
		formulario.mostrarErrorCampoDistancia(MENSAJE_VALIDACION_DISTANCIA);
		return false;
	}
	
}
