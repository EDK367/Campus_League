package com.umesdnd.CampusLeague.service.interfaces;

import java.util.List;
import java.util.Map;

public interface StatisticsServiceInterface {
    Map<String, Object> getStatisticsTournaments();
    Map<String, Object> getStatisticsTeams();
    List<Map<String, Object>> getStatisticsPlayers();
    List<Map<String, Object>> getStatisticsMatches();

}
