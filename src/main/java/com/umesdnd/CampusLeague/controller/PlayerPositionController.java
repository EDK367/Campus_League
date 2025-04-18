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
@RequestMapping("player-position")
public class PlayerPositionController {

    @Autowired
    private PlayerPositionService playerPositionService;

    @GetMapping("/{idPosition}")
    public ResponseEntity<PlayerPosition> getPlayerPositionById(@PathVariable Long idPosition) {
        try {
            PlayerPosition position = playerPositionService.getById(idPosition);
            return ResponseEntity.ok(position);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
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

    @PutMapping("/{idPosition}")
    public ResponseEntity<PlayerPosition> updatePlayer(@PathVariable Long idPosition, @RequestBody PlayerPosition playerPosition) {
        try {
            PlayerPosition updatedPosition = playerPositionService.update(idPosition, playerPosition);
            return ResponseEntity.ok(updatedPosition);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{idPosition}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long idPosition) {
        try {
            playerPositionService.delete(idPosition);
            return ResponseEntity.ok("Posicion eliminada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la posicion");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<PlayerPosition>> getAllPlayerPositions() {
        try {
            List<PlayerPosition> positions = playerPositionService.getAll();
            return ResponseEntity.ok(positions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
