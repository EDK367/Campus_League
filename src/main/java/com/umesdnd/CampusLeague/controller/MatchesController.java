package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.Match;
import com.umesdnd.CampusLeague.model.Tournament;
import com.umesdnd.CampusLeague.service.MatchService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
@RequestMapping("/partido")
public class MatchesController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long id) {
        Match match = matchService.getById(id);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Match> saveOneMatch(@RequestBody Match match) {
        Match saveMatch = matchService.saveOne(match);
        return new ResponseEntity<>(saveMatch, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable Long id, @RequestBody Match match) {
        Match updatedMatch = matchService.update(id, match);
        return new ResponseEntity<>(updatedMatch, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public ResponseEntity<List<Match>> getAllMatches() {
        List<Match> matches = matchService.getAll();
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    @PostMapping("/generate")
    public ResponseEntity<List<Match>> generateMatch(@RequestBody Tournament tournament) {
        List<Match> generateMatch = null;
        return new ResponseEntity<>(generateMatch, HttpStatus.CREATED);
    }
}
