package com.umesdnd.CampusLeague.controller.Login;

import com.umesdnd.CampusLeague.model.DTO.AuthenticationResponse;
import com.umesdnd.CampusLeague.model.DTO.EmailForPassword;
import com.umesdnd.CampusLeague.model.DTO.LoginRequest;
import com.umesdnd.CampusLeague.model.DTO.NewPassword;
import com.umesdnd.CampusLeague.service.OtpService;
import com.umesdnd.CampusLeague.service.Token.AuthService;
import com.umesdnd.CampusLeague.service.UserService;
import com.umesdnd.CampusLeague.service.interfaces.EmailForPasswordServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Autowired
    private EmailForPasswordServiceInterface emailForPasswordService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
        //System.out.println("entra");
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/request-reset")
    public ResponseEntity<String> requestReset(@RequestBody EmailForPassword email){
        if (emailForPasswordService.sendEmail(email)) {
            return new ResponseEntity<>("Email enviado, verifique en su bandeja de entrada o spam", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se pudo enviar el email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/verify-code")
    public ResponseEntity<AuthenticationResponse> verifyCode(@RequestParam String email, @RequestParam String code) {
        String token = otpService.verifyCodeJwt(email, code);
        AuthenticationResponse response = new AuthenticationResponse(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody NewPassword newPassword) {
        userService.newPassword(newPassword.getToken(), newPassword);
        return new ResponseEntity<>("Contraseña actualizada", HttpStatus.OK);
    }

}
