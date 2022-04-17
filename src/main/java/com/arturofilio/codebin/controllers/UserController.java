package com.arturofilio.codebin.controllers;

import java.util.ArrayList;
import java.util.List;

import com.arturofilio.codebin.models.requests.UserDetailsRequestModel;
import com.arturofilio.codebin.models.responses.PostRest;
import com.arturofilio.codebin.models.responses.UserRest;
import com.arturofilio.codebin.services.IUserService;
import com.arturofilio.codebin.shared.Dto.PostDto;
import com.arturofilio.codebin.shared.Dto.UserDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    ModelMapper mapper;

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRest getUser(String str) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getPrincipal().toString();
        UserDto userDto = userService.getUser(email);
        UserRest userToReturn = mapper.map(userDto, UserRest.class);
        BeanUtils.copyProperties(userDto, userToReturn);
        return userToReturn;
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

    @GetMapping(path = "/posts") // localhost:8080
    public List<PostRest> getPosts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getPrincipal().toString();
        List<PostDto> posts = userService.getUserPosts(email);
        List<PostRest> postRests = new ArrayList<>();
        for(PostDto post: posts) {
            PostRest postRest = mapper.map(post, PostRest.class);
            postRests.add(postRest);
        }       
        
        return postRests;
    }
}