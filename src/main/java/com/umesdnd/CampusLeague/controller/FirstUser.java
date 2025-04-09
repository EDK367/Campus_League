package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.User;
import com.umesdnd.CampusLeague.service.FirstUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campus_league/api")
public class FirstUser {

    @Autowired
    private FirstUserService firstUserService;

    @GetMapping("/first")
    public void getFirstUser(){
        //System.out.println(idUser);
        this.firstUserService.createFirstUser();
    }
}
