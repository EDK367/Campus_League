package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.model.Coach;
import com.umesdnd.CampusLeague.repository.CoachRepository;
import com.umesdnd.CampusLeague.service.interfaces.CoachServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService implements CoachServiceInterface {

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public Coach getById(Long id) {
        return coachRepository.findById(id).orElseThrow(() -> new RuntimeException("Coach not found"));
    }

    @Override
    public Coach saveOne(Coach coach) {
        return coachRepository.save(coach);
    }

    @Override
    public Coach update(Long id, Coach coach) {
        Coach existingCoach = coachRepository.findById(id).orElseThrow(() -> new RuntimeException("Coach not found"));

        existingCoach.setName(coach.getName());
        existingCoach.setExperience_years(coach.getExperience_years());

        return coachRepository.save(existingCoach);
    }

    @Override
    public void delete(Long id) {
        if (!coachRepository.existsById(id)){
            throw new RuntimeException("Coach with ID " + id + " not found");
        }
        coachRepository.deleteById(id);
    }

    @Override
    public List<Coach> getAll() {
        return coachRepository.findAll();
    }
}
