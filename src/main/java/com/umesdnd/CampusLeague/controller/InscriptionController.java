package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.Inscription;
import com.umesdnd.CampusLeague.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inscripcion")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;

    @GetMapping("/{id}")
    public ResponseEntity<Inscription> getInscriptionById(@PathVariable Long id){
        Inscription inscription = inscriptionService.getById(id);
        return new ResponseEntity<>(inscription, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Inscription> saveInscription(@RequestBody Inscription inscription) {
        Inscription saveInscription = inscriptionService.saveOne(inscription);
        return new ResponseEntity<>(saveInscription, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inscription> updateInscription(@PathVariable Long id, @RequestBody Inscription inscription) {
        Inscription updateInscription = inscriptionService.update(id, inscription);
        return new ResponseEntity<>(updateInscription, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInscription(@PathVariable Long id) {
        inscriptionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Inscription>> getAllInscription() {
        List<Inscription> inscriptions = inscriptionService.getAll();
        return new ResponseEntity<>(inscriptions, HttpStatus.OK);
    }
}
