package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long>{
}
