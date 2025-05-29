package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.Sanction;
import com.umesdnd.CampusLeague.repository.SanctionRepository;
import com.umesdnd.CampusLeague.service.interfaces.SanctionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanctionService implements SanctionServiceInterface {

    @Autowired
    private SanctionRepository sanctionRepository;

    @Override
    public Sanction getById(Long id) {
        return sanctionRepository.findById(id).orElseThrow(() -> new NewExceptionType("Sancion no encontrada", HttpStatus.NOT_FOUND));
    }

    @Override
    public Sanction saveOne(Sanction sanction) {
        return null;
    }

    @Override
    public Sanction update(Long id, Sanction sanction) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Sanction> getAll() {
        return sanctionRepository.findAll();
    }
}
