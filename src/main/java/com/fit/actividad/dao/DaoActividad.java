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
			String query = "insert into actividad (user_id, tipo_actividad_id, fecha_hora, duracion, ubicacion) values(?, ?, ?, ?, ?);";
			PreparedStatement insertStatement = this.conexion.prepareStatement(query);
			insertStatement.setInt(1, actividad.getUserId());
			insertStatement.setInt(2, actividad.getDetalleActividad().getTipoActividad());
			insertStatement.setTimestamp(3, actividad.getFechaHora());
			insertStatement.setTime(4, actividad.getDuracion());
			insertStatement.setString(5, actividad.getUbicación());
			if (insertStatement.executeUpdate() > 0) {
				PreparedStatement statementForId = this.conexion.prepareStatement("select last_insert_id();");
				ResultSet resultadoActividadId = statementForId.executeQuery();
				return (resultadoActividadId.next()) ? resultadoActividadId.getInt(1) : -1;
			}
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public boolean actualizarActividad(Actividad actividad) {
		try {
			String query = "update actividad set fecha_hora = ?, duracion = ?, ubicacion = ? where actividad_id = ?;";
			PreparedStatement updateStatement = this.conexion.prepareStatement(query);
			updateStatement.setInt(4, actividad.getId());
			updateStatement.setTimestamp(1, actividad.getFechaHora());
			updateStatement.setTime(2, actividad.getDuracion());
			updateStatement.setString(3, actividad.getUbicación());
			return(updateStatement.executeUpdate() > 0);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean eliminarActividadPorId(int idActividad) {
		try {
			String query = "delete from actividad where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, idActividad);
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/*
	private Actividad leerActividadPorId(int idActividad) {
		try {
			String query = "select * from actividad where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, idActividad);
			ResultSet resultado = statement.executeQuery();
			if(resultado.next()) {
				Actividad actividad = new Actividad.ActividadBuilder()
						.setId(resultado.getInt("actividad_id"))
						.setUserId(resultado.getInt("user_id"))
						.setFechaHora(resultado.getTimestamp("fecha_hora"))
						.setDuracion(resultado.getTime("duracion"))
						.setUbicación(resultado.getString("ubicacion"))	
						.build();
				System.out.println("Actividad en base de datos: " +  actividad);
				return actividad;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	 */
	public List<Actividad> leerListaActividadesPorUsuarioId(int usuarioId) {
		try {
			String query = "select actividad_id, user_id, a.tipo_actividad_id as tipo_actividad, fecha_hora, duracion, ubicacion "
					+ "from actividad a " + "join tipo_actividad t " + "on t.tipo_actividad_id = a.tipo_actividad_id "
					+ "where a.user_id = ? " + "order by fecha_hora DESC;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, usuarioId);
			ResultSet resultado = statement.executeQuery();
			if (resultado.next()) {
				List<Actividad> listaActividades = new ArrayList<>();
				do {
					if (resultado.getInt("actividad_id") != 0) {
						listaActividades.add(new Actividad.ActividadBuilder()
								.setId(resultado.getInt("actividad_id"))
								.setUserId(resultado.getInt("user_id"))
								.setTipoActividad(resultado.getInt("tipo_actividad"))
								.setFechaHora(resultado.getTimestamp("fecha_hora"))
								.setDuracion(resultado.getTime("duracion"))
								.setUbicación(resultado.getString("ubicacion")).build());
					}
				} while (resultado.next());
				return listaActividades;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
