package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.User;
import com.umesdnd.CampusLeague.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    // debuging
    //private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/{idUser}")
    public User getUserById(@PathVariable Long idUser){
        System.out.println(idUser);
        return this.userService.getUserId(idUser);
    }

}
