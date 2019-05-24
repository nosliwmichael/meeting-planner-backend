package com.meeting.service;

import java.util.List;

import com.meeting.model.Login;
import com.meeting.viewmodel.UserView;

public interface UserService {

	UserView create(UserView user);
	
	UserView update(UserView user);
	
	boolean deleteById(Long id);
	
	UserView findById(Long id);
	
	List<UserView> findAll();
	
	boolean login(Login account);
}
