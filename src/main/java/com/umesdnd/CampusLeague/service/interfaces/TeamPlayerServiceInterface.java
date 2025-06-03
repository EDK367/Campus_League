package com.umesdnd.CampusLeague.service.interfaces;

import com.umesdnd.CampusLeague.model.TeamPlayer;

import java.util.List;

public interface TeamPlayerServiceInterface {
    List<TeamPlayer> playersTeamIdP(Long id);
}
