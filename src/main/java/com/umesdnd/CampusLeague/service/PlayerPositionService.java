package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.PlayerPosition;
import com.umesdnd.CampusLeague.repository.PlayerPositionRepository;
import com.umesdnd.CampusLeague.service.interfaces.PlayerPositionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerPositionService implements PlayerPositionServiceInterface {

    @Autowired
    private PlayerPositionRepository playerPositionRepository;


    @Override
    public List<PlayerPosition> getPositionSport(Long idSport) {
        return playerPositionRepository.findBySport_Id(idSport);
    }

    @Override
    public PlayerPosition getById(Long id) {
        return playerPositionRepository.findById(id).orElseThrow(() -> new NewExceptionType("Posicion no encontrada", HttpStatus.NOT_FOUND));
    }

    @Override
    public PlayerPosition saveOne(PlayerPosition playerPosition) {
        return playerPositionRepository.save(playerPosition);
    }

    @Override
    public PlayerPosition update(Long id, PlayerPosition playerPosition) {
        PlayerPosition existingPosition = playerPositionRepository.findById(id).orElseThrow(() -> new RuntimeException("Posicion no encontrada"));
        existingPosition.setName(playerPosition.getName());
        existingPosition.setDescription(playerPosition.getDescription());

        return playerPositionRepository.save(existingPosition);
    }

    @Override
    public void delete(Long id) {
        PlayerPosition existingPosition = playerPositionRepository.findById(id).orElseThrow(() -> new RuntimeException("Posicion no encontrada"));

        playerPositionRepository.delete(existingPosition);
    }

    @Override
    public List<PlayerPosition> getAll() {
        return playerPositionRepository.findAll();
    }
}
