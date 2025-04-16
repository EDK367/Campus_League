package com.umesdnd.CampusLeague.service.interfaces;

import com.umesdnd.CampusLeague.model.Player;
import com.umesdnd.CampusLeague.service.interfaces.common.CrudServiceInterface;

import java.util.List;

public interface PlayerServiceInterface extends CrudServiceInterface<Player> {
    List<Player> findByTeamId(Long teamId);
}
