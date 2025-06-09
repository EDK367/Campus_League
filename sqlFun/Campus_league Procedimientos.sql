-- 1. Registrar nuevo jugador
DELIMITER //
CREATE PROCEDURE sp_register_player(
  IN p_names VARCHAR(255), IN p_carnet VARCHAR(255), IN p_age INT, IN p_position_id BIGINT, IN p_status_id BIGINT
)
BEGIN
  INSERT INTO players (names, carnet, age, position_id, status_id)
  VALUES (p_names, p_carnet, p_age, p_position_id, p_status_id);
END;
//
DELIMITER ;

CALL sp_register_player('Juan Pérez', '12345678', 24, 1, 1);

-- 2. Cambiar estado de un equipo
DELIMITER //
CREATE PROCEDURE sp_update_team_status(IN p_team_id BIGINT, IN p_status_id BIGINT)
BEGIN
  UPDATE teams SET status_id = p_status_id WHERE id = p_team_id;
END;
//
DELIMITER ;

CALL sp_update_team_status(3, 2);

-- 3. Obtener jugadores de un equipo
DELIMITER //
CREATE PROCEDURE sp_get_team_players(IN p_team_id BIGINT)
BEGIN
  SELECT p.id, p.names, p.age, p.carnet
  FROM players p
  JOIN team_players tp ON p.id = tp.player_id
WHERE tp.team_id = p_team_id;
END;
//
DELIMITER ;

CALL sp_get_team_players(16);

-- 4. Registrar gol
DELIMITER //
CREATE PROCEDURE sp_add_goal(IN p_match_id BIGINT, IN p_player_id BIGINT, IN p_points BIGINT)
BEGIN
  INSERT INTO goals (match_id, player_id, points) VALUES (p_match_id, p_player_id, p_points);
END;
//
DELIMITER ;

CALL sp_add_goal(8, 5, 1);

-- 5. Registrar sanción
DELIMITER //

CREATE PROCEDURE sp_add_sanction (
    IN p_player_id BIGINT,
    IN p_match_id BIGINT,
    IN p_sanction_type_id BIGINT,
    IN p_description VARCHAR(255),
    IN p_team_id BIGINT
)
BEGIN
    INSERT INTO sanctions (player_id, match_id, sanction_type_id, description, team_id)
    VALUES (p_player_id, p_match_id, p_sanction_type_id, p_description, p_team_id);
END//

DELIMITER ;

CALL sp_add_sanction(4, 9, 1, 'Falta agresiva', 16);

-- 6. Actualizar el resultado de un partido dado su ID
DELIMITER //
CREATE PROCEDURE sp_update_match_score(
  IN p_match_id BIGINT,
  IN p_team1_score INT,
  IN p_team2_score INT
)
BEGIN
  UPDATE matches
  SET team1_score = p_team1_score, team2_score = p_team2_score
  WHERE id = p_match_id;
END;
//
DELIMITER ;

CALL sp_update_match_score(19, 3, 2);

-- 7. Cambiar el estado de un jugador dado su ID
DELIMITER //
CREATE PROCEDURE sp_update_player_status(
  IN p_player_id BIGINT,
  IN p_status_id BIGINT
)
BEGIN
  UPDATE players
  SET status_id = p_status_id
  WHERE id = p_player_id;
END;
//
DELIMITER ;

CALL sp_update_player_status(24, 1);