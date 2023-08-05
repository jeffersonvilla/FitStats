package com.fit.actividad.modelo;

public class Natacion extends DetalleActividad {

	public static final int TAMANIO_MAXIMO_ESTILO_NATACION = 50;
	
	public static final String MENSAJE_TAMANIO_MAXIMO_ESTILO_NATACION = "Máximo " + TAMANIO_MAXIMO_ESTILO_NATACION + " caracteres";

	private float distancia;
	
	private String estilosNatacion;

	public Natacion(int id, float distancia, String estilosNatacion) {
		super(id);
		this.distancia = distancia;
		this.estilosNatacion = estilosNatacion;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	public void setEstilosNatacion(String estilosNatacion) {
		this.estilosNatacion = estilosNatacion;
	}

	public String getEstilosNatacion() {
		return estilosNatacion;
	}

	public void setEstiloNatacion(String estiloNatacion) {
		this.estilosNatacion = estiloNatacion;
	}

	@Override
	public String toString() {
		return "Natacion [distancia=" + distancia + ", estiloNatacion=" + estilosNatacion + ", idRegistro= " + super.getId() + "]";
	}

	@Override
	public boolean guardarEnDB_AsociadoConRegistro(int actividadId) {
		return daoDetalleActividad.guardarNatacion(actividadId, this);
	}
}
