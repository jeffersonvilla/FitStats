package com.fit.actividad.vista.panel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class PanelFormulario extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected GridBagConstraints constraints;
	
	protected final String textField_border_key = "TextField.border";
	
	public PanelFormulario() {
		setLayout(new GridBagLayout());
		this.constraints = new GridBagConstraints();
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
	}
	
	protected JLabel getLabelError() {
		JLabel labelError = new JLabel(" ");
		labelError.setForeground(Color.RED);
		return labelError;
	}
	
	protected void ajustarConstraints(int x , int y, int w, int h) {
		this.constraints.gridx = x;
		this.constraints.gridy = y;
		this.constraints.gridwidth = w;
		this.constraints.gridheight = h;
	}
	
	public abstract void limpiarCamposTexto();
	
	public abstract void limpiarCamposError();
}
