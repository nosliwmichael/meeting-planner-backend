package com.meeting.validation;

import com.meeting.model.User;
import com.meeting.viewmodel.UserView;

public class ValidateUser {

	public static boolean validateNewUser(User user) {
		
		return new Validator()
				.isStringLongerThan(user.getEmail(), 6)
				.isStringLongerThan(user.getFirstName(), 1)
				.isStringLongerThan(user.getPassword(), 5)
				.isValid();
		
	}

	public static boolean validateNewUser(UserView user) {
		
		return new Validator()
				.isStringLongerThan(user.getEmail(), 6)
				.isStringLongerThan(user.getFirstName(), 1)
				.isStringLongerThan(user.getPassword(), 5)
				.isValid();
		
	}

	public static boolean validateUser(User user) {
		
		return new Validator()
				.isGreaterThan(user.getId(), 0)
				.isStringLongerThan(user.getEmail(), 6)
				.isStringLongerThan(user.getFirstName(), 1)
				.isStringLongerThan(user.getPassword(), 5)
				.isValid();
		
	}

	public static boolean validateUser(UserView user) {
		
		return new Validator()
				.isGreaterThan(user.getId(), 0)
				.isStringLongerThan(user.getEmail(), 6)
				.isStringLongerThan(user.getFirstName(), 1)
				.isStringLongerThan(user.getPassword(), 5)
				.isValid();
		
	}
	
}
