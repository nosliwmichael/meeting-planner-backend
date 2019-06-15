package com.meeting.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meeting.service.UserService;
import com.meeting.viewmodel.UserView;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/login")
public class LoginRestController {

	@Autowired
	private UserService userService;
	
	// LOGIN
	@RequestMapping(value="/", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserView> create(@RequestBody UserView user) {
		
		System.out.println(user);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
	
}
