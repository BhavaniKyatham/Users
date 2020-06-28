package com.example.user.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.user.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

	User findByUserNameAndPassword(String userName, String password);

}
