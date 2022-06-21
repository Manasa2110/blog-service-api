package com.btpanorma.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table (name="BlogPosts")
@NoArgsConstructor
@AllArgsConstructor
public class PostsEntity {

	private int userId;
	@Id
	private int id;
	private String title;
	private String body;

}
