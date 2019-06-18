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
import com.meeting.validation.ValidateUser;
import com.meeting.viewmodel.UserView;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	@Override
	public UserView create(UserView userView) {
		
		// User is valid and does not already exist
		if (ValidateUser.validateNewUser(userView) && !userExists(userView)) {
			
			// Convert from View Model to Entity Model
			User user = convertUserViewWithMeetings(userView);
		
			// Create new user
			User newUser = dao.create(user);
			
			if (ValidateUser.validateNewUser(newUser)) {

				// Convert from Entity Model to View Model
				return convertUserWithMeetings(newUser);
				
			} else {
				
				return null;
				
			}
			
		} else {
			
			return null;
			
		}
		
	}

	@Override
	public UserView update(UserView userView) {

		if (ValidateUser.validateUser(userView)) {
			
			// Convert from View Model to Entity Model
			User user = convertUserViewWithMeetings(userView);
			
			// Update user
			User updatedUser = dao.update(user);
			
			if (ValidateUser.validateUser(updatedUser)) {

				// Convert from Entity Model to View Model
				return convertUserWithMeetings(updatedUser);
				
			} else {
				
				return null;
				
			}
		
		} else {
			
			return null;
			
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
	public UserView findByEmail(String email) {
		
		// Find user
		User user = dao.findByEmail(email);
		
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
	public UserView login(Login account) {
		
		User user = dao.findByLogin(account.getEmail(), account.getPassword());
		
		if (user != null) {
			return convertUser(user);
		} else {
			return null;
		}
		
	}
	
	private boolean userExists(UserView user) {
		
		UserView findUser = findByEmail(user.getEmail());
		return findUser == null;
		
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
