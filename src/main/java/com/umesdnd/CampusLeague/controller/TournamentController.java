package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.Tournament;
import com.umesdnd.CampusLeague.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/torneo")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournament(@PathVariable Long id) {
        Tournament tournament = tournamentService.getById(id);
        return new ResponseEntity<>(tournament, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Tournament>> getAllTournament() {
        List<Tournament> tournaments = tournamentService.getAll();
        //System.out.println("aca estan los torneso " + tournaments);
        return new ResponseEntity<>(tournaments, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Tournament> saveTournament(@RequestBody Tournament tournament) {
        Tournament savedTournament = tournamentService.saveOne(tournament);
        return new ResponseEntity<>(savedTournament, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tournament> updateTournament(@PathVariable Long id, @RequestBody Tournament tournament) {
        try {
            Tournament updateTournament = tournamentService.update(id, tournament);
            return new ResponseEntity<>(updateTournament, HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tournament> deleteTournament(@PathVariable Long id) {
        try {
            tournamentService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
