package com.meeting.validation;

import com.meeting.model.Meeting;
import com.meeting.viewmodel.MeetingView;

public class ValidateMeeting {

	public static boolean validNewMeeting(Meeting meeting) {
		
		Validator meetingValidator = new Validator();
		
		if (meetingValidator.isNotNull(meeting).isValid()) {
			return meetingValidator
					.isStringLongerThan(meeting.getSubject(), 1)
					.isTimeAfter(meeting.getTime(), Validator.currentTime())
					.isStringLongerThan(meeting.getLocation(), 1)
					.isNotNull(meeting.getHostUser())
					.isValid();
		} else {
			return false;
		}
		
	}

	public static boolean validNewMeeting(MeetingView meeting) {

		Validator meetingValidator = new Validator();
		
		if (meetingValidator.isNotNull(meeting).isValid()) {
			return meetingValidator
				.isStringLongerThan(meeting.getSubject(), 1)
				.isTimeAfter(meeting.getTime(), Validator.currentTime())
				.isStringLongerThan(meeting.getLocation(), 1)
				.isNotNull(meeting.getHostUser())
				.isValid();
		} else {
			return false;
		}
		
	}
	
	public static boolean validateMeeting(Meeting meeting) {

		Validator meetingValidator = new Validator();
		
		if (meetingValidator.isNotNull(meeting).isValid()) {
			return meetingValidator
				.isGreaterThan(meeting.getId(), 0)
				.isStringLongerThan(meeting.getSubject(), 1)
				.isTimeAfter(meeting.getTime(), Validator.currentTime())
				.isStringLongerThan(meeting.getLocation(), 1)
				.isNotNull(meeting.getHostUser())
				.isValid();
		} else {
			return false;
		}
		
	}
	
	public static boolean validateMeeting(MeetingView meeting) {

		Validator meetingValidator = new Validator();
		
		if (meetingValidator.isNotNull(meeting).isValid()) {
			return meetingValidator
				.isGreaterThan(meeting.getId(), 0)
				.isStringLongerThan(meeting.getSubject(), 1)
				.isTimeAfter(meeting.getTime(), Validator.currentTime())
				.isStringLongerThan(meeting.getLocation(), 1)
				.isNotNull(meeting.getHostUser())
				.isValid();
		} else {
			return false;
		}
		
	}
	
}
