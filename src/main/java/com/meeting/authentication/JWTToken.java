package com.meeting.authentication;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTToken {

	public static Key key = generateKey(); 
	
	private static Key generateKey() {
		byte[] bytes = {95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95,95};
		return Keys.hmacShaKeyFor(bytes);
	}
	
	public static String token(String email, Key key) {
		return Jwts.builder()
				.claim("email", email)
				.setExpiration(new Date(new Date().getTime() + 1800000))
				.signWith(key)
				.compact();
	}
	
	public static boolean validate(Key key, String token) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			System.out.println(e);
			return false;
		}	
	}
	
	public static String getUsername(Key key, String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().get("email", String.class);
	}
	
}
