package com.umesdnd.CampusLeague.Hash;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashPass {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String plainPassword = "1234";

        String hashedPassword = encoder.encode(plainPassword);

        for (int i = 0; i < 20; i++) {
            System.out.println("hjola " + i);
        }
        System.out.println("Contraseña encriptada: " + hashedPassword);
    }
}
