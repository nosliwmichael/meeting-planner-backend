package com.meeting.viewmodel;

import java.sql.Timestamp;

import com.meeting.model.Meeting;

public class MeetingView {

	// Properties
	private Long id;
	private String subject;
	private Timestamp time;
	private String location;
	private UserView hostUser;
		
	public MeetingView() {}
	public MeetingView(Meeting meeting) {
		this.id = meeting.getId();
		this.subject = meeting.getSubject();
		this.time = meeting.getTime();
		this.location = meeting.getLocation();
	}
	
	// Getters & Setters
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSubject() {
		return this.subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Timestamp getTime() {
		return this.time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	public String getLocation() {
		return this.location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public UserView getHostUser() {
		return this.hostUser;
	}
	public void setHostUser(UserView user) {
		this.hostUser = user;
	}
}
