package com.umesdnd.CampusLeague.service;


import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.Inscription;
import com.umesdnd.CampusLeague.model.Tournament;
import com.umesdnd.CampusLeague.model.User;
import com.umesdnd.CampusLeague.repository.InscriptionRepository;
import com.umesdnd.CampusLeague.service.interfaces.InscriptionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InscriptionService implements InscriptionServiceInterface {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private UserService userService;

    @Override
    public Inscription getById(Long id) {
        return inscriptionRepository.findById(id).orElseThrow(() -> new NewExceptionType("Inscription not found", HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Inscription saveOne(Inscription inscription) {

        if (inscription == null) {
            throw  new NewExceptionType("Inscription cannot be null", HttpStatus.BAD_REQUEST);
        }

        if (inscription.getTournament() == null) {
            throw new NewExceptionType("Tournament cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (inscription.getTournament().getId() == null) {
            throw new NewExceptionType("Tournament id cannot be null", HttpStatus.BAD_REQUEST);
        }
        Tournament tournament = tournamentService.getById(inscription.getTournament().getId());

        if (inscription.getUser() == null) {
            throw new NewExceptionType("User cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (inscription.getUser().getId() == null) {
            throw new NewExceptionType("User id cannot be null", HttpStatus.BAD_REQUEST);
        }
        User user = userService.getUserId(inscription.getUser().getId());

        if (user.getStatus() == null) {
            throw new NewExceptionType("User is not active", HttpStatus.BAD_REQUEST);
        }
        if (user.getStatus().getId() != 1) {
            throw new NewExceptionType("User is not active", HttpStatus.BAD_REQUEST);
        }

        if (inscriptionRepository.existsByTournamentId(tournament.getId())) {
            throw new NewExceptionType("Tournament and Inscription already exists", HttpStatus.BAD_REQUEST);
        }

        if (tournament.getEnd_date().isBefore(LocalDateTime.now())) {
            throw new NewExceptionType("Tournament is not available", HttpStatus.BAD_REQUEST);
        }

        inscription.setTournament(tournament);
        inscription.setUser(user);
        return inscriptionRepository.save(inscription);
    }

    @Override
    public Inscription update(Long id, Inscription inscription) {
        Inscription existingInscription = inscriptionRepository.findById(id).orElseThrow(() -> new NewExceptionType("Inscription not found", HttpStatus.NOT_FOUND));
        if (inscription.getTournament() != null) {
            existingInscription.setTournament(inscription.getTournament());
        }
        if (inscription.getUser() != null) {
            existingInscription.setUser(inscription.getUser());
        }
        if (inscription.getOpen_date() != null) {
            existingInscription.setOpen_date(inscription.getOpen_date());
        }
        if (inscription.getClose_date() != null) {
            existingInscription.setClose_date(inscription.getClose_date());
        }

        if (inscription.getOpen_date().isAfter(inscription.getClose_date())) {
            throw new NewExceptionType("Open date cannot be after close date", HttpStatus.BAD_REQUEST);
        }
        if (inscription.getOpen_date().isBefore(LocalDateTime.now())) {
            throw new NewExceptionType("Open date cannot be before now", HttpStatus.BAD_REQUEST);
        }
        return inscriptionRepository.save(existingInscription);
    }

    // pendiente
    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Inscription> getAll() {
        return inscriptionRepository.findAll();
    }
}
