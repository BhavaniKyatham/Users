package com.example.user.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.user.dto.Credentials;
import com.example.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	ObjectMapper objectMapper;

	@Mock
	UserService userService;

	@InjectMocks
	UserController userController;

	MockMvc mockMvc;

	Credentials credentials;

	@BeforeEach
	public void setUp() {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

		credentials = new Credentials();
		credentials.setPassword("123");
		credentials.setUserName("bhavani");

	}

	@Test
	public void loginTest() throws Exception {
		// given
		when(userService.authenticate("bhavani", "123")).thenReturn(true);

		// when and then
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(credentials))).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.is("login success")));

		verify(userService).authenticate("manasa", "1234");
	}

	@Test
	public void loginTest1() throws Exception {
		// given
		when(userService.authenticate("bhavani", "123")).thenReturn(false);

		// when and then
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(credentials))).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.is("please enter valid username and password")));

		verify(userService).authenticate("bhavani", "123");
	}

}
