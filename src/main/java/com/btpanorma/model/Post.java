package com.btpanorma.model;

import lombok.Data;

@Data
public class Post {

	public Post() {

	}

	public Post(int userId, int id, String title, String body) {
		this.userId = userId;
		this.id = id;
		this.title = title;
		this.body = body;
	}

	private int userId;
	private int id;
	private String title;
	private String body;

}
