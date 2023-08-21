package com.fit.actividad.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.ModeloActividad;
import com.fit.actividad.modelo.TipoActividad;
import com.fit.actividad.vista.Cerrable;
import com.fit.actividad.vista.ModeloActividadObserver;
import com.fit.actividad.vista.AbstracFactory.CrudMvcFactory;
import com.fit.actividad.vista.caminata.CaminataFactory;
import com.fit.actividad.vista.caminata.VistaCrearCaminata;
import com.fit.util.OpcionesTipoActividad;

public class ControladorActividad implements ActionListener{
	
	private Cerrable ventanaParaCerrar;

	protected ModeloActividad modelo;

	public ControladorActividad(ModeloActividad modelo) {
		this.modelo = modelo;
	}
	
	public List<Actividad> leerListaActividades() {
		return modelo.obtenerActividadesDelUsuario(27);//TODO: cambiar
	}
	
	public void eliminarActividad(int idActividad) {
		modelo.eliminarActividad(idActividad);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		CrudMvcFactory factory = new CaminataFactory(modelo);
		
		String[] opcionesTipoActividad = OpcionesTipoActividad.getOpciones();
		
		Object source = e.getSource();
		if(source instanceof JComboBox<?>) {
			
			System.out.println(e.getActionCommand());
			
			JComboBox<?> combo = (JComboBox<?>) source;
			String opcionSeleccionada = opcionesTipoActividad[combo.getSelectedIndex()];
			
			System.out.println(opcionSeleccionada);
			
			if(opcionSeleccionada.equals(TipoActividad.CAMINATA.getNombre())) {
				System.out.println("Seleccionado " +  opcionSeleccionada);
				VistaCrearCaminata vista = (VistaCrearCaminata) factory.getFormularioCrear();
				vista.setVisible(true);
				ventanaParaCerrar.cerrar();
			}else if(opcionSeleccionada.equals(TipoActividad.CICLISMO.getNombre())) {
				System.out.println("Seleccionado " +  opcionSeleccionada);
			}
		}
	}
	
	public void setCerrable(Cerrable cerrable) {
		ventanaParaCerrar = cerrable;
	}
	
	public void agregarObservadorAlModeloActividad(ModeloActividadObserver observador) {
		modelo.agregarObservador(observador);
	}
	
	public void eliminarObservadorDelModeloActividad(ModeloActividadObserver observador) {
		modelo.eliminarObservador(observador);
	}
}