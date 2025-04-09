package com.umesdnd.CampusLeague.service.Token;

import com.umesdnd.CampusLeague.model.DTO.AuthenticationResponse;
import com.umesdnd.CampusLeague.model.DTO.LoginRequest;
import com.umesdnd.CampusLeague.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = (UserDetails) userRepository.findByUsername(request.getUsername()).orElseThrow();
        System.out.println(user.getPassword());
        String token = jwtService.getToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
    /*
    public Object register(RegisterRequest request) {
        return null;
    }
    */
}
