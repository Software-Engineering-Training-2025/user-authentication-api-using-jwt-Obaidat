package com.school.userauthenticationapiusingjwtobaidat.security.model;

import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

@Component
public interface Token {

    String createRefreshToken(UserDetails userDetails);

    Claims extractAllClaims(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String getTokenType();
}
