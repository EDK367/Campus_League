package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.Player;
import com.umesdnd.CampusLeague.model.PlayerPosition;
import com.umesdnd.CampusLeague.service.PlayerPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posicion-jugador")
public class PlayerPositionController {

    @Autowired
    private PlayerPositionService playerPositionService;


    @GetMapping("")
    public ResponseEntity<List<PlayerPosition>> getAllPlayerPositions() {
        try {
            List<PlayerPosition> positions = playerPositionService.getAll();
            return ResponseEntity.ok(positions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerPosition> getPlayerPositionById(@PathVariable Long id) {
            PlayerPosition position = playerPositionService.getById(id);
            return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<PlayerPosition> savePlayerPosition(@RequestBody PlayerPosition playerPosition) {
        try {
            PlayerPosition savedPosition = playerPositionService.saveOne(playerPosition);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPosition);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerPosition> updatePlayer(@PathVariable Long id, @RequestBody PlayerPosition playerPosition) {
        try {
            PlayerPosition updatedPosition = playerPositionService.update(id, playerPosition);
            return ResponseEntity.ok(updatedPosition);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id) {
        try {
            playerPositionService.delete(id);
            return ResponseEntity.ok("Posicion eliminada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la posicion");
        }
    }

}
