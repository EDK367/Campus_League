package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.model.Status;
import com.umesdnd.CampusLeague.model.User;
import com.umesdnd.CampusLeague.repository.UserRepository;
import com.umesdnd.CampusLeague.service.interfaces.UserInterfaceService;
import com.umesdnd.CampusLeague.utills.BCryptPass;
import com.umesdnd.CampusLeague.utills.DecipherPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserInterfaceService {

    @Autowired
    private UserRepository userRepository;

    private DecipherPassword decipherPassword = new DecipherPassword();
    private BCryptPass bCryptPass = new BCryptPass();

    @Override
    public User getUserId(Long idUser) {
        User user = this.userRepository.findById(idUser).orElse(null);
        return user;
    }

    @Override
    public void saveUser(User user) {
        String pass = user.getPassword();
        pass = decipherPassword.getDecipherPasswordUser(pass);
        pass = bCryptPass.getBCriptPasswordUser(pass);
        user.setPassword(pass);
        //System.out.println(user);
        this.userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        String pass = user.getPassword();
        pass = decipherPassword.getDecipherPasswordUser(pass);
        pass = bCryptPass.getBCriptPasswordUser(pass);
        user.setPassword(pass);
        this.userRepository.save(user);
    }

    @Override
    public void deleteUser(Long idUser) {
        User user = this.userRepository.findById(idUser).orElse(null);
        Status status = user.getStatus();
        status.setId(Long.valueOf(2));
        user.setStatus(status);
        this.userRepository.save(user);
    }
}
