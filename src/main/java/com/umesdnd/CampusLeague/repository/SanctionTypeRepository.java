package com.umesdnd.CampusLeague.repository;

import com.umesdnd.CampusLeague.model.SanctionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SanctionTypeRepository extends JpaRepository<SanctionType, Long>{
    @Query("SELECT COUNT(s) > 0 FROM SanctionType s WHERE s.type_name = :typeName")
    boolean existsByTypeName(@Param("typeName") String typeName);
}
