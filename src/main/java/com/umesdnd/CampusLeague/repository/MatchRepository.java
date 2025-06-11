package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.Field;
import com.umesdnd.CampusLeague.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{
    boolean existsByFieldIdAndMatchDate(Long fieldId, LocalDateTime matchDate);

}
