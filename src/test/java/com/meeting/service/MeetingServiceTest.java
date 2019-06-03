package com.meeting.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.meeting.dao.MeetingDao;
import com.meeting.model.Meeting;
import com.meeting.model.User;
import com.meeting.viewmodel.MeetingView;
import com.meeting.viewmodel.UserView;

public class MeetingServiceTest {
	
	@Mock
	private MeetingDao daoMock;
	
	@InjectMocks
	private MeetingServiceImpl service;

	private List<Meeting> meetings = new ArrayList<>();
	private List<MeetingView> meetingViews = new ArrayList<>();

	@BeforeTest
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
		UserView userView = new UserView(1L, "Michael", "Wilson", "michael@email.com", "password");
		User user = new User(userView);
		
		meetingViews.add(new MeetingView(1L, "Task 1 Code Review", new Timestamp(1558587888), "Conference Room", userView));
		meetingViews.add(new MeetingView(2L, "Task 1 Demo", new Timestamp(1558699488), "Conference Room", userView));
		meetingViews.add(new MeetingView(3L, "Task 2 Code Review", new Timestamp(1558785888), "Conference Room", userView));
		
		meetings.add(new Meeting(meetingViews.get(0), user));
		meetings.add(new Meeting(meetingViews.get(1), user));
		meetings.add(new Meeting(meetingViews.get(2), user));
		
	}
	
	@Test
	public void CreateNotNull() {
		
		MeetingView meetingView = meetingViews.get(0);
		Meeting meeting = meetings.get(0);
		
		Mockito.when(daoMock.create(Mockito.any(Meeting.class))).thenReturn(meeting);
		
		MeetingView actual = service.create(meetingView);
		
		Assert.assertNotNull(actual);
		
	}
	
	@Test
	public void CreateNull() {

		MeetingView meetingView = meetingViews.get(0);
		
		Mockito.when(daoMock.create(Mockito.any(Meeting.class))).thenReturn(null);
		
		MeetingView actual = service.create(meetingView);

		Assert.assertNull(actual);
		
	}
	
	@Test
	public void CreateEquals() {
		
		MeetingView meetingView = meetingViews.get(0);
		Meeting meeting = meetings.get(0);
		
		Mockito.when(daoMock.create(Mockito.any(Meeting.class))).thenReturn(meeting);
		
		MeetingView actual = service.create(meetingView);

		Assert.assertEquals(actual.getSubject(), meetingView.getSubject());
		
	}
	
	@Test
	public void CreateNotEquals() {
		
		MeetingView meetingView = meetingViews.get(0);
		Meeting meeting = meetings.get(1);
		
		Mockito.when(daoMock.create(Mockito.any(Meeting.class))).thenReturn(meeting);
		
		MeetingView actual = service.create(meetingView);

		Assert.assertNotEquals(actual.getSubject(), meetingView.getSubject());
		
	}
	
	@Test
	public void FindByIdNotNull() {
		
		Meeting meeting = meetings.get(0);
		
		Mockito.when(daoMock.findById(1L)).thenReturn(meeting);
		
		MeetingView actual = service.findById(1L);

		Assert.assertNotNull(actual);
		
	}
	
	@Test
	public void FindByIdNull() {
				
		Mockito.when(daoMock.findById(1L)).thenReturn(null);
		
		MeetingView actual = service.findById(1L);

		Assert.assertNull(actual);
		
	}
	
	@Test
	public void FindByHostEquals() {
		
		Mockito.when(daoMock.findByHost(1L)).thenReturn(meetings);
		
		List<MeetingView> actual = service.findByHost(1L);
		
		Assert.assertEquals(actual.toString(), meetingViews.toString());
		
	}
	
	@Test
	public void FindByHostNull() {
		
		Mockito.when(daoMock.findByHost(1L)).thenReturn(null);
		
		List<MeetingView> actual = service.findByHost(1L);
		
		Assert.assertNull(actual);
		
	}
	
	@Test
	public void FindAllEquals() {
		
		Mockito.when(daoMock.findAll()).thenReturn(meetings);
		
		List<MeetingView> actual = service.findAll();

		Assert.assertEquals(actual.toString(), meetingViews.toString());
		
	}
	
	@Test
	public void FindAllNull() {
		
		Mockito.when(daoMock.findAll()).thenReturn(null);
		
		List<MeetingView> actual = service.findAll();
		
		Assert.assertNull(actual);
		
	}
	
	@Test
	public void UpdateNotNull() {
		
		MeetingView meetingView = meetingViews.get(0);
		Meeting meeting = meetings.get(0);
		
		Mockito.when(daoMock.update(Mockito.any(Meeting.class))).thenReturn(meeting);
		
		MeetingView actual = service.update(meetingView);
		
		Assert.assertNotNull(actual);
		
	}
	
	@Test
	public void UpdateNull() {
		
		MeetingView meetingView = meetingViews.get(0);
		
		Mockito.when(daoMock.update(Mockito.any(Meeting.class))).thenReturn(null);
		
		MeetingView actual = service.update(meetingView);
		
		Assert.assertNull(actual);
		
	}
	
	@Test
	public void DeleteByIdTrue() {
		
		Mockito.when(daoMock.deleteById(1L)).thenReturn(1);
		
		Assert.assertTrue(service.deleteById(1L));
		
	}
	
	@Test
	public void DeleteByIdFalse() {
		
		Mockito.when(daoMock.deleteById(1L)).thenReturn(0);
		
		Assert.assertFalse(service.deleteById(1L));
		
	}
	
}
