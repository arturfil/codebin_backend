package com.arturofilio.codebin.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.arturofilio.codebin.entities.ExposureEntity;
import com.arturofilio.codebin.entities.PostEntity;
import com.arturofilio.codebin.entities.UserEntity;
import com.arturofilio.codebin.respositories.ExposureRepository;
import com.arturofilio.codebin.respositories.IPostRepository;
import com.arturofilio.codebin.respositories.IUserRepository;
import com.arturofilio.codebin.shared.Dto.PostCreationDto;
import com.arturofilio.codebin.shared.Dto.PostDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {
    
    @Autowired
    IPostRepository postRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    ExposureRepository exposureRepository;

    @Override
    public PostDto createPost(PostCreationDto post) {
        UserEntity userEntity = userRepository.findByEmail(post.getUserEmail());
        ExposureEntity exposureEntity = exposureRepository.findById(post.getExposureId());
        PostEntity postEntity = new PostEntity();

        postEntity.setUser(userEntity);
        postEntity.setExposure(exposureEntity);
        postEntity.setTitle(post.getTitle());
        postEntity.setContent(post.getContent());
        postEntity.setPostId(UUID.randomUUID().toString());
        postEntity.setExpiresAt(new Date(System.currentTimeMillis() + (post.getExpirationTime() * 60000)));

        PostEntity createdPost = postRepository.save(postEntity);
        PostDto postToReturn = mapper.map(createdPost, PostDto.class);
        return postToReturn;
    }

    @Override
    public List<PostDto> getLastPosts() {
        long publicExposureId = 2;
        List<PostEntity> postEntities = postRepository.getLastPublicPosts(publicExposureId, new Date(System.currentTimeMillis()));
        List<PostDto> postDtos = new ArrayList<>();
        for(PostEntity post: postEntities) {
            PostDto postDto = mapper.map(post, PostDto.class);
            postDtos.add(postDto);
        }
        return postDtos;
    }

    @Override
    public PostDto getPost(String postId) {
        PostEntity postEntity = postRepository.findByPostId(postId);
        PostDto postDto = mapper.map(postEntity, PostDto.class);
        return postDto;
    }

}
