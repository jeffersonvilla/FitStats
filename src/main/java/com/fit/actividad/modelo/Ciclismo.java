package com.fit.actividad.modelo;

import java.sql.Time;
import java.sql.Timestamp;

public class Ciclismo extends Actividad {

	public static final int TAMANIO_MAXIMO_TIPO_BICICLETA = 50;

	public static final String MENSAJE_TAMANIO_MAXIMO_TIPO_BICICLETA = "Máximo " + TAMANIO_MAXIMO_TIPO_BICICLETA
			+ " caracteres";

	private float distancia;

	private String tipoBicicleta;

	public Ciclismo(int id, int userId, Timestamp fechaHora, Time duracion, String ubicacion, float distancia, String tipo_bicicleta) {
		super(id, userId, fechaHora, duracion, ubicacion
				);
		this.distancia = distancia;
		this.tipoBicicleta = tipo_bicicleta;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	public String getTipo_bicicleta() {
		return tipoBicicleta;
	}

	public void setTipo_bicicleta(String tipo_bicicleta) {
		this.tipoBicicleta = tipo_bicicleta;
	}

	@Override
	public int getTipoActividad() {
		return TipoActividad.CICLISMO.getValor();
	}

	@Override
	public String getNombreTipoActividad() {
		return TipoActividad.CICLISMO.getNombre();
	}

	@Override
	public String toString() {
		return super.toString() + "Ciclismo [distancia=" + distancia + ", tipo_bicicleta=" + tipoBicicleta + "]";
	}

	@Override
	public boolean atributosIguales(Actividad otraActividad) {
		if (!super.atributosIguales(otraActividad))
			return false;
		if (!(otraActividad instanceof Ciclismo))
			return false;
		Ciclismo ciclismo = (Ciclismo) otraActividad;
		return this.distancia == ciclismo.getDistancia() && this.tipoBicicleta.equals(ciclismo.getTipo_bicicleta());
	}
	
	public static class CiclismoBuilder extends ActividadBuilder{

		private float distancia = -1;
		
		private String tipoBicicleta;
		
		public CiclismoBuilder setDistancia(float distancia) {
			this.distancia = distancia;
			return this;
		}
		
		public CiclismoBuilder setTipoBicicleta(String tipoBicicleta) {
			this.tipoBicicleta = tipoBicicleta;
			return this;
		}
		
		@Override
		public Actividad build() {
			return new Ciclismo(id, userId, fechaHora, duracion, ubicacion, distancia, tipoBicicleta);
		}
		
	}
	
}
