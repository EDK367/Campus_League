package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.model.Player;
import com.umesdnd.CampusLeague.model.Status;
import com.umesdnd.CampusLeague.repository.PlayerRepository;
import com.umesdnd.CampusLeague.service.interfaces.PlayerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements PlayerServiceInterface {

    @Autowired
    private PlayerRepository repository;

    @Autowired
    private StatusService statusService;

    @Override
    public Player getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player with ID " + id + " not found"));
    }

    @Override
    public Player saveOne(Player player) {
        return repository.save(player);
    }

    @Override
    public Player update(Long id, Player player) {
        Player existingPlayer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player with ID " + id + " not found"));

        Status status = statusService.getById(player.getStatus().getId());

        existingPlayer.setNames(player.getNames());
        existingPlayer.setAge(player.getAge());
        existingPlayer.setCarnet(player.getCarnet());
        existingPlayer.setPosition(player.getPosition());
        existingPlayer.setTeam(player.getTeam());
        existingPlayer.setStatus(status);

        return repository.save(existingPlayer);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Player with ID " + id + " not found");
        }
        repository.deleteById(id);
    }

    @Override
    public List<Player> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Player> findByTeamId(Long teamId) {
        return repository.getByTeam(teamId);
    }
}
