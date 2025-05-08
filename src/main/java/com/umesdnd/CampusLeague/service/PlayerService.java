package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.Match;
import com.umesdnd.CampusLeague.model.Player;
import com.umesdnd.CampusLeague.model.Status;
import com.umesdnd.CampusLeague.repository.PlayerRepository;
import com.umesdnd.CampusLeague.service.interfaces.PlayerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements PlayerServiceInterface {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private StatusService statusService;

    @Override
    public Player getById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player with ID " + id + " not found"));
    }

    @Override
    public Player saveOne(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player update(Long id, Player player) {
        Player existingPlayer = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player with ID " + id + " not found"));

        Status status = statusService.getById(player.getStatus().getId());

        existingPlayer.setNames(player.getNames());
        existingPlayer.setAge(player.getAge());
        existingPlayer.setCarnet(player.getCarnet());
        existingPlayer.setPosition(player.getPosition());
        existingPlayer.setTeam(player.getTeam());
        existingPlayer.setStatus(status);

        return playerRepository.save(existingPlayer);
    }

    @Override
    public void delete(Long id) {
        Player existingPlayer = playerRepository.findById(id).orElseThrow(() -> new NewExceptionType("Player not found", HttpStatus.NOT_FOUND));
        existingPlayer.setStatus(statusService.getById(7L));
        playerRepository.save(existingPlayer);
    }

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public List<Player> findByTeamId(Long teamId) {
        return playerRepository.getByTeam(teamId);
    }
}
