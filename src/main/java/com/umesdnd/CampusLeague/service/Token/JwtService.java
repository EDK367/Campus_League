package com.umesdnd.CampusLeague.service.Token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    @Value("${spring.jwt.expiration}")
    private long expirationTime;

    public  String getToken(UserDetails user){
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(getTodayExpiration(18))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    private Date getTodayExpiration(int hour) {
        Calendar now = Calendar.getInstance();
        int currentHour = now.get(Calendar.HOUR_OF_DAY);

        Calendar expirationCalendar = Calendar.getInstance();

        if (currentHour < 8) {
            expirationCalendar.add(Calendar.HOUR_OF_DAY, 3);
        } else {
            expirationCalendar.set(Calendar.HOUR_OF_DAY, hour);
            expirationCalendar.set(Calendar.MINUTE, 0);
            expirationCalendar.set(Calendar.SECOND, 0);
            expirationCalendar.set(Calendar.MILLISECOND, 0);

            if (expirationCalendar.getTime().before(new Date())) {
                expirationCalendar = Calendar.getInstance();
                expirationCalendar.add(Calendar.HOUR_OF_DAY, 3);
            }
        }

        return expirationCalendar.getTime();
    }
}
