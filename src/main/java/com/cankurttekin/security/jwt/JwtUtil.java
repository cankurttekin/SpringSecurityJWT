package com.cankurttekin.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    //@Value("${app.jwtSecret}")
    //private String SECRET_KEY;
    private SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build(); //or HS384.key() or HS512.key()

    @Value("${app.jwtExpirationInMs}")
    private int EXPIRATION_TIME;

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities())
                .issuedAt(new Date())
                .expiration(expiryDate)
                .signWith(SECRET_KEY)
                .compact();
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        //return Long.parseLong(claims.getSubject()); refactored returns username instead of user id
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().
                    verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(authToken);
            // refactor later this and upper one!
            return true;
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            // Handle exceptions
        }
        return false;
    }

}
