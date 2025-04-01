package com.umesdnd.CampusLeague.service.interfaces;

import com.umesdnd.CampusLeague.model.User;

import java.util.List;

public interface UserInterfaceService {

    public User getUserId(Long idUser);

    public void saveUser(User user);

    public void updateUser(User user);

}
