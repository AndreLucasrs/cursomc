package com.andrelrs.cursomc.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

import static java.util.Objects.nonNull;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public boolean tokenValido(String token) {

        Claims claims = getClaims(token);

        if (nonNull(claims)) {

            String userName = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            if (nonNull(userName) && nonNull(expirationDate) && now.before(expirationDate)) {
                return true;
            }
        }
        return false;
    }

    public String getUserName(String token) {

        Claims claims = getClaims(token);

        if (nonNull(claims)) {

            return claims.getSubject();
        }

        return null;
    }

    private Claims getClaims(String token) {

        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }
}