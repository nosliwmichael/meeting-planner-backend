package com.meeting.service;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.meeting.dao.UserDao;
import com.meeting.model.Login;
import com.meeting.model.User;
import com.meeting.viewmodel.UserView;

public class UserServiceTest {
	
	@Mock
	private UserDao daoMock;
	
	@InjectMocks
	private UserServiceImpl service;
	
	private List<User> users = new ArrayList<>();
	private List<UserView> userViews = new ArrayList<>();
	
	@BeforeTest
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
		userViews.add(new UserView(1L, "Michael", "Wilson", "michael@email.com", "password"));
		userViews.add(new UserView(1L, "George", "Washington", "george@email.com", "password"));
		userViews.add(new UserView(1L, "Johnny", "Appleseed", "appleseed@email.com", "password"));
		
		users.add(new User(userViews.get(0)));
		users.add(new User(userViews.get(1)));
		users.add(new User(userViews.get(2)));
		
	}
	
	@Test
	public void createPositive() throws Exception {
		
		UserView userView = userViews.get(0);
		User user = users.get(0);
		
		Mockito.when(daoMock.create(Mockito.any(User.class))).thenReturn(user);
		
		UserView actual = service.create(userView);

		Assert.assertNotNull(actual);
		
	}
	
	@Test
	public void createNegative() throws Exception {
		
		UserView userView = userViews.get(0);
		User user = users.get(0);
		
		Mockito.when(daoMock.create(user)).thenReturn(null);
		
		UserView actual = service.create(userView);
		
		Assert.assertNull(actual);
		
	}
	
	@Test
	public void findByIdPositive() throws Exception {
		
		User user = users.get(0);
		
		Mockito.when(daoMock.findById(1L)).thenReturn(user);
		
		UserView actual = service.findById(1L);

		Assert.assertNotNull(actual);
		
	}
	
	@Test
	public void findByIdNegative() throws Exception {
		
		Mockito.when(daoMock.findById(1L)).thenReturn(null);
		
		UserView actual = service.findById(1L);
		
		Assert.assertNull(actual);
		
	}
	
	@Test
	public void findAllPositive() throws Exception {
		
		Mockito.when(daoMock.findAll()).thenReturn(users);
		
		List<UserView> actual = service.findAll();
		
		Assert.assertEquals(actual.toString(), userViews.toString());
		
	}
	
	@Test
	public void findAllNegative() throws Exception {
		
		Mockito.when(daoMock.findAll()).thenReturn(null);
		
		List<UserView> actual = service.findAll();
		
		Assert.assertNull(actual);
		
	}
	
	@Test
	public void updatePositive() throws Exception {
		
		UserView userView = userViews.get(0);
		User user = users.get(0);
		
		Mockito.when(daoMock.update(Mockito.any(User.class))).thenReturn(user);
		
		UserView actual = service.update(userView);
		
		Assert.assertNotNull(actual);
		
	}
	
	@Test
	public void updateNegative() throws Exception {
		
		UserView userView = userViews.get(0);
		User user = users.get(0);
		
		Mockito.when(daoMock.update(user)).thenReturn(null);
		
		UserView actual = service.update(userView);
		
		Assert.assertNull(actual);
		
	}
	
	@Test
	public void loginPositive() throws Exception {

		Login login = new Login();
		User user = users.get(0);
		
		Mockito.when(daoMock.findByLogin(login.getEmail(), login.getPassword())).thenReturn(user);
		
		boolean actual = service.login(login);

		Assert.assertTrue(actual);
		
	}
	
	@Test
	public void loginNegative() throws Exception {
		
		Login login = new Login();
		
		Mockito.when(daoMock.findByLogin(login.getEmail(), login.getPassword())).thenReturn(null);
				
		Assert.assertFalse(service.login(login));
		
	}
	
	@Test
	public void deleteByIdPositive() throws Exception {
		
		Mockito.when(daoMock.deleteById(1L)).thenReturn(1);
		
		
		Assert.assertTrue(service.deleteById(1L));
		
	}
	
	@Test
	public void deleteByIdNegative() throws Exception {
		
		Mockito.when(daoMock.deleteById(1L)).thenReturn(0);
				
		Assert.assertFalse(service.deleteById(1L));
		
		
	}

}
