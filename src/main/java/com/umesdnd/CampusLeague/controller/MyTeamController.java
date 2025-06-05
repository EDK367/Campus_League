package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.DTO.TeamDTO;
import com.umesdnd.CampusLeague.model.Team;
import com.umesdnd.CampusLeague.service.interfaces.MyTeamInfoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mi-equipo")
public class MyTeamController {

    @Autowired
    private MyTeamInfoServiceInterface myTeamInfoService;

    @GetMapping("/{code}")
    public ResponseEntity<TeamDTO> getMyTeamInfo(@PathVariable String code) {
        TeamDTO team = myTeamInfoService.getMyTeamInfo(code);
        //System.out.println(team);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
}
