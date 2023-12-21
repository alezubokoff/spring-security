package com.zubokoff.springsecurity.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zubokoff.springsecurity.dtos.AuthenticationDTO;
import com.zubokoff.springsecurity.dtos.ErrorDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;

public class WebSecurityFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BASIC = "Basic ";
    private static final String BEARER = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (isBasicAuthentication(request)) {
            AuthenticationDTO authenticationDTO = authentication(request);
            response.setContentType("application/json");
            response.getWriter().print(new ObjectMapper().writeValueAsString(
                    authenticationDTO
            ));
            response.getWriter().flush();
            return;
        }


        if (isBearerAuthentication(request)) {
            Authentication auth = decodeBearer(request);
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                ErrorDTO error = getError();
                response.setStatus(error.getStatus());
                response.setContentType("application/json");
                response.getWriter().print(new ObjectMapper().writeValueAsString(error));
                response.getWriter().flush();
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    public boolean isBasicAuthentication(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return header != null && header.startsWith(BASIC);
    }

    public boolean isBearerAuthentication(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return header != null && header.startsWith(BEARER);
    }

    public AuthenticationDTO authentication(HttpServletRequest request) {
        var base64 = Base64.getDecoder().decode(request.getHeader(AUTHORIZATION).replace(BASIC, ""));
        String[] credentials = new String(base64).split(":");
        String bearer = new BCryptPasswordEncoder().encode(credentials[0] + credentials[1]);
        String username = credentials[0];
        return new AuthenticationDTO(username,  bearer);
    }

    public Authentication decodeBearer(HttpServletRequest request) {
        if (request.getHeader(AUTHORIZATION).equals(BEARER + "zubokoff")) {
            return new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
        }
        return null;
    }

    public ErrorDTO getError() {
        return new ErrorDTO(HttpStatus.UNAUTHORIZED.value(), "Usuário não autorizado para essa solicitação");
    }
}
