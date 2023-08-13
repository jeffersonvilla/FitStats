package com.fit.usuario.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.fit.util.Pantalla;

public class VentanaInicioSesion extends JFrame implements VistaInicioSesion {

	private static final long serialVersionUID = 1L;

	private ControladorInicioSesion controladorInicioSesion;

	private PanelFormularioInicioSesion panelFormularioInicioSesion;

	public VentanaInicioSesion(ControladorInicioSesion controlador) {
		this.controladorInicioSesion = controlador;

		setSize(Pantalla.ancho / 2, Pantalla.alto / 2);
		setLocation(Pantalla.ancho / 4, Pantalla.alto / 4);

		this.panelFormularioInicioSesion = new PanelFormularioInicioSesion(this.controladorInicioSesion);
		add(this.panelFormularioInicioSesion, BorderLayout.CENTER);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void validarEmail(String mensajeValidacionEmail) {
		panelFormularioInicioSesion.limpiarPassField();
		panelFormularioInicioSesion.mostrarErrorCampoEmail(mensajeValidacionEmail);
	}

	@Override
	public void validarPassword(String mensajeValidacionPassword) {
		panelFormularioInicioSesion.mostrarErrorCampoPassword(mensajeValidacionPassword);
	}
}

class PanelFormularioInicioSesion extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldEmail;
	private JPasswordField passFieldPassword;

	private JLabel labelErrorEmail;
	private JLabel labelErrorPassword;

	private GridBagConstraints constraints;

	public PanelFormularioInicioSesion(final ControladorInicioSesion controlador) {
		super(new GridBagLayout());

		this.constraints = new GridBagConstraints();
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		this.constraints.anchor = GridBagConstraints.CENTER;

		ajustarConstraints(0, 0, 2, 1);
		add(new JLabel("Inicio sesion", JLabel.CENTER), this.constraints);

		ajustarConstraints(0, 1, 2, 1);
		add(new JLabel(" "), this.constraints);

		ajustarConstraints(0, 2, 1, 1);
		this.constraints.anchor = GridBagConstraints.WEST;
		add(new JLabel("Email:"), this.constraints);

		ajustarConstraints(1, 2, 1, 1);
		this.textFieldEmail = new JTextField(15);
		add(this.textFieldEmail, this.constraints);

		ajustarConstraints(0, 3, 2, 1);
		this.labelErrorEmail = new JLabel(" ");
		this.labelErrorEmail.setForeground(Color.RED);
		add(this.labelErrorEmail, this.constraints);

		ajustarConstraints(0, 4, 1, 1);
		add(new JLabel("Contraseña: "), this.constraints);

		ajustarConstraints(1, 4, 1, 1);
		this.passFieldPassword = new JPasswordField(15);
		add(this.passFieldPassword, this.constraints);

		ajustarConstraints(0, 5, 2, 1);
		this.labelErrorPassword = new JLabel(" ");
		this.labelErrorPassword.setForeground(Color.RED);
		add(this.labelErrorPassword, this.constraints);

		ajustarConstraints(0, 6, 2, 1);
		this.constraints.anchor = GridBagConstraints.CENTER;
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		JButton botonIniciarSesion = new JButton("Inicio");
		add(botonIniciarSesion, this.constraints);

		botonIniciarSesion.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				limpiarMensajesError();
				controlador.iniciarSesion(textFieldEmail.getText(), passFieldPassword.getPassword());
			}
		});

		ajustarConstraints(0, 7, 2, 1);
		JButton botonCancelar = new JButton("Cancelar");
		add(botonCancelar, this.constraints);

		botonCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				limpiarMensajesError();
				limpiarTodosLosTextAndFields();
				controlador.cancelarInicioSesion();
			}
		});
	}

	private void ajustarConstraints(int x, int y, int w, int h) {
		this.constraints.gridx = x;
		this.constraints.gridy = y;
		this.constraints.gridwidth = w;
		this.constraints.gridheight = h;
	}

	public void limpiarTodosLosTextAndFields() {
		this.passFieldPassword.setText("");
		this.textFieldEmail.setText("");
	}

	public void limpiarPassField() {
		this.passFieldPassword.setText("");
	}

	public void mostrarErrorCampoEmail(String mensajeEmailNoValido) {
		this.labelErrorEmail.setText(mensajeEmailNoValido);
		this.textFieldEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	public void mostrarErrorCampoPassword(String mensajeCampoVacio) {
		this.labelErrorPassword.setText(mensajeCampoVacio);
		this.passFieldPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
	}

	public void limpiarMensajesError() {
		this.labelErrorEmail.setText(" ");
		this.textFieldEmail.setBorder(UIManager.getBorder("TextField.border"));
		this.labelErrorPassword.setText(" ");
		this.passFieldPassword.setBorder(UIManager.getBorder("PasswordField.border"));
	}
}
