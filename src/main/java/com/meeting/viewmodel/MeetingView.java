package com.meeting.viewmodel;

import java.sql.Timestamp;
import java.util.Set;

import com.meeting.model.Meeting;

public class MeetingView {

	// Properties
	private Long meeting_id;
	
	private String subject;

	private Timestamp time;
	
	private String location;
	
	private UserView hostUser;
	
	private Set<UserView> users;
		
	public MeetingView() {}
	public MeetingView(Meeting meeting) {
		this.meeting_id = meeting.getId();
		this.subject = meeting.getSubject();
		this.time = meeting.getTime();
		this.location = meeting.getLocation();
	}
	public MeetingView(Meeting meeting, UserView user) {
		this.meeting_id = meeting.getId();
		this.subject = meeting.getSubject();
		this.time = meeting.getTime();
		this.location = meeting.getLocation();
		this.hostUser = user;
	}
	public MeetingView(Long id, String subject, Timestamp time, String location) {
		this.meeting_id = id;
		this.subject = subject;
		this.time = time;
		this.location = location;
		this.hostUser = new UserView();
	}
	public MeetingView(Long id, String subject, Timestamp time, String location, UserView hostUser) {
		this.meeting_id = id;
		this.subject = subject;
		this.time = time;
		this.location = location;
		this.hostUser = hostUser;
	}
	
	// Getters & Setters
	public Long getId() {
		return this.meeting_id;
	}
	public void setId(Long id) {
		this.meeting_id = id;
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

	public Set<UserView> getUsers() {
		return this.users;
	}
	public void setUsers(Set<UserView> users) {
		this.users = users;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.meeting_id + 
				", Subject: " + this.subject + 
				", Time: " + this.time + 
				", Location: " + this.location + 
				" | " + this.hostUser.toString();
	}
	
}
