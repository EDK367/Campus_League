package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long>{
    boolean existsByTournamentId(Long tournamentId);

    Optional<Inscription> findByTournamentId(Long tournamentId);

}
