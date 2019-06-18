package com.meeting.validation;

import com.meeting.model.Meeting;
import com.meeting.viewmodel.MeetingView;

public class ValidateMeeting {

	public static boolean validNewMeeting(Meeting meeting) {
		
		return new Validator()
				.isStringLongerThan(meeting.getSubject(), 1)
				.isTimeAfter(meeting.getTime(), Validator.currentTime())
				.isStringLongerThan(meeting.getLocation(), 1)
				.isNotNull(meeting.getHostUser())
				.isValid();
		
	}

	public static boolean validNewMeeting(MeetingView meeting) {
		
		return new Validator()
				.isStringLongerThan(meeting.getSubject(), 1)
				.isTimeAfter(meeting.getTime(), Validator.currentTime())
				.isStringLongerThan(meeting.getLocation(), 1)
				.isNotNull(meeting.getHostUser())
				.isValid();
		
	}
	
	public static boolean validateMeeting(Meeting meeting) {

		return new Validator()
				.isGreaterThan(meeting.getId(), 0)
				.isStringLongerThan(meeting.getSubject(), 1)
				.isTimeAfter(meeting.getTime(), Validator.currentTime())
				.isStringLongerThan(meeting.getLocation(), 1)
				.isNotNull(meeting.getHostUser())
				.isValid();
		
	}
	
	public static boolean validateMeeting(MeetingView meeting) {

		return new Validator()
				.isGreaterThan(meeting.getId(), 0)
				.isStringLongerThan(meeting.getSubject(), 1)
				.isTimeAfter(meeting.getTime(), Validator.currentTime())
				.isStringLongerThan(meeting.getLocation(), 1)
				.isNotNull(meeting.getHostUser())
				.isValid();
		
	}
	
}
