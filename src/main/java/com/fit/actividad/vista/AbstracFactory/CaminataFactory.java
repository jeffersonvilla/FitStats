package com.fit.actividad.vista.AbstracFactory;

import com.fit.actividad.controlador.Controlador;
import com.fit.actividad.controlador.ControladorCaminata;
import com.fit.actividad.modelo.ModeloActividad;
import com.fit.actividad.vista.actualizar.VistaActualizarCaminata;
import com.fit.actividad.vista.actualizar.VistaFormularioActualizar;
import com.fit.actividad.vista.crear.VistaCrearCaminata;
import com.fit.actividad.vista.crear.VistaFormularioCrear;
import com.fit.actividad.vista.panelFormulario.FormularioActividad;
import com.fit.actividad.vista.panelFormulario.FormularioCaminata;

public class CaminataFactory implements CrudMvcFactory {
	
	private ControladorCaminata controlador;
	
	private VistaCrearCaminata vistaCrear;
	
	private VistaActualizarCaminata vistaActualizar;
	
	public CaminataFactory(ModeloActividad modelo) {
		this.controlador = new ControladorCaminata(modelo);
		this.vistaCrear = new VistaCrearCaminata(controlador, new FormularioCaminata());
	}

	@Override
	public Controlador getControlador() {
		return this.controlador;
	}

	@Override
	public VistaFormularioCrear getFormularioCrear() {
		return this.vistaCrear;
	}

	@Override
	public VistaFormularioActualizar getFormularioActualizar(FormularioActividad formulario) {
		this.vistaActualizar = new VistaActualizarCaminata(controlador, (FormularioCaminata) formulario);
		return this.vistaActualizar;
	}

}
