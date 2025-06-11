package com.umesdnd.CampusLeague.service.interfaces;

import com.umesdnd.CampusLeague.model.Match;
import com.umesdnd.CampusLeague.service.interfaces.common.CrudServiceInterface;

import java.util.List;


public interface MatchServiceInterface extends CrudServiceInterface<Match> {
    List<Match> generateMatches(Long idTournament);
    List<Match> generateMatch(Match match);
}
