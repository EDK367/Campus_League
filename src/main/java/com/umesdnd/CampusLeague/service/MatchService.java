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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.time.DayOfWeek;
import java.util.*;

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

    @Override
    @Transactional
    public List<Match> generateMatches(Long idTournament) {
        Tournament tournament = tournamentService.getById(idTournament);
        List<Team> teams = teamRepository.findByTournamentId(idTournament);
        List<Field> fields = fieldService.getAll();
        List<Referee> referees = refereeService.getAll();

        if (teams.size() < 2) {
            throw new NewExceptionType("No hay suficientes equipos para generar partidos", HttpStatus.BAD_REQUEST);
        }

        List<Match> generatedMatches = new ArrayList<>();
        Set<String> scheduledPairs = new HashSet<>();
        LocalDate startDate = LocalDate.now().plusDays(1);

        int dailyMatches = 0;
        int weeklyMatches = 0;
        int fieldIndex = 0;
        int refereeIndex = 0;

        int[] matchHours = {15, 17};

        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Team team1 = teams.get(i);
                Team team2 = teams.get(j);

                String key = team1.getId() + "-" + team2.getId();
                if (scheduledPairs.contains(key)) continue;
                scheduledPairs.add(key);

                boolean scheduled = false;
                while (!scheduled) {
                    // Evitar fines de semana
                    while (startDate.getDayOfWeek() == DayOfWeek.SATURDAY || startDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        startDate = startDate.plusDays(1);
                        dailyMatches = 0;
                    }

                    if (weeklyMatches >= 4) {
                        startDate = startDate.with(DayOfWeek.MONDAY).plusWeeks(1);
                        weeklyMatches = 0;
                        dailyMatches = 0;
                    }

                    if (dailyMatches >= matchHours.length) {
                        startDate = startDate.plusDays(1);
                        dailyMatches = 0;
                        continue;
                    }

                    Field field = fields.get(fieldIndex % fields.size());
                    Referee referee = referees.get(refereeIndex % referees.size());

                    LocalDateTime matchDateTime = startDate.atTime(matchHours[dailyMatches], 0);

                    boolean fieldOccupied = matchRepository.existsByFieldIdAndMatchDate(field.getId(), matchDateTime);
                    if (!fieldOccupied) {
                        Match match = new Match();
                        match.setTournament(tournament);
                        match.setTeam1(team1);
                        match.setTeam2(team2);
                        match.setTeam1_score(0L);
                        match.setTeam2_score(0L);
                        match.setStatus(statusService.getById(1L));
                        match.setField(field);
                        match.setReferee(referee);
                        match.setMatchDate(matchDateTime);

                        matchRepository.save(match);
                        generatedMatches.add(match);

                        dailyMatches++;
                        weeklyMatches++;
                        fieldIndex++;
                        refereeIndex++;
                        scheduled = true;
                    } else {
                        // Si campo ocupado, probar siguiente campo
                        fieldIndex++;
                        // Si ya intentaste con todos los campos para esa hora, pasar a siguiente hora
                        if (fieldIndex % fields.size() == 0) {
                            dailyMatches++;
                        }
                    }
                }
            }
        }


        return generatedMatches;
    }


    @Transactional
    public Match saveOne(Match match) {
        System.out.println(match);
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
        if (existingTeam1.getStatus().getId() != 1 && existingTeam1.getStatus().getId() != 5 ) {
            throw new NewExceptionType("Equipo: " + existingTeam1.getName() + " no puede participar mas", HttpStatus.BAD_REQUEST);
        }
        if (existingTeam2.getStatus().getId() != 1 && existingTeam2.getStatus().getId() != 5 ) {
            throw new NewExceptionType("Equipo: " + existingTeam2.getName() + " no puede participar mas", HttpStatus.BAD_REQUEST);
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
