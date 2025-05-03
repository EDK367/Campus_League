package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.Sanction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanctionRepository extends JpaRepository<Sanction, Long>{
}
