package com.fit.actividad.vista.actividades;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fit.actividad.ControladorActividad;
import com.fit.actividad.modelo.TipoActividad;
import com.fit.actividad.vista.panelFormulario.FormularioCaminata;
import com.fit.actividad.vista.panelFormulario.FormularioCiclismo;
import com.fit.actividad.vista.panelFormulario.FormularioDeporteEquipo;
import com.fit.actividad.vista.panelFormulario.FormularioEntrenamientoGimnasio;
import com.fit.actividad.vista.panelFormulario.FormularioEstiramientos;
import com.fit.actividad.vista.panelFormulario.FormularioNatacion;
import com.fit.actividad.vista.panelFormulario.FormularioOtraActividad;
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
				new CreacionActividad(controlador, new FormularioCaminata());
			}else if(opcionSeleccionada.equals(TipoActividad.CICLISMO.getNombre())) {
				new CreacionActividad(controlador, new FormularioCiclismo());
			}else if(opcionSeleccionada.equals(TipoActividad.NATACION.getNombre())) {
				new CreacionActividad(controlador, new FormularioNatacion());
			}else if(opcionSeleccionada.equals(TipoActividad.DEPORTE_EQUIPO.getNombre())) {
				new CreacionActividad(controlador, new FormularioDeporteEquipo());
			}else if(opcionSeleccionada.equals(TipoActividad.ENTRENAMIENTO_GIMNASIO.getNombre())) {
				new CreacionActividad(controlador, new FormularioEntrenamientoGimnasio());
			}else if(opcionSeleccionada.equals(TipoActividad.ESTIRAMIENTOS.getNombre())) {
				new CreacionActividad(controlador, new FormularioEstiramientos());
			}else if(opcionSeleccionada.equals(TipoActividad.OTRA_ACTIVIDAD.getNombre())) {
				new CreacionActividad(controlador, new FormularioOtraActividad());
			}
			
			dispose();
		}
	}
	
}
