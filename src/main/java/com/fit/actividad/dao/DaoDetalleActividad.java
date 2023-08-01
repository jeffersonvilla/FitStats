package com.fit.actividad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fit.actividad.modelo.detalle_actividad.Caminata;
import com.fit.actividad.modelo.detalle_actividad.Carrera;
import com.fit.actividad.modelo.detalle_actividad.Ciclismo;
import com.fit.actividad.modelo.detalle_actividad.Natacion;
import com.fit.util.MysqlConnection;

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

	public boolean guardarCiclismo(int registroId, Ciclismo ciclismo) {
		try {
			String query = "insert into detalles_ciclismo (registro_id, distancia, tipo_bicicleta) values (?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, registroId);
			statement.setFloat(2, ciclismo.getDistancia());
			statement.setString(3, ciclismo.getTipo_bicicleta());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean guardarNatacion(int registroId, Natacion natacion) {
		try {
			String query = "insert into detalles_natacion (registro_id, distancia, tipo_estilo_natacion) values (?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, registroId);
			statement.setFloat(2, natacion.getDistancia());
			statement.setString(3, natacion.getEstiloNatacion());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
