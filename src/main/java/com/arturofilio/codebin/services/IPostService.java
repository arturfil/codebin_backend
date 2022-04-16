package com.arturofilio.codebin.services;

import com.arturofilio.codebin.shared.Dto.PostCreationDto;
import com.arturofilio.codebin.shared.Dto.PostDto;

public interface IPostService {
    public PostDto  createPost(PostCreationDto post);
}
