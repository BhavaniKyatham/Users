package com.example.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user.dao.UserDao;
import com.example.user.exceptions.InvalidCredentialsException;
import com.example.user.model.User;
import com.example.user.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;

	@Override
	public boolean authenticate(String userName, String password) throws InvalidCredentialsException {
		// TODO Auto-generated method stub
		User user=userDao.findByUserNameAndPassword(userName, password);
		if(user!=null) return true;
		throw new InvalidCredentialsException("invalid credentials !! please try again with valid credentials"); 
	}

}
