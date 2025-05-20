package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.PlayerPosition;
import com.umesdnd.CampusLeague.model.Sport;
import com.umesdnd.CampusLeague.model.Team;
import com.umesdnd.CampusLeague.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("deporte")
public class SportController {

    @Autowired
    private SportService sportService;

    @GetMapping("/{id}")
    public ResponseEntity<Sport> getSportById(@PathVariable Long id) {
        Sport sport = sportService.getById(id);
        return new ResponseEntity<>(sport, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Sport>> getAllSports() {
        try {
            List<Sport> sports = sportService.getAll();
            return ResponseEntity.ok(sports);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
