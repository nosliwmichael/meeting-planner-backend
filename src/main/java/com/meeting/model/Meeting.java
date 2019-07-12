package com.meeting.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "host_id", nullable = false)
	private User hostUser;
	
	@ManyToMany
	@JoinTable(
		name="user_meeting", 
		joinColumns = { @JoinColumn(name = "meeting_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "user_id") }
	)
	Set<User> users = new HashSet<>();
	
	// Constructors
	public Meeting() {}
	public Meeting(MeetingView meetingView) {
		this.meetingId = meetingView.getId();
		this.subject = meetingView.getSubject();
		this.time = meetingView.getTime();
		this.location = meetingView.getLocation();
		this.hostUser = new User();
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
		this.hostUser = new User();
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

	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
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
