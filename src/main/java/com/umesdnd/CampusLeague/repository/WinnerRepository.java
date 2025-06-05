package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WinnerRepository extends JpaRepository<Winner, Long> {
    boolean existsByTournamentIdAndTeamIdAndPositionNot(Long tournamentId, Long teamId, Integer position);

    @Query("SELECT COUNT(w) > 0 FROM Winner w WHERE w.tournament.id = :tournamentId AND w.position = :position AND w.team.id <> :teamId")
    boolean existsByTournamentIdAndPositionAndTeamIdIsNot(Long tournamentId, int position, Long teamId);

}
