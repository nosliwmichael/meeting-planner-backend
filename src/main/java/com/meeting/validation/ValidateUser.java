package com.meeting.validation;

import com.meeting.model.User;
import com.meeting.viewmodel.UserView;

public class ValidateUser {

	public static boolean validateNewUser(User user) {
		
		Validator userValidator = new Validator();
		
		if (userValidator.isNotNull(user).isValid()) {
			return userValidator
					.isStringLongerThan(user.getEmail(), 6)
					.isStringLongerThan(user.getFirstName(), 1)
					.isStringLongerThan(user.getPassword(), 5)
					.isValid();
		} else {
			return false;
		}
		
	}

	public static boolean validateNewUser(UserView user) {

		Validator userValidator = new Validator();
		
		if (userValidator.isNotNull(user).isValid()) {
			return userValidator
				.isStringLongerThan(user.getEmail(), 6)
				.isStringLongerThan(user.getFirstName(), 1)
				.isStringLongerThan(user.getPassword(), 5)
				.isValid();
		} else {
			return false;
		}
		
	}

	public static boolean validateUser(User user) {

		Validator userValidator = new Validator();
		
		if (userValidator.isNotNull(user).isValid()) {
			return userValidator
				.isGreaterThan(user.getId().intValue(), 0)
				.isStringLongerThan(user.getEmail(), 6)
				.isStringLongerThan(user.getFirstName(), 1)
				.isStringLongerThan(user.getPassword(), 5)
				.isValid();
		} else {
			return false;
		}
		
	}

	public static boolean validateUser(UserView user) {

		Validator userValidator = new Validator();
		
		if (userValidator.isNotNull(user).isValid()) {
			return userValidator
				.isGreaterThan(user.getId().intValue(), 0)
				.isStringLongerThan(user.getEmail(), 6)
				.isStringLongerThan(user.getFirstName(), 1)
				.isStringLongerThan(user.getPassword(), 5)
				.isValid();
		} else {
			return false;
		}
		
	}
	
}
