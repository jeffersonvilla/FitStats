package com.fit.actividad.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fit.util.MysqlConnection;

public class DaoActividad {

	private Connection conexion;
	
	public DaoActividad() {
		conexion = MysqlConnection.getConnection();
	}

	public String[] getListaActividades() {
		String[] actividades = new String[getNumeroActividades()];
		try {
			String query = "select nombre_actividad from actividad;";
			Statement statement = conexion.createStatement();
			ResultSet resultado = statement.executeQuery(query);
			resultado.next();
			for(int i = 0; i < actividades.length; i++) {
				actividades[i] = resultado.getString("nombre_actividad");
				resultado.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return actividades;
		}		
		return actividades;
	}
	
	private int getNumeroActividades() {
		try {
			String query = "select count(*) from actividad;";
			Statement statement = conexion.createStatement();
			ResultSet resultado = statement.executeQuery(query);
			if(resultado.next()) return resultado.getInt(1); 
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
