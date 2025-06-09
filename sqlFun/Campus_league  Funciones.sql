DELIMITER //

-- 1. Función que calcula la edad promedio de los jugadores de un equipo dado su ID
CREATE FUNCTION fn_avg_team_age(p_team_id BIGINT) RETURNS DECIMAL(5,2)
DETERMINISTIC
READS SQL DATA
BEGIN
  DECLARE avg_age DECIMAL(5,2);
  SELECT AVG(p.age) INTO avg_age
  FROM players p
  JOIN team_players tp ON p.id = tp.player_id
  WHERE tp.team_id = p_team_id;
  RETURN IFNULL(avg_age, 0);
END;
//

SELECT fn_avg_team_age(16) AS promedio_edad;

-- 2. Función que retorna el nombre completo del equipo combinando nombre y código
CREATE FUNCTION fn_team_full_name(p_team_id BIGINT) RETURNS VARCHAR(511)
DETERMINISTIC
READS SQL DATA
BEGIN
  DECLARE full_name VARCHAR(511);
  SELECT CONCAT(name, ' (', team_code, ')') INTO full_name
  FROM teams WHERE id = p_team_id;
  RETURN IFNULL(full_name, 'Equipo no encontrado');
END;
//

SELECT fn_team_full_name(16) AS nombre_completo;

-- 3. Función que verifica si un jugador está activo (status_id = 1)
CREATE FUNCTION fn_is_player_active(p_player_id BIGINT) RETURNS BOOLEAN
DETERMINISTIC
READS SQL DATA
BEGIN
  DECLARE active BOOL;
  SELECT CASE WHEN status_id = 1 THEN TRUE ELSE FALSE END INTO active
  FROM players WHERE id = p_player_id;
  RETURN IFNULL(active, FALSE);
END;
//

SELECT fn_is_player_active(10) AS esta_activo;

-- 4. Función que cuenta cuántas sanciones tiene un jugador dado su ID
CREATE FUNCTION fn_count_player_sanctions(p_player_id BIGINT) RETURNS INT
DETERMINISTIC
READS SQL DATA
BEGIN
  DECLARE sanction_count INT;
  SELECT COUNT(*) INTO sanction_count
  FROM sanctions
  WHERE player_id = p_player_id;
  RETURN sanction_count;
END;
//

SELECT fn_count_player_sanctions(7) AS total_sanciones;

DELIMITER ;