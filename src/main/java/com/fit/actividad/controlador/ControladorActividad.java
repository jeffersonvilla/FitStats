package com.fit.actividad.controlador;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.Caminata;
import com.fit.actividad.modelo.Ciclismo;
import com.fit.actividad.modelo.ModeloActividad;
import com.fit.actividad.modelo.Natacion;
import com.fit.actividad.modelo.TipoActividad;
import com.fit.actividad.vista.ModeloActividadObserver;
import com.fit.actividad.vista.actividades.ActualizacionActividad;
import com.fit.actividad.vista.detalles.DetallesCaminata;
import com.fit.actividad.vista.detalles.DetallesCiclismo;
import com.fit.actividad.vista.detalles.DetallesNatacion;
import com.fit.actividad.vista.detalles.DetallesActividad;
import com.fit.actividad.vista.panelFormulario.FormularioCaminata;
import com.fit.actividad.vista.panelFormulario.FormularioCiclismo;
import com.fit.actividad.vista.panelFormulario.FormularioNatacion;

public class ControladorActividad {
	
	private static final Logger logger = LoggerFactory.getLogger(ControladorActividad.class);

	protected ModeloActividad modelo;

	public ControladorActividad(ModeloActividad modelo) {
		this.modelo = modelo;
	}
	
	public void registrarActividad(Actividad actividad) {
		logger.info("guardando {}", actividad);
		modelo.guardarActividad(actividad);
	}

	public List<Actividad> leerListaActividades() {
		return modelo.obtenerActividadesDelUsuario(27);//TODO: cambiar
	}
	
	public void actualizarActividad(Actividad actividad) {
		modelo.actualizarActividad(actividad);
	}
	
	public void eliminarActividad(int index) {
		modelo.eliminarActividad(modelo.obtenerActividadPorIndexEnLista(index).getId());
	}
	
	public void abrirVentanaDetalles(int index) {
		Actividad actividad = modelo.obtenerActividadPorIndexEnLista(index);
		if(actividad.getTipoActividad() == TipoActividad.CAMINATA.getValor()) {
			new DetallesActividad(new DetallesCaminata((Caminata) actividad));
		}else if(actividad.getTipoActividad() == TipoActividad.CICLISMO.getValor()) {
			new DetallesActividad(new DetallesCiclismo((Ciclismo) actividad));
		}else if(actividad.getTipoActividad() == TipoActividad.NATACION.getValor()) {
			new DetallesActividad(new DetallesNatacion((Natacion) actividad));
		}
	}
	
	public void abrirVentanaActualizarActividad(int index) {
		Actividad actividad = modelo.obtenerActividadPorIndexEnLista(index);
		if(actividad.getTipoActividad() == TipoActividad.CAMINATA.getValor()) {
			new ActualizacionActividad(this, new FormularioCaminata((Caminata) actividad));
		}else if(actividad.getTipoActividad() == TipoActividad.CICLISMO.getValor()) {
			new ActualizacionActividad(this, new FormularioCiclismo((Ciclismo) actividad));
		}else if(actividad.getTipoActividad() == TipoActividad.NATACION.getValor()) {
			new ActualizacionActividad(this, new FormularioNatacion((Natacion) actividad));
		}
	}
	
	public void agregarObservadorAlModeloActividad(ModeloActividadObserver observador) {
		modelo.agregarObservador(observador);
	}
	
	public void eliminarObservadorDelModeloActividad(ModeloActividadObserver observador) {
		modelo.eliminarObservador(observador);
	}
}
