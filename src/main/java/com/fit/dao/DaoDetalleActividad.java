package com.fit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fit.dao.conexion.MysqlConnection;
import com.fit.modelo.Caminata;
import com.fit.modelo.Carrera;

public class DaoDetalleActividad {

	private Connection conexion;
	
	public DaoDetalleActividad() {
		this.conexion = MysqlConnection.getConnection();
	}
	
	public boolean guardarCaminata(int registroId, Caminata caminata) {
		try {
			String query = "insert into detalles_caminata (registro_id, distancia) values(?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, registroId);
			statement.setFloat(2, caminata.getDistancia());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean guardarCarrera(int registroId, Carrera carrera) {
		try {
			String query = "insert into detalles_carrera (registro_id, distancia, ritmo_promedio) values(?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, registroId);
			statement.setFloat(2, carrera.getDistancia());
			statement.setFloat(3, carrera.getRitmo_promedio());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
