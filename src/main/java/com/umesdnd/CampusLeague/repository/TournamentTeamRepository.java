package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.TournamentTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentTeamRepository extends JpaRepository<TournamentTeam, Long>{
    TournamentTeam findByTeamIdAndTournamentId(Long teamId, Long tournamentId);
    TournamentTeam findByTeamIdAndTournamentIdAndGroupId(Long teamId, Long tournamentId, Long groupId);
}
