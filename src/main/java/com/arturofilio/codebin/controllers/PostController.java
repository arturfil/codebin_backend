package com.arturofilio.codebin.controllers;

import java.util.Date;

import com.arturofilio.codebin.models.requests.PostCreateRequestModel;
import com.arturofilio.codebin.models.responses.PostRest;
import com.arturofilio.codebin.services.IPostService;
import com.arturofilio.codebin.shared.Dto.PostCreationDto;
import com.arturofilio.codebin.shared.Dto.PostDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
}
