package com.fit.actividad.controlador;

import java.util.List;

import com.fit.actividad.AbstractFactory.Controlador;
import com.fit.actividad.AbstractFactory.CrudMvcFactory;
import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.Caminata;
import com.fit.actividad.modelo.ModeloActividad;
import com.fit.actividad.modelo.TipoActividad;
import com.fit.actividad.vista.ModeloActividadObserver;
import com.fit.actividad.vista.actividades.VistaDetalles;
import com.fit.actividad.vista.caminata.CaminataFactory;
import com.fit.actividad.vista.caminata.DetallesCaminata;
import com.fit.actividad.vista.caminata.VistaActualizarCaminata;
import com.fit.actividad.vista.panelFormulario.FormularioCaminata;

public class ControladorActividad implements Controlador{
	
	private CrudMvcFactory factory;

	protected ModeloActividad modelo;

	public ControladorActividad(ModeloActividad modelo) {
		this.modelo = modelo;
	}
	
	@Override
	public void registrarActividad(Actividad actividad) {
		modelo.guardarActividad(actividad);
	}

	@Override
	public List<Actividad> leerListaActividades() {
		return modelo.obtenerActividadesDelUsuario(27);//TODO: cambiar
	}
	
	@Override
	public void actualizarActividad(Actividad actividad) {
		modelo.actualizarActividad(actividad);
	}
	
	@Override
	public void eliminarActividad(int index) {
		modelo.eliminarActividad(modelo.obtenerActividadPorIndexEnLista(index).getId());
	}
	
	public void abrirVentanaDetalles(int index) {
		Actividad actividad = modelo.obtenerActividadPorIndexEnLista(index);
		if(actividad.getTipoActividad() == TipoActividad.CAMINATA.getValor()) {
			new VistaDetalles(new DetallesCaminata((Caminata) actividad));
		}
	}
	
	public void abrirVentanaActualizarActividad(int index) {
		Actividad actividad = modelo.obtenerActividadPorIndexEnLista(index);
		if(actividad.getTipoActividad() == TipoActividad.CAMINATA.getValor()) {
			factory = new CaminataFactory(this, new FormularioCaminata((Caminata) actividad));
			VistaActualizarCaminata vista = (VistaActualizarCaminata) factory.getVistaFormularioActualizar();
			vista.setVisible(true);
		}
	}
	
	public void agregarObservadorAlModeloActividad(ModeloActividadObserver observador) {
		modelo.agregarObservador(observador);
	}
	
	public void eliminarObservadorDelModeloActividad(ModeloActividadObserver observador) {
		modelo.eliminarObservador(observador);
	}
}
