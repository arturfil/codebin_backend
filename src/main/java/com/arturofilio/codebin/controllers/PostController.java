package com.arturofilio.codebin.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.arturofilio.codebin.models.requests.PostCreateRequestModel;
import com.arturofilio.codebin.models.responses.PostRest;
import com.arturofilio.codebin.services.IPostService;
import com.arturofilio.codebin.services.IUserService;
import com.arturofilio.codebin.shared.Dto.PostCreationDto;
import com.arturofilio.codebin.shared.Dto.PostDto;
import com.arturofilio.codebin.shared.Dto.UserDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    ModelMapper mapper;

    @Autowired
    IPostService postService;

    @Autowired
    IUserService userService;

    @PostMapping
    public PostRest createPost(@RequestBody PostCreateRequestModel createRequestModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getPrincipal().toString();
        PostCreationDto postCreationDto = mapper.map(createRequestModel, PostCreationDto.class);
        postCreationDto.setUserEmail(email);
        PostDto postDto = postService.createPost(postCreationDto);
        PostRest postToReturn = mapper.map(postDto, PostRest.class);
        if (postToReturn.getExpiresAt().compareTo(new Date(System.currentTimeMillis())) < 0) {
            postToReturn.setExpired(true);
        }
        return postToReturn;
    }

    @GetMapping(path = "/last")
    public List<PostRest> lastPosts() {
        List<PostDto> posts = postService.getLastPosts();
        List<PostRest> postRests = new ArrayList<>();
        for (PostDto post : posts) {
            PostRest postRest = mapper.map(post, PostRest.class);
            postRests.add(postRest);
        }
        return postRests;
    }

    @GetMapping(path = "/{id}")
    public PostRest getPost(@PathVariable String id) {
        PostDto postDto = postService.getPost(id);
        PostRest postRest = mapper.map(postDto, PostRest.class);
       
        if (postRest.getExpiresAt().compareTo(new Date(System.currentTimeMillis())) < 0) {
            postRest.setExpired(true);
        }
        if (postRest.getExposure().getId() == 1 || postRest.getExpired())  {// 1 == Private
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDto user = userService.getUser(authentication.getPrincipal().toString());
            
            if (user.getId() != postDto.getUser().getId()) {
                throw new RuntimeException("You aren't Autorized");
            }
        }
        return postRest;
    }
}
