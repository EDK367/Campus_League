-- Insert into statuses
INSERT INTO statuses (id, status_name) VALUES 
(1, 'Activo'),
(2, 'Desactivado'),
(3, 'Autorizado'),
(4, 'Disponible'),
(5, 'Aceptado'),
(6, 'Rechazado'),
(7, 'Eliminado'),
(8, 'Aprobado'),
(9, 'Abierto'),
(10, 'Pendiente'),
(11, 'Reabierto'),
(12, 'Descartado'),
(13, 'En proceso'),
(14, 'Publicado'),
(15, 'Habilitado'),
(16, 'Iniciado'),
(17, 'Asignado'),
(18, 'Fializado'),
(19, 'Cancelado'),
(20, 'Suspendido'),
(21, 'Descalificado'),
(22, 'anulado'),
(23, 'Postergado'),
(24, 'Interrumpido');

-- Insert into sports
INSERT INTO sports (id, name) VALUES
(1, 'Fútbol'),
(2, 'Béisbol'),
(3, 'Básquetbol'),
(4, 'Voleibol'),
(5, 'Boxeo'),
(6, 'Atletismo'),
(7, 'Ciclismo'),
(8, 'Natación'),
(9, 'Tenis'),
(10, 'Judo'),
(11, 'Karate'),
(12, 'Taekwondo'),
(13, 'Levantamiento de pesas'),
(14, 'Lucha libre'),
(15, 'Rugby'),
(16, 'Hockey sobre césped'),
(17, 'Golf'),
(18, 'Escalada deportiva'),
(19, 'Surf'),
(20, 'Esgrima');

