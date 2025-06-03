package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.service.interfaces.StatisticsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("estadistica")
public class StatisticsController {

    @Autowired
    private StatisticsServiceInterface statisticsService;

    @GetMapping("/equipos")
    public ResponseEntity<Map<String, Object>> getStatisticsTeams() {
        return new ResponseEntity<>(statisticsService.getStatisticsTeams(), HttpStatus.OK);
    }
}
