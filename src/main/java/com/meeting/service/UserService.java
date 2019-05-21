package com.meeting.service;

import java.util.List;

import com.meeting.model.Login;
import com.meeting.modelView.UserView;

public interface UserService {

	UserView createUser(UserView user);
	
	UserView updateUser(UserView user);
	
	boolean deleteById(Long id);
	
	UserView findById(Long id);
	
	List<UserView> findAllUsers();
	
	boolean loginUser(Login account);
}
