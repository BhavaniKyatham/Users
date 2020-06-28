package com.example.user;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.user.dao.UserDao;
import com.example.user.dto.Credentials;
import com.example.user.exceptions.InvalidCredentialsException;
import com.example.user.model.User;
import com.example.user.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	    User user;
		
		@Mock
		UserDao userDao;
		
		@InjectMocks
		UserServiceImpl userServiceImpl;
		
		Credentials credentials;
		
		
		@Test
		public void authenticateTest1() throws InvalidCredentialsException {
			//given
			User user=new User();
			user.setUserName("bhavani");
			user.setPassword("123");			
			//when
			when(userDao.findByUserNameAndPassword("bhavani","123")).thenReturn(user);				
			//then
			Boolean isExists = userServiceImpl.authenticate("bhavani", "123");
			verify(userDao).findByUserNameAndPassword("bhavani","123");
			assertTrue(isExists);
		}
		
		@Test
		public void authenticateTest2() {
			//given
			User user = new User();
			user.setUserName("bhavani");
			user.setPassword("123");	
			//when
			when(userDao.findByUserNameAndPassword("bhavani","123")).thenReturn(null);
			//then
			assertThrows(InvalidCredentialsException.class, () -> userServiceImpl.authenticate("bhavani", "123"));
		}
}