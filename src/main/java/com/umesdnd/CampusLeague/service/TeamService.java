package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.*;
import com.umesdnd.CampusLeague.model.DTO.TeamDTO;
import com.umesdnd.CampusLeague.repository.PlayerPositionRepository;
import com.umesdnd.CampusLeague.repository.PlayerRepository;
import com.umesdnd.CampusLeague.repository.TeamRepository;
import com.umesdnd.CampusLeague.service.interfaces.PlayerPositionServiceInterface;
import com.umesdnd.CampusLeague.service.interfaces.TeamPlayerServiceInterface;
import com.umesdnd.CampusLeague.service.interfaces.TeamServiceInterface;
import com.umesdnd.CampusLeague.utills.DuplicateData;
import com.umesdnd.CampusLeague.utills.TeamCode;
import org.springdoc.webmvc.ui.SwaggerIndexTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService implements TeamServiceInterface {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamPlayerServiceInterface teamPlayerService;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CoachService coachService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private PlayerPositionRepository playerPositionRepository;

    @Autowired
    DuplicateData duplicateData;

    @Autowired
    private SwaggerIndexTransformer indexPageTransformer;

    @Autowired
    private PlayerPositionService playerPositionService;

    @Transactional
    public TeamDTO getWithPlayers(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new NewExceptionType("Equipo no encontrado con ", HttpStatus.NOT_FOUND));

        List<Player> players = team.getTeamPlayers().stream()
                .map(TeamPlayer::getPlayer)
                .collect(Collectors.toList());

        TeamDTO teamDTO = TeamDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .inscription_date(team.getInscription_date())
                .approved_date(team.getApproved_date())
                .user(team.getUser())
                .status(team.getStatus())
                .coach(team.getCoach())
                .players(players)
                .captain(team.getCaptain())
                .tournament(team.getTournament())
                .teamCode(team.getTeamCode())
                .contact_email(team.getContact_email())
                .contact_phone(team.getContact_phone())
                .build();

        return teamDTO;
    }

    @Override
    public Team activeTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new NewExceptionType("Equipo no encontrado con id " + id, HttpStatus.NOT_FOUND));
        if (team.getStatus() != null) {
            if (team.getStatus().getId() == 5L) {
                throw new NewExceptionType("El equipo ya esta aceptado", HttpStatus.BAD_REQUEST);
            }
        }
        team.setStatus(statusService.getById(5L));
        return teamRepository.save(team);
    }

    @Override
    public List<TeamPlayer> positionTeam(Long id) {
        List<TeamPlayer> teamPlayers = teamPlayerService.positionTeam(id);
        return teamPlayers;
    }

    @Transactional
    public Team getById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new NewExceptionType("Equipo no encontrado con id " + id, HttpStatus.NOT_FOUND));
        team.getTeamPlayers().size();
        return team;
    }

    @Transactional
    public Team saveOne(Team team) {
        if (team == null) {
            throw new NewExceptionType("El equipo no puede ser nulo", HttpStatus.BAD_REQUEST);
        }

        if (team.getPlayers() == null || team.getPlayers().isEmpty()) {
            throw new NewExceptionType("Los jugadores no pueden ser nulos o vacios", HttpStatus.BAD_REQUEST);
        }

        if (team.getName() == null || team.getName().trim().isBlank()) {
            throw new NewExceptionType("El nombre del equipo no puede ser nulo", HttpStatus.BAD_REQUEST);
        }

        if (this.teamRepository.existsByName(team.getName())) {
            throw new NewExceptionType("El nombre del equipo ya existe", HttpStatus.BAD_REQUEST);
        }

        if (team.getTournament() == null || team.getTournament().getId() == null) {
            throw new NewExceptionType("El torneo no puede ser nulo", HttpStatus.BAD_REQUEST);
        }

        Tournament tournament = tournamentService.getById(team.getTournament().getId());

        if (tournament.getInscriptions_open_date() == null || tournament.getInscriptions_close_date() == null) {
            throw new NewExceptionType("Las fechas de inscripcion no estan configuraadas correctamente", HttpStatus.BAD_REQUEST);
        }
        if (tournament.getInscriptions_open_date().isAfter(LocalDateTime.now())) {
            throw new NewExceptionType("El periodo de inscripcion aun no ha comenzado", HttpStatus.BAD_REQUEST);
        }
        if (tournament.getInscriptions_close_date().isBefore(LocalDateTime.now())) {
            throw new NewExceptionType("El perido de inscripcion ya ha finalizado", HttpStatus.BAD_REQUEST);
        }

        if (team.getStatus() == null) {
            Status status = statusService.getById(2L);
            team.setStatus(status);
        }

        if (team.getCoach() != null) {
            Coach coach = coachService.saveOne(team.getCoach());
            team.setCoach(coach);
        }

        if (team.getCaptain() == null || team.getCaptain().trim().isBlank()) {
            throw new NewExceptionType("El capitan no puede ser nulo", HttpStatus.BAD_REQUEST);
        }

        Status fullStatus = statusService.getById(team.getStatus().getId());
        team.setStatus(fullStatus);

        List<Player> savedPlayers = team.getPlayers().stream().map(player -> {

            if (player.getNames() == null || player.getNames().trim().isBlank()) {
                throw new NewExceptionType("Falta el nombre del jugador con estudiante ID: " + player.getCarnet(), HttpStatus.BAD_REQUEST);
            }
            if (player.getAge() <= 15 || player.getAge() > 75) {
                throw new NewExceptionType("Edad no valida para el jugador " + player.getNames(), HttpStatus.BAD_REQUEST);
            }
            if (player.getCarnet() == null || player.getCarnet().trim().isBlank()) {
                throw new NewExceptionType("Falda el estudiante ID (carnet) para el jugador " + player.getNames(), HttpStatus.BAD_REQUEST);
            }
            if (player.getPosition() == null || player.getPosition().getId() == null) {
                throw new NewExceptionType("Falta la posicion para el jugador " + player.getNames(), HttpStatus.BAD_REQUEST);
            }
            if (!this.playerPositionRepository.existsById(player.getPosition().getId())) {
                throw new NewExceptionType("La posicion no existe para el jugador " + player.getNames(), HttpStatus.BAD_REQUEST);
            }

            PlayerPosition fullPosition = playerPositionService.getById(player.getPosition().getId());
            player.setPosition(fullPosition);
            player.setStatus(statusService.getById(2L));

            if (playerRepository.existsByCarnet(player.getCarnet())) {
                List<Player> player1 = playerRepository.findByCarnet(player.getCarnet());
                if (player1.size() > 0) {
                    return player1.get(0);
                }
            }
            return playerRepository.save(player);
        }).collect(Collectors.toList());

        if (savedPlayers.size() > tournament.getMax_team_members()) {
            throw new NewExceptionType("El equipo supera el numero maximo de jugadores permitidos", HttpStatus.BAD_REQUEST);
        }

        if (savedPlayers.size() < tournament.getMin_team_members()) {
            throw new NewExceptionType("El equipo no cumple con el numero minimo de jugadores requerido", HttpStatus.BAD_REQUEST);
        }

        team.setPlayers(savedPlayers);

        List<TeamPlayer> teamPlayers = savedPlayers.stream().map(player -> {
            TeamPlayer tp = new TeamPlayer();
            tp.setPlayer(player);
            tp.setTeam(team);
            tp.setPlayerPosition(player.getPosition());
            return tp;
        }).collect(Collectors.toList());

        team.setTeamPlayers(teamPlayers);
        team.setTournament(tournament);

        TeamCode teamCode = new TeamCode();
        String code = teamCode.generateTeamCode(tournament.getTournament_name());
        while (teamRepository.existsByTeamCode(code)) {
            code = teamCode.generateTeamCode(tournament.getTournament_name());
        }
        team.setTeamCode(code);

        Team savedTeam = teamRepository.save(team);
        savedTeam.getTeamPlayers().forEach(tp -> {
            tp.getPlayer().getCarnet();
        });

        return savedTeam;
    }

    @Override
    public Team update(Long id, Team team) {
        System.out.println(team + " " + id);
        Team existingTeam = teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Equipo no encontrado con id " + id));

        existingTeam.setName(team.getName());
        existingTeam.setStatus(team.getStatus());
        existingTeam.setCoach(team.getCoach());
        existingTeam.setUser(team.getUser());
        existingTeam.setCaptain(team.getCaptain());
        existingTeam.setTournament(team.getTournament());
        return teamRepository.save(existingTeam);
    }

    @Override
    public void delete(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Equipo no encontrado con id " + id));

        teamRepository.delete(team);
    }

    @Transactional
    public List<Team> getAll() {
        List<Team> teams = teamRepository.findAll();
        teams.forEach(team -> team.getTeamPlayers().size());
        return teamRepository.findAll();
    }

}
