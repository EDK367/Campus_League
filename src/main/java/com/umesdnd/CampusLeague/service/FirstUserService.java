package com.umesdnd.CampusLeague.service;


import com.umesdnd.CampusLeague.model.Status;
import com.umesdnd.CampusLeague.model.User;
import com.umesdnd.CampusLeague.repository.StatusRepository;
import com.umesdnd.CampusLeague.repository.UserRepository;
import com.umesdnd.CampusLeague.utills.BCryptPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatusRepository statusRepository;

    BCryptPass bCryptPass = new BCryptPass();

    public void createFirstUser(){

        if (this.statusRepository.count() == 0){
            Status status = new Status();
            status.setStatus_name("Active");
            this.statusRepository.save(status);
        }

        if (this.userRepository.count() == 0){
            User user = new User();
            user.setUsername("edk");
            user.setEmail("XXXXXXXXXXXXXXX");
            user.setPassword(bCryptPass.getBCriptPasswordUser("1234"));
            user.setStatus(this.statusRepository.findById(Long.valueOf(1)).orElse(null));
            this.userRepository.save(user);
        }
    }

}
