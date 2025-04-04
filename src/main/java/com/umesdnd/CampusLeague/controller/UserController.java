package com.umesdnd.CampusLeague.controller;

import com.umesdnd.CampusLeague.model.User;
import com.umesdnd.CampusLeague.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{idUser}")
    public User getUserById(@PathVariable Long idUser){
        //System.out.println(idUser);
        return this.userService.getUserId(idUser);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        try {
            //System.out.println(user);
            userService.saveUser(user);
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.CREATED).body("Usuario guardado con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el usuario");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody User user){
        try {
            //System.out.println(user);
            userService.updateUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario actualizado con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el usuario");
        }
    }

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<String> deleteUser(@PathVariable Long idUser){
        try {
            userService.deleteUser(idUser);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario");
        }
    }
}
