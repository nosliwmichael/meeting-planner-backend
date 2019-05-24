package com.meeting.service;

import java.util.List;

import com.meeting.viewmodel.MeetingView;

public interface MeetingService {
	
	MeetingView create(MeetingView meeting);
	
	MeetingView update(MeetingView meeting);
	
	boolean deleteById(Long id);
	
	MeetingView findById(Long id);
	
	List<MeetingView> findByHost(Long id);
	
	List<MeetingView> findAll();
	
}
