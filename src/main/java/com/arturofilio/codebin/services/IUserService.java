package com.arturofilio.codebin.services;

import com.arturofilio.codebin.shared.Dto.UserDto;

public interface IUserService {
    public UserDto createUser(UserDto user);
}
