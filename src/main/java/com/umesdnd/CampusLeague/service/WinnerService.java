package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.Team;
import com.umesdnd.CampusLeague.model.Tournament;
import com.umesdnd.CampusLeague.model.Winner;
import com.umesdnd.CampusLeague.repository.WinnerRepository;
import com.umesdnd.CampusLeague.service.interfaces.WinnerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WinnerService implements WinnerServiceInterface {

    @Autowired
    private WinnerRepository winnerRepository;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private TeamService teamService;

    @Override
    public Winner getById(Long id) {
        return winnerRepository.findById(id).orElseThrow(() -> new NewExceptionType("Ganador no encontrado", HttpStatus.NOT_FOUND));
    }

    @Override
    public Winner saveOne(Winner winner) {
        if (winner == null) {
            throw new NewExceptionType("El ganador no puede ser nulo", HttpStatus.BAD_REQUEST);
        }
        if (winner.getTournament() == null) {
            throw new NewExceptionType("El torneo no puede ser nulo", HttpStatus.BAD_REQUEST);
        }
        if (winner.getTeam() == null) {
            throw new NewExceptionType("El equipo no puede ser nulo", HttpStatus.BAD_REQUEST);
        }
        if (winner.getTournament().getId() == null) {
            throw new NewExceptionType("El torneo no puede ser nulo", HttpStatus.BAD_REQUEST);
        }
        Tournament tournament = tournamentService.getById(winner.getTournament().getId());
        winner.setTournament(tournament);
        if (winner.getTeam().getId() == null) {
            throw new NewExceptionType("El equipo no puede ser nulo", HttpStatus.BAD_REQUEST);
        }
        Team winnerTeam = teamService.getById(winner.getTeam().getId());
        winner.setTeam(winnerTeam);
        if (tournament.getEnd_date().isAfter(LocalDateTime.now())) {
            throw new NewExceptionType("El torneo ya ha terminado", HttpStatus.BAD_REQUEST);
        }

        if (winner.getPosition() < 1 ) {
            throw new NewExceptionType("La posicion no puede ser menor a 1", HttpStatus.BAD_REQUEST);
        }

        if (winnerRepository.existsByTournamentIdAndTeamIdAndPositionNot(tournament.getId(), winnerTeam.getId(), 0)) {
            throw new NewExceptionType("El ganador ya existe", HttpStatus.BAD_REQUEST);
        }

        return winnerRepository.save(winner);
    }

    @Override
    public Winner update(Long id, Winner winner) {
        Winner existingWinner = winnerRepository.findById(id).orElseThrow(() -> new NewExceptionType("Ganador no encontrado", HttpStatus.NOT_FOUND));

        if (winner.getTournament() != null) {
            Tournament tournament = tournamentService.getById(winner.getTournament().getId());
            if (tournament.getEnd_date().isAfter(LocalDateTime.now())) {
                throw new NewExceptionType("El torneo ya ha terminado", HttpStatus.BAD_REQUEST);
            }
            existingWinner.setTournament(tournament);
        }
        if (winner.getTeam() != null) {
            Team winnerTeam = teamService.getById(winner.getTeam().getId());
            existingWinner.setTeam(winnerTeam);
        }
        if (winner.getPosition() != 0) {
            existingWinner.setPosition(winner.getPosition());
        }

        if (winner.getPosition() < 1 ) {
            throw new NewExceptionType("La posicon no puede ser meno a 1", HttpStatus.BAD_REQUEST);
        }

        if (winnerRepository.existsByTournamentIdAndPositionAndTeamIdIsNot(existingWinner.getTournament().getId(), winner.getPosition(), existingWinner.getTeam().getId())) {
            throw new NewExceptionType("El ganador ya existe", HttpStatus.BAD_REQUEST);
        }

        return winnerRepository.save(existingWinner);
    }

    // pendiente
    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Winner> getAll() {
        return winnerRepository.findAll();
    }
}
