package com.meeting.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.meeting.viewmodel.MeetingView;


@Entity
@Table(name="meeting")
public class Meeting {
	
	// Properties
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "meeting_id", updatable = false, nullable = false)
	private Long meetingId;

	@Column(name = "subject", nullable = false)
	private String subject;

	@Column(name = "time", nullable = false)
	private Timestamp time;
	
	@Column(name = "location", nullable = false)
	private String location;
	
	@ManyToOne
	@JoinColumn(name = "host_id", nullable = false)
	private User hostUser;
	
	// Constructors
	public Meeting() {}
	public Meeting(MeetingView meetingView) {
		this.meetingId = meetingView.getId();
		this.subject = meetingView.getSubject();
		this.time = meetingView.getTime();
		this.location = meetingView.getLocation();
	}
	public Meeting(MeetingView meetingView, User user) {
		this.meetingId = meetingView.getId();
		this.subject = meetingView.getSubject();
		this.time = meetingView.getTime();
		this.location = meetingView.getLocation();
		this.hostUser = user;
	}
	public Meeting(Long id, String subject, Timestamp time, String location) {
		this.meetingId = id;
		this.subject = subject;
		this.time = time;
		this.location = location;
	}
	public Meeting(Long id, String subject, Timestamp time, String location, User user) {
		this.meetingId = id;
		this.subject = subject;
		this.time = time;
		this.location = location;
		this.hostUser = user;
	}
	
	// Getters & Setters
	public Long getId() {
		return this.meetingId;
	}
	public void setId(Long id) {
		this.meetingId = id;
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
	
	public User getHostUser() {
		return this.hostUser;
	}
	public void setHostUser(User user) {
		this.hostUser = user;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.meetingId + 
				", Subject: " + this.subject + 
				", Time: " + this.time + 
				", Location: " + this.location + 
				" | " + this.hostUser.toString();
	}
	
}
