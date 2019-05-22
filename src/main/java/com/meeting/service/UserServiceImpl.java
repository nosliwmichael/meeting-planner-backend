package com.meeting.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meeting.dao.UserDao;
import com.meeting.model.Login;
import com.meeting.model.Meeting;
import com.meeting.model.User;
import com.meeting.viewmodel.MeetingView;
import com.meeting.viewmodel.UserView;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	@Override
	public UserView createUser(UserView userView) {
		
		// Convert from View Model to Entity Model
		User user = convertUser(userView);
		
		// Create new user
		User newUser = dao.createUser(user);
		
		// Convert from Entity Model to View Model
		UserView newUserView = convertUser(newUser);
		
		return newUserView;
		
	}

	@Override
	public UserView updateUser(UserView userView) {

		// Convert from View Model to Entity Model
		User user = convertUser(userView);
		
		// Update user
		User updatedUser = dao.updateUser(user);

		// Convert from Entity Model to View Model
		UserView updatedUserView = convertUser(updatedUser);
		
		return updatedUserView;
	}

	@Override
	public boolean deleteById(Long id) {
		
		return dao.deleteById(id);
		
	}

	@Override
	public UserView findById(Long id) {
		
		// Find user
		User user = dao.findById(id);

		// Convert from Entity Model to View Model
		UserView userView = convertUser(user);
		
		return userView;
	}

	@Override
	public List<UserView> findAllUsers() {
		
		List<User> users = dao.findAllUsers();
		List<UserView> userViews = new ArrayList<>();
		
		for (User user: users) {
			UserView userView = convertUser(user);
			userViews.add(userView);
		}
		
		return userViews;
		
	}

	@Override
	public boolean loginUser(Login account) {
		// TODO Implement login user
		return true;
	}

	
	// Convert from View Model to Entity Model
	private User convertUser(UserView userView) {
		User user = new User(userView);
		user.setMeetings( convertMeetingViews( userView.getMeetings() ) );
		return user;
	}
	// Convert from Entity Model to View Model
	private UserView convertUser(User user) {
		UserView userView = new UserView(user);
		userView.setMeetings( convertMeetings( user.getMeetings() ) );
		return userView;
	}
	
	// Convert collection type Meeting to MeetingView
	private Set<MeetingView> convertMeetings(Set<Meeting> meetings) {
				
		return meetings.stream().map(MeetingView::new).collect(Collectors.toSet());
		
	}
	// Convert collection type MeetingView to Meeting
	private Set<Meeting> convertMeetingViews(Set<MeetingView> meetingViews) {
		
		return meetingViews.stream().map(Meeting::new).collect(Collectors.toSet());
		
	}
}
