package com.school.userauthenticationapiusingjwtobaidat.security.model;

import com.school.userauthenticationapiusingjwtobaidat.security.JwtKeyProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class AccessToken implements Token {

    private final JwtKeyProvider jwtKeyProvider;

    public AccessToken(JwtKeyProvider jwtKeyProvider) {
        this.jwtKeyProvider = jwtKeyProvider;
    }

    @Value("${jwt.access-expiration}")
    Long expiration;

    @Override
    public String createRefreshToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .claim("type", "access")
                .signWith(jwtKeyProvider.getAccessKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtKeyProvider.getAccessKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        Claims claims = extractAllClaims(token);
        String username = claims.getSubject();
        Date expirationDate = claims.getExpiration();

        return username.equals(userDetails.getUsername()) && expirationDate.after(new Date());
    }

    @Override
    public String getTokenType() {
        return "access";
    }
}
