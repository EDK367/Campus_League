package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.model.Status;
import com.umesdnd.CampusLeague.model.User;
import com.umesdnd.CampusLeague.repository.StatusRepository;
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

    @Autowired
    private StatusRepository statusRepository;

    private DecipherPassword decipherPassword = new DecipherPassword();
    private BCryptPass bCryptPass = new BCryptPass();

    @Override
    public User getUserId(Long idUser) {
        User user = this.userRepository.findById(idUser).orElse(null);
        return user;
    }

    @Override
    public User saveUser(User user) {
        String pass = user.getPassword();
        pass = decipherPassword.getDecipherPasswordUser(pass);
        pass = bCryptPass.getBCriptPasswordUser(pass);
        user.setPassword(pass);
        Status fullStatus = statusRepository.findById(user.getStatus().getId())
                .orElseThrow(() -> new RuntimeException("Status no encontrado"));
        user.setStatus(fullStatus);
        //System.out.println(user);
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(Long idUser, User user) {
        user.setId(idUser);
        User newPass = this.userRepository.findById(idUser).orElse(null);
        if(!newPass.getPassword().equals(user.getPassword())) {
            user.setPassword(newPass.getPassword());
        }
        return this.userRepository.save(user);
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
