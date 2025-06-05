package com.umesdnd.CampusLeague.service.interfaces;

import com.umesdnd.CampusLeague.model.DTO.PlayersDTO;
import com.umesdnd.CampusLeague.model.DTO.TeamDTO;
import com.umesdnd.CampusLeague.model.Team;
import com.umesdnd.CampusLeague.model.TeamPlayer;
import com.umesdnd.CampusLeague.service.interfaces.common.CrudServiceInterface;

import java.util.List;

public interface TeamServiceInterface extends CrudServiceInterface<Team> {
    TeamDTO getWithPlayers(Long id);
    Team activeTeam(Long id);
    List<TeamPlayer> playersTeamsId(Long id);
    List<PlayersDTO> playersTeamsIdDTO(Long id);
}
