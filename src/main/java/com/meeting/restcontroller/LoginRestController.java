package com.meeting.restcontroller;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meeting.authentication.JWTToken;
import com.meeting.model.Login;
import com.meeting.service.UserService;
import com.meeting.viewmodel.UserView;

@RestController
public class LoginRestController {
	
	private Key key = JWTToken.key;
	
	@Autowired
	private UserService userService;

	private static final Logger logger = LogManager.getLogger(LoginRestController.class);
	
	// LOGIN
	@PostMapping(value="/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Login login) {

		UserView user = userService.login(login);
		
		if (user != null) {

			logger.debug("Login PASSED: " + login.toString());
			String token = JWTToken.token(user.getEmail(), key);
			Map<String, Object> response = new HashMap<>();
			response.put("token", token);
			response.put("user", user);
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		} else {

			logger.debug("Login FAILED: " + login.toString());
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			
		}
		
	}
	
	// REGISTER
	@PostMapping(value="/register")
	public ResponseEntity<UserView> create(@RequestBody UserView user) {
		
		UserView newUser = userService.create(user);
		
		if (newUser == null) {
			
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			
		} else {
			
			return new ResponseEntity<>(newUser, HttpStatus.OK);
		
		}
		
	}
	
	// VALIDATE TOKEN
	@PostMapping(value="/validate")
	public ResponseEntity<Boolean> validate(@RequestBody String token) {
		
		if (JWTToken.validate(key, token)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
		
	}
	
}
