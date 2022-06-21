package com.btpanorma.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.btpanorma.model.PostsEntity;
@Repository
public interface BlogServiceRepository extends CrudRepository<PostsEntity, Integer> {
}
