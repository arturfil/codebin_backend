package com.arturofilio.codebin.controllers;

import com.arturofilio.codebin.models.requests.UserDetailsRequestModel;
import com.arturofilio.codebin.models.responses.UserRest;
import com.arturofilio.codebin.services.IUserService;
import com.arturofilio.codebin.shared.Dto.UserDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping
    public String getUser() {
        return "GET/user_details";
    }

    @GetMapping("/test")
    public String testing() {
        return "TESTING";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserRest userToReturn = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);
        UserDto createUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createUser, userToReturn);
        return userToReturn;
    }
}