package com.meeting.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meeting.service.MeetingService;
import com.meeting.viewmodel.MeetingView;

@RestController
@RequestMapping(value="/meeting")
public class MeetingRestController {
	
	private static final Logger logger = LogManager.getLogger(MeetingRestController.class);
	
	@Autowired
	private MeetingService meetingService;
	
	// CREATE NEW MEETING
	@PostMapping(value="/")
	public ResponseEntity<MeetingView> createMeeting(@RequestBody MeetingView meeting) {
		
		MeetingView newMeeting = meetingService.create(meeting);
		
		if (newMeeting == null) {
			
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			
		} else {
			
			return new ResponseEntity<>(newMeeting, HttpStatus.CREATED);
			
		}
		
	}
	
	// GET MEETING BY ID
	@GetMapping(value="/{id}")
	public ResponseEntity<MeetingView> findById(@PathVariable("id") Long id) {
		
		MeetingView meeting = meetingService.findById(id);
		
		if (meeting == null) {
			
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			
		} else {
			
			return new ResponseEntity<>(meeting, HttpStatus.OK);
			
		}
		
	}
	
	// GET ALL MEETINGS
	@GetMapping(value="/")
	public ResponseEntity<List<MeetingView>> findAllMeetings(HttpServletRequest request) {
		
		logger.debug("Get all meetings");
		logger.debug(request.getAttribute("email"));
		List<MeetingView> allMeetings = meetingService.findAll();
		return new ResponseEntity<>(allMeetings, HttpStatus.OK);
		
	}
	
	// GET MEETINGS BY HOST ID
	@GetMapping(value="/byHost/{id}")
	public ResponseEntity<List<MeetingView>> findByHost(@PathVariable("id") Long id) {
		
		List<MeetingView> hostedMeetings = meetingService.findByHost(id);
		return new ResponseEntity<>(hostedMeetings, HttpStatus.OK);
		
	}	
	
	// UPDATE MEETING
	@PutMapping(value="/")
	public ResponseEntity<MeetingView> updateMeeting(@RequestBody MeetingView meeting) {
		
		MeetingView updatedMeeting = meetingService.update(meeting);
		return new ResponseEntity<>(updatedMeeting, HttpStatus.OK);
			
	}
	
	// DELETE MEETING
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable ("id") Long id) {
	
		return new ResponseEntity<>(meetingService.deleteById(id), HttpStatus.OK);
	
	}

}
