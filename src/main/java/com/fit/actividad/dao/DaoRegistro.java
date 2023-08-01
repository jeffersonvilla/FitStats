package com.fit.actividad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.fit.actividad.modelo.Registro;
import com.fit.util.MysqlConnection;

public class DaoRegistro {

	private Connection conexion;
	
	public DaoRegistro() {
		this.conexion = MysqlConnection.getConnection();
	}
	
	public int guardarRegistro(Registro registro) {
		try {
			String query = "";
			boolean tieneFechaFin = false;
			if(!registro.getFechaFinComoString().equals("null")) {				
				query ="insert into registro (user_id, activity_id, fecha_inicio, fecha_fin) values(?, ?, ?, ?);";
				tieneFechaFin = true;
			}else query = "insert into registro (user_id, activity_id, fecha_inicio) values(?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, registro.getUserId());
			statement.setInt(2, registro.getActividadId());
			statement.setTimestamp(3, new Timestamp(registro.getFechaInicio().getTimeInMillis()));
			if(tieneFechaFin) statement.setTimestamp(4, new Timestamp(registro.getFechaFin().getTimeInMillis()));
			if(statement.executeUpdate() > 0) {
				PreparedStatement statementForId = this.conexion.prepareStatement("select last_insert_id();");
				ResultSet resultadoRegistroId = statementForId.executeQuery(); 
				if(resultadoRegistroId.next()) {
					return resultadoRegistroId.getInt(1);
				}
				return -1;
			}
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
