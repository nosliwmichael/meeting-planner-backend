package com.meeting.dao;

import java.util.List;

import com.meeting.model.User;

public interface UserDao {
	
	User createUser(User user);
	
	User updateUser(User user);
	
	boolean deleteById(Long id);
	
	User findById(Long id);
	
	List<User> findAllUsers();
	
}
