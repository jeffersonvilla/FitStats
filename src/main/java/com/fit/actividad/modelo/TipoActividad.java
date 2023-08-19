package com.fit.actividad.modelo;

public enum TipoActividad {
	CAMINATA(1, "Caminar/correr"),
    CICLISMO(2, "Ciclismo"),
    NATACION(3, "Natacion"),
    DEPORTE_EQUIPO(4, "Deportes de equipo"),
    ENTRENAMIENTO_GIMNASIO(5, "Entrenamiento en el Gimnasio"),
    ESTIRAMIENTOS(6, "Yoga/estiramientos/pilates"),
    OTRA_ACTIVIDAD(7, "Otra actividad");

    private final int valor;
    
    private final String nombre;

    TipoActividad(int valor, String nombre) {
        this.valor = valor;
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }
    
    public String getNombre() {
    	return nombre;
    }
}
