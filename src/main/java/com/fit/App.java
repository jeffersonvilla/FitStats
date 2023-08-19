package com.fit;

import javax.swing.JFrame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fit.actividad.controlador.ControladorActividad;
import com.fit.actividad.vista.VistaCrearCaminata;
import com.fit.actividad.vista.panelFormulario.FormularioCaminata;


public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("configuracion.xml");

		//context.getBean("vistaCrearCaminata", VistaCrearCaminata.class);
		
		ControladorActividad controladorActividad = context.getBean("controladorActividad", ControladorActividad.class);
		controladorActividad.leerListaActividades();
		
		//		context.getBean("vistaConsolaEliminar", VistaCaminataFormularioEliminar.class);

		
		//TODO: Un JFrame padre para FormCrear con los elementos comunes y JFrame hijos agregan sus propios elementos
		//Boton "guardar" comun en el padre con metodo para agregar listener y el controlador correspondiente
		// se agrega a si mismo como listener
	}

}
