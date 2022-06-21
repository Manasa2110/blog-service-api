package com.btpanorma.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btpanorma.model.Post;
import com.btpanorma.model.PostsEntity;
import com.btpanorma.repo.BlogServiceRepository;

@Service
public class BlogService {

	@Autowired
	private BlogServiceRepository blogServiceRepository;

	public List<PostsEntity> list() {
		List<PostsEntity> result = new ArrayList<PostsEntity>();
		blogServiceRepository.findAll().forEach(result::add);
		return result;
	}

	/*
	 * public List<PostsDto> getPostsByUserId(List<UserIds> userIdObjects) {
	 * 
	 * Iterable<Integer> userIDs = userIdObjects.stream().map(userID ->
	 * userID.getUserID()) .collect(Collectors.toList()); List<PostsDto> posts = new
	 * ArrayList<PostsDto>(); Iterable<PostsDto> iterable =
	 * blogServiceRepository.findAllById(userIDs);// iterable.forEach(post ->
	 * posts.add(post)); return posts; }
	 */

	public void save(List<Post> posts) {
		for (Post post : posts) {
			PostsEntity postsDto = PostsEntity.builder().userId(post.getUserId()).id(post.getId())
					.title(post.getTitle()).body(post.getBody()).build();
			blogServiceRepository.save(postsDto);
		}
	}

}
