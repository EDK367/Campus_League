package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.TournamentTeam;
import com.umesdnd.CampusLeague.service.TournamentTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipo-torneo")
public class TournamentTeamController {

    @Autowired
    private TournamentTeamService tournamentTeamService;

    @GetMapping("/{id}")
    public ResponseEntity<TournamentTeam> getTournamenteTById(@PathVariable Long id){
        TournamentTeam tournamentTeam = tournamentTeamService.getById(id);
        return new ResponseEntity<>(tournamentTeam, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<TournamentTeam> createTournamentTeam(@RequestBody TournamentTeam tournamentTeam){
        TournamentTeam newTournamentTeam = tournamentTeamService.saveOne(tournamentTeam);
        return new ResponseEntity<>(newTournamentTeam, HttpStatus.CREATED);
    }
}
