DELIMITER //
-- 1. Registrar automáticamente la fecha de inscripción de un equipo si no se proporciona
CREATE TRIGGER trg_set_inscription_date
BEFORE INSERT ON teams
FOR EACH ROW
BEGIN
  IF NEW.inscription_date IS NULL THEN
    SET NEW.inscription_date = CURRENT_TIMESTAMP();
  END IF;
END;
//

INSERT INTO teams (name, status_id, captain, contact_email, contact_phone, team_code, coach_id, tournament_id)
VALUES ('Los Invencibles', 1, 'Carlos López', 'carlos@email.com', '555-1234', 'INV123', 9, 12);


-- 2. Evitar insertar jugadores duplicados en un equipo
CREATE TRIGGER trg_no_duplicate_team_players
BEFORE INSERT ON team_players
FOR EACH ROW
BEGIN
  IF EXISTS (
    SELECT 1 FROM team_players
    WHERE player_id = NEW.player_id AND team_id = NEW.team_id
  ) THEN
    SIGNAL SQLSTATE '45000' 
    SET MESSAGE_TEXT = 'Player already exists in this team';
  END IF;
END;
//

INSERT INTO team_players (player_id, team_id, player_position_id)
VALUES (10, 16, 2);

INSERT INTO team_players (player_id, team_id, player_position_id)
VALUES (10, 16, 3);

-- 3. Validar que un jugador no tenga más de 1 sanción en un mismo partido
CREATE TRIGGER trg_one_sanction_per_match
BEFORE INSERT ON sanctions
FOR EACH ROW
BEGIN
  IF EXISTS (
    SELECT 1 FROM sanctions
    WHERE match_id = NEW.match_id AND player_id = NEW.player_id
  ) THEN
    SIGNAL SQLSTATE '45000' 
    SET MESSAGE_TEXT = 'Player already sanctioned in this match';
  END IF;
END;
//

INSERT INTO sanctions (description, sanction_date, match_id, player_id, sanction_type_id, team_id)
VALUES ('Tarjeta amarilla', NOW(), 8, 15, 1, 16);

INSERT INTO sanctions (description, sanction_date, match_id, player_id, sanction_type_id, team_id)
VALUES ('Tarjeta roja', NOW(), 8, 15, 2, 16);

-- 4. Actualizar estado de campo a "En proceso" si se asigna un partido
CREATE TRIGGER trg_update_field_status
AFTER INSERT ON matches
FOR EACH ROW
BEGIN
  UPDATE fields 
  SET status_id = (SELECT id FROM statuses WHERE status_name = 'En proceso' LIMIT 1)
  WHERE id = NEW.field_id;
END;
//

INSERT INTO matches (match_date, team1_score, team2_score, field_id, referee_id, status_id, team1_id, team2_id, tournament_id)
VALUES ('2025-06-01 18:00:00', 0, 0, 6, 3, 1, 10, 12, 12);

-- 5. Evitar que se asignen dos partidos al mismo campo a la misma hora
CREATE TRIGGER trg_unique_match_field_time
BEFORE INSERT ON matches
FOR EACH ROW
BEGIN
  IF EXISTS (
    SELECT 1 FROM matches
    WHERE field_id = NEW.field_id AND match_date = NEW.match_date
  ) THEN
    SIGNAL SQLSTATE '45000' 
    SET MESSAGE_TEXT = 'Field already in use at this time';
  END IF;
END;
//

INSERT INTO matches (match_date, team1_score, team2_score, field_id, referee_id, status_id, team1_id, team2_id, tournament_id)
VALUES ('2025-06-01 18:00:00', 0, 0, 4, 3, 1, 10, 12, 2);

-- 6. Actualizar la fecha de última modificación del equipo cuando se cambia algún dato
DELIMITER //
CREATE TRIGGER trg_update_team_modified_date
BEFORE UPDATE ON teams
FOR EACH ROW
BEGIN
  SET NEW.approved_date = CURRENT_TIMESTAMP();
END;
//

UPDATE teams SET captain = 'Capitán Águila' WHERE id = 9;

-- 7. Registrar automáticamente la fecha de sanción al insertar un nuevo registro en sanciones
DELIMITER //
CREATE TRIGGER trg_set_sanction_date
BEFORE INSERT ON sanctions
FOR EACH ROW
BEGIN
  IF NEW.sanction_date IS NULL THEN
    SET NEW.sanction_date = CURRENT_TIMESTAMP();
  END IF;
END;
//

INSERT INTO sanctions (player_id, match_id, sanction_type_id, description, team_id) 
VALUES (12, 19, 2, 'Tarjeta amarilla por falta', 16);

DELIMITER ;