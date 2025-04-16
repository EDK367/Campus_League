package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.model.PlayerPosition;
import com.umesdnd.CampusLeague.repository.PlayerPositionRepository;
import com.umesdnd.CampusLeague.service.interfaces.PlayerPositionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerPositionService implements PlayerPositionServiceInterface {

    @Autowired
    private PlayerPositionRepository repository;

    @Override
    public PlayerPosition getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Position not found"));
    }

    @Override
    public PlayerPosition saveOne(PlayerPosition playerPosition) {
        return repository.save(playerPosition);
    }

    @Override
    public PlayerPosition update(Long id, PlayerPosition playerPosition) {
        PlayerPosition existingPosition = repository.findById(id).orElseThrow(() -> new RuntimeException("Position not found"));

        existingPosition.setName(playerPosition.getName());
        existingPosition.setDescription(playerPosition.getDescription());

        return repository.save(existingPosition);
    }

    @Override
    public void delete(Long id) {
        PlayerPosition existingPosition = repository.findById(id).orElseThrow(() -> new RuntimeException("Position not found"));

        repository.delete(existingPosition);
    }

    @Override
    public List<PlayerPosition> getAll() {
        return repository.findAll();
    }
}
