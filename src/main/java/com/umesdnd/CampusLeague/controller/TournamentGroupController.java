package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.TournamentGroup;
import com.umesdnd.CampusLeague.service.TournamentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupo-torneo")
public class TournamentGroupController {

    @Autowired
    private TournamentGroupService tournamentGService;

    @GetMapping("/{id}")
    public ResponseEntity<TournamentGroup> getTournamentGroupById(@PathVariable Long id){
        TournamentGroup tournamentGroup = tournamentGService.getById(id);
        return new ResponseEntity<>(tournamentGroup, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<TournamentGroup> saveTournamentGroup(@RequestBody TournamentGroup tournamentGroup){
        TournamentGroup saveTournamentGroup = tournamentGService.saveOne(tournamentGroup);
        return new ResponseEntity<>(saveTournamentGroup, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TournamentGroup> updateTournamentGroup(@PathVariable Long id, @RequestBody TournamentGroup tournamentGroup){
        TournamentGroup updateTournamentGroup = tournamentGService.update(id, tournamentGroup);
        return new ResponseEntity<>(updateTournamentGroup, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<TournamentGroup>> getAllTournamentGroups(){
        List<TournamentGroup> tournamentGroups = tournamentGService.getAll();
        return new ResponseEntity<>(tournamentGroups, HttpStatus.OK);
    }
}
