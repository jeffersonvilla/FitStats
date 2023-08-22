package com.fit.actividad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fit.actividad.modelo.Actividad;
import com.fit.actividad.modelo.Caminata;
import com.fit.actividad.modelo.Ciclismo;
import com.fit.actividad.modelo.DeporteEquipo;
import com.fit.actividad.modelo.EntrenamientoGimnasio;
import com.fit.actividad.modelo.Natacion;
import com.fit.actividad.modelo.OtraActividad;
import com.fit.actividad.modelo.Estiramientos;

public class DaoActividadImpl implements DaoActividad{

	private Connection conexion;
	
	private static final Logger logger = LoggerFactory.getLogger(DaoActividadImpl.class);

	public DaoActividadImpl(Connection conexion) {
		this.conexion = conexion;
	}
	
	@Override
	public boolean guardarActividad(Actividad actividad) {
		try {	
			String query = "insert into actividad (user_id, tipo_actividad_id, fecha_hora, duracion, ubicacion) values(?, ?, ?, ?, ?);";
			PreparedStatement insertStatement = this.conexion.prepareStatement(query);
			insertStatement.setInt(1, 27);//actividad.getUserId()); TODO: cambiar
			insertStatement.setInt(2, actividad.getTipoActividad());
			insertStatement.setTimestamp(3, actividad.getFechaHora());
			insertStatement.setTime(4, actividad.getDuracion());
			insertStatement.setString(5, actividad.getUbicacion());
			if(insertStatement.executeUpdate() > 0) {
				actividad.setId(leerIdUltimoRegistroCreado());
				logger.debug("guardado en tabla actividad {}", actividad);
				if(actividad.getId() > 0) return guardarDetalleActividad(actividad);
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Actividad leerActividadPorId(int idActividad) {
		logger.debug("leyendo actividad con id {}", idActividad);
		try {
			String query = "select * from actividad where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, idActividad);
			ResultSet resultadoActividad = statement.executeQuery();
			if(resultadoActividad.next()) {
				logger.debug("leido con exito actividad con id {}", idActividad);
				return leerDetalleActividad(resultadoActividad);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean actualizarActividad(Actividad actividad) {
		logger.debug("actualizando actividad");
		try {
			String query = "update actividad set fecha_hora = ?, duracion = ?, ubicacion = ? where actividad_id = ?;";
			PreparedStatement updateStatement = this.conexion.prepareStatement(query);
			updateStatement.setInt(4, actividad.getId());
			updateStatement.setTimestamp(1, actividad.getFechaHora());
			updateStatement.setTime(2, actividad.getDuracion());
			updateStatement.setString(3, actividad.getUbicacion());
			boolean actualizadoActividad = (updateStatement.executeUpdate() > 0);
			boolean actualizadoDetalleActividad = actualizarDetalleActividad(actividad);
			return actualizadoActividad && actualizadoDetalleActividad;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
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
	
	@Override
	public List<Actividad> leerListaActividadesPorUsuarioId(int usuarioId) {
		try {
			String query = "select actividad_id from actividad where user_id = ? order by fecha_hora DESC;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, usuarioId);
			ResultSet resultado = statement.executeQuery();
			if (resultado.next()) {
				List<Actividad> listaActividades = new ArrayList<>();
				do {
					if(resultado.getInt("actividad_id") != 0) 
						listaActividades.add(leerActividadPorId(resultado.getInt("actividad_id")));
				} while (resultado.next());
				return listaActividades;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private int leerIdUltimoRegistroCreado() {
		try {
			PreparedStatement statementForId = this.conexion.prepareStatement("select last_insert_id();");
			ResultSet resultadoActividadId = statementForId.executeQuery();
			return (resultadoActividadId.next()) ? resultadoActividadId.getInt(1) : -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	private boolean guardarDetalleActividad(Actividad detalleActividad) {
		 Class<? extends Actividad> clase = detalleActividad.getClass();
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
			String query = "insert into detalles_caminata (actividad_id, distancia) values(?, ?);";
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
	
	private Actividad leerDetalleActividad(ResultSet resultadoActividad) {
		try {
			switch (resultadoActividad.getInt("tipo_actividad_id")) {
			case 1: return leerDetallesCaminata(resultadoActividad);
			case 2: return leerDetallesCiclismo(resultadoActividad);
			case 3: return leerDetallesNatacion(resultadoActividad);
			case 4: return leerDetallesDeporteEquipo(resultadoActividad);
			case 5: return leerDetallesEntrenamientoGimnasio(resultadoActividad);
			case 6: return leerDetallesEstiramientos(resultadoActividad);
			case 7: return leerDetallesOtraActividad(resultadoActividad);
			default: return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Caminata leerDetallesCaminata(ResultSet resultadoActividad) {
		try {
			logger.debug("leyendo caminata con id {}", resultadoActividad.getInt("actividad_id"));
			String query = "select * from detalles_caminata where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, resultadoActividad.getInt("actividad_id"));
			ResultSet resultadoDetalleActividad = statement.executeQuery();
			if (resultadoDetalleActividad.next()) {
				Caminata caminata = (Caminata) new Caminata.CaminataBuilder()
						.setDistancia(resultadoDetalleActividad.getFloat("distancia"))
						.setId(resultadoActividad.getInt("actividad_id"))
						.setUserId(resultadoActividad.getInt("user_id"))
						.setFechaHora(resultadoActividad.getTimestamp("fecha_hora"))
						.setDuracion(resultadoActividad.getTime("duracion"))
						.setUbicacion(resultadoActividad.getString("ubicacion"))
						.build();				
				logger.debug("leido con exito ", caminata);
				return caminata;
			}
			logger.error("no se a podido leer caminata con id {}", resultadoActividad.getInt("actividad_id"));
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Ciclismo leerDetallesCiclismo(ResultSet resultadoActividad) {
		try {
			String query = "select * from detalles_ciclismo where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, resultadoActividad.getInt("actividad_id"));
			ResultSet resultadoDetalleActividad = statement.executeQuery();
			if (resultadoDetalleActividad.next())
				return (Ciclismo) new Ciclismo.CiclismoBuilder()
						.setDistancia(resultadoDetalleActividad.getFloat("distancia"))
						.setTipoBicicleta(resultadoDetalleActividad.getString("tipo_bicicleta"))
						.setId(resultadoActividad.getInt("actividad_id"))
						.setUserId(resultadoActividad.getInt("user_id"))
						.setFechaHora(resultadoActividad.getTimestamp("fecha_hora"))
						.setDuracion(resultadoActividad.getTime("duracion"))
						.setUbicacion(resultadoActividad.getString("ubicacion"))
						.build();
			
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Natacion leerDetallesNatacion(ResultSet resultadoActividad) {
		try {
			String query = "select * from detalles_natacion where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, resultadoActividad.getInt("actividad_id"));
			ResultSet resultado = statement.executeQuery();
			if (resultado.next())
				return (Natacion) new Natacion.NatacionBuilder()
						.setDistancia(resultado.getFloat("distancia"))
						.setEstilosNatacion(resultado.getString("estilos_natacion"))
						.setId(resultadoActividad.getInt("actividad_id"))
						.setUserId(resultadoActividad.getInt("user_id"))
						.setFechaHora(resultadoActividad.getTimestamp("fecha_hora"))
						.setDuracion(resultadoActividad.getTime("duracion"))
						.setUbicacion(resultadoActividad.getString("ubicacion"))
						.build();
						
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private DeporteEquipo leerDetallesDeporteEquipo(ResultSet resultadoActividad) {
		try {
			String query = "select * from detalles_deporte_equipo where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, resultadoActividad.getInt("actividad_id"));
			ResultSet resultado = statement.executeQuery();
			if (resultado.next())
				return (DeporteEquipo) new DeporteEquipo.DeporteEquipoBuilder()
						.setNombreDeporte(resultado.getString("nombre_deporte"))
						.setNombreEquipos(resultado.getString("nombre_equipo"))
						.setResultadoDelPartido(resultado.getString("resultado_partido"))
						.setId(resultadoActividad.getInt("actividad_id"))
						.setUserId(resultadoActividad.getInt("user_id"))
						.setFechaHora(resultadoActividad.getTimestamp("fecha_hora"))
						.setDuracion(resultadoActividad.getTime("duracion"))
						.setUbicacion(resultadoActividad.getString("ubicacion"))
						.build();
						
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private EntrenamientoGimnasio leerDetallesEntrenamientoGimnasio(ResultSet resultadoActividad) {
		try {
			String query = "select * from detalles_entrenamiento where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, resultadoActividad.getInt("actividad_id"));
			ResultSet resultado = statement.executeQuery();
			if (resultado.next())
				return (EntrenamientoGimnasio) new EntrenamientoGimnasio.EntrenamientoGimnasioBuilder()
						.setEjerciciosRealizados(resultado.getString("ejercicios_realizados"))
						.setDescansoEntreEjercicios(resultado.getString("descanso_entre_ejercicios"))
						.setDescansoEntreSeries(resultado.getString("descanso_entre_series"))
						.setId(resultadoActividad.getInt("actividad_id"))
						.setUserId(resultadoActividad.getInt("user_id"))
						.setFechaHora(resultadoActividad.getTimestamp("fecha_hora"))
						.setDuracion(resultadoActividad.getTime("duracion"))
						.setUbicacion(resultadoActividad.getString("ubicacion"))
						.build();
						
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Estiramientos leerDetallesEstiramientos(ResultSet resultadoActividad) {
		try {
			String query = "select * from detalles_yoga_estiramientos where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, resultadoActividad.getInt("actividad_id"));
			ResultSet resultado = statement.executeQuery();
			if (resultado.next())
				return (Estiramientos) new Estiramientos.EstiramientosBuilder()
						.setTipoSesion(resultado.getString("tipo_sesion"))
						.setNivelDificultad(resultado.getString("nivel_dificultad"))
						.setId(resultadoActividad.getInt("actividad_id"))
						.setUserId(resultadoActividad.getInt("user_id"))
						.setFechaHora(resultadoActividad.getTimestamp("fecha_hora"))
						.setDuracion(resultadoActividad.getTime("duracion"))
						.setUbicacion(resultadoActividad.getString("ubicacion"))
						.build();
						
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private OtraActividad leerDetallesOtraActividad(ResultSet resultadoActividad) {
		try {
			String query = "select * from detalles_otra_actividad where actividad_id = ?;";
			PreparedStatement statement = this.conexion.prepareStatement(query);
			statement.setInt(1, resultadoActividad.getInt("actividad_id"));
			ResultSet resultado = statement.executeQuery();
			if (resultado.next())
				return (OtraActividad) new OtraActividad.OtraActividadBuilder()
						.setDescripcion(resultado.getString("descripcion"))
						.setId(resultadoActividad.getInt("actividad_id"))
						.setUserId(resultadoActividad.getInt("user_id"))
						.setFechaHora(resultadoActividad.getTimestamp("fecha_hora"))
						.setDuracion(resultadoActividad.getTime("duracion"))
						.setUbicacion(resultadoActividad.getString("ubicacion"))
						.build();
						
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private boolean actualizarDetalleActividad(Actividad actividad) {
		 Class<? extends Actividad> clase = actividad.getClass();
		    switch (clase.getSimpleName()) {
		        case "Caminata":
		            return actualizarCaminata((Caminata) actividad);
		        case "Ciclismo":
		            return actualizarCiclismo((Ciclismo) actividad);
		        case "Natacion":
		            return actualizarNatacion((Natacion) actividad);
		        case "DeporteEquipo":
		        	return actualizarDeporteEquipo((DeporteEquipo) actividad);
		        case "EntrenamientoGimnasio":
		        	return actualizarEntrenamientoGimnasio((EntrenamientoGimnasio) actividad);
		        case "Estiramientos":
		        	return actualizarEstiramientos((Estiramientos) actividad);
		        case "OtraActividad":
		        	return actualizarOtraActividad((OtraActividad) actividad);
		        default:
		            return false;
		    }
	}
	
	private boolean actualizarCaminata(Caminata caminata) {
		logger.debug("actualizando caminata");
		try {
			String query = "update detalles_caminata set distancia = ? where actividad_id = ?;";
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
}
