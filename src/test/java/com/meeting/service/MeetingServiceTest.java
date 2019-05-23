package com.meeting.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
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
		
		UserView userView = new UserView(new Long(1), "Michael", "Wilson", "michael@email.com", "password");
		meetingViews.add(new MeetingView(new Long(1), "Task 1 Code Review", new Timestamp(1558587888), "Conference Room", userView));
		meetingViews.add(new MeetingView(new Long(2), "Task 1 Demo", new Timestamp(1558699488), "Conference Room", userView));
		meetingViews.add(new MeetingView(new Long(3), "Task 2 Code Review", new Timestamp(1558785888), "Conference Room", userView));
		
		User user = new User(userView);
		meetings.add(new Meeting(meetingViews.get(0), user));
		meetings.add(new Meeting(meetingViews.get(1), user));
		meetings.add(new Meeting(meetingViews.get(2), user));
		
	}
	
	@Test
	public void createPositive() throws Exception {

		MeetingView meetingView = meetingViews.get(0);
		Meeting meeting = meetings.get(0);

		Mockito.when(daoMock.createMeeting(meeting)).thenReturn(meeting);
		
		
		
	}
	
	@Test
	public void createNegative() throws Exception {

		MeetingView meetingView = meetingViews.get(0);
		Meeting meeting = meetings.get(0);

		Mockito.when(daoMock.createMeeting(meeting)).thenReturn(meeting);
		
		
		
	}	
	
	@Test
	public void findByIdPositive() throws Exception {
		
		MeetingView meetingView = meetingViews.get(0);
		Meeting meeting = meetings.get(0);
		
		Mockito.when(daoMock.findById(meetingView.getId())).thenReturn(meeting);
		
		MeetingView actual = service.findById(meetingView.getId());
		
		Assert.assertTrue(new ReflectionEquals(actual, "hostUser").matches(meetingView));
		
	}
	
	@Test
	public void findByIdNegative() throws Exception {
		
		MeetingView meetingView = meetingViews.get(0);
		
		Mockito.when(daoMock.findById(meetingView.getId())).thenReturn(null);
		
		MeetingView actual = service.findById(meetingView.getId());
		
		Assert.assertTrue(new ReflectionEquals(actual, "hostUser").matches(null));
		
	}
	
	@Test
	public void deleteByIdPositive() throws Exception {
		
		Mockito.when(daoMock.deleteById(new Long(1))).thenReturn(true);
		
		boolean actual = service.deleteById(new Long(1));
		boolean expected = true;
		
		Assert.assertEquals(actual, expected);
		
	}
	
	@Test
	public void deleteByIdNegative() throws Exception {
		
		Mockito.when(daoMock.deleteById(new Long(1))).thenReturn(false);
		
		boolean actual = service.deleteById(new Long(1));
		boolean expected = false;
		
		Assert.assertEquals(actual, expected);
		
	}
	
}
