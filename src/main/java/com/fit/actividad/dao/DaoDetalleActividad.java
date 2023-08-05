package com.fit.actividad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fit.actividad.modelo.Caminata;
import com.fit.actividad.modelo.Ciclismo;
import com.fit.actividad.modelo.DeporteEquipo;
import com.fit.actividad.modelo.EntrenamientoGimnasio;
import com.fit.actividad.modelo.Natacion;
import com.fit.util.MysqlConnection;

public class DaoDetalleActividad {

	private Connection conexion;
	
	public DaoDetalleActividad() {
		this.conexion = MysqlConnection.getConnection();
	}
	
	public boolean guardarCaminata(int actividadId, Caminata caminata) {
		try {
			String query = "insert into detalles_caminata_carrera (actividad_id, distancia) values(?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			statement.setFloat(2, caminata.getDistancia());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean guardarCiclismo(int actividadId, Ciclismo ciclismo) {
		try {
			String query = "insert into detalles_ciclismo (actividad_id, distancia, tipo_bicicleta) values (?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			statement.setFloat(2, ciclismo.getDistancia());
			statement.setString(3, ciclismo.getTipo_bicicleta());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean guardarNatacion(int actividadId, Natacion natacion) {
		try {
			String query = "insert into detalles_natacion (actividad_id, distancia, estilos_natacion) values (?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			statement.setFloat(2, natacion.getDistancia());
			statement.setString(3, natacion.getEstilosNatacion());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean guardarDeporteEquipo(int actividadId, DeporteEquipo deporteEquipo) {
		try {
			String query = "insert into detalles_deporte_equipo (actividad_id, nombre_deporte, nombre_equipo, resultado_partido) values (?, ? ,? ,?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			statement.setString(2, deporteEquipo.getNombreDeporte());
			statement.setString(3, deporteEquipo.getNombreEquipos());
			statement.setString(4, deporteEquipo.getResultadoDelPartido());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean guardarEntrenamientoGimnasio(int actividadId, EntrenamientoGimnasio entrenamientoGimnasio) {
		try {
			String query = "insert into detalles_entrenamiento(actividad_id, ejercicios_realizados, descanso_entre_ejercicios, descanso_entre_series) values(?, ?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			statement.setString(2, entrenamientoGimnasio.getEjerciciosRealizados());
			statement.setString(3, entrenamientoGimnasio.getDescansoEntreEjercicios());
			statement.setString(4, entrenamientoGimnasio.getDescansoEntreSeries());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
