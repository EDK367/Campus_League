package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.Player;
import com.umesdnd.CampusLeague.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/{idPlayer}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long idPlayer) {
        try {
            Player player = playerService.getById(idPlayer);
            return ResponseEntity.ok(player);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Player> savePlayer(@RequestBody Player player) {
        try {
            Player savedPlayer = playerService.saveOne(player);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPlayer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{idPlayer}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long idPlayer, @RequestBody Player player) {
        try {
            Player updatedPlayer = playerService.update(idPlayer, player);
            return ResponseEntity.ok(updatedPlayer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{idPlayer}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long idPlayer) {
        try {
            playerService.delete(idPlayer);
            return ResponseEntity.ok("Jugador eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el jugador");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Player>> getAllPlayers() {
        try {
            List<Player> players = playerService.getAll();
            return ResponseEntity.ok(players);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("team/{teamId}")
    public ResponseEntity<List<Player>> getAllPlayersByTeam(@PathVariable Long teamId) {
        try {
            List<Player> players = playerService.findByTeamId(teamId);
            return ResponseEntity.ok(players);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
