package com.meeting.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.meeting.service.MeetingService;

@Profile("test")
@Configuration
public class MeetingServiceConfiguration {
	
	@Bean
	@Primary
	public MeetingService meetingService() {
		return Mockito.mock(MeetingService.class);
	}
	
}
