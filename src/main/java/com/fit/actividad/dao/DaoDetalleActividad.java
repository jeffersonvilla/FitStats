package com.fit.actividad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fit.actividad.modelo.Caminata;
import com.fit.actividad.modelo.Carrera;
import com.fit.actividad.modelo.Ciclismo;
import com.fit.actividad.modelo.DeporteEquipo;
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

	public boolean guardarCarrera(int actividadId, Carrera carrera) {
		try {
			String query = "insert into detalles_carrera (actividad_id, distancia, ritmo_promedio) values(?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			statement.setFloat(2, carrera.getDistancia());
			statement.setFloat(3, carrera.getRitmo_promedio());
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
			String query = "insert into detalles_natacion (actividad_id, distancia, tipo_estilo_natacion) values (?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			statement.setFloat(2, natacion.getDistancia());
			statement.setString(3, natacion.getEstiloNatacion());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean guardarDeporteEquipo(int actividadId, DeporteEquipo deporteEquipo) {
		try {
			String query = "insert into detalles_deporte_equipo (actividad_id, nombre_equipo, resultado_partido, duracion_partido) values (?, ? ,? ,?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			statement.setString(2, deporteEquipo.getNombreDeporte());
			statement.setString(3, deporteEquipo.getResultadoDelPartido());
			statement.setInt(4, deporteEquipo.getDuracionDelPartido());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
