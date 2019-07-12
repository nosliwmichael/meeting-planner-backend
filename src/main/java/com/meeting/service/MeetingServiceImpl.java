package com.meeting.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meeting.dao.MeetingDao;
import com.meeting.model.Meeting;
import com.meeting.viewmodel.MeetingView;

@Service
@Transactional
public class MeetingServiceImpl implements MeetingService {
	
	@Autowired
	private MeetingDao dao;

	@Override
	public MeetingView create(MeetingView meetingView) {
		
		// Convert object to be used by DAO
		Meeting meeting = convertMeetingView(meetingView);
		
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
		Meeting meeting = convertMeetingView(meetingView);
		
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
			
			List<MeetingView> meetingViews = meetings.stream()
					.map(MeetingServiceImpl::convertMeeting)
					.collect(Collectors.toList());
			
			return meetingViews;
			
		}
		
	}

	@Override
	public List<MeetingView> findAll() {
		
		List<Meeting> meetings = dao.findAll();
		
		if (meetings == null) {
			
			return null;
			
		} else {
			
			List<MeetingView> meetingViews = meetings.stream()
					.map(MeetingServiceImpl::convertMeeting)
					.collect(Collectors.toList());
			
			return meetingViews;
			
		}
		
	}
	
	// Conversion methods
	public static Meeting convertMeetingView(MeetingView meetingView) {
		
		Meeting meeting = new Meeting(meetingView);
		meeting.setHostUser(UserServiceImpl.convertUserView(meetingView.getHostUser()));
		meeting.setUsers(
				meetingView
				.getUsers().stream()
				.map(UserServiceImpl::convertUserView)
				.collect(Collectors.toSet()));
		return meeting;
		
	}
	public static MeetingView convertMeeting(Meeting meeting) {
		
		MeetingView meetingView = new MeetingView(meeting);
		meetingView.setHostUser(UserServiceImpl.convertUser(meeting.getHostUser()));
		meetingView.setUsers(
				meeting
				.getUsers().stream()
				.map(UserServiceImpl::convertUser)
				.collect(Collectors.toSet()));
		return meetingView;
		
	}
	
}
