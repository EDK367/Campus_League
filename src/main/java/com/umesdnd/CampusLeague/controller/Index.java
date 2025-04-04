package com.umesdnd.CampusLeague.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Index {

    @GetMapping("/campus_league")
    public String index(){
        return "Bienvenido al index de Campus League";
    }

}
