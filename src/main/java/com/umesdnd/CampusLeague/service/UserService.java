package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.BadRequestException;
import com.umesdnd.CampusLeague.model.Status;
import com.umesdnd.CampusLeague.model.User;
import com.umesdnd.CampusLeague.repository.UserRepository;
import com.umesdnd.CampusLeague.service.interfaces.UserInterfaceService;
import com.umesdnd.CampusLeague.utills.BCryptPassService;
import com.umesdnd.CampusLeague.utills.DecipherPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserInterfaceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatusService statusService;

    @Autowired
    private DecipherPasswordService decipherPasswordService;

    @Autowired
    private BCryptPassService bCryptPassService;

    @Override
    public User getUserId(Long idUser) {
        return this.userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public User saveUser(User user) {
        if (user == null) {
            throw new BadRequestException("No se recivio ningun usuario");
        }

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new BadRequestException("El nombre de usuario no puede estar vacío");
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new BadRequestException("La contraseña no puede estar vacía");
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new BadRequestException("El correo electrónico no puede estar vacío");
        }

        if (user.getStatus() == null) {
            user.setStatus(statusService.getById(1L));
        }
        if (this.userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new BadRequestException("El username del usuario ya existe");
        }

        if (this.userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("El email del usuario ya existe");
        }


        String pass = user.getPassword();
        pass = decipherPasswordService.getDecipherPasswordUser(pass);
        pass = bCryptPassService.getBCriptPasswordUser(pass);
        user.setPassword(pass);
        Status fullStatus = statusService.getById(user.getStatus().getId());
        user.setStatus(fullStatus);
        //System.out.println(user);
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(Long idUser, User user) {
        user.setId(idUser);
        User newPass = this.userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if(!newPass.getPassword().equals(user.getPassword())) {
            user.setPassword(newPass.getPassword());
        }
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(Long idUser) {
        User user = this.userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Status status = user.getStatus();
        status.setId(2L);
        user.setStatus(status);
        this.userRepository.save(user);
    }
}
