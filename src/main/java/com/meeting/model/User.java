package com.meeting.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.meeting.viewmodel.UserView;

@Entity
@Table(name="user") // Change table name
public class User {
	
	// Properties
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", updatable = false, nullable = false)
	private Long userId;
	
	@Column(name = "firstName", nullable = false)
	private String firstName;
	
	@Column(name = "lastName", nullable = true)
	private String lastName;
	
	@Column(name = "email", nullable = false)
	private String email;


	@OneToMany(mappedBy="hostUser", orphanRemoval=true)
	private Set<Meeting> meetings;

	// Constructor
	public User() {}
	public User(UserView userView) {
		
		this.userId = userView.getId();
		this.firstName = userView.getFirstName();
		this.lastName = userView.getLastName();
		this.email = userView.getEmail();
		
	}
	
	// Getters & Setters
	public Long getId() {
		return this.userId;
	}
	public void setId(Long id) {
		this.userId = id;
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
	
	public Set<Meeting> getMeetings() {
		return this.meetings;
	}
	public void setMeetings(Set<Meeting> meetings) {
		this.meetings = meetings;
	}
	
}
