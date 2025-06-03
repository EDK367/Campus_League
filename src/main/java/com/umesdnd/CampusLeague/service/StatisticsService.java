package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.model.Team;
import com.umesdnd.CampusLeague.model.Tournament;
import com.umesdnd.CampusLeague.repository.TeamRepository;
import com.umesdnd.CampusLeague.repository.TournamentRepository;
import com.umesdnd.CampusLeague.service.interfaces.StatisticsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService implements StatisticsServiceInterface {

    private List<Long> tournamentStatusActive = List.of(1L);
    private List<Long> teamsStatusInactive = List.of(2L, 10L, 13L);
    private List<Long> teamsStatusActive = List.of(1L, 3L, 5L, 8L);


    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    @Transactional
    public Map<String, Object> getStatisticsTournaments() {
        Map<String, Object> tournamentMap = new HashMap<>();
        List<Tournament> tournaments = tournamentRepository.findTournamentByStatusIdIn(tournamentStatusActive);
        tournamentMap.put("Torneos Activos", tournaments.size());
        return tournamentMap;
    }

    @Override
    @Transactional
    public Map<String, Object> getStatisticsTeams() {
        Map<String, Object> teamMap = new HashMap<>();
        List<Team> teams = teamRepository.findTeamByStatusIdIn(teamsStatusActive);
        List<Team> teamsInactive = teamRepository.findTeamByStatusIdIn(teamsStatusInactive);
        teamMap.put("Equipos Activos", teams.size());
        teamMap.put("Equipos en Revisión", teamsInactive.size());
        return teamMap;
    }

    @Override
    public List<Map<String, Object>> getStatisticsPlayers() {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> getStatisticsMatches() {
        return List.of();
    }
}
