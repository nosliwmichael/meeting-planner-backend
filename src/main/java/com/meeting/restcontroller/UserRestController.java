package com.meeting.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meeting.service.UserService;
import com.meeting.viewmodel.UserView;

@RestController
@RequestMapping(value="/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	// GET USER BY ID
	@GetMapping(value="/{id}")
	public ResponseEntity<UserView> findById(@PathVariable("id") Long id) {
		
		UserView user = userService.findById(id);
		
		if (user == null) {
			
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			
		} else {
			
			return new ResponseEntity<>(user, HttpStatus.OK);
			
		}
		
	}
	
	// GET ALL USERS
	@GetMapping(value="/")
	public ResponseEntity<List<UserView>> getAll() {
		
		List<UserView> allUsers = userService.findAll();
		
		if (allUsers.isEmpty()) {
			
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			
		} else {
			
			return new ResponseEntity<>(allUsers, HttpStatus.OK);
		
		}
		
	}
	
	// UPDATE USER
	@PutMapping(value="/")
	public ResponseEntity<UserView> update(@RequestBody UserView user) {
		
		UserView updatedUser = userService.update(user);
		
		if (updatedUser == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			
		} else {
			
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		
		}
			
	}
	
	// DELETE USER
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		
		return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
		
	}
	
}
