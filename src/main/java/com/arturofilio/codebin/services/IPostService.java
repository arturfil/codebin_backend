package com.arturofilio.codebin.services;

import java.util.List;

import com.arturofilio.codebin.shared.Dto.PostCreationDto;
import com.arturofilio.codebin.shared.Dto.PostDto;

public interface IPostService {
    public List<PostDto> getLastPosts();
    public PostDto  createPost(PostCreationDto post);
    public PostDto getPost(String postId);
    public PostDto updatePost(String postId, long userId, PostCreationDto postUpdateDto);
    public void deletePost(String postId, long userId);
}
