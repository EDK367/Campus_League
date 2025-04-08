package com.umesdnd.CampusLeague.Hash;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashPass {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String plainPassword = "123";

        String hashedPassword = encoder.encode(plainPassword);

        System.out.println("Contraseña encriptada: " + hashedPassword);
    }
}
