package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    //@Query("SELECT p FROM Player p WHERE p.team.id = :teamId")
    //public List<Player> getByTeam(Long teamId);

    boolean existsByCarnet(String carnet);
    List<Player> findByCarnet(String email);
}
