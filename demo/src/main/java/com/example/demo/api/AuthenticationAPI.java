package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.TokenProvider;
import com.example.demo.dto.AuthToken;
import com.example.demo.dto.UserDTO;

@RestController
public class AuthenticationAPI {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@PostMapping("/authentication")
	public ResponseEntity<?> register(@RequestBody UserDTO userDTO) throws AuthenticationException {

		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
				
		final String token = jwtTokenUtil.generateToken(authentication);
		return ResponseEntity.ok(new AuthToken(token));
	}
}
