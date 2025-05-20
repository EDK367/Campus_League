package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long>{
    boolean existsByTournamentId(Long tournamentId);
}
