package com.fit.vista;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.fit.controlador.ControladorUsuario;

public class VentanaRegistroUsuario extends JFrame implements VistaUsuario{

	private ControladorUsuario controlador;
	
	private PanelFormularioRegistroUsuario panelRegistroUsuario;
	
	public VentanaRegistroUsuario(ControladorUsuario controlador) {
		
		this.controlador = controlador;
		
		setSize(Pantalla.ancho/2, Pantalla.alto/2);
		setLocation(Pantalla.ancho/4, Pantalla.alto/4);
		
		this.panelRegistroUsuario = new PanelFormularioRegistroUsuario(this.controlador); 
		getContentPane().add(this.panelRegistroUsuario, BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void validarNombre(String mensajeNombreNoValido) {
		this.panelRegistroUsuario.limpiarPassField();
		this.panelRegistroUsuario.mostrarErrorCampoNombre(mensajeNombreNoValido);
	}

	public void validarEmail(String mensajeEmailNoValido) {
		this.panelRegistroUsuario.limpiarPassField();
		this.panelRegistroUsuario.mostrarErrorCampoEmail(mensajeEmailNoValido);
	}

	public void validarPassword(String mensajePasswordNoValido) {
		this.panelRegistroUsuario.limpiarPassField();
		this.panelRegistroUsuario.mostrarErrorCampoPassword(mensajePasswordNoValido);
	}

	public void usuarioRegistradoCorrectamente(String mensajeUsuarioRegistradoCorrectamente) {
		this.panelRegistroUsuario.limpiarTodosLosTextAndPassFields();
		JOptionPane.showMessageDialog(this, mensajeUsuarioRegistradoCorrectamente, "Registro existo", JOptionPane.INFORMATION_MESSAGE);
	}

	public void problemasRegistrandoUsuario(String mensajeProblemaRegistrandoUsuario) {
		this.panelRegistroUsuario.limpiarTodosLosTextAndPassFields();
		JOptionPane.showMessageDialog(this, mensajeProblemaRegistrandoUsuario, "Error de registro", JOptionPane.ERROR_MESSAGE);
	}
}

class PanelFormularioRegistroUsuario extends JPanel{
	
	private JTextField textFieldNombre ;
	private JTextField textFieldEmail;
	private JPasswordField passFieldPassword;
	
	private JLabel labelNombre;
	private JLabel labelEmail;
	private JLabel labelPassword;

	private JLabel labelErrorNombre;
	private JLabel labelErrorEmail;
	private JLabel labelErrorPassword;
	
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	
	public PanelFormularioRegistroUsuario(final ControladorUsuario controlador) {
		
		this.layout = new GridBagLayout();
		setLayout(this.layout);
		
		this.constraints = new GridBagConstraints();
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		
		agregarCamposNombre();
		agregarCamposEmail();
		agregarCamposPassword();
		
		
		ajustarConstraints(0, 6, 2, 1, GridBagConstraints.CENTER);
		JButton botonRegitrarUsuario = new JButton("Registrar");
		
		botonRegitrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarMesajesError();
				controlador.registrarUsuario(textFieldNombre.getText(), textFieldEmail.getText(), passFieldPassword.getPassword());	
			}
		});
		
		add(botonRegitrarUsuario, this.constraints);
	}
	
	private void agregarCamposNombre() {
		ajustarConstraints(0, 0, 1, 1, GridBagConstraints.EAST);
		this.labelNombre = new JLabel("Nombre:",  JLabel.RIGHT);
		add(this.labelNombre, this.constraints);
		
		ajustarConstraints(1, 0, 1, 1, GridBagConstraints.WEST);
		this.textFieldNombre = new JTextField(10);
		this.labelNombre.setLabelFor(this.textFieldNombre);
		add(this.textFieldNombre, this.constraints);
		
		ajustarConstraints(0, 1, 2, 1, GridBagConstraints.WEST);
		this.labelErrorNombre = new JLabel(" ");
		this.labelErrorNombre.setForeground(Color.RED);
		add(this.labelErrorNombre, this.constraints);
		
	}
	
	private void agregarCamposEmail() {
		ajustarConstraints(0, 2, 1, 1, GridBagConstraints.EAST);
		this.labelEmail = new JLabel("Email:",  JLabel.RIGHT);
		add(this.labelEmail, this.constraints);
		
		ajustarConstraints(1, 2, 1, 1, GridBagConstraints.WEST);
		this.textFieldEmail = new JTextField(10);
		this.labelEmail.setLabelFor(this.textFieldEmail);
		add(this.textFieldEmail, this.constraints);
		
		ajustarConstraints(0, 3, 2, 1, GridBagConstraints.WEST);
		this.constraints.anchor = GridBagConstraints.WEST;
		this.labelErrorEmail = new JLabel(" ");
		this.labelErrorEmail.setForeground(Color.RED);
		this.labelErrorEmail.setHorizontalTextPosition(SwingConstants.LEFT);
		add(this.labelErrorEmail, this.constraints);
	}

	private void agregarCamposPassword() {
		ajustarConstraints(0, 4, 1, 1, GridBagConstraints.EAST);
		this.labelPassword = new JLabel("Contraseña:", JLabel.RIGHT);
		add(this.labelPassword, this.constraints);
		
		ajustarConstraints(1, 4, 1, 1, GridBagConstraints.WEST);
		this.passFieldPassword = new JPasswordField(10);
		this.labelPassword.setLabelFor(this.passFieldPassword);
		add(this.passFieldPassword, this.constraints);
		
		ajustarConstraints(0, 5, 2, 1, GridBagConstraints.WEST);
		this.labelErrorPassword = new JLabel(" ");
		this.labelErrorPassword.setForeground(Color.RED);
		add(this.labelErrorPassword, this.constraints);
	}
	
	private void ajustarConstraints(int x , int y, int w, int h, int a) {
		this.constraints.gridx = x;
		this.constraints.gridy = y;
		this.constraints.gridwidth = w;
		this.constraints.gridheight = h;
		if(a != -1) this.constraints.anchor = a;
	}
	
	public void limpiarTodosLosTextAndPassFields() {
		this.textFieldNombre.setText("");
		this.textFieldEmail.setText("");
		this.passFieldPassword.setText("");
	}
	
	public void limpiarPassField() {
		this.passFieldPassword.setText("");
	}
	
	public void mostrarErrorCampoNombre(String mensajeError) {
		this.labelErrorNombre.setText(mensajeError);
		this.textFieldNombre.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	public void mostrarErrorCampoEmail(String mensajeError) {
		this.labelErrorEmail.setText(mensajeError);
		this.textFieldEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
	}	
	
	public void mostrarErrorCampoPassword(String mensajeError) {
		this.labelErrorPassword.setText(mensajeError);
		this.passFieldPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	private void limpiarMesajesError() {
		this.labelErrorNombre.setText(" ");
		this.textFieldNombre.setBorder(UIManager.getBorder("TextField.border"));
		this.labelErrorEmail.setText(" ");
		this.textFieldEmail.setBorder(UIManager.getBorder("TextField.border"));
		this.labelErrorPassword.setText(" ");
		this.passFieldPassword.setBorder(UIManager.getBorder("PasswordField.border"));
	}
	
}