-- Insert into player_positions
INSERT INTO player_positions (id, name, description, sport_id) VALUES
-- Fútbol
(1, 'Portero', 'Portero en Fútbol', 1),
(2, 'Defensa', 'Defensa en Fútbol', 1),
(3, 'Mediocampista', 'Mediocampista en Fútbol', 1),
(4, 'Delantero', 'Delantero en Fútbol', 1),
-- Béisbol
(5, 'Lanzador', 'Lanzador en Béisbol', 2),
(6, 'Receptor', 'Receptor en Béisbol', 2),
(7, 'Primera base', 'Primera base en Béisbol', 2),
(8, 'Jardinero', 'Jardinero en Béisbol', 2),
-- Básquetbol
(9, 'Base', 'Base en Básquetbol', 3),
(10, 'Escolta', 'Escolta en Básquetbol', 3),
(11, 'Alero', 'Alero en Básquetbol', 3),
(12, 'Pívot', 'Pívot en Básquetbol', 3),
-- Voleibol
(13, 'Armador', 'Armador en Voleibol', 4),
(14, 'Receptor', 'Receptor en Voleibol', 4),
(15, 'Central', 'Central en Voleibol', 4),
(16, 'Líbero', 'Líbero en Voleibol', 4),
-- Boxeo
(17, 'Peso pluma', 'Peso pluma en Boxeo', 5),
(18, 'Peso ligero', 'Peso ligero en Boxeo', 5),
(19, 'Peso mediano', 'Peso mediano en Boxeo', 5),
(20, 'Peso pesado', 'Peso pesado en Boxeo', 5),
-- Atletismo
(21, 'Velocista', 'Velocista en Atletismo', 6),
(22, 'Fondista', 'Fondista en Atletismo', 6),
(23, 'Saltador', 'Saltador en Atletismo', 6),
(24, 'Lanzador', 'Lanzador en Atletismo', 6),
-- Ciclismo
(25, 'Escalador', 'Escalador en Ciclismo', 7),
(26, 'Velocista', 'Velocista en Ciclismo', 7),
(27, 'Contrarrelojista', 'Contrarrelojista en Ciclismo', 7),
-- Natación
(28, 'Estilo libre', 'Estilo libre en Natación', 8),
(29, 'Espalda', 'Espalda en Natación', 8),
(30, 'Pecho', 'Pecho en Natación', 8),
(31, 'Mariposa', 'Mariposa en Natación', 8),
-- Tenis
(32, 'Singles', 'Singles en Tenis', 9),
(33, 'Dobles', 'Dobles en Tenis', 9),
-- Judo
(34, 'Ligeros', 'Ligeros en Judo', 10),
(35, 'Medios', 'Medios en Judo', 10),
(36, 'Pesados', 'Pesados en Judo', 10),
-- Karate
(37, 'Kata', 'Kata en Karate', 11),
(38, 'Kumite', 'Kumite en Karate', 11),
-- Taekwondo
(39, 'Peso mosca', 'Peso mosca en Taekwondo', 12),
(40, 'Peso gallo', 'Peso gallo en Taekwondo', 12),
(41, 'Peso pluma', 'Peso pluma en Taekwondo', 12),
-- Levantamiento de pesas
(42, 'Ligeros', 'Ligeros en Levantamiento de pesas', 13),
(43, 'Medios', 'Medios en Levantamiento de pesas', 13),
(44, 'Pesados', 'Pesados en Levantamiento de pesas', 13),
-- Lucha libre
(45, 'Categoría ligera', 'Categoría ligera en Lucha libre', 14),
(46, 'Categoría media', 'Categoría media en Lucha libre', 14),
(47, 'Categoría pesada', 'Categoría pesada en Lucha libre', 14),
-- Rugby
(48, 'Pilar', 'Pilar en Rugby', 15),
(49, 'Segunda línea', 'Segunda línea en Rugby', 15),
(50, 'Ala', 'Ala en Rugby', 15),
(51, 'Zaguero', 'Zaguero en Rugby', 15),
-- Hockey sobre césped
(52, 'Portero', 'Portero en Hockey', 16),
(53, 'Defensa', 'Defensa en Hockey', 16),
(54, 'Mediocampo', 'Mediocampo en Hockey', 16),
(55, 'Delantero', 'Delantero en Hockey', 16),
-- Golf
(56, 'Jugador amateur', 'Jugador amateur en Golf', 17),
(57, 'Jugador profesional', 'Jugador profesional en Golf', 17),
-- Escalada deportiva
(58, 'Velocidad', 'Velocidad en Escalada', 18),
(59, 'Dificultad', 'Dificultad en Escalada', 18),
(60, 'Bloque', 'Bloque en Escalada', 18),
-- Surf
(61, 'Shortboard', 'Shortboard en Surf', 19),
(62, 'Longboard', 'Longboard en Surf', 19),
-- Esgrima
(63, 'Florete', 'Florete en Esgrima', 20),
(64, 'Espada', 'Espada en Esgrima', 20),
(65, 'Sable', 'Sable en Esgrima', 20);

-- DEFAUL USER admin : admin
INSERT INTO users (id, email, password, username, status_id) 
VALUES (1, 'admin@example.com', '$2a$10$qUhJ49kWObZmZMrwyLkLKO51NSABu9zuQoqyNlJWcFmnwpIZDNzMO', 'admin', 1);

-- Insert example tournament
INSERT INTO tournaments (
    id, name, description, start_date, end_date, inscriptions_open_date, inscriptions_close_date,
    min_team_members, max_team_members, sport_id, status_id, creator_user_id
) VALUES 
-- Tournament 1: Fútbol
(1, 'Torneo de Fútbol Interuniversitario', 'Torneo anual de fútbol para universidades',
 '2025-06-01 10:00:00', '2025-07-01 18:00:00',
 '2025-05-01 08:00:00', '2025-05-20 23:59:00',
 7, 11, 1, 9, 1),

-- Tournament 2: Básquetbol
(2, 'Copa Nacional de Básquetbol Sub-21', 'Competencia nacional de equipos juveniles',
 '2025-07-15 09:00:00', '2025-08-10 20:00:00',
 '2025-06-01 00:00:00', '2025-07-01 23:59:00',
 5, 12, 3, 9, 1),

-- Tournament 3: Voleibol Playa Mixto
(3, 'Torneo de Voleibol Playa Mixto', 'Competencia mixta de voleibol en la playa',
 '2025-08-05 08:00:00', '2025-08-15 18:00:00',
 '2025-07-01 00:00:00', '2025-07-25 23:59:00',
 2, 4, 4, 9, 1);
