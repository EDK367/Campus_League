package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.*;
import com.umesdnd.CampusLeague.repository.MatchRepository;
import com.umesdnd.CampusLeague.repository.TeamRepository;
import com.umesdnd.CampusLeague.service.interfaces.MatchServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

    @Override
    public Match getById(Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new NewExceptionType("Match not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Match saveOne(Match match) {
        System.out.println(match);
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
        return null;
    }

    @Override
    public Match update(Long id, Match match) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Match> getAll() {
        return matchRepository.findAll();
    }

    @Override
    public List<Match> generateMatch(Match match) {
        return List.of();
    }
}
