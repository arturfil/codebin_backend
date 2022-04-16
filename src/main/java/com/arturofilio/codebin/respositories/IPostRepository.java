package com.arturofilio.codebin.respositories;

import com.arturofilio.codebin.entities.PostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends CrudRepository<PostEntity, Long> {
}
