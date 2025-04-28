package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.Tournaments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentsRepository extends JpaRepository<Tournaments, Long>{
}
