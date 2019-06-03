package com.meeting.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.meeting.dao.UserDao;
import com.meeting.model.Login;
import com.meeting.model.Meeting;
import com.meeting.model.User;
import com.meeting.viewmodel.MeetingView;
import com.meeting.viewmodel.UserView;

public class UserServiceTest {
	
	@Mock
	private UserDao daoMock;
	
	@InjectMocks
	private UserServiceImpl service;
	
	private List<User> users = new ArrayList<>();
	private List<UserView> userViews = new ArrayList<>();
	
	@BeforeTest
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		Set<MeetingView> meetingViews = new HashSet<>();
		meetingViews.add(new MeetingView(1L, "Task 1 Code Review", new Timestamp(1558587888), "Conference Room"));
		meetingViews.add(new MeetingView(2L, "Task 1 Demo", new Timestamp(1558699488), "Conference Room"));
		meetingViews.add(new MeetingView(3L, "Task 2 Code Review", new Timestamp(1558785888), "Conference Room"));
		
		userViews.add(new UserView(1L, "Michael", "Wilson", "michael@email.com", "password"));
		userViews.add(new UserView(1L, "George", "Washington", "george@email.com", "password", meetingViews));
		userViews.add(new UserView(1L, "Johnny", "Appleseed", "appleseed@email.com", "password"));
		
		Set<Meeting> meetings = meetingViews.stream().map(MeetingServiceImpl::convertMeetingView).collect(Collectors.toSet());
		
		users.add(new User(userViews.get(0)));
		users.add(new User(userViews.get(1), meetings));
		users.add(new User(userViews.get(2)));
		
	}
	
	@Test
	public void createNotNull() {
		
		UserView userView = userViews.get(0);
		User user = users.get(0);
		
		Mockito.when(daoMock.create(Mockito.any(User.class))).thenReturn(user);
		
		UserView actual = service.create(userView);

		Assert.assertNotNull(actual);
		
	}
	
	@Test
	public void createNull() {
		
		UserView userView = userViews.get(0);
		
		Mockito.when(daoMock.create(Mockito.any(User.class))).thenReturn(null);
		
		UserView actual = service.create(userView);
		
		Assert.assertNull(actual);
		
	}
	
	@Test
	public void CreateEquals() {
		
		UserView userView = userViews.get(1);
		User user = users.get(1);
		
		Mockito.when(daoMock.create(Mockito.any(User.class))).thenReturn(user);
		
		UserView actual = service.create(userView);

		Assert.assertEquals(actual.getEmail(), userView.getEmail());
		
	}
	
	@Test
	public void CreateNotEquals() {
		
		UserView userView = userViews.get(0);
		User user = users.get(1);
		
		Mockito.when(daoMock.create(Mockito.any(User.class))).thenReturn(user);
		
		UserView actual = service.create(userView);

		Assert.assertNotEquals(actual.getEmail(), userView.getEmail());
		
	}
	
	@Test
	public void findByIdNotNull() {
		
		User user = users.get(0);
		
		Mockito.when(daoMock.findById(1L)).thenReturn(user);
		
		UserView actual = service.findById(1L);

		Assert.assertNotNull(actual);
		
	}
	
	@Test
	public void findByIdNull() {
		
		Mockito.when(daoMock.findById(1L)).thenReturn(null);
		
		UserView actual = service.findById(1L);
		
		Assert.assertNull(actual);
		
	}
	
	@Test
	public void findAllEquals() {
		
		Mockito.when(daoMock.findAll()).thenReturn(users);
		
		List<UserView> actual = service.findAll();
		
		Assert.assertEquals(actual.toString(), userViews.toString());
		
	}
	
	@Test
	public void findAllNull() {
		
		Mockito.when(daoMock.findAll()).thenReturn(null);
		
		List<UserView> actual = service.findAll();
		
		Assert.assertNull(actual);
		
	}
	
	@Test
	public void updateNotNull() {
		
		UserView userView = userViews.get(0);
		User user = users.get(0);
		
		Mockito.when(daoMock.update(Mockito.any(User.class))).thenReturn(user);
		
		UserView actual = service.update(userView);
		
		Assert.assertNotNull(actual);
		
	}
	
	@Test
	public void updateNull() {
		
		UserView userView = userViews.get(0);
		
		Mockito.when(daoMock.update(Mockito.any(User.class))).thenReturn(null);
		
		UserView actual = service.update(userView);
		
		Assert.assertNull(actual);
		
	}
	
	@Test
	public void loginTrue() {

		Login login = new Login();
		User user = users.get(0);
		
		Mockito.when(daoMock.findByLogin(login.getEmail(), login.getPassword())).thenReturn(user);
		
		boolean actual = service.login(login);

		Assert.assertTrue(actual);
		
	}
	
	@Test
	public void loginFalse() {
		
		Login login = new Login();
		
		Mockito.when(daoMock.findByLogin(login.getEmail(), login.getPassword())).thenReturn(null);
				
		Assert.assertFalse(service.login(login));
		
	}
	
	@Test
	public void deleteByIdTrue() {
		
		Mockito.when(daoMock.deleteById(1L)).thenReturn(1);
		
		
		Assert.assertTrue(service.deleteById(1L));
		
	}
	
	@Test
	public void deleteByIdFalse() {
		
		Mockito.when(daoMock.deleteById(1L)).thenReturn(0);
				
		Assert.assertFalse(service.deleteById(1L));
		
		
	}

}
