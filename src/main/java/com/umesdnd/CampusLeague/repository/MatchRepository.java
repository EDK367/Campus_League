package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{
}
