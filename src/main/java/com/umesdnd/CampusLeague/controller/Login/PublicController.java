package com.umesdnd.CampusLeague.controller.Login;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campus_league")
@RequiredArgsConstructor
public class PublicController {

    @GetMapping("/home")
    public String home(){
        return "Bienvenido a Campus League";
    }
}
