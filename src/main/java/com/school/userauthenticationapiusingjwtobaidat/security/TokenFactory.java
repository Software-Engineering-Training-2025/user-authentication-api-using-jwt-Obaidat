package com.school.userauthenticationapiusingjwtobaidat.security;

import com.school.userauthenticationapiusingjwtobaidat.security.enums.TokenType;
import com.school.userauthenticationapiusingjwtobaidat.security.model.AccessToken;
import com.school.userauthenticationapiusingjwtobaidat.security.model.RefreshToken;
import com.school.userauthenticationapiusingjwtobaidat.security.model.Token;
import org.springframework.stereotype.Component;

@Component
public class TokenFactory {

    private final AccessToken accessToken;
    private final RefreshToken refreshToken;

    public TokenFactory(AccessToken accessToken, RefreshToken refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Token getTokenType(TokenType type) {
        return switch (type) {
            case ACCESS -> accessToken;
            case REFRESH -> refreshToken;
        };
    }
}