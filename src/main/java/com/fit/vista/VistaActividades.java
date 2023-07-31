package com.fit.vista;

public interface VistaActividades {

	void limpiarCamposError(int actividad);

	void validarDistancia(int actividad, String mensajeError);

	void validarRitmoPromedio(int actividad, String mensajeError);
	
	void limpiarCamposTexto(int actividad);

	int getActividadSelecionada();

	void validarTipoBicicleta(int actividad, String mensajeError);

}
