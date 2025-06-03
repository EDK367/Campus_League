package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    boolean existsByName(String name);
    boolean existsByTeamCode(String code);
    Optional<Team> findByTeamCode(String code);
    List<Team> findByTournamentId(Long tournamentId);
    List<Team> findTeamByStatusIdIn(List<Long> statusIds);
}
