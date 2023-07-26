package com.fit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fit.dao.conexion.MysqlConnection;
import com.fit.modelo.Registro;

public class DaoRegistro {

	private Connection conexion;
	
	public DaoRegistro() {
		this.conexion = MysqlConnection.getConnection();
	}
	
	public boolean guardarRegistro(Registro registro) {
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
			statement.setString(3, registro.getFechaInicioComoString());
			if(tieneFechaFin) statement.setString(4, registro.getFechaFinComoString());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public int getRegistroId(Registro registro) {
		try {
			String query = "select registro_id from registro where user_id = ? and activity_id = ? and fecha_inicio = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, registro.getUserId());
			statement.setInt(2, registro.getActividadId());
			statement.setString(3, registro.getFechaInicioComoString());
			ResultSet resultado = statement.executeQuery();
			if(resultado.next()) return resultado.getInt("registro_id");
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

}
