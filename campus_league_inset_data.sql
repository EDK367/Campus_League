-- Insertar datos en coaches (ya está correcto)
INSERT INTO coaches (experience_years, name) VALUES
(5, 'Carlos Pérez'),
(10, 'Ana Martínez'),
(3, 'Luis Gómez'),
(7, 'Fernanda Díaz'),
(12, 'Jorge Ramírez'),
(9, 'Valeria López'),
(6, 'Andrés Torres');

-- Insertar tipos de sanción (ya está correcto)
INSERT INTO sanction_types (type_name) VALUES
('Falta'),
('Tarjeta Roja'),
('Tarjeta Amarilla'),
('Fuera de Lugar'),
('Conducta Antideportiva'),
('Llegada Tardía'),
('Juego Agresivo');

-- Insertar deportes (ya está correcto)
INSERT INTO sports (name) VALUES
('Soccer'),
('Basketball'),
('Volleyball'),
('Tennis'),
('Baseball'),
('Rugby'),
('Hockey');

-- Insertar posiciones de jugadores (corregido para usar sport_id existentes)
INSERT INTO player_positions (description, name, sport_id) VALUES
('Media Alta', 'Media Alta', 1),
('Media Baja', 'Media Baja', 1),
('Defensa Central', 'Defensa Central', 1),
('Delantero', 'Delantero', 1),
('Portero', 'Portero', 1),
('Base', 'Base', 2),
('Alero', 'Alero', 2);

-- Insertar estados (ya está correcto)
INSERT INTO statuses (id, status_name) VALUES
(1, 'Activo'),
(2, 'En Revision'),
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

-- Insertar campos (ya está correcto)
INSERT INTO fields (capacity, location, name, status_id) VALUES
(500, 'Ciudad A', 'Campo Norte', 1),
(750, 'Ciudad B', 'Estadio Sur', 2),
(300, 'Ciudad C', 'Cancha Este', 3),
(600, 'Ciudad D', 'Campo Oeste', 4),
(450, 'Ciudad E', 'Miniestadio Uno', 5),
(800, 'Ciudad F', 'Estadio Central', 6),
(400, 'Ciudad G', 'Campo Alterno', 7);

-- Insertar árbitros (ya está correcto)
INSERT INTO referees (experience_years, name, status_id) VALUES
(8, 'Mario Castillo', 1),
(5, 'Lucía Hernández', 2),
(12, 'Raúl Jiménez', 3),
(10, 'Patricia Vega', 4),
(7, 'Camilo Ríos', 5),
(15, 'Laura Moreno', 6),
(9, 'Eduardo Soto', 7);

-- Insertar grupos de torneo (ya está correcto)
INSERT INTO tournament_groups (name) VALUES
('Grupo A'),
('Grupo B'),
('Grupo C'),
('Grupo D'),
('Grupo E'),
('Grupo F'),
('Grupo G');

-- Insertar usuarios (corregido para usar status_id existentes)
INSERT INTO users (email, password, username, status_id) VALUES
('juan@example.com', '$2a$10$qUhJ49kWObZmZMrwyLkLKO51NSABu9zuQoqyNlJWcFmnwpIZDNzMO', 'edk', 1),
('ana@example.com', '$2a$10$qUhJ49kWObZmZMrwyLkLKO51NSABu9zuQoqyNlJWcFmnwpIZDNzMO', 'admin', 1),
('pedro@example.com', '$2a$10$qUhJ49kWObZmZMrwyLkLKO51NSABu9zuQoqyNlJWcFmnwpIZDNzMO', 'pedrop', 3),
('luisa@example.com', '$2a$10$qUhJ49kWObZmZMrwyLkLKO51NSABu9zuQoqyNlJWcFmnwpIZDNzMO', 'luisa_l', 4),
('carlos@example.com', '$2a$10$qUhJ49kWObZmZMrwyLkLKO51NSABu9zuQoqyNlJWcFmnwpIZDNzMO', 'carloz', 5),
('maria@example.com', '$2a$10$qUhJ49kWObZmZMrwyLkLKO51NSABu9zuQoqyNlJWcFmnwpIZDNzMO', 'maria_g', 1),
('roberto@example.com', '$2a$10$qUhJ49kWObZmZMrwyLkLKO51NSABu9zuQoqyNlJWcFmnwpIZDNzMO', 'roberto_f', 1);

-- Insertar torneos (corregido para usar sport_id y status_id existentes)
INSERT INTO tournaments (
    description,
    end_date,
    inscriptions_close_date,
    inscriptions_open_date,
    max_team_members,
    min_team_members,
    start_date,
    name,
    sport_id,
    status_id,
    creator_user_id
) VALUES
      ('Torneo de verano', '2025-07-20', '2025-06-25', '2025-05-01', 11, 7, '2025-06-01', 'Verano 2025', 1, 1, 1),
      ('Copa Municipal', '2025-09-10', '2025-07-20', '2025-05-01', 10, 6, '2025-08-01', 'Municipal 2025', 2, 2, 2),
      ('Liga Escolar', '2025-10-15', '2025-08-10', '2025-05-01', 12, 8, '2025-09-01', 'Escolar 2025', 3, 3, 3),
      ('Torneo Senior', '2025-08-05', '2025-06-10', '2025-05-01', 10, 5, '2025-07-01', 'Senior League', 4, 4, 4),
      ('Juvenil Pro', '2025-12-01', '2025-09-15', '2025-09-01', 11, 7, '2025-10-01', 'Juvenil 2025', 1, 5, 5),
      ('Copa Nacional', '2026-01-20', '2025-10-25', '2025-10-01', 13, 8, '2025-11-10', 'Nacional 2025', 2, 6, 6),
      ('Desafío Regional', '2025-11-05', '2025-08-20', '2025-08-01', 10, 6, '2025-09-15', 'Regional 2025', 3, 7, 7);

