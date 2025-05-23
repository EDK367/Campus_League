package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.*;
import com.umesdnd.CampusLeague.repository.MatchRepository;
import com.umesdnd.CampusLeague.repository.TeamRepository;
import com.umesdnd.CampusLeague.service.interfaces.MatchServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchService implements MatchServiceInterface {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private FieldService fieldService;

    @Autowired
    private RefereeService refereeService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private TeamRepository teamRepository;

    @Transactional
    public Match getById(Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new NewExceptionType("Match not found", HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Match saveOne(Match match) {
        //System.out.println(match);
        // validacion de objetos
        if (match.getTournament() == null) {
            throw new NewExceptionType("Tournament is required", HttpStatus.BAD_REQUEST);
        }
        if (match.getTeam1() == null) {
            throw new NewExceptionType("Team 1 is required", HttpStatus.BAD_REQUEST);
        }
        if (match.getTeam2() == null) {
            throw new NewExceptionType("Team 2 is required", HttpStatus.BAD_REQUEST);
        }
        if (match.getField() == null) {
            throw new NewExceptionType("Field is required", HttpStatus.BAD_REQUEST);
        }
        if (match.getReferee() == null) {
            throw new NewExceptionType("Referee is required", HttpStatus.BAD_REQUEST);
        }

        // validacion de objetos existentes
        Tournament existingTournament = tournamentService.getById(match.getTournament().getId());
        Team existingTeam1 = teamRepository.findById(match.getTeam1().getId()).orElseThrow(() -> new NewExceptionType("Team 1 not found", HttpStatus.NOT_FOUND));
        Team existingTeam2 = teamRepository.findById(match.getTeam2().getId()).orElseThrow(() -> new NewExceptionType("Team 2 not found", HttpStatus.NOT_FOUND));
        Field existingField = fieldService.getById(match.getField().getId());
        Referee existingReferee = refereeService.getById(match.getReferee().getId());

        // validacion de fecha
        if (existingTournament.getEnd_date().isBefore(LocalDateTime.now())) {
            throw new NewExceptionType("Tournament is already finished", HttpStatus.BAD_REQUEST);
        }

        // validacion de equipos, campo y del referee
        if (existingTeam1.getStatus().getId() != 1) {
            throw new NewExceptionType("Team: " + existingTeam1.getName() + " is loser", HttpStatus.BAD_REQUEST);
        }
        if (existingTeam2.getStatus().getId() != 1) {
            throw new NewExceptionType("Team: " + existingTeam2.getName() + " is loser", HttpStatus.BAD_REQUEST);
        }
        if (existingField.getStatus().getId() != 1) {
            throw new NewExceptionType("Field: " + existingField.getName() + " is not available", HttpStatus.BAD_REQUEST);
        }
        if (existingReferee.getStatus().getId() != 1) {
            throw new NewExceptionType("Referee: " + existingReferee.getName() + " is not available", HttpStatus.BAD_REQUEST);
        }
        if (existingTeam1.getId() == existingTeam2.getId()) {
            throw new NewExceptionType("Teams are the same", HttpStatus.BAD_REQUEST);
        }

        int minMembers = existingTournament.getMin_team_members();
        int maxMembers = existingTournament.getMax_team_members();

        int team1Members = existingTeam1.getTeamPlayers().size();
        int team2Members = existingTeam2.getTeamPlayers().size();

        if (team1Members < minMembers || team1Members > maxMembers) {
            throw new NewExceptionType("Team: " + existingTeam1.getName() + " does not comply with the tournament's rules.", HttpStatus.BAD_REQUEST);
        }
        if (team2Members < minMembers || team2Members > maxMembers) {
            throw new NewExceptionType("Team: " + existingTeam2.getName() + " does not comply with the tournament's rules.", HttpStatus.BAD_REQUEST);
        }
        // validaciones extras

        // ingreso de datos
        match.setTournament(existingTournament);
        match.setTeam1(existingTeam1);
        match.setTeam2(existingTeam2);
        match.setTeam1_score(0L);
        match.setTeam2_score(0L);
        match.setStatus(statusService.getById(1L));
        match.setField(existingField);
        match.setReferee(existingReferee);

        return matchRepository.save(match);
    }

    @Override
    public Match update(Long id, Match match) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Match existingMatch = matchRepository.findById(id).orElseThrow(() -> new NewExceptionType("Match not found", HttpStatus.NOT_FOUND));
        existingMatch.setStatus(statusService.getById(2L));
        matchRepository.save(existingMatch);
    }

    @Transactional
    public List<Match> getAll() {
        return matchRepository.findAll();
    }

    @Override
    public List<Match> generateMatch(Match match) {
        return List.of();
    }

}
