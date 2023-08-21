package com.fit.actividad.vista.AbstracFactory;

import com.fit.actividad.controlador.Controlador;
import com.fit.actividad.vista.panelFormulario.FormularioActividad;

public interface CrudMvcFactory {

	public Controlador getControlador();
	
	public VistaFormularioCrear getFormularioCrear();

	public VistaFormularioActualizar getFormularioActualizar(FormularioActividad formulario);
}
