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

import java.time.LocalDateTime;
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
    @Autowired
    private StatusService statusService;


    @Override
    public TournamentTeam getById(Long id) {
        return tournamentTRepository.findById(id).orElseThrow(
                () -> new NewExceptionType("Tournament Team not found", HttpStatus.NOT_FOUND));
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
        tournamentTeam.setTournament(tournament);
        if (tournamentTeam.getTeam().getId() == null) {
            throw new NewExceptionType("Team cannot be null", HttpStatus.BAD_REQUEST);
        }
        Team team = teamService.getById(tournamentTeam.getTeam().getId());
        team.setStatus(statusService.getById(1L));
        tournamentTeam.setTeam(team);
        if (tournamentTeam.getGroup().getId() == null) {
            throw new NewExceptionType("Group cannot be null", HttpStatus.BAD_REQUEST);
        }
        TournamentGroup group = tournamentGroupService.getById(tournamentTeam.getGroup().getId());
        tournamentTeam.setGroup(group);
        if (tournament.getEnd_date().isBefore(LocalDateTime.now())) {
            throw new NewExceptionType("Tournament has already ended", HttpStatus.BAD_REQUEST);
        }

       /* if (team.getPlayers().size() > tournament.getMax_team_members() || team.getPlayers().size() < tournament.getMin_team_members()) {
            throw new NewExceptionType("Team is not valid", HttpStatus.BAD_REQUEST);
        }
        */


        return tournamentTRepository.save(tournamentTeam);
    }

    // pendiente
    @Transactional
    public TournamentTeam update(Long id, TournamentTeam tournamentTeam) {
        return null;
    }


    // pendiente
    @Override
    public void delete(Long id) {

    }

    @Override
    public List<TournamentTeam> getAll() {
        return tournamentTRepository.findAll();
    }
}
