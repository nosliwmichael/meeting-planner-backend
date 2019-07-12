package com.meeting.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class JwtInterceptor implements HandlerInterceptor {

	private static final Logger logger = LogManager.getLogger(JwtInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String uri = request.getRequestURI();
		String requestMethod = request.getMethod();
		String token = request.getHeader("Authorization");
		logger.debug(requestMethod + " - " + uri, token);
		
		if ("OPTIONS".equals(requestMethod)) {
			return true;
		}
		else if (token == null) {
			return false;
		}
		else if (JWTToken.validate(JWTToken.key, token)) {
			request.setAttribute("email", JWTToken.getUsername(JWTToken.key, token));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

	
	
}
