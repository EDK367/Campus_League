package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.TeamPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamPlayerRepository extends JpaRepository<TeamPlayer, Long> {
    List<TeamPlayer> findByTeamId(Long id);
    boolean existsByTeamIdAndPlayerId(Long teamId, Long playerId);

}
