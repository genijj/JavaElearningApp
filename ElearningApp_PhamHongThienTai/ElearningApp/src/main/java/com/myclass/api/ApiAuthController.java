package com.myclass.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.LoginDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("api/auth")
public class ApiAuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("login")
	public Object login(@RequestBody LoginDto dto) {
		
		try {
			
			Authentication authentication = 
					new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
			authentication = authenticationManager.authenticate(authentication);

			SecurityContextHolder.getContext().setAuthentication(authentication);

			Date now = new Date();
			String token = Jwts.builder()
			.setSubject(dto.getEmail())
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + 864000000L))
			.signWith(SignatureAlgorithm.HS512, "CYBERSOFT")
			.compact();
			
			return new ResponseEntity<String>(token, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}
}
