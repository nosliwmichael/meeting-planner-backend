package com.meeting.model;

import java.util.HashSet;
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
	
	@Column(name = "password", nullable = false)
	private String password;


	@OneToMany(mappedBy="hostUser")
	private Set<Meeting> meetings;

	// Constructor
	public User() {}
	public User(UserView userView) {
		
		this.userId = userView.getId();
		this.firstName = userView.getFirstName();
		this.lastName = userView.getLastName();
		this.email = userView.getEmail();
		this.password = userView.getPassword();
		this.meetings = new HashSet<>();
		
	}
	public User(UserView userView, Set<Meeting> meetings) {
		
		this.userId = userView.getId();
		this.firstName = userView.getFirstName();
		this.lastName = userView.getLastName();
		this.email = userView.getEmail();
		this.password = userView.getPassword();
		this.meetings = meetings;
		
	}
	public User(Long id, String firstName, String lastName, String email, String password) {
		this.userId = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.meetings = new HashSet<>();
	}
	public User(Long id, String firstName, String lastName, String email, String password, Set<Meeting> meetings) {
		this.userId = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.meetings = meetings;
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
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Meeting> getMeetings() {
		return this.meetings;
	}
	public void setMeetings(Set<Meeting> meetings) {
		this.meetings = meetings;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.userId +
				", First Name: " + this.firstName + 
				", Last Name: " + this.lastName + 
				", Email: " + this.email + 
				", Password: " + this.password;
	}
	
}
