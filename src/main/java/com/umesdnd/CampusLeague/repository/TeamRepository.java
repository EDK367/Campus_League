package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
