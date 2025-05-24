package com.booking.app.utils;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.booking.app.dto.UserDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtUtil {
	private final String SecretKey="whateveritis";
	
	public String generateToken(UserDto userDto) {
	
		return Jwts.builder()
				.setSubject(userDto.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
				.signWith(SignatureAlgorithm.HS256, SecretKey)
				.compact();
		
	}

	public String extractUsername(String token) {
		
		return Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		  return extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody().getExpiration().before(new Date());
	}

}
