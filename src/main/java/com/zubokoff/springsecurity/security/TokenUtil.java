package com.zubokoff.springsecurity.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Base64;
import java.util.Collections;

public class TokenUtil {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    public static Authentication decodeToken(HttpServletRequest request) {

        if (request.getHeader(AUTHORIZATION).equals(BEARER + "zubokoff")) {
            return new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
        }
        return null;
    }
}
