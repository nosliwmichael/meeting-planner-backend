package com.meeting.dao;

import java.util.List;

import com.meeting.model.Meeting;

public interface MeetingDao {
	
	Meeting create(Meeting meeting);
	
	Meeting update(Meeting meeting);

	int deleteById(Long id);

	Meeting findById(Long id);
	
	List<Meeting> findByHost(Long id);
	
	List<Meeting> findAll();
	
}
