package com.meeting.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meeting.dao.UserDao;
import com.meeting.model.Login;
import com.meeting.model.User;
import com.meeting.viewmodel.UserView;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	@Override
	public UserView create(UserView userView) {
		
		// Convert from View Model to Entity Model
		User user = convertUserViewWithMeetings(userView);

		// Create new user
		User newUser = dao.create(user);
		
		if (newUser == null) {
			
			return null;
			
		} else {
			
			// Convert from Entity Model to View Model
			return convertUserWithMeetings(newUser);
			
		}
		
	}

	@Override
	public UserView update(UserView userView) {

		// Convert from View Model to Entity Model
		User user = convertUserViewWithMeetings(userView);
		
		// Update user
		User updatedUser = dao.update(user);
		
		if (updatedUser == null) {
			
			return null;
			
		} else {
			
			// Convert from Entity Model to View Model
			return convertUserWithMeetings(updatedUser);
			
		}
		
	}

	@Override
	public boolean deleteById(Long id) {
		
		return (dao.deleteById(id) > 0);
		
	}

	@Override
	public UserView findById(Long id) {
		
		// Find user
		User user = dao.findById(id);
		
		if (user == null) {
			
			return null;
			
		} else {
			
			// Convert from Entity Model to View Model
			return convertUserWithMeetings(user);
			
		}
		
	}

	@Override
	public List<UserView> findAll() {
		
		List<User> users = dao.findAll();
		
		if (users == null) {
			
			return null;
			
		} else {

			List<UserView> userViews = new ArrayList<>();
			
			for (User user: users) {
				UserView userView = convertUserWithMeetings(user);
				userViews.add(userView);
			}
			
			return users.stream().map(UserView::new).collect(Collectors.toList());
			
		}
		
	}

	@Override
	public boolean login(Login account) {
		
		User user = dao.findByLogin(account.getEmail(), account.getPassword());
		
		// TODO Implement login user
		if (user == null) {
			
			return false;
			
		} else {
			
			return true;
			
		}
		
	}

	
	// Conversion methods without meetings
	public static User convertUserView(UserView userView) {

		return new User(userView);
		
	}
	public static UserView convertUser(User user) {
		
		return new UserView(user);
		
	}
	
	// Conversion methods with meetings
	public static User convertUserViewWithMeetings(UserView userView) {
		
		User user = new User(userView);
		user.setMeetings( userView
							.getMeetings()
							.stream()
							.map(MeetingServiceImpl::convertMeetingView)
							.collect(Collectors.toSet())
						);
		return user;
		
	}
	public static UserView convertUserWithMeetings(User user) {
		
		UserView userView = new UserView(user);
		userView.setMeetings( user
								.getMeetings()
								.stream()
								.map(MeetingServiceImpl::convertMeeting)
								.collect(Collectors.toSet())
							);
		userView.setPassword(null);
		return userView;
		
	}
	
}