-- Insertar equipos (corregido para usar IDs existentes)
INSERT INTO teams (
    name,
    coach_id,
    status_id,
    approved_by,
    approved_date,
    captain,
    contact_email,
    contact_phone,
    inscription_date,
    team_code,
    tournament_id
) VALUES
      ('Tiburones FC', 1, 1, 1, '2025-05-15', 'Capitán Tiburón', 'contacto1@equipo.com', '555-1111', '2025-05-14', 'TIBU2025', 1),
      ('Águilas Rojas', 2, 2, 2, '2025-07-10', 'Capitán Águila', 'contacto2@equipo.com', '555-2222', '2025-07-08', 'AGUI2025', 2),
      ('Leones del Sur', 3, 3, 1, '2025-08-15', 'Capitán León', 'contacto3@equipo.com', '555-3333', '2025-08-14', 'LEON2025', 3),
      ('Dragones Azules', 4, 4, 2, '2025-06-25', 'Capitán Dragón', 'contacto4@equipo.com', '555-4444', '2025-06-24', 'DRAG2025', 4),
      ('Titanes del Norte', 5, 5, 1, '2025-09-20', 'Capitán Titán', 'contacto5@equipo.com', '555-5555', '2025-09-18', 'TITA2025', 5),
      ('Fénix Juvenil', 6, 6, 2, '2025-10-20', 'Capitán Fénix', 'contacto6@equipo.com', '555-6666', '2025-10-18', 'FENI2025', 6),
      ('Gladiadores', 7, 7, 1, '2025-08-30', 'Capitán Gladiador', 'contacto7@equipo.com', '555-7777', '2025-08-29', 'GLAD2025', 7);

-- Insertar jugadores (corregido para usar position_id existentes)
INSERT INTO players (age, carnet, names, position_id, status_id) VALUES
(18, 'CAR001', 'José Ruiz', 1, 1),
(20, 'CAR002', 'Miguel Torres', 2, 2),
(19, 'CAR003', 'Sofía Mendoza', 3, 3),
(21, 'CAR004', 'Lucas Ríos', 1, 4),
(17, 'CAR005', 'Camila Vázquez', 2, 5),
(22, 'CAR006', 'Daniel Acosta', 3, 6),
(18, 'CAR007', 'Valentina Romero', 1, 7);

-- Insertar partidos (añadido match_date)
INSERT INTO matches (team1_score, team2_score, match_date, field_id, referee_id, status_id, team1_id, team2_id, tournament_id) VALUES
(2, 1, '2025-06-05 15:00:00', 1, 1, 1, 1, 2, 1),
(0, 0, '2025-08-03 16:30:00', 2, 2, 2, 3, 4, 2),
(3, 2, '2025-09-10 17:00:00', 3, 3, 3, 5, 6, 3),
(1, 4, '2025-07-15 18:00:00', 4, 4, 4, 7, 1, 4),
(2, 2, '2025-10-05 19:00:00', 5, 5, 5, 2, 3, 5),
(0, 1, '2025-11-12 20:00:00', 6, 6, 6, 4, 5, 6),
(1, 1, '2025-09-25 21:00:00', 7, 7, 7, 6, 7, 7);

-- Insertar goles (ya está correcto)
INSERT INTO goals (points, match_id, player_id) VALUES
(1, 1, 1),
(1, 1, 2),
(1, 2, 3),
(1, 3, 4),
(1, 4, 5),
(1, 5, 6),
(1, 6, 7);

-- Insertar sanciones (ya está correcto)
INSERT INTO sanctions (description, match_id, player_id, sanction_type_id, team_id) VALUES
('Foul peligrosa', 1, 1, 1, 1),
('Retraso al iniciar', 2, 2, 2, 2),
('Juego brusco', 3, 3, 3, 3),
('Conducta antideportiva', 4, 4, 4, 4),
('Empujón', 5, 5, 5, 5),
('Protesta al árbitro', 6, 6, 6, 6),
('Simulación', 7, 7, 7, 7);

-- Insertar equipos de torneo (ya está correcto)
INSERT INTO tournament_teams (points, group_id, team_id, tournament_id) VALUES
(3, 1, 1, 1),
(1, 2, 2, 2),
(0, 3, 3, 3),
(2, 4, 4, 4),
(3, 5, 5, 5),
(1, 6, 6, 6),
(2, 7, 7, 7);

-- Insertar ganadores (ya está correcto)
INSERT INTO winners (position, team_id, tournament_id) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(1, 4, 4),
(2, 5, 5),
(3, 6, 6),
(1, 7, 7);

-- Insertar relaciones equipo-jugador (nuevo)
INSERT INTO team_players (player_id, team_id) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 3),
(6, 3),
(7, 4),
(1, 5),
(2, 6),
(3, 7);