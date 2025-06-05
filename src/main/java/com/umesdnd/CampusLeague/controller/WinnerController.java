package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.Winner;
import com.umesdnd.CampusLeague.service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ganador")
public class WinnerController {

    @Autowired
    private WinnerService winnerService;

    @GetMapping("/{id}")
    public ResponseEntity<Winner> getWinnerById(@PathVariable Long id) {
        Winner winner = winnerService.getById(id);
        return new ResponseEntity<>(winner, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Winner> saveOneWinner(@RequestBody Winner winner) {
        Winner saveWinner = winnerService.saveOne(winner);
        return new ResponseEntity<>(saveWinner, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Winner> updateWinner(@PathVariable Long id, @RequestBody Winner winner) {
        Winner updateWinner = winnerService.update(id, winner);
        return new ResponseEntity<>(updateWinner, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWinner(@PathVariable Long id) {
        winnerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public ResponseEntity<List<Winner>> getAllWinner() {
        List<Winner> winner = winnerService.getAll();
        return new ResponseEntity<>(winner, HttpStatus.OK);
    }

}
