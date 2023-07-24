package com.fit.vista;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.fit.controlador.ControladorInicioSesion;

public class VentanaInicioSesion extends JFrame implements VistaInicioSesion {

	private ControladorInicioSesion controladorInicioSesion;

	private PanelFormularioInicioSesion panelFormularioInicioSesion;
	
	public VentanaInicioSesion(ControladorInicioSesion controlador) {
		this.controladorInicioSesion = controlador;
		
		setSize(Pantalla.ancho/2, Pantalla.alto/2);
		setLocation(Pantalla.ancho/4, Pantalla.alto/4);
		
		this.panelFormularioInicioSesion = new PanelFormularioInicioSesion(this.controladorInicioSesion);
		add(this.panelFormularioInicioSesion, BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void mostrarErrorValidarCredenciales() {
		JOptionPane.showMessageDialog(this, "Contraseña incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
		panelFormularioInicioSesion.limpiarPassField();
	}

	public void mostrarErrorEmailNoRegistrado() {
		JOptionPane.showMessageDialog(this, "Email no registrado.", "Error", JOptionPane.ERROR_MESSAGE);
		panelFormularioInicioSesion.limpiarFields();
	}

	public void validarFormatoEmail() {
		JOptionPane.showMessageDialog(this, "Verifique el formato del email.", "Error", JOptionPane.ERROR_MESSAGE);
		panelFormularioInicioSesion.limpiarPassField();	
	}

}

class PanelFormularioInicioSesion extends JPanel{
	
	
	private JTextField textFieldEmail;
	private JPasswordField passFieldPassword;
	
	private GridBagConstraints constraints;
	
	public PanelFormularioInicioSesion(final ControladorInicioSesion controlador) {
		super(new GridBagLayout());
		
		this.constraints = new GridBagConstraints();
		
		ajustarConstraints(0, 0, 2, 1, GridBagConstraints.CENTER);
		add(new JLabel("Inicio sesion"), this.constraints);
		
		ajustarConstraints(0, 1, 1, 1, GridBagConstraints.WEST);
		add(new JLabel("Email:"), this.constraints);
		
		ajustarConstraints(1, 1, 1, 1, GridBagConstraints.WEST);
		this.textFieldEmail = new JTextField(15);
		add(this.textFieldEmail, this.constraints);
		
		ajustarConstraints(0, 2, 1, 1, GridBagConstraints.WEST);
		add(new JLabel("Contraseña: "), this.constraints);
		
		ajustarConstraints(1, 2, 1, 1, GridBagConstraints.WEST);
		this.passFieldPassword = new JPasswordField(15);
		add(this.passFieldPassword, this.constraints);
		
		ajustarConstraints(0, 3, 2, 1, GridBagConstraints.CENTER);
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		JButton botonIniciarSesion = new JButton("Inicio");
		add(botonIniciarSesion, this.constraints);
		
		botonIniciarSesion.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
		
				controlador.iniciarSesion(textFieldEmail.getText(), passFieldPassword.getPassword());
			}
		});
		
		ajustarConstraints(0, 4, 2, 1, GridBagConstraints.CENTER);
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		JButton botonCancelar = new JButton("Cancelar");
		add(botonCancelar, this.constraints);
		
		botonCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
		
				controlador.cancelarInicioSesion();
			}
		});
		
		
	}
	
	private void ajustarConstraints(int x , int y, int w, int h, int a) {
		this.constraints.gridx = x;
		this.constraints.gridy = y;
		this.constraints.gridwidth = w;
		this.constraints.gridheight = h;
		if(a != -1) this.constraints.anchor = a;
	}
	
	public void limpiarFields() {
		this.passFieldPassword.setText("");
		this.textFieldEmail.setText("");
	}
	
	public void limpiarPassField() {
		this.passFieldPassword.setText("");
	}
	
}
