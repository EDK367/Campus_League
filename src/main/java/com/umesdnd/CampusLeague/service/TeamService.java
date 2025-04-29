package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.BadRequestException;
import com.umesdnd.CampusLeague.model.*;
import com.umesdnd.CampusLeague.repository.PlayerPositionRepository;
import com.umesdnd.CampusLeague.repository.TeamRepository;
import com.umesdnd.CampusLeague.service.interfaces.TeamServiceInterface;
import com.umesdnd.CampusLeague.utills.DuplicateData;
import org.springdoc.webmvc.ui.SwaggerIndexTransformer;
import org.springframework.beans.factory.annotation.Autowired;
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

        if (team.getCoach() == null) {
            throw new BadRequestException("No existe coach para el Team");
        }
        if (team.getName() == null || team.getName().trim().isEmpty()) {
            throw new BadRequestException("No existe nombre para el Team");
        }
        if (this.teamRepository.existsByName(team.getName())) {
            throw new BadRequestException("Ya existe un Team con ese nombre");
        }
        if (team.getPlayers() == null || team.getPlayers().isEmpty()) {
            throw new BadRequestException("No existen jugadores para el Team");
        }

        if (team.getStatus() == null) {
            Status status = statusService.getById(2L);
            team.setStatus(status);
        }
        Coach coach = coachService.saveOne(team.getCoach());
        team.setCoach(coach);
        Status fullStatus = statusService.getById(team.getStatus().getId());
        team.setStatus(fullStatus);

        List<String> carnets = team.getPlayers()
                .stream()
                .map(player -> player.getCarnet())
                .collect(Collectors.toList());
        List<String> repeated = duplicateData.duplicate(carnets);

        if (!repeated.isEmpty()) {
            throw new BadRequestException("No se puede ingresar carnets duplicados: " + repeated);
        }

        for (Player player : team.getPlayers()) {

            if (player.getNames() == null || player.getNames().trim().isEmpty()) {
                throw new BadRequestException("No se ingreso nombre para el jugador con carnet: " + player.getCarnet());
            }

            if (player.getAge() <= 0 || player.getAge() > 100) {
                throw new BadRequestException("La edad no es correcta para el jugador " + player.getNames());
            }

            if (player.getCarnet() == null || player.getCarnet().trim().isEmpty()) {
                throw new BadRequestException("No se ingreso carnet para el jugador " + player.getNames());
            }

            if (player.getPosition() == null || player.getPosition().getId() == null) {
                throw new BadRequestException("No se ingreso posicion para el jugador " + player.getNames());
            }
            if (!this.playerPositionRepository.existsById(player.getPosition().getId())) {
                throw new BadRequestException("No existe la posición  para el jugador " + player.getNames());
            }
            PlayerPosition fullPosition = playerPositionService.getById(player.getPosition().getId());
            player.setPosition(fullPosition);

            player.setTeam(team);
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
