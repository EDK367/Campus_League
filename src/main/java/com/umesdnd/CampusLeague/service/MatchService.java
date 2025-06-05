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
        return matchRepository.findById(id).orElseThrow(() -> new NewExceptionType("Partido no encontrado", HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Match saveOne(Match match) {
        //System.out.println(match);
        // validacion de objetos
        if (match.getTournament() == null) {
            throw new NewExceptionType("El torneo es requerido", HttpStatus.BAD_REQUEST);
        }
        if (match.getTeam1() == null) {
            throw new NewExceptionType("El equipo 1 es requerido", HttpStatus.BAD_REQUEST);
        }
        if (match.getTeam2() == null) {
            throw new NewExceptionType("El equipo 2 es requerido", HttpStatus.BAD_REQUEST);
        }
        if (match.getField() == null) {
            throw new NewExceptionType("El campo es requerido", HttpStatus.BAD_REQUEST);
        }
        if (match.getReferee() == null) {
            throw new NewExceptionType("El arbitro es requerido", HttpStatus.BAD_REQUEST);
        }

        // validacion de objetos existentes
        Tournament existingTournament = tournamentService.getById(match.getTournament().getId());
        Team existingTeam1 = teamRepository.findById(match.getTeam1().getId()).orElseThrow(() -> new NewExceptionType("Equipo 1 no encontrado", HttpStatus.NOT_FOUND));
        Team existingTeam2 = teamRepository.findById(match.getTeam2().getId()).orElseThrow(() -> new NewExceptionType("Equipo 2 no encontrado", HttpStatus.NOT_FOUND));
        Field existingField = fieldService.getById(match.getField().getId());
        Referee existingReferee = refereeService.getById(match.getReferee().getId());

        // validacion de fecha
        if (existingTournament.getEnd_date().isBefore(LocalDateTime.now())) {
            throw new NewExceptionType("El torneo ya ha finalizado", HttpStatus.BAD_REQUEST);
        }

        // validacion de equipos, campo y del referee
        if (existingTeam1.getStatus().getId() != 1) {
            throw new NewExceptionType("Equipo: " + existingTeam1.getName() + " es el perdedor", HttpStatus.BAD_REQUEST);
        }
        if (existingTeam2.getStatus().getId() != 1) {
            throw new NewExceptionType("Equipo: " + existingTeam2.getName() + " es el perdedor", HttpStatus.BAD_REQUEST);
        }
        if (existingField.getStatus().getId() != 1) {
            throw new NewExceptionType("Campo: " + existingField.getName() + " no esta disponible", HttpStatus.BAD_REQUEST);
        }
        if (existingReferee.getStatus().getId() != 1) {
            throw new NewExceptionType("Arbitro: " + existingReferee.getName() + " no esta disponible", HttpStatus.BAD_REQUEST);
        }
        if (existingTeam1.getId() == existingTeam2.getId()) {
            throw new NewExceptionType("Los equipos son iguales", HttpStatus.BAD_REQUEST);
        }

        int minMembers = existingTournament.getMin_team_members();
        int maxMembers = existingTournament.getMax_team_members();

        int team1Members = existingTeam1.getTeamPlayers().size();
        int team2Members = existingTeam2.getTeamPlayers().size();

        if (team1Members < minMembers || team1Members > maxMembers) {
            throw new NewExceptionType("Equipo: " + existingTeam1.getName() + " no cumple con las reglas del torneo.", HttpStatus.BAD_REQUEST);
        }
        if (team2Members < minMembers || team2Members > maxMembers) {
            throw new NewExceptionType("Equipo: " + existingTeam2.getName() + " no cumple con las reglas del torneo.", HttpStatus.BAD_REQUEST);
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
        Match existingMatch = matchRepository.findById(id).orElseThrow(() -> new NewExceptionType("Partido no encontrado", HttpStatus.NOT_FOUND));
        existingMatch.setStatus(statusService.getById(7L));
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
