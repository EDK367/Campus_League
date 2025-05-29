package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.TournamentGroup;
import com.umesdnd.CampusLeague.repository.TournamentGroupRepository;
import com.umesdnd.CampusLeague.repository.TournamentRepository;
import com.umesdnd.CampusLeague.service.interfaces.TournamentGroupServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentGroupService implements TournamentGroupServiceInterface {

    @Autowired
    private TournamentGroupRepository tournamentGRepository;

    @Override
    public TournamentGroup getById(Long id) {
        return tournamentGRepository.findById(id).orElseThrow(() -> new NewExceptionType("Grupo no encontrado", HttpStatus.NOT_FOUND));
    }

    @Override
    public TournamentGroup saveOne(TournamentGroup tournamentGroup) {
        if (tournamentGroup.getName()  == null || tournamentGroup.getName().isBlank()) {
            throw new NewExceptionType("El nombre es requerido", HttpStatus.BAD_REQUEST);
        }
        if (tournamentGRepository.findByName(tournamentGroup.getName()).isPresent()) {
            throw new NewExceptionType("El nombre ya existe", HttpStatus.BAD_REQUEST);
        }
        return tournamentGRepository.save(tournamentGroup);
    }

    @Override
    public TournamentGroup update(Long id, TournamentGroup tournamentGroup) {
        TournamentGroup existingTournamentGroup = getById(id);
        if (existingTournamentGroup == null) {
            throw new NewExceptionType("Grupo de torneo no encontrado", HttpStatus.NOT_FOUND);
        }
        if (tournamentGroup.getName()  == null || tournamentGroup.getName().isBlank()) {
            throw new NewExceptionType("Nombre es requerido", HttpStatus.BAD_REQUEST);
        }

        TournamentGroup existingTournamentGroupWithName = tournamentGRepository.findByName(tournamentGroup.getName()).orElse(null);
        if (existingTournamentGroupWithName != null) {
            if (existingTournamentGroupWithName.getId() != id) {
                throw new NewExceptionType("El nombre ya existe", HttpStatus.BAD_REQUEST);
            }
        }
        existingTournamentGroup.setName(tournamentGroup.getName());
        return tournamentGRepository.save(existingTournamentGroup);
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public List<TournamentGroup> getAll() {
        return tournamentGRepository.findAll();
    }
}
