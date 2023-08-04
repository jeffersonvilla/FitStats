package com.fit.actividad.vista.interfaces;

import java.util.List;

public interface VistaActividades {
	
	int getActividadSelecionada();
	
	void limpiarCamposTexto(int actividad);

	void limpiarCamposError(int actividad);

	void validarDistancia(int actividad, String mensajeError);
	
	void validarTipoBicicleta(int actividad, String mensajeError);

	void validarEstilosNatacion(int actividad, String mensajeError);

	void validarNombreDeporte(int actividad, String mensajeError);

	void validarResultadoDelPartido(int actividad, String mensajeError);

	void validarNombreEquipos(int actividad, String mensajeError);

	void validarFechaActividad(String mensajeError);

	void actualizarListaActividades(List<Object[]> listaActividades);

	void verListaActividades();
}
