package com.fit.actividad.vista.detalles;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.fit.actividad.modelo.EntrenamientoGimnasio;

public class DetallesEntrenamientoGimnasio extends PanelDetalles {

	private static final long serialVersionUID = 1L;

	public DetallesEntrenamientoGimnasio(EntrenamientoGimnasio entrenamiento) {
		super(entrenamiento);

		add(new JLabel("Ejercicios realizados:"));
		JTextArea textArea = new JTextArea(entrenamiento.getEjerciciosRealizados());
		textArea.setEditable(false);
		add(textArea, "wrap");
		
		add(new JLabel("Descanso entre ejercicios:"));
		add(new JLabel(entrenamiento.getDescansoEntreEjercicios()), "wrap");
		
		add(new JLabel("Descanso entre series:"));
		add(new JLabel(entrenamiento.getDescansoEntreSeries()), "wrap");
	}

}
