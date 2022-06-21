package com.btpanorma.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.btpanorma.model.Post;
import com.btpanorma.model.PostsEntity;
import com.btpanorma.repo.BlogServiceRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataLayerTest {

	@Autowired
	BlogServiceRepository blogServiceRepository;

	@Test
	public void saveAndReadtest() {
		List<Post> posts = Arrays.asList(new Post(1, 1, "Ooty",
				"Ooty, the capital of Nilgiris district and the Queen of hill stations nestled in the beautiful Nilgiris Mountains is the land of picturesque picnic spots."),
				new Post(1, 2, "Kodaikanal",
						"Kodaikanal is one of the most popular tourist destinations. This hill station of Tamil Nadu has mesmerising settings and exciting activities to offer its guests."));
		for (Post post : posts) {
			PostsEntity postsDto = PostsEntity.builder().userId(post.getUserId()).id(post.getId())
					.title(post.getTitle()).body(post.getBody()).build();
			blogServiceRepository.save(postsDto);
		}

		List<PostsEntity> result = new ArrayList<PostsEntity>();
		blogServiceRepository.findAll().forEach(result::add);
		Assertions.assertThat(result).extracting(PostsEntity::getUserId).containsOnly(1);
	}
}