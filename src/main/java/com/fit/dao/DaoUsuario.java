package com.fit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.fit.dao.conexion.MysqlConnection;
import com.fit.modelo.Usuario;

public class DaoUsuario {
	
	private Connection con ;
	
	public DaoUsuario() {
		this.con = MysqlConnection.getConnection();
	}
	
	public boolean crearUsuario(Usuario usuario) {
		try {
			String query = "insert into usuario (nombre, email, password) values (?, ?, ? )";
			PreparedStatement preparedStatement = this.con.prepareStatement(query);
			preparedStatement.setString(1, usuario.getNombre());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getPassword());
			return (preparedStatement.executeUpdate() == 1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String leerPassUsuarioPorEmail(String email) {
		try {
			String query = "select password from usuario where email = ?";
			PreparedStatement preparedStatement = this.con.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()) return result.getString("password");
			else return null;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Usuario leerUsuarioPorEmail(String email) {
		try {
			String query = "select id, nombre, email from usuario where email = ?";
			PreparedStatement preparedStatement = this.con.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()) return new Usuario(result.getInt("id"), result.getString("nombre"), result.getString("email"));
			else return null;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
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
