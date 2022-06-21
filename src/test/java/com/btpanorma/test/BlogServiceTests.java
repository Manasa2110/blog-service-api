package com.btpanorma.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.btpanorma.controller.MainController;
import com.btpanorma.model.Post;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BlogServiceTests {

	@Autowired
	ObjectMapper objectMapper;

	@LocalServerPort
	int randomServerPort;

	MockMvc mockMvc;

	final String baseUrl = "http://localhost:" + randomServerPort;

	@BeforeEach
	void setUp(WebApplicationContext wac) {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new MainController()).build();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testPostsBlogService() throws Exception {
		Post post = new Post(1, 1, "Ooty",
				"Ooty, the capital of Nilgiris district and the Queen of hill stations nestled in the beautiful Nilgiris Mountains is the land of picturesque picnic spots.");
		mockMvc.perform(
				post(baseUrl + "/posts").contentType("application/json").content(objectMapper.writeValueAsString(post)))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetPostsBlogService() throws Exception {
		this.mockMvc.perform(get(baseUrl + "/posts").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetUsersBlogService() throws Exception {
		this.mockMvc.perform(get(baseUrl + "/users").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}