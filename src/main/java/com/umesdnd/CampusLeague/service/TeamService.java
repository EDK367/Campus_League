package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.*;
import com.umesdnd.CampusLeague.repository.PlayerPositionRepository;
import com.umesdnd.CampusLeague.repository.TeamRepository;
import com.umesdnd.CampusLeague.service.interfaces.TeamServiceInterface;
import com.umesdnd.CampusLeague.utills.DuplicateData;
import org.springdoc.webmvc.ui.SwaggerIndexTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService implements TeamServiceInterface {

    @Autowired
    private TeamRepository teamRepository;

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

    @Override
    public Team getById(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found with id " + id));
    }

    @Override
    public Team saveOne(Team team) {

        if (team.getName() == null || team.getName().trim().isEmpty()) {
            throw new NewExceptionType("No existe nombre para el Team" , HttpStatus.BAD_REQUEST);
        }
        if (this.teamRepository.existsByName(team.getName())) {
            throw new NewExceptionType("Ya existe un Team con ese nombre", HttpStatus.BAD_REQUEST);
        }
        if (team.getPlayers() == null || team.getPlayers().isEmpty()) {
            throw new NewExceptionType("No existen jugadores para el Team", HttpStatus.BAD_REQUEST);
        }

        if (team.getStatus() == null) {
            Status status = statusService.getById(2L);
            team.setStatus(status);
        }

        if (team.getCoach() != null) {
            Coach coach = coachService.saveOne(team.getCoach());
            team.setCoach(coach);
        }
       
        Status fullStatus = statusService.getById(team.getStatus().getId());
        team.setStatus(fullStatus);

        List<String> carnets = team.getPlayers()
                .stream()
                .map(player -> player.getCarnet())
                .collect(Collectors.toList());
        List<String> repeated = duplicateData.duplicate(carnets);

        if (!repeated.isEmpty()) {
            throw new NewExceptionType("No se puede ingresar carnets duplicados: " + repeated, HttpStatus.BAD_REQUEST);
        }

        for (Player player : team.getPlayers()) {

            if (player.getNames() == null || player.getNames().trim().isEmpty()) {
                throw new NewExceptionType("No se ingreso nombre para el jugador con carnet: " + player.getCarnet(), HttpStatus.BAD_REQUEST);
            }

            if (player.getAge() <= 0 || player.getAge() > 100) {
                throw new NewExceptionType("La edad no es correcta para el jugador " + player.getNames(), HttpStatus.BAD_REQUEST);
            }

            if (player.getCarnet() == null || player.getCarnet().trim().isEmpty()) {
                throw new NewExceptionType("No se ingreso carnet para el jugador " + player.getNames(), HttpStatus.BAD_REQUEST);
            }

            if (player.getPosition() == null || player.getPosition().getId() == null) {
                throw new NewExceptionType("No se ingreso posicion para el jugador " + player.getNames(), HttpStatus.BAD_REQUEST);
            }
            if (!this.playerPositionRepository.existsById(player.getPosition().getId())) {
                throw new NewExceptionType("No existe la posición  para el jugador " + player.getNames(), HttpStatus.BAD_REQUEST);
            }
            PlayerPosition fullPosition = playerPositionService.getById(player.getPosition().getId());
            player.setPosition(fullPosition);

            player.setTeam(team);
        }
        return teamRepository.save(team);
    }

    @Override
    public Team update(Long id, Team team) {
        System.out.println(team + " " + id);
        Team existingTeam = teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found with id " + id));

        existingTeam.setName(team.getName());
        //existingTeam.setStatus(team.getStatus());
        //existingTeam.setCoach(team.getCoach());
        //existingTeam.setUser(team.getUser());

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
