-- 1. Índice para búsquedas rápidas por correo en users
CREATE INDEX idx_users_email ON users(email);

SELECT id, username, email, status_id FROM users WHERE email = 'paul@umes.edu.gt';

-- 2. Índice para mejorar la búsqueda de partidos por campo y fecha
CREATE INDEX idx_matches_field_date ON matches(field_id, match_date);

SELECT id, match_date, field_id, team1_id, team2_id FROM matches WHERE field_id = 4 AND match_date = '2025-06-01 18:00:00';

-- 3. Índice para mejorar rendimiento en sanciones por jugador y partido
CREATE INDEX idx_sanctions_player_match ON sanctions(player_id, match_id);

SELECT id, player_id, match_id, sanction_type_id, description FROM sanctions WHERE player_id = 15 AND match_id = 8;

-- 4. Índice para optimizar búsqueda por nombre en players
CREATE INDEX idx_players_names ON players(names);

SELECT id, names, age, carnet FROM players WHERE names LIKE '%Juan%';

-- 5. Índice para campos y su estado
CREATE INDEX idx_fields_status ON fields(status_id);

SELECT id, name, location, status_id FROM fields WHERE status_id = 2;

-- 6. Índice para acelerar búsquedas por estado de jugador y posición
CREATE INDEX idx_players_status_position ON players(status_id, position_id);

SELECT id, names, status_id, position_id FROM players WHERE status_id = 1 AND position_id = 3;

-- 7. Índice para mejorar consultas que filtran partidos por torneo y estado
CREATE INDEX idx_matches_tournament_status ON matches(tournament_id, status_id);

SELECT id, match_date, tournament_id, status_id FROM matches WHERE tournament_id = 12 AND status_id = 1;