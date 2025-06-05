package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.Referee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefereeRepository extends JpaRepository<Referee, Long>{
}
