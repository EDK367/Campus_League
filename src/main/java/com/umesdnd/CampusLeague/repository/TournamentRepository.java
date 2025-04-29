package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long>{
}
