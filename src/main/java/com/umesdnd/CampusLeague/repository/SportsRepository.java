package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.Sports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportsRepository extends JpaRepository<Sports, Long>{
}
