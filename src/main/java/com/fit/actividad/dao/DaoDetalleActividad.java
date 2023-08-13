package com.fit.actividad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fit.actividad.modelo.Caminata;
import com.fit.actividad.modelo.Ciclismo;
import com.fit.actividad.modelo.DeporteEquipo;
import com.fit.actividad.modelo.EntrenamientoGimnasio;
import com.fit.actividad.modelo.Natacion;
import com.fit.actividad.modelo.OtraActividad;
import com.fit.actividad.modelo.Estiramientos;
import com.fit.util.MysqlConnection;

public class DaoDetalleActividad {

	private Connection conexion;

	public DaoDetalleActividad() {
		this.conexion = MysqlConnection.getConnection();
	}

	public boolean guardarCaminata(int actividadId, Caminata caminata) {
		try {
			String query = "insert into detalles_caminata_carrera (actividad_id, distancia) values(?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			statement.setFloat(2, caminata.getDistancia());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean guardarCiclismo(int actividadId, Ciclismo ciclismo) {
		try {
			String query = "insert into detalles_ciclismo (actividad_id, distancia, tipo_bicicleta) values (?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			statement.setFloat(2, ciclismo.getDistancia());
			statement.setString(3, ciclismo.getTipo_bicicleta());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean guardarNatacion(int actividadId, Natacion natacion) {
		try {
			String query = "insert into detalles_natacion (actividad_id, distancia, estilos_natacion) values (?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			statement.setFloat(2, natacion.getDistancia());
			statement.setString(3, natacion.getEstilosNatacion());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean guardarDeporteEquipo(int actividadId, DeporteEquipo deporteEquipo) {
		try {
			String query = "insert into detalles_deporte_equipo (actividad_id, nombre_deporte, nombre_equipo, resultado_partido) values (?, ? ,? ,?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			statement.setString(2, deporteEquipo.getNombreDeporte());
			statement.setString(3, deporteEquipo.getNombreEquipos());
			statement.setString(4, deporteEquipo.getResultadoDelPartido());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean guardarEntrenamientoGimnasio(int actividadId, EntrenamientoGimnasio entrenamientoGimnasio) {
		try {
			String query = "insert into detalles_entrenamiento(actividad_id, ejercicios_realizados, descanso_entre_ejercicios, descanso_entre_series) values(?, ?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			statement.setString(2, entrenamientoGimnasio.getEjerciciosRealizados());
			statement.setString(3, entrenamientoGimnasio.getDescansoEntreEjercicios());
			statement.setString(4, entrenamientoGimnasio.getDescansoEntreSeries());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean guardarEstiramientos(int actividadId, Estiramientos estiramientos) {
		try {
			String query = "insert into detalles_yoga_estiramientos(actividad_id, tipo_sesion, nivel_dificultad) values(?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			statement.setString(2, estiramientos.getTipoSesion());
			statement.setString(3, estiramientos.getNivelDificultad());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean guardarOtraActividad(int actividadId, OtraActividad otraActividad) {
		try {
			String query = "insert into detalles_otra_actividad(actividad_id, descripcion) values(?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			statement.setString(2, otraActividad.getDescripcion());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Caminata leerDetallesCaminata(int actividadId) {
		try {
			String query = "select * from detalles_caminata_carrera where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			ResultSet resultado = statement.executeQuery();
			if (resultado.next())
				return new Caminata(resultado.getInt("actividad_id"), resultado.getFloat("distancia"));
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Ciclismo leerDetallesCiclismo(int actividadId) {
		try {
			String query = "select * from detalles_ciclismo where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			ResultSet resultado = statement.executeQuery();
			if (resultado.next())
				return new Ciclismo(resultado.getInt("actividad_id"), resultado.getFloat("distancia"),
						resultado.getString("tipo_bicicleta"));
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Natacion leerDetallesNatacion(int actividadId) {
		try {
			String query = "select * from detalles_natacion where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			ResultSet resultado = statement.executeQuery();
			if (resultado.next())
				return new Natacion(resultado.getInt("actividad_id"), resultado.getFloat("distancia"),
						resultado.getString("estilos_natacion"));
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public DeporteEquipo leerDetallesDeporteEquipo(int actividadId) {
		try {
			String query = "select * from detalles_deporte_equipo where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			ResultSet resultado = statement.executeQuery();
			if (resultado.next())
				return new DeporteEquipo(resultado.getInt("actividad_id"), resultado.getString("nombre_deporte"),
						resultado.getString("nombre_equipo"), resultado.getString("resultado_partido"));
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public EntrenamientoGimnasio leerDetallesEntrenamientoGimnasio(int actividadId) {
		try {
			String query = "select * from detalles_entrenamiento where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			ResultSet resultado = statement.executeQuery();
			if (resultado.next())
				return new EntrenamientoGimnasio(resultado.getInt("actividad_id"),
						resultado.getString("ejercicios_realizados"), resultado.getString("descanso_entre_ejercicios"),
						resultado.getString("descanso_entre_series"));
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Estiramientos leerDetallesEstiramientos(int actividadId) {
		try {
			String query = "select * from detalles_yoga_estiramientos where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			ResultSet resultado = statement.executeQuery();
			if (resultado.next())
				return new Estiramientos(resultado.getInt("actividad_id"), resultado.getString("tipo_sesion"),
						resultado.getString("nivel_dificultad"));
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public OtraActividad leerDetallesOtraActividad(int actividadId) {
		try {
			String query = "select * from detalles_otra_actividad where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, actividadId);
			ResultSet resultado = statement.executeQuery();
			if (resultado.next())
				return new OtraActividad(resultado.getInt("actividad_id"), resultado.getString("descripcion"));
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
