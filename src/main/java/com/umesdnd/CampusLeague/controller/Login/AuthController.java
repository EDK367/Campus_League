package com.umesdnd.CampusLeague.controller.Login;

import com.umesdnd.CampusLeague.model.DTO.AuthenticationResponse;
import com.umesdnd.CampusLeague.model.DTO.LoginRequest;
import com.umesdnd.CampusLeague.service.Token.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
        //System.out.println("entra");
        return ResponseEntity.ok(authService.login(request));
    }
}
