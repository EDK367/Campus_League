package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.model.User;
import com.umesdnd.CampusLeague.repository.UserRepository;
import com.umesdnd.CampusLeague.service.interfaces.UserInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserInterfaceService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserId(Long idUser) {
        User user = this.userRepository.findById(idUser).orElse(null);
        return user;
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        this.userRepository.save(user);
    }
}
