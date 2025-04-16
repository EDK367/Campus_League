package com.umesdnd.CampusLeague.utills;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptPassService {

    private String bcriptPasswordUser(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);

        return hashedPassword;
    }

    public String getBCriptPasswordUser(String password){
        return bcriptPasswordUser(password);
    }

}
