package test.meeting.backend;

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
import com.meeting.service.UserServiceImpl;
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
		
		userViews.add(new UserView(1L, "Michael", "Wilson", "michael@email.com", "password"));
		userViews.add(new UserView(1L, "George", "Washington", "george@email.com", "password"));
		userViews.add(new UserView(1L, "Johnny", "Appleseed", "appleseed@email.com", "password"));
		
		users.add(new User(userViews.get(0)));
		users.add(new User(userViews.get(1)));
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
		
		UserView actual = service.login(login);

		Assert.assertNotNull(actual);
		
	}
	
	@Test
	public void loginFalse() {
		
		Login login = new Login();
		
		Mockito.when(daoMock.findByLogin(login.getEmail(), login.getPassword())).thenReturn(null);
				
		Assert.assertNull(service.login(login));
		
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
