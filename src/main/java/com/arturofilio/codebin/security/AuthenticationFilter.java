package com.arturofilio.codebin.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arturofilio.codebin.SpringApplicationContext;
import com.arturofilio.codebin.models.requests.UserLoginRequestModel;
import com.arturofilio.codebin.services.IUserService;
import com.arturofilio.codebin.shared.Dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {
            try {
                UserLoginRequestModel userModel = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequestModel.class);                
                return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        userModel.getEmail(), 
                        userModel.getPassword(), 
                        new ArrayList<>())
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, 
        HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
            String username = ((User) authentication.getPrincipal()).getUsername();
            String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_DATE))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                .compact();
        
        IUserService userService = (IUserService) SpringApplicationContext.getBean("userService");
        UserDto userDto = userService.getUser(username);
        response.addHeader("UserId", userDto.getUserId());
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        
    }
}