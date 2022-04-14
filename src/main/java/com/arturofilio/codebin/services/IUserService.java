package com.arturofilio.codebin.services;

import com.arturofilio.codebin.shared.Dto.UserDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    public UserDto createUser(UserDto user);
    public UserDto getUser(String email);
}
