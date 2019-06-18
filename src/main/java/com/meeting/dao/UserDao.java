package com.meeting.dao;

import java.util.List;

import com.meeting.model.User;

public interface UserDao {
	
	User create(User user);
	
	User update(User user);
	
	int deleteById(Long id);
	
	User findById(Long id);
	
	User findByEmail(String email);
	
	User findByLogin(String username, String password);
	
	List<User> findAll();
	
}
