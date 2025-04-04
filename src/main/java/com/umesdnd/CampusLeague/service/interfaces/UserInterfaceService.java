package com.umesdnd.CampusLeague.service.interfaces;

import com.umesdnd.CampusLeague.model.User;


public interface UserInterfaceService {

    public User getUserId(Long idUser);

    public void saveUser(User user);

    public void updateUser(User user);

    public void deleteUser(Long idUser);

}
