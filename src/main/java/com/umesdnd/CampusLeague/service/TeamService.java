package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.model.Coach;
import com.umesdnd.CampusLeague.model.Team;
import com.umesdnd.CampusLeague.repository.TeamRepository;
import com.umesdnd.CampusLeague.service.interfaces.TeamServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService implements TeamServiceInterface {

    @Autowired
    private TeamRepository repository;

    @Autowired
    private CoachService coachService;

    @Override
    public Team getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Team not found with id " + id));
    }

    @Override
    public Team saveOne(Team team) {
        Coach coach = coachService.saveOne(team.getCoach());
        team.setCoach(coach);

        return repository.save(team);
    }

    @Override
    public Team update(Long id, Team team) {
        Team existingTeam = repository.findById(id).orElseThrow(() -> new RuntimeException("Team not found with id " + id));

        existingTeam.setName(team.getName());
        existingTeam.setStatus(team.getStatus());
        existingTeam.setCoach(team.getCoach());
        existingTeam.setUser(team.getUser());

        return repository.save(existingTeam);
    }

    @Override
    public void delete(Long id) {
        Team team = repository.findById(id).orElseThrow(() -> new RuntimeException("Team not found with id " + id));

        repository.delete(team);
    }

    @Override
    public List<Team> getAll() {
        return repository.findAll();
    }
}
