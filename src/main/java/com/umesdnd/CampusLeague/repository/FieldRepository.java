package com.umesdnd.CampusLeague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.umesdnd.CampusLeague.model.Field;

import java.util.Optional;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long>{
    Optional<Object> findByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}
