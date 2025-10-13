package com.school.userauthenticationapiusingjwtobaidat.security.service;

import com.school.userauthenticationapiusingjwtobaidat.security.TokenFactory;
import com.school.userauthenticationapiusingjwtobaidat.security.enums.TokenType;
import com.school.userauthenticationapiusingjwtobaidat.security.model.Token;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final TokenFactory tokenFactory;

    public JwtService(TokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }

    public String generateToken(UserDetails userDetails, TokenType type) {
        Token token = tokenFactory.getTokenType(type);
        return token.createRefreshToken(userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails, TokenType type) {
        return tokenFactory.getTokenType(type).isTokenValid(token, userDetails);
    }

    public Claims extractAllClaims(String token, TokenType type) {
        return tokenFactory.getTokenType(type).extractAllClaims(token);
    }

    public String getUsername(String token, TokenType type) {
        return tokenFactory.getTokenType(type).extractAllClaims(token).getSubject();
    }
}
