package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.Referee;
import com.umesdnd.CampusLeague.service.RefereeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arbitro")
public class RefereeController {

    @Autowired
    private RefereeService refereeService;

    @GetMapping("/{id}")
    public ResponseEntity<Referee> getRefereeById(@PathVariable Long id) {
        Referee referee = refereeService.getById(id);
        return new ResponseEntity<>(referee, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Referee> saveReferee(@RequestBody Referee referee) {
        Referee saveReferee = refereeService.saveOne(referee);
        return new ResponseEntity<>(saveReferee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Referee> updateReferee(@PathVariable Long id, @RequestBody Referee referee) {
        Referee updatedReferee = refereeService.update(id, referee);
        return new ResponseEntity<>(updatedReferee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Referee> deleteReferee(@PathVariable Long id) {
       refereeService.delete(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public ResponseEntity<List<Referee>> getAllReferees() {
        try {
            List<Referee> referees = refereeService.getAll();
            return ResponseEntity.ok(referees);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
