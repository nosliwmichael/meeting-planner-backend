package com.meeting.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meeting.modelView.UserView;
import com.meeting.service.UserService;

@RestController
@RequestMapping(value="/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	// CREATE USER
	@RequestMapping(value="/", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserView> create(@RequestBody UserView user) {
		
		UserView newUser = userService.createUser(user);
		
		if (newUser == null) {
			
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			
		} else {
			
			return new ResponseEntity<>(newUser, HttpStatus.OK);
		
		}
		
	}
	
	// GET USER BY ID
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserView> findById(@PathVariable("id") Long id) {
		
		UserView user = userService.findById(id);
		
		if (user == null) {
			
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			
		} else {
			
			return new ResponseEntity<>(user, HttpStatus.OK);
			
		}
		
	}
	
	// GET ALL USERS
	@RequestMapping(value="/", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserView>> getAll() {
		
		List<UserView> allUsers = userService.findAllUsers();
		
		if (allUsers.isEmpty()) {
			
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			
		} else {
			
			return new ResponseEntity<>(allUsers, HttpStatus.OK);
		
		}
		
	}
	
	// UPDATE USER
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public ResponseEntity<UserView> update(@RequestBody UserView user) {
		
		UserView updatedUser = userService.updateUser(user);
		
		if (updatedUser == null) {
			
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			
		} else {
			
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		
		}
			
	}
	
	// DELETE USER
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		
		return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
		
	}
	
}
