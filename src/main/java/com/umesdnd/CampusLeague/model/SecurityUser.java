package com.umesdnd.CampusLeague.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.umesdnd.CampusLeague.model.User;
import java.util.Collection;
import java.util.Collections;

// informacion para el usuario
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements UserDetails {

    // variable de instancia
    private User user;

    // aca verifica si perteneciente
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ADMIN"));
    }

    // aca se va a desencriptar la password
    @Override
    public String getPassword() {
        return user.getPassword();
    }
 /*try {
            return AESUtil.decrypt(user.getPassword()); // 🔹 Desencripta la contraseña
        } catch (Exception e) {
            throw new RuntimeException("Error al desencriptar la contraseña", e);
        }
        */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

}
