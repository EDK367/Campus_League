package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.model.Sport;
import com.umesdnd.CampusLeague.repository.SportRepository;
import com.umesdnd.CampusLeague.service.interfaces.SportServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportService implements SportServiceInterface {

    @Autowired
    private SportRepository sportRepository;

    @Override
    public Sport getById(Long id) {
        return sportRepository.findById(id).orElseThrow(() -> new RuntimeException("Sport not found"));
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
        if (!sportRepository.existsById(id)){
            throw new RuntimeException("Sport with ID " + id + "not found");
        }
        sportRepository.deleteById(id);
    }

    @Override
    public List<Sport> getAll() { return sportRepository.findAll();}
}
