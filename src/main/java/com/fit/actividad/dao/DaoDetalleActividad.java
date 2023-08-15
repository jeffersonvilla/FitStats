package com.fit.actividad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fit.actividad.modelo.Caminata;
import com.fit.actividad.modelo.Ciclismo;
import com.fit.actividad.modelo.DeporteEquipo;
import com.fit.actividad.modelo.DetalleActividad;
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

	public boolean guardarDetalleActividad(DetalleActividad detalleActividad) {
		 Class<? extends DetalleActividad> clase = detalleActividad.getClass();
		    switch (clase.getSimpleName()) {
		        case "Caminata":
		            return guardarCaminata((Caminata) detalleActividad);
		        case "Ciclismo":
		            return guardarCiclismo((Ciclismo) detalleActividad);
		        case "Natacion":
		            return guardarNatacion((Natacion) detalleActividad);
		        case "DeporteEquipo":
		        	return guardarDeporteEquipo((DeporteEquipo) detalleActividad);
		        case "EntrenamientoGimnasio":
		        	return guardarEntrenamientoGimnasio((EntrenamientoGimnasio) detalleActividad);
		        case "Estiramientos":
		        	return guardarEstiramientos((Estiramientos) detalleActividad);
		        case "OtraActividad":
		        	return guardarOtraActividad((OtraActividad) detalleActividad);
		        default:
		            return false;
		    }
	}
	
	private boolean guardarCaminata(Caminata caminata) {
		try {
			String query = "insert into detalles_caminata_carrera (actividad_id, distancia) values(?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, caminata.getId());
			statement.setFloat(2, caminata.getDistancia());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	private boolean guardarCiclismo(Ciclismo ciclismo) {
		try {
			String query = "insert into detalles_ciclismo (actividad_id, distancia, tipo_bicicleta) values (?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, ciclismo.getId());
			statement.setFloat(2, ciclismo.getDistancia());
			statement.setString(3, ciclismo.getTipo_bicicleta());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean guardarNatacion(Natacion natacion) {
		try {
			String query = "insert into detalles_natacion (actividad_id, distancia, estilos_natacion) values (?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, natacion.getId());
			statement.setFloat(2, natacion.getDistancia());
			statement.setString(3, natacion.getEstilosNatacion());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean guardarDeporteEquipo(DeporteEquipo deporteEquipo) {
		try {
			String query = "insert into detalles_deporte_equipo (actividad_id, nombre_deporte, nombre_equipo, resultado_partido) values (?, ? ,? ,?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, deporteEquipo.getId());
			statement.setString(2, deporteEquipo.getNombreDeporte());
			statement.setString(3, deporteEquipo.getNombreEquipos());
			statement.setString(4, deporteEquipo.getResultadoDelPartido());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean guardarEntrenamientoGimnasio(EntrenamientoGimnasio entrenamientoGimnasio) {
		try {
			String query = "insert into detalles_entrenamiento(actividad_id, ejercicios_realizados, descanso_entre_ejercicios, descanso_entre_series) values(?, ?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, entrenamientoGimnasio.getId());
			statement.setString(2, entrenamientoGimnasio.getEjerciciosRealizados());
			statement.setString(3, entrenamientoGimnasio.getDescansoEntreEjercicios());
			statement.setString(4, entrenamientoGimnasio.getDescansoEntreSeries());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean guardarEstiramientos(Estiramientos estiramientos) {
		try {
			String query = "insert into detalles_yoga_estiramientos(actividad_id, tipo_sesion, nivel_dificultad) values(?, ?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, estiramientos.getId());
			statement.setString(2, estiramientos.getTipoSesion());
			statement.setString(3, estiramientos.getNivelDificultad());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean guardarOtraActividad(OtraActividad otraActividad) {
		try {
			String query = "insert into detalles_otra_actividad(actividad_id, descripcion) values(?, ?);";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, otraActividad.getId());
			statement.setString(2, otraActividad.getDescripcion());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean actualizarDetalleActividad(DetalleActividad detalleActividad) {
		 Class<? extends DetalleActividad> clase = detalleActividad.getClass();
		    switch (clase.getSimpleName()) {
		        case "Caminata":
		            return actualizarCaminata((Caminata) detalleActividad);
		        case "Ciclismo":
		            return actualizarCiclismo((Ciclismo) detalleActividad);
		        case "Natacion":
		            return actualizarNatacion((Natacion) detalleActividad);
		        case "DeporteEquipo":
		        	return actualizarDeporteEquipo((DeporteEquipo) detalleActividad);
		        case "EntrenamientoGimnasio":
		        	return actualizarEntrenamientoGimnasio((EntrenamientoGimnasio) detalleActividad);
		        case "Estiramientos":
		        	return actualizarEstiramientos((Estiramientos) detalleActividad);
		        case "OtraActividad":
		        	return actualizarOtraActividad((OtraActividad) detalleActividad);
		        default:
		            return false;
		    }
	}
	
	private boolean actualizarCaminata(Caminata caminata) {
		try {
			String query = "update detalles_caminanta_carrera set distancia = ? where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(2, caminata.getId());
			statement.setFloat(1, caminata.getDistancia());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean actualizarCiclismo(Ciclismo ciclismo) {
		try {
			String query = "update detalles_ciclismo set distancia = ?, tipo_bicicleta = ? where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(3, ciclismo.getId());
			statement.setFloat(1, ciclismo.getDistancia());
			statement.setString(2, ciclismo.getTipo_bicicleta());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean actualizarNatacion(Natacion natacion) {
		try {
			String query = "update detalles_natacion set distancia = ?, estilos_natacion = ? where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(3, natacion.getId());
			statement.setFloat(1, natacion.getDistancia());
			statement.setString(2, natacion.getEstilosNatacion());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean actualizarDeporteEquipo(DeporteEquipo deporteEquipo) {
		try {
			String query = "update detalles_deporte_equipo set nombre_deporte = ?, nombre_equipo = ?, resultado_partido = ? where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(4, deporteEquipo.getId());
			statement.setString(1, deporteEquipo.getNombreDeporte());
			statement.setString(2, deporteEquipo.getNombreEquipos());
			statement.setString(3, deporteEquipo.getResultadoDelPartido());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean actualizarEntrenamientoGimnasio(EntrenamientoGimnasio entrenamiento) {
		try {
			String query = "update detalles_entrenamiento set ejercicios_realizados = ?, descanso_entre_ejercicios = ?, descanso_entre_series = ? where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(4, entrenamiento.getId());
			statement.setString(1, entrenamiento.getEjerciciosRealizados());
			statement.setString(2, entrenamiento.getDescansoEntreEjercicios());
			statement.setString(3, entrenamiento.getDescansoEntreSeries());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	private boolean actualizarEstiramientos(Estiramientos estiramientos) {
		try {
			String query = "update detalles_yoga_estiramientos set tipo_sesion = ?, nivel_dificultad = ? where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(3, estiramientos.getId());
			statement.setString(1, estiramientos.getTipoSesion());
			statement.setString(2, estiramientos.getNivelDificultad());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	private boolean actualizarOtraActividad(OtraActividad otraActividad) {
		try {
			String query = "update detalles_otra_actividad set descripcion = ? where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(2, otraActividad.getId());
			statement.setString(1, otraActividad.getDescripcion());
			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public DetalleActividad leerDetalleActividad(int actividadId, int tipoActividad) {
		System.out.println("actividadId: " + actividadId + " ,tipoActividad: " + tipoActividad);
		switch (tipoActividad) {
		case 1: return leerDetallesCaminata(actividadId);
		case 2: return leerDetallesCiclismo(actividadId);
		case 3: return leerDetallesNatacion(actividadId);
		case 4: return leerDetallesDeporteEquipo(actividadId);
		case 5: return leerDetallesEntrenamientoGimnasio(actividadId);
		case 6: return leerDetallesEstiramientos(actividadId);
		case 7: return leerDetallesOtraActividad(actividadId);
		default: return null;
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
