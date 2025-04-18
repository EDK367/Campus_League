package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.PlayerPosition;
import com.umesdnd.CampusLeague.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface PlayerPositionRepository extends JpaRepository<PlayerPosition, Long> {
}
