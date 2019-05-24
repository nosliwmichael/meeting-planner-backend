package com.meeting.service;

import java.util.ArrayList;
import java.util.HashSet;
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
	public UserView create(UserView userView) {
		
		// Convert from View Model to Entity Model
		User user = convertUser(userView);

		// Create new user
		User newUser = dao.create(user);
		
		if (newUser == null) {
			
			return null;
			
		} else {
			
			// Convert from Entity Model to View Model
			return convertUser(newUser);
			
		}
		
	}

	@Override
	public UserView update(UserView userView) {

		// Convert from View Model to Entity Model
		User user = convertUser(userView);
		
		// Update user
		User updatedUser = dao.update(user);
		
		if (updatedUser == null) {
			
			return null;
			
		} else {
			
			// Convert from Entity Model to View Model
			return convertUser(updatedUser);
			
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
			return convertUser(user);
			
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
				UserView userView = convertUser(user);
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
		userView.setPassword(null);
		return userView;
		
	}
	
	// Convert collection type Meeting to MeetingView
	private Set<MeetingView> convertMeetings(Set<Meeting> meetings) {
		
		if (meetings == null) {
			return new HashSet<MeetingView>();
		} else {
			return meetings.stream().map(MeetingView::new).collect(Collectors.toSet());
		}
		
	}
	// Convert collection type MeetingView to Meeting
	private Set<Meeting> convertMeetingViews(Set<MeetingView> meetingViews) {
		
		if (meetingViews == null) {
			return new HashSet<Meeting>();
		} else {
			return meetingViews.stream().map(Meeting::new).collect(Collectors.toSet());
		}
		
	}
}
