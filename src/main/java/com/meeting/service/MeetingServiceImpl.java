package com.meeting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meeting.dao.MeetingDao;
import com.meeting.model.Meeting;
import com.meeting.model.User;
import com.meeting.viewmodel.MeetingView;
import com.meeting.viewmodel.UserView;

@Service
@Transactional
public class MeetingServiceImpl implements MeetingService {
	
	@Autowired
	private MeetingDao dao;

	@Override
	public MeetingView createMeeting(MeetingView meetingView) {
		
		// Convert object to be used by DAO
		Meeting meeting = convertMeeting(meetingView);
		
		// Create new meeting
		Meeting newMeeting = dao.createMeeting(meeting);
		
		// Convert object to be sent back to client
		MeetingView newMeetingView = convertMeeting(newMeeting);
		
		return newMeetingView;
		
	}

	@Override
	public MeetingView updateMeeting(MeetingView meetingView) {

		// Convert object to be used by DAO
		Meeting meeting = convertMeeting(meetingView);
		
		// Create update meeting
		Meeting updatedMeeting = dao.updateMeeting(meeting);
		
		// Convert object to be sent back to client
		MeetingView updatedMeetingView = convertMeeting(updatedMeeting);
		
		return updatedMeetingView;
		
	}

	@Override
	public boolean deleteById(Long id) {
		
		return dao.deleteById(id);
		
	}

	@Override
	public MeetingView findById(Long id) {
		
		// Find meeting
		Meeting meeting = dao.findById(id);
		
		if (meeting == null) {
			return null;
		} else {
			return convertMeeting(meeting);
		}

	}
	
	@Override
	public List<MeetingView> findByHost(Long id) {
		
		List<Meeting> meetings = dao.findByHost(id);
		List<MeetingView> meetingViews = new ArrayList<>();
		
		for (Meeting meeting: meetings) {
			MeetingView meetingView = convertMeeting(meeting);
			meetingViews.add(meetingView);
		}
		
		return meetingViews;
		
	}

	@Override
	public List<MeetingView> findAllMeetings() {
		
		List<Meeting> meetings = dao.findAllMeetings();
		List<MeetingView> meetingViews = new ArrayList<>();
		
		for (Meeting meeting: meetings) {
			MeetingView meetingView = convertMeeting(meeting);
			meetingViews.add(meetingView);
		}
		
		return meetingViews;
		
	}
	
	
	// Helper Methods
	private Meeting convertMeeting(MeetingView meetingView) {
		
		Meeting meeting = new Meeting(meetingView);
		UserView userView = meetingView.getHostUser();
		User user = new User(userView);
		meeting.setHostUser(user);
		
		return meeting;
	}
	private MeetingView convertMeeting(Meeting meeting) {
		
		MeetingView meetingView = new MeetingView(meeting);
		User user = meeting.getHostUser();
		UserView userView = new UserView(user);
		meetingView.setHostUser(userView);
		
		return meetingView;
	}
}
