package com.fit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.fit.dao.conexion.MysqlConnection;
import com.fit.modelo.Usuario;

public class DaoUsuario {
	
	public void crearUsuario(Usuario usuario) {
		Connection con = MysqlConnection.getConnection();
		try {
			String query = "insert into usuario (nombre, email, password) values (?, ?, ? )";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, usuario.getNombre());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getPassword());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void leerUsuarioPorEmail(String email) {
		
	}
	
	public void actualizarUsuario(Usuario usuario) {
		
	}
	
	public void eliminarUsuario(Usuario usuario) {
		
	}
}
