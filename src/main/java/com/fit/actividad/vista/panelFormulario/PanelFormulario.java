package com.fit.actividad.vista.panelFormulario;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public abstract class PanelFormulario extends JPanel {

	private static final long serialVersionUID = 1L;

	protected final String textField_border_key = "TextField.border";

	protected PanelFormulario() {
		setLayout(new MigLayout());
	}

	protected JLabel getLabelError() {
		JLabel labelError = new JLabel(" ");
		labelError.setForeground(Color.RED);
		return labelError;
	}

	public abstract void limpiarCamposTexto();

	public abstract void limpiarCamposError();
}
