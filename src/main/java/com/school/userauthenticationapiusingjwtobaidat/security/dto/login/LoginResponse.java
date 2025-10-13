package com.school.userauthenticationapiusingjwtobaidat.security.dto.login;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String message;
}