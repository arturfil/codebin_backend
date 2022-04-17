package com.arturofilio.codebin.respositories;

import java.util.List;

import com.arturofilio.codebin.entities.PostEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends PagingAndSortingRepository<PostEntity, Long> {
    List<PostEntity> getByUserIdOrderByCreatedAtDesc(long userId);
}
