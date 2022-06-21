package com.btpanorma.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.btpanorma.model.Post;
import com.btpanorma.model.Users;
import com.btpanorma.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MainController {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private BlogService blogService;

	@Value("${usersURL}")
	private String usersURL;

	@Value("${postsURL}")
	private String postsURL;

	@GetMapping("/users")
	public ResponseEntity<Users[]> listUsers() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Users[]> responseEntity = restTemplate.getForEntity(usersURL, Users[].class);
		return responseEntity;
	}

	@GetMapping("/posts")
	public ResponseEntity<Post[]> listAllPosts() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Post[]> responseEntity = restTemplate.getForEntity(postsURL, Post[].class);
		return responseEntity;
	}

	@PostMapping("/posts")
	public ResponseEntity<Post> listAllUsersPosts(@RequestBody Post userPosts) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Post[]> postsResponseEntity = restTemplate.getForEntity(postsURL, Post[].class);
		// save all the posts for the user
		List<Post> posts = Arrays.asList(postsResponseEntity.getBody()).stream()
				.filter(post -> post.getUserId() == userPosts.getUserId()).collect(Collectors.toList());
		// save the newly added post
		posts.add(userPosts);
		blogService.save(posts);
		// blogService.list()
		return ResponseEntity.ok(userPosts);
	}

}
