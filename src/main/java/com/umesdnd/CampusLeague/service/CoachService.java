package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.Coach;
import com.umesdnd.CampusLeague.model.PlayerPosition;
import com.umesdnd.CampusLeague.repository.CoachRepository;
import com.umesdnd.CampusLeague.service.interfaces.CoachServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService implements CoachServiceInterface {

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public Coach getById(Long id) {
        return coachRepository.findById(id).orElseThrow(() -> new NewExceptionType("Coach not found", HttpStatus.NOT_FOUND));
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
        Coach existingCoach = coachRepository.findById(id).orElseThrow(() -> new RuntimeException("Coach not found"));

        coachRepository.delete(existingCoach);
    }

    @Override
    public List<Coach> getAll() {
        return coachRepository.findAll();
    }
}
