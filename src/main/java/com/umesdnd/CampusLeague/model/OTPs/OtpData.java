package com.umesdnd.CampusLeague.model.OTPs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpData {

    private String email;
    private String code;
    private LocalDateTime expirationTime;

    public boolean isExpired() {
        return expirationTime.isBefore(LocalDateTime.now());
    }
}
