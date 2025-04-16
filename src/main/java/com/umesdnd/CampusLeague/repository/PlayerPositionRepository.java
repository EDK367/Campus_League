package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.PlayerPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerPositionRepository extends JpaRepository<PlayerPosition, Long> {
}
