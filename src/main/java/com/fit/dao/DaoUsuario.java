package com.fit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.fit.dao.conexion.MysqlConnection;
import com.fit.modelo.Usuario;

public class DaoUsuario {
	
	public boolean crearUsuario(Usuario usuario) {
		Connection con = MysqlConnection.getConnection();
		try {
			String query = "insert into usuario (nombre, email, password) values (?, ?, ? )";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, usuario.getNombre());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getPassword());
			return (preparedStatement.executeUpdate() == 1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void leerUsuarioPorEmail(String email) {
		
	}
	
	public void actualizarUsuario(Usuario usuario) {
		
	}
	
	public void eliminarUsuario(Usuario usuario) {
		
	}

	public boolean validarEmailDisponible(String email) {
		Connection con = MysqlConnection.getConnection();
		try {
			String query = "select email from usuario where email = ?;";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()) return false; 
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
