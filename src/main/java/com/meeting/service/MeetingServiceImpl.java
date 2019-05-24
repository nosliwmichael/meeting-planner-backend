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
	public MeetingView create(MeetingView meetingView) {
		
		// Convert object to be used by DAO
		Meeting meeting = convertMeeting(meetingView);
		
		// Create new meeting
		Meeting newMeeting = dao.create(meeting);
		
		if (newMeeting == null) {
			return null;
		} else {
			// Convert object to be sent back to client
			return convertMeeting(newMeeting);
		}
		
	}

	@Override
	public MeetingView update(MeetingView meetingView) {

		// Convert object to be used by DAO
		Meeting meeting = convertMeeting(meetingView);
		
		// Create update meeting
		Meeting updatedMeeting = dao.update(meeting);
		
		if (updatedMeeting == null) {
			return null;
		} else {
			// Convert object to be sent back to client
			return convertMeeting(updatedMeeting);
		}
		
	}

	@Override
	public boolean deleteById(Long id) {
		
		return (dao.deleteById(id) > 0);
		
	}

	@Override
	public MeetingView findById(Long id) {
		
		// Find meeting
		Meeting meeting = dao.findById(id);
		
		if (meeting == null) {
			return null;
		} else {
			// Convert object to be sent back to client
			return convertMeeting(meeting);
		}

	}
	
	@Override
	public List<MeetingView> findByHost(Long id) {
		
		List<Meeting> meetings = dao.findByHost(id);
		
		if (meetings == null) {
			
			return null;
			
		} else {
			
			List<MeetingView> meetingViews = new ArrayList<>();
			
			for (Meeting m : meetings) {
				meetingViews.add(convertMeeting(m));
			}
			
			return meetingViews;
			
		}
		
	}

	@Override
	public List<MeetingView> findAll() {
		
		List<Meeting> meetings = dao.findAll();
		
		if (meetings == null) {
			
			return null;
			
		} else {
			
			List<MeetingView> meetingViews = new ArrayList<>();
			
			for (Meeting m : meetings) {
				meetingViews.add(convertMeeting(m));
			}
			
			return meetingViews;
			
		}
		
	}
	
	
	// Helper Methods
	private Meeting convertMeeting(MeetingView meetingView) {
		
		Meeting meeting = new Meeting(meetingView, new User(meetingView.getHostUser()));
		return meeting;
	}
	private MeetingView convertMeeting(Meeting meeting) {
		
		MeetingView meetingView = new MeetingView(meeting, new UserView(meeting.getHostUser()));
		return meetingView;
	}
	
}
