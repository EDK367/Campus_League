package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.Tournament;
import com.umesdnd.CampusLeague.repository.TournamentRepository;
import com.umesdnd.CampusLeague.service.interfaces.TournamentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService implements TournamentServiceInterface{

    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private StatusService statusService;

    @Override
    public Tournament getById(Long id) {
        return tournamentRepository.findById(id).orElseThrow(() -> new NewExceptionType("Torneo no encontrado", HttpStatus.NOT_FOUND));
    }

    @Override
    public Tournament saveOne(Tournament tournament) {return tournamentRepository.save(tournament);}

    @Override
    public Tournament update(Long id, Tournament tournament) {
        Tournament existingTournament = tournamentRepository.findById(id).orElseThrow(() -> new RuntimeException("Torneo no encontrado"));

        existingTournament.setTournament_name(tournament.getTournament_name());
        existingTournament.setSport(tournament.getSport());
        existingTournament.setStart_date(tournament.getStart_date());
        existingTournament.setEnd_date(tournament.getEnd_date());
        existingTournament.setDescription(tournament.getDescription());
        existingTournament.setMax_team_members(tournament.getMax_team_members());
        existingTournament.setMin_team_members(tournament.getMin_team_members());
        existingTournament.setStatus(tournament.getStatus());

        return tournamentRepository.save(existingTournament);
    }

    @Override
    public void delete(Long id) {
        if (!tournamentRepository.existsById(id)){
            throw new RuntimeException("Torneo con ID " + id + " no encontrado");
        }
        tournamentRepository.deleteById(id);
        Tournament existingTournament = tournamentRepository.findById(id).orElseThrow(() -> new NewExceptionType("Tournament not found", HttpStatus.NOT_FOUND));
        existingTournament.setStatus(statusService.getById(2L));
        tournamentRepository.save(existingTournament);
    }

    @Override
    public List<Tournament> getAll() { return tournamentRepository.findAll();}
}
