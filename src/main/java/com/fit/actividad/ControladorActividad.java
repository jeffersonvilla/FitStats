package com.fit.actividad;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.Caminata;
import com.fit.actividad.modelo.Ciclismo;
import com.fit.actividad.modelo.DeporteEquipo;
import com.fit.actividad.modelo.EntrenamientoGimnasio;
import com.fit.actividad.modelo.Estiramientos;
import com.fit.actividad.modelo.ModeloActividad;
import com.fit.actividad.modelo.Natacion;
import com.fit.actividad.modelo.OtraActividad;
import com.fit.actividad.modelo.TipoActividad;
import com.fit.actividad.vista.ModeloActividadObserver;
import com.fit.actividad.vista.actividades.ActualizacionActividad;
import com.fit.actividad.vista.detalles.DetallesCaminata;
import com.fit.actividad.vista.detalles.DetallesCiclismo;
import com.fit.actividad.vista.detalles.DetallesDeporteEquipo;
import com.fit.actividad.vista.detalles.DetallesEntrenamientoGimnasio;
import com.fit.actividad.vista.detalles.DetallesEstiramientos;
import com.fit.actividad.vista.detalles.DetallesNatacion;
import com.fit.actividad.vista.detalles.DetallesOtraActividad;
import com.fit.actividad.vista.detalles.DetallesActividad;
import com.fit.actividad.vista.panelFormulario.FormularioCaminata;
import com.fit.actividad.vista.panelFormulario.FormularioCiclismo;
import com.fit.actividad.vista.panelFormulario.FormularioDeporteEquipo;
import com.fit.actividad.vista.panelFormulario.FormularioEntrenamientoGimnasio;
import com.fit.actividad.vista.panelFormulario.FormularioEstiramientos;
import com.fit.actividad.vista.panelFormulario.FormularioNatacion;
import com.fit.actividad.vista.panelFormulario.FormularioOtraActividad;

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
		}else if(actividad.getTipoActividad() == TipoActividad.DEPORTE_EQUIPO.getValor()) {
			new DetallesActividad(new DetallesDeporteEquipo((DeporteEquipo) actividad));
		}else if(actividad.getTipoActividad() == TipoActividad.ENTRENAMIENTO_GIMNASIO.getValor()) {
			new DetallesActividad(new DetallesEntrenamientoGimnasio((EntrenamientoGimnasio) actividad));
		}else if(actividad.getTipoActividad() == TipoActividad.ESTIRAMIENTOS.getValor()) {
			new DetallesActividad(new DetallesEstiramientos((Estiramientos) actividad));
		}else if(actividad.getTipoActividad() == TipoActividad.OTRA_ACTIVIDAD.getValor()) {
			new DetallesActividad(new DetallesOtraActividad((OtraActividad) actividad));
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
		}else if(actividad.getTipoActividad() == TipoActividad.DEPORTE_EQUIPO.getValor()) {
			new ActualizacionActividad(this, new FormularioDeporteEquipo((DeporteEquipo) actividad));
		}else if(actividad.getTipoActividad() == TipoActividad.ENTRENAMIENTO_GIMNASIO.getValor()) {
			new ActualizacionActividad(this, new FormularioEntrenamientoGimnasio((EntrenamientoGimnasio) actividad));
		}else if(actividad.getTipoActividad() == TipoActividad.ESTIRAMIENTOS.getValor()) {
			new ActualizacionActividad(this, new FormularioEstiramientos((Estiramientos) actividad));
		}else if(actividad.getTipoActividad() == TipoActividad.OTRA_ACTIVIDAD.getValor()) {
			new ActualizacionActividad(this, new FormularioOtraActividad((OtraActividad) actividad));
		}
	}
	
	public void agregarObservadorAlModeloActividad(ModeloActividadObserver observador) {
		modelo.agregarObservador(observador);
	}
	
	public void eliminarObservadorDelModeloActividad(ModeloActividadObserver observador) {
		modelo.eliminarObservador(observador);
	}
}
