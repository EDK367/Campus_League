package com.umesdnd.CampusLeague.service.interfaces;

import com.umesdnd.CampusLeague.model.User;


public interface UserInterfaceService {

    public User getUserId(Long idUser);

    public User saveUser(User user);

    public User updateUser(Long idUser, User user);

    public void deleteUser(Long idUser);

}
