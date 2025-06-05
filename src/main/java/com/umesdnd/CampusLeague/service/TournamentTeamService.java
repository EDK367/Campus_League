package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.Team;
import com.umesdnd.CampusLeague.model.Tournament;
import com.umesdnd.CampusLeague.model.TournamentGroup;
import com.umesdnd.CampusLeague.model.TournamentTeam;
import com.umesdnd.CampusLeague.repository.TournamentTeamRepository;
import com.umesdnd.CampusLeague.service.interfaces.TournamentTeamServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.List;

@Service
public class TournamentTeamService implements TournamentTeamServiceInterface {

    @Autowired
    private TournamentTeamRepository tournamentTRepository;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TournamentGroupService tournamentGroupService;


    @Override
    public TournamentTeam getById(Long id) {
        return tournamentTRepository.findById(id).orElseThrow(
                () -> new NewExceptionType("Tournament team not found", HttpStatus.NOT_FOUND));
    }

    @Transactional
    public TournamentTeam saveOne(TournamentTeam tournamentTeam) {
        System.out.println("aca esta torneo" + tournamentTeam);
        if (tournamentTeam == null) {
            throw new NewExceptionType("Tournament team cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (tournamentTeam.getPoints() == null) {
            tournamentTeam.setPoints(0L);
        }
        if (tournamentTeam.getTournament() == null) {
            throw new NewExceptionType("Tournament cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (tournamentTeam.getTeam() == null) {
            throw new NewExceptionType("Team cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (tournamentTeam.getGroup() == null) {
            throw new NewExceptionType("Group cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (tournamentTeam.getTournament().getId() == null) {
               throw new NewExceptionType("Tournament cannot be null", HttpStatus.BAD_REQUEST);
        }
        Tournament tournament = tournamentService.getById(tournamentTeam.getTournament().getId());
        if (tournamentTeam.getTeam().getId() == null) {
            throw new NewExceptionType("Team cannot be null", HttpStatus.BAD_REQUEST);
        }
        Team team = teamService.getById(tournamentTeam.getTeam().getId());
        if (tournamentTeam.getGroup().getId() == null) {
            throw new NewExceptionType("Group cannot be null", HttpStatus.BAD_REQUEST);
        }
        TournamentGroup group = tournamentGroupService.getById(tournamentTeam.getGroup().getId());



        return null;
    }

    @Override
    public TournamentTeam update(Long id, TournamentTeam tournamentTeam) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<TournamentTeam> getAll() {
        return List.of();
    }
}
