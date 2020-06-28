package com.example.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.user.dto.Credentials;
import com.example.user.exceptions.InvalidCredentialsException;
import com.example.user.service.UserService;

@RestController
public class UserController {
	
	private static Log logger = LogFactory.getLog(UserController.class);
	@Autowired
	UserService userService;
	
	/**
	 * @param credentials
	 * @return String
	 * @throws InvalidCredentialsException
	 */
    @PostMapping("/users/login")
    public String login(@Valid @RequestBody Credentials credentials) throws InvalidCredentialsException {
        logger.info("authenticating the user");
    	boolean isExists = userService.authenticate(credentials.getUserName(), credentials.getPassword());
       
        if(isExists) return "login success";
       
        return "invalid credentials";
    }
   
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleException(MethodArgumentNotValidException ex) {
        Map<String,String> errorMessage = new HashMap<String,String>();
                ex.getBindingResult().getFieldErrors().forEach(error -> errorMessage.put("message",error.getDefaultMessage()));
       return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
   
    }

	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> exceptionHandler(InvalidCredentialsException exception){
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
}
