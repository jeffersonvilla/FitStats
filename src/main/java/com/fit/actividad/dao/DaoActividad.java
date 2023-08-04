package com.fit.actividad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fit.actividad.modelo.Actividad;
import com.fit.util.MysqlConnection;

public class DaoActividad {

	private Connection conexion;
	
	public DaoActividad() {
		this.conexion = MysqlConnection.getConnection();
	}
	
	public int guardarActividad(Actividad actividad) {
		try {
			String query ="insert into actividad (user_id, tipo_actividad_id, fecha_hora, duracion, ubicacion) values(?, ?, ?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividad.getUserId());
			statement.setInt(2, actividad.getTipoActividadId());
			statement.setTimestamp(3, actividad.getFechaHora());
			statement.setTime(4, actividad.getDuracion());
			statement.setString(5, actividad.getUbicación());
			if(statement.executeUpdate() > 0) {
				PreparedStatement statementForId = this.conexion.prepareStatement("select last_insert_id();");
				ResultSet resultadoActividadId = statementForId.executeQuery(); 
				return (resultadoActividadId.next())? resultadoActividadId.getInt(1) : -1 ;
			}
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public List<Actividad> leerListaActividadesPorUsuarioId(int usuarioId) {
		try {
			String query = 
					"select actividad_id, user_id, a.tipo_actividad_id as tipo_actividad, fecha_hora, duracion, ubicacion " +
					"from actividad a " + 
					"join tipo_actividad t " + 
					"on t.tipo_actividad_id = a.tipo_actividad_id " + 
					"where a.user_id = ? " + 
					"order by fecha_hora DESC;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, usuarioId);
			ResultSet resultado = statement.executeQuery();
			if(resultado.next()) {
				List<Actividad> listaActividades = new ArrayList<>();
				do {
					if(resultado.getInt("actividad_id") != 0) {						
						listaActividades.add(
								new Actividad.ActividadBuilder().
								setId(resultado.getInt("actividad_id")).
								setUserId(resultado.getInt("user_id")).
								setTipoActividadId(resultado.getInt("tipo_actividad")).
								setFechaHora(resultado.getTimestamp("fecha_hora")).
								setDuracion(resultado.getTime("duracion")).
								setUbicación(resultado.getString("ubicacion")).
								build()
								);
					}
				}while(resultado.next());
				return listaActividades;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
