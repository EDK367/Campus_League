package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
}
