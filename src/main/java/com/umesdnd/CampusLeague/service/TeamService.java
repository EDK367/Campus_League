package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.model.Coach;
import com.umesdnd.CampusLeague.model.Player;
import com.umesdnd.CampusLeague.model.Status;
import com.umesdnd.CampusLeague.model.Team;
import com.umesdnd.CampusLeague.repository.TeamRepository;
import com.umesdnd.CampusLeague.service.interfaces.TeamServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService implements TeamServiceInterface {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CoachService coachService;

    @Autowired
    private StatusService statusService;

    @Override
    public Team getById(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found with id " + id));
    }

    @Override
    public Team saveOne(Team team) {
        Coach coach = coachService.saveOne(team.getCoach());
        team.setCoach(coach);

        if (team.getPlayers() != null) {
            for (Player player : team.getPlayers()) {
                player.setTeam(team);
            }
        }
        return teamRepository.save(team);
    }

    @Override
    public Team update(Long id, Team team) {
        Team existingTeam = teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found with id " + id));

        existingTeam.setName(team.getName());
        existingTeam.setStatus(team.getStatus());
        existingTeam.setCoach(team.getCoach());
        existingTeam.setUser(team.getUser());

        return teamRepository.save(existingTeam);
    }

    @Override
    public void delete(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found with id " + id));

        teamRepository.delete(team);
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }
}
