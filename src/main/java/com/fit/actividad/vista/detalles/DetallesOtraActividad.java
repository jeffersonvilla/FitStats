package com.fit.actividad.vista.detalles;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.fit.actividad.modelo.OtraActividad;

public class DetallesOtraActividad extends PanelDetalles {

	private static final long serialVersionUID = 1L;

	public DetallesOtraActividad(OtraActividad otraActividad) {
		super(otraActividad);
		
		add(new JLabel("Descripcion:"));
		JTextArea textArea = new JTextArea(otraActividad.getDescripcion());
		textArea.setEditable(false);
		add(textArea, "wrap");		
	}

}
