package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.User;
import com.umesdnd.CampusLeague.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{idUser}")
    public User getUserById(@PathVariable Long idUser){
        //System.out.println(idUser);
        return this.userService.getUserId(idUser);
    }

    @PostMapping("")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        try {
            User savedUser = userService.saveUser(user);
            //System.out.println(savedUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<User> updateUser(@PathVariable Long idUser, @RequestBody User user){
        try {
            //System.out.println(user);
            User userUpdateNew = userService.updateUser(idUser, user);
            return ResponseEntity.status(HttpStatus.OK).body(userUpdateNew);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<String> deleteUser(@PathVariable Long idUser){
        try {
            userService.deleteUser(idUser);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario");
        }
    }
}
