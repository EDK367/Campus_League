package com.umesdnd.CampusLeague.utills;

import org.springframework.stereotype.Service;

@Service
public class DecipherPasswordService {

    private String decipherPasswordUser(String password){

        return password;
    }

    public String getDecipherPasswordUser(String password){
        return decipherPasswordUser(password);
    }
}
