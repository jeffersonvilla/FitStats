package com.fit.actividad.vista.actividades;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.fit.actividad.AbstractFactory.CrudMvcFactory;
import com.fit.actividad.controlador.ControladorActividad;
import com.fit.actividad.modelo.Caminata;
import com.fit.actividad.modelo.TipoActividad;
import com.fit.actividad.vista.caminata.CaminataFactory;
import com.fit.actividad.vista.caminata.VistaActualizarCaminata;
import com.fit.actividad.vista.caminata.VistaCrearCaminata;
import com.fit.actividad.vista.panelFormulario.FormularioCaminata;
import com.fit.util.OpcionesTipoActividad;
import com.fit.util.Pantalla;

public class SeleccionTipoActividadCrear extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private ControladorActividad controlador;
	
	public SeleccionTipoActividadCrear(ControladorActividad controlador) {
		
		this.controlador = controlador;
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(Pantalla.ancho / 3, Pantalla.alto / 3);
		
		inicializarPanelContenedor();
		
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	private void inicializarPanelContenedor() {
		JPanel panelContenedor = new JPanel();
		
		panelContenedor.add(new JLabel("Tipo actividad"));

		JComboBox<String> listaOpcionesTipoActividad = new JComboBox<String>(OpcionesTipoActividad.getOpciones());
		listaOpcionesTipoActividad.addActionListener(this);
		panelContenedor.add(listaOpcionesTipoActividad);
		
		add(panelContenedor, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String[] opcionesTipoActividad = OpcionesTipoActividad.getOpciones();
		
		Object source = e.getSource();
		if(source instanceof JComboBox<?>) {
			
			System.out.println(e.getActionCommand());
			
			JComboBox<?> combo = (JComboBox<?>) source;
			String opcionSeleccionada = opcionesTipoActividad[combo.getSelectedIndex()];
			
			if(opcionSeleccionada.equals(TipoActividad.CAMINATA.getNombre())) {
				CrudMvcFactory factory = new CaminataFactory(controlador, new FormularioCaminata());
				VistaCrearCaminata vista = (VistaCrearCaminata) factory.getVistaFormularioCrear();
				vista.setVisible(true);
			}else if(opcionSeleccionada.equals(TipoActividad.CICLISMO.getNombre())) {
				System.out.println("Seleccionado " +  opcionSeleccionada);
			}
			
			dispose();
		}
	}
	
}
