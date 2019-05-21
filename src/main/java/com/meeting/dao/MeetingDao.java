package com.meeting.dao;

import java.util.List;

import com.meeting.model.Meeting;

public interface MeetingDao {
	
	Meeting createMeeting(Meeting meeting);
	
	Meeting updateMeeting(Meeting meeting);

	boolean deleteById(Long id);

	Meeting findById(Long id);
	
	List<Meeting> findByHost(Long id);
	
	List<Meeting> findAllMeetings();
	
}
