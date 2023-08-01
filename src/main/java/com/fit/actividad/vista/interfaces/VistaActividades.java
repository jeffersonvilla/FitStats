package com.fit.actividad.vista.interfaces;

public interface VistaActividades {
	
	int getActividadSelecionada();
	
	void limpiarCamposTexto(int actividad);

	void limpiarCamposError(int actividad);

	void validarDistancia(int actividad, String mensajeError);

	void validarRitmoPromedio(int actividad, String mensajeError);
	
	void validarTipoBicicleta(int actividad, String mensajeError);

	void validarEstiloNatacion(int actividad, String mensajeError);

}
