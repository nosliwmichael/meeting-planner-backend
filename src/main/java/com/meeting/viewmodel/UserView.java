package com.meeting.viewmodel;

import java.util.Set;

import com.meeting.model.User;

public class UserView {
	
	// Properties
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Set<MeetingView> meetings;
	
	public UserView() {}
	public UserView(User user) {
		
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		
	}

	// Getters & Setters
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Set<MeetingView> getMeetings() {
		return this.meetings;
	}
	public void setMeetings(Set<MeetingView> meetings) {
		this.meetings = meetings;
	}
	
}