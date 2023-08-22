package com.fit.actividad.vista.caminata;

import com.fit.actividad.AbstractFactory.CrudMvcFactory;
import com.fit.actividad.AbstractFactory.VistaFormularioActualizar;
import com.fit.actividad.AbstractFactory.VistaFormularioCrear;
import com.fit.actividad.controlador.ControladorActividad;
import com.fit.actividad.vista.panelFormulario.FormularioCaminata;

public class CaminataFactory implements CrudMvcFactory {
	
	private ControladorActividad controlador;
	
	private FormularioCaminata formulario;
	
	public CaminataFactory(ControladorActividad controlador) {
		this.controlador = controlador;
	}

	public CaminataFactory(ControladorActividad controlador, FormularioCaminata formulario) {
		this.controlador = controlador;
		this.formulario = formulario;
	}

	@Override
	public VistaFormularioCrear getVistaFormularioCrear() {
		return new VistaCrearCaminata(controlador, formulario);
	}

	@Override
	public VistaFormularioActualizar getVistaFormularioActualizar() {
		return new VistaActualizarCaminata(controlador, formulario);
	}

}
