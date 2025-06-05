package com.umesdnd.CampusLeague.service;

import com.umesdnd.CampusLeague.exception.NewExceptionType;
import com.umesdnd.CampusLeague.model.OTPs.OtpData;
import com.umesdnd.CampusLeague.service.Token.JwtPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    @Autowired
    private JwtPasswordService jwtPasswordService;

    private final Map<String, OtpData> otpStore = new ConcurrentHashMap<>();

    public String generateCode(String email) {
        String code = String.valueOf(new Random().nextInt(900000) + 100000);
        OtpData otp = new OtpData(email, code, LocalDateTime.now().plusMinutes(10));
        otpStore.put(email, otp);
        return code;
    }

    private boolean validateCode(String email, String code) {
        OtpData otp = otpStore.get(email);
        return otp != null && otp.getCode().equals(code) && !otp.isExpired();
    }

    private void removeCode(String email) {
        otpStore.remove(email);
    }

    public String verifyCodeJwt(String email, String code) {
        if (validateCode(email, code)) {
            removeCode(email);
            String jwt = jwtPasswordService.generateTempToken(email);
            return jwt;
        } else {
            throw new NewExceptionType("Código inválido o expirado", HttpStatus.UNAUTHORIZED);
        }
    }

}
