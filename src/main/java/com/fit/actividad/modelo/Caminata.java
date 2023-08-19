package com.fit.actividad.modelo;

import java.sql.Time;
import java.sql.Timestamp;

public class Caminata extends Actividad {

	private float distancia;

	public Caminata() {}
	
	public Caminata(int id, int userId, Timestamp fechaHora, Time duracion, String ubicación, float distancia) {
		super(id, userId, fechaHora, duracion, ubicación);
		this.distancia = distancia;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}
	
	@Override
	public int getTipoActividad() {
		return TipoActividad.CAMINATA.getValor();
	}
	
	@Override
	public String getNombreTipoActividad() {
		return TipoActividad.CAMINATA.getNombre();
	}

	@Override
	public String toString() {
		return super.toString() + "Caminata [distancia=" + distancia + "]";
	}

	@Override
	public boolean atributosIguales(Actividad otraActividad) {
		if(!super.atributosIguales(otraActividad)) return false;
		if(!(otraActividad instanceof Caminata)) return false;
		return this.distancia == ((Caminata) otraActividad).getDistancia();
	}

	public static class CaminataBuilder extends ActividadBuilder{

		private float distancia = -1;
		
		public CaminataBuilder setDistancia(float distancia) {
			this.distancia = distancia;
			return this;
		}
		
		@Override
		public Caminata build() {
			return new Caminata(id, userId, fechaHora, duracion, ubicacion, distancia);
		}
		
	}
}
