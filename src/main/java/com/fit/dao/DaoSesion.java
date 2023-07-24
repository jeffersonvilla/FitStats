package com.fit.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import com.fit.dao.conexion.MysqlConnection;
import com.fit.modelo.Sesion;

public class DaoSesion {

	private Connection conexion;
	
	public DaoSesion() {
		this.conexion = MysqlConnection.getConnection();
	}
	
	public boolean crearSesion(Sesion sesion) {
		try {
			String query = "insert into sesion (fecha_inicio, idUsuario) values (?, ?)";
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setString(1, sesion.getFechaIncioComoString());
			preparedStatement.setInt(2, sesion.getIdUsuario());
			return preparedStatement.execute();
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
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				GregorianCalendar fechaIncio = new GregorianCalendar();
				fechaIncio.setTime(dateFormat.parse(resultado.getString("fecha_inicio")));
				return new Sesion(resultado.getInt("idSesion"), fechaIncio, resultado.getInt("idUsuario"));
			}else return null;		
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean cerrarSesion(Sesion sesion) {
		try {
			String query = "update sesion set fecha_fin = ? where idSesion = ?";
			PreparedStatement preparedStatement = conexion.prepareStatement(query);
			preparedStatement.setString(1, sesion.getFechaFinComoString());
			preparedStatement.setInt(2, sesion.getIdSesion());
			return preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
