package com.umesdnd.CampusLeague.service.interfaces;

import com.umesdnd.CampusLeague.model.PlayerPosition;
import com.umesdnd.CampusLeague.service.interfaces.common.CrudServiceInterface;

import java.util.List;

public interface PlayerPositionServiceInterface extends CrudServiceInterface<PlayerPosition> {

    public List<PlayerPosition> getPositionSport(Long idSport);
}
