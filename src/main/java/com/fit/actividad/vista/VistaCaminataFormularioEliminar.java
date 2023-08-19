package com.fit.actividad.vista;

import java.util.Scanner;

import com.fit.actividad.controlador.ControladorCaminata;

public class VistaCaminataFormularioEliminar {
	
	private ControladorCaminata controlador;
	
	public VistaCaminataFormularioEliminar(ControladorCaminata controlador) {
		this.controlador = controlador;
		
		init();
	}
	
	private void init() {
	
		Scanner in = new Scanner(System.in);
		int id = 0;
		do {
			System.out.print("ID de la actividad a eliminar (0 para salir) : ");
			id = in.nextInt();
			//controlador.eliminarCaminata(id);
		}while(id != 0);
	}
	
}
