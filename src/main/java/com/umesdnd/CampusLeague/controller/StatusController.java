package com.umesdnd.CampusLeague.controller;


import com.umesdnd.CampusLeague.model.Status;
import com.umesdnd.CampusLeague.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("estado")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping("")
    public ResponseEntity<List<Status>> getStatus(){
        List<Status> status = statusService.getAll();
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
