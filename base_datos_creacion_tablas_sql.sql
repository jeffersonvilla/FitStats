CREATE DATABASE `fitstats` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

use fitstats;

CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sesion` (
  `idSesion` int NOT NULL AUTO_INCREMENT,
  `fecha_inicio` datetime NOT NULL,
  `fecha_fin` datetime DEFAULT NULL,
  `idUsuario` int NOT NULL,
  PRIMARY KEY (`idSesion`),
  KEY `fk_idusuario` (`idUsuario`),
  CONSTRAINT `fk_idusuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tipo_actividad` (
  `tipo_actividad_id` int NOT NULL AUTO_INCREMENT,
  `nombre_actividad` varchar(50) NOT NULL,
  PRIMARY KEY (`tipo_actividad_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `actividad` (
  `actividad_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `tipo_actividad_id` int NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `duracion` time DEFAULT NULL,
  `ubicacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`actividad_id`),
  KEY `fk_registros_usuarios` (`user_id`),
  KEY `fk_registros_actividades` (`tipo_actividad_id`),
  CONSTRAINT `fk_registros_actividades` FOREIGN KEY (`tipo_actividad_id`) REFERENCES `tipo_actividad` (`tipo_actividad_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_registros_usuarios` FOREIGN KEY (`user_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `detalles_caminata_carrera` (
  `actividad_id` int NOT NULL,
  `distancia` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`actividad_id`),
  CONSTRAINT `fk_detalles_caminata_registros` FOREIGN KEY (`actividad_id`) REFERENCES `actividad` (`actividad_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `detalles_ciclismo` (
  `actividad_id` int NOT NULL,
  `distancia` decimal(10,2) DEFAULT NULL,
  `tipo_bicicleta` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`actividad_id`),
  CONSTRAINT `fk_detalles_ciclismo_registros` FOREIGN KEY (`actividad_id`) REFERENCES `actividad` (`actividad_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `detalles_natacion` (
  `actividad_id` int NOT NULL,
  `distancia` decimal(10,2) DEFAULT NULL,
  `estilos_natacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`actividad_id`),
  CONSTRAINT `fk_detalles_natacion_registros` FOREIGN KEY (`actividad_id`) REFERENCES `actividad` (`actividad_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `detalles_deporte_equipo` (
  `actividad_id` int NOT NULL,
  `nombre_equipo` varchar(20) DEFAULT NULL,
  `resultado_partido` varchar(20) DEFAULT NULL,
  `nombre_deporte` varchar(20) NOT NULL,
  PRIMARY KEY (`actividad_id`),
  CONSTRAINT `fk_detalles_deporte_equipo_registros` FOREIGN KEY (`actividad_id`) REFERENCES `actividad` (`actividad_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `detalles_entrenamiento` (
  `actividad_id` int NOT NULL,
  `ejercicios_realizados` text,
  `descanso_entre_ejercicios` varchar(10) DEFAULT NULL,
  `descanso_entre_series` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`actividad_id`),
  CONSTRAINT `fk_detalles_entrenamiento_registros` FOREIGN KEY (`actividad_id`) REFERENCES `actividad` (`actividad_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
