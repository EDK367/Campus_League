package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.Status;
import com.umesdnd.CampusLeague.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long>{
    //Optional<Status> findById(Long id);
}
