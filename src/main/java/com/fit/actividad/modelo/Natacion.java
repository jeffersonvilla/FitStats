package com.fit.actividad.modelo;

import java.sql.Time;
import java.sql.Timestamp;

public class Natacion extends Actividad {

	public static final int TAMANIO_MAXIMO_ESTILO_NATACION = 50;

	public static final String MENSAJE_TAMANIO_MAXIMO_ESTILO_NATACION = "Máximo " + TAMANIO_MAXIMO_ESTILO_NATACION
			+ " caracteres";

	private float distancia;

	private String estilosNatacion;

	public Natacion() {
		super();
	}

	public Natacion(int id, int userId, Timestamp fechaHora, Time duracion, String ubicacion, 
			float distancia, String estilosNatacion) {
		super(id, userId, fechaHora, duracion, ubicacion);
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

	@Override
	public int getTipoActividad() {
		return TipoActividad.NATACION.getValor();
	}

	@Override
	public String getNombreTipoActividad() {
		return TipoActividad.NATACION.getNombre();
	}

	@Override
	public String toString() {
		return super.toString() + "Natacion [distancia=" + distancia + ", estilosNatacion=" + estilosNatacion + "]";
	}

	@Override
	public boolean atributosIguales(Actividad otraActividad) {
		if(! super.atributosIguales(otraActividad)) return false;
		if(!(otraActividad instanceof Natacion)) return false;
		Natacion natacion = (Natacion) otraActividad;
		return this.distancia == natacion.getDistancia() &&
				this.estilosNatacion.equals(natacion.getEstilosNatacion());
	}
	
	public static class NatacionBuilder extends ActividadBuilder{

		private float distancia = -1;

		private String estilosNatacion;

		public NatacionBuilder setDistancia(float distancia) {
			this.distancia = distancia;
			return this;
		}
		
		public NatacionBuilder setEstilosNatacion(String estilosNatacion) {
			this.estilosNatacion = estilosNatacion;
			return this;
		}
		
		@Override
		public Actividad build() {
			return new Natacion(id, userId, fechaHora, duracion, ubicacion, distancia, estilosNatacion);
		}
	}
}
