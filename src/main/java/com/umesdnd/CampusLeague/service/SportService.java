package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.PlayerPosition;
import com.umesdnd.CampusLeague.model.Sport;
import com.umesdnd.CampusLeague.repository.SportRepository;
import com.umesdnd.CampusLeague.service.interfaces.SportServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportService implements SportServiceInterface {

    @Autowired
    private SportRepository sportRepository;

    @Override
    public Sport getById(Long id) {
        return sportRepository.findById(id).orElseThrow(() -> new NewExceptionType("Sport not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Sport saveOne(Sport sports) {return sportRepository.save(sports);}

    @Override
    public Sport update(Long id, Sport sport) {
        Sport existingSport = sportRepository.findById(id).orElseThrow(() -> new RuntimeException("Sport not found"));

        existingSport.setSport_name(sport.getSport_name());

        return sportRepository.save(existingSport);
    }

    @Override
    public void delete(Long id) {
        Sport existingSport = sportRepository.findById(id).orElseThrow(() -> new RuntimeException("Sport not found"));

        sportRepository.delete(existingSport);
    }

    @Override
    public List<Sport> getAll() {
        return sportRepository.findAll();
    }

}
