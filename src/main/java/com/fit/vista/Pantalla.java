package com.fit.vista;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Pantalla {
	private static Toolkit toolkit = Toolkit.getDefaultToolkit();
	private static Dimension screenSize = toolkit.getScreenSize();
	public static int ancho = screenSize.width;
	public static int alto = screenSize.height;
}
