package com.example.user.service;

import com.example.user.exceptions.InvalidCredentialsException;

public interface UserService {

	boolean authenticate(String userName, String password) throws InvalidCredentialsException;

}
