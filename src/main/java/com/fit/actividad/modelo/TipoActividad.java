package com.fit.actividad.modelo;

public enum TipoActividad {
	CAMINATA(1),
    CICLISMO(2),
    NATACION(3),
    DEPORTES_EQUIPO(4),
    ENTRENAMIENTO_GIMNASIO(5),
    ESTIRAMIENTOS(6),
    OTRA_ACTIVIDAD(7);

    private final int valor;

    TipoActividad(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
