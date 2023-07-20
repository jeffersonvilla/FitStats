package com.fit.vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.fit.controlador.ControladorUsuario;
import com.fit.util.FormatoNoValido;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class VentanaRegistroUsuario extends JFrame{

	private ControladorUsuario controlador;
	
	public VentanaRegistroUsuario(ControladorUsuario controlador) {
		
		this.controlador = controlador;
		
		setSize(Pantalla.ancho/2, Pantalla.alto/2);
		setLocation(Pantalla.ancho/4, Pantalla.alto/4);
		add(new PanelFormularioRegistroUsuario(this.controlador), BorderLayout.CENTER);
		setVisible(true);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

class PanelFormularioRegistroUsuario extends JPanel{
	
	private JTextField textFieldNombre;
	private JTextField textFieldEmail;
	private JPasswordField passFieldPassword;
	
	private ControladorUsuario controlador;
	
	public PanelFormularioRegistroUsuario(final ControladorUsuario controlador) {
		super(new BorderLayout());
		
		this.controlador = controlador;
		
		Box labels = Box.createVerticalBox();
		labels.add(new JLabel("nombre:"));
		labels.add(new JLabel("correo electrónico:"));
		labels.add(new JLabel("contraseña:"));
		
		Box textFields = Box.createVerticalBox();
		this.textFieldNombre = new JTextField(15);
		textFields.add(this.textFieldNombre);
		this.textFieldEmail = new JTextField(15);
		textFields.add(this.textFieldEmail);
		this.passFieldPassword = new JPasswordField();
		textFields.add(this.passFieldPassword);
		
		Box formulario = Box.createHorizontalBox();
		formulario.add(labels);
		formulario.add(textFields);
		
		add(formulario, BorderLayout.CENTER);
		
		JButton botonRegitrarUsuario = new JButton("Registrar");
		botonRegitrarUsuario.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String nombre = textFieldNombre.getText();
				String email = textFieldEmail.getText();
				
				try {
					controlador.registrarUsuario(nombre, email, passFieldPassword.getPassword());
					
					textFieldNombre.setText("");
					textFieldEmail.setText("");
					passFieldPassword.setText("");
					
				}catch(FormatoNoValido fenv) {
					System.out.println(fenv.getMessage());
					
					passFieldPassword.setText("");
				}
				
				
			}
		});
		
		add(botonRegitrarUsuario, BorderLayout.SOUTH);
	}
}
