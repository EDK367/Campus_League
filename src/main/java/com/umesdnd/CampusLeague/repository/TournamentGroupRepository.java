package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.TournamentGroup;
import com.umesdnd.CampusLeague.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentGroupRepository extends JpaRepository<TournamentGroup, Long> {
    Optional<TournamentGroup> findByName(String username);

}
