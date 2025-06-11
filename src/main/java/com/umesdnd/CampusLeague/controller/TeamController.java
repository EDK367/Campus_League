package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.DTO.PlayersDTO;
import com.umesdnd.CampusLeague.model.DTO.TeamDTO;
import com.umesdnd.CampusLeague.model.Team;
import com.umesdnd.CampusLeague.model.TeamPlayer;
import com.umesdnd.CampusLeague.service.TeamService;
import com.umesdnd.CampusLeague.service.interfaces.TeamServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/equipo")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id) {
            TeamDTO team = teamService.getWithPlayers(id);
            return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @GetMapping("/jugadores-info/{id}")
    public ResponseEntity<List<TeamPlayer>> getTeamPlayers(@PathVariable Long id) {
        List<TeamPlayer> teamPlayers = teamService.playersTeamsId(id);
        return new ResponseEntity<>(teamPlayers, HttpStatus.OK);
    }

    @GetMapping("/jugadores/{id}")
    public ResponseEntity<List<PlayersDTO>> getTeamPlayer(@PathVariable Long id) {
        List<PlayersDTO> players = teamService.playersTeamsIdDTO(id);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @PutMapping("/aceptado/{id}")
    public ResponseEntity<Team> activateTeam(@PathVariable Long id) {
        Team team = teamService.activeTeam(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @PutMapping("/descartado/{id}")
    public ResponseEntity<Team> discardTeam(@PathVariable Long id) {
        Team team = teamService.discardTeam(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getAll();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Team> saveTeam(@RequestBody Team team) {
        Team savedTeam = teamService.saveOne(team);
        return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        try {
            Team updatedTeam = teamService.update(id, team);
            return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        try {
            teamService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
