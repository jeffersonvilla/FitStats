package com.fit.usuario.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import com.fit.util.MysqlConnection;

public class DaoSesion {

	private Connection conexion;
	
	public DaoSesion() {
		this.conexion = MysqlConnection.getConnection();
	}
	
	public boolean crearSesion(Sesion sesion) {
		try {
			String query = "insert into sesion (fecha_inicio, idUsuario) values (?, ?)";
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setTimestamp(1, new Timestamp(sesion.getFechaInicio().getTimeInMillis()));
			preparedStatement.setInt(2, sesion.getIdUsuario());
			return preparedStatement.executeUpdate() > 0;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public Sesion sesionAbiertaPorIdUsuario(int idUsuario) {
		try {
			String query = "select * from sesion where idUsuario = ? and fecha_fin is null";
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setInt(1, idUsuario);
			ResultSet resultado = preparedStatement.executeQuery();
			if(resultado.next()) {
				GregorianCalendar fechaIncio = new GregorianCalendar();
				fechaIncio.setTimeInMillis(resultado.getTimestamp("fecha_inicio").getTime());
				return new Sesion(resultado.getInt("idSesion"), fechaIncio, resultado.getInt("idUsuario"));
			}else return null;		
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean cerrarSesion(Sesion sesion) {
		try {
			String query = "update sesion set fecha_fin = ? where idSesion = ?";
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setTimestamp(1, new Timestamp(sesion.getFechaFin().getTimeInMillis()));
			preparedStatement.setInt(2, sesion.getIdSesion());
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
