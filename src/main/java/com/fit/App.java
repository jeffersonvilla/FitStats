package com.fit;

import com.fit.controlador.ControladorUsuario;
import com.fit.vista.VentanaRegistroUsuario;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        ControladorUsuario controlador = new ControladorUsuario();
        VentanaRegistroUsuario vista = new VentanaRegistroUsuario(controlador);
        controlador.setVista(vista);
    }
}
