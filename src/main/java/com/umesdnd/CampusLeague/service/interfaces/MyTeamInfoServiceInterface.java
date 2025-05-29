package com.umesdnd.CampusLeague.service.interfaces;

import com.umesdnd.CampusLeague.model.DTO.TeamDTO;
import com.umesdnd.CampusLeague.model.Team;

public interface MyTeamInfoServiceInterface {
    TeamDTO getMyTeamInfo(String code);
}
