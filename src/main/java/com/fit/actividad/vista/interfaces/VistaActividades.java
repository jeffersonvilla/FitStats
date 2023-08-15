package com.fit.actividad.vista.interfaces;

import java.util.List;

import com.fit.actividad.vista.panelFormulario.PanelFormulario;
import com.fit.actividad.vista.panelFormulario.PanelFormularioActividad;

public interface VistaActividades {

	int getTipoActividadSelecionada();

	void limpiarCamposTexto(int actividad);

	void limpiarCamposError(int actividad);

	void validarFechaActividad(String mensajeError);

	void validarDistancia(int actividad, String mensajeError);

	void validarTipoBicicleta(int actividad, String mensajeError);

	void validarEstilosNatacion(int actividad, String mensajeError);

	void validarNombreDeporte(int actividad, String mensajeError);

	void validarResultadoDelPartido(int actividad, String mensajeError);

	void validarNombreEquipos(int actividad, String mensajeError);

	void validarDescansosEntreEjercicios(int actividad, String mensajeError);

	void validarDescansosEntreSeries(int actividad, String mensajeError);

	void validarTipoSesionEstiramientos(int actividad, String mensajeError);

	void validarNivelDificultadEstiramientos(int actividad, String mensajeError);

	void validarDescripcionOtraActividad(int actividad, String mensajeError);

	void actualizarListaActividades(List<Object[]> listaActividades);

	void verListaActividades();
	
	void mostrarPanelActualizacionActividad(PanelFormularioActividad panelActividad, int tipoActividad,
			PanelFormulario panelDetalleActividad);

	boolean isActualizando();
}
