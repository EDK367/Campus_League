package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.model.TeamPlayer;
import com.umesdnd.CampusLeague.repository.TeamPlayerRepository;
import com.umesdnd.CampusLeague.service.interfaces.TeamPlayerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamPlayerService implements TeamPlayerServiceInterface {

    @Autowired
    private TeamPlayerRepository teamPlayerRepository;

    @Override
    public List<TeamPlayer> positionTeam(Long id) {
        List<TeamPlayer> teamPlayers = teamPlayerRepository.findByTeamId(id);
        return teamPlayers;
    }

}
