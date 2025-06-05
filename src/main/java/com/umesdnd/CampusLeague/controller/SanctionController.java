package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.Sanction;
import com.umesdnd.CampusLeague.service.SanctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sanciones")
public class SanctionController {

    @Autowired
    private SanctionService sanctionService;

    @GetMapping("/{id}")
    public ResponseEntity<Sanction> getSanctionById(Long id) {
        Sanction sanction = sanctionService.getById(id);
        return new ResponseEntity<>(sanction, HttpStatus.OK);
    }
}
