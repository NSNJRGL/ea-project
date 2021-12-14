package com.miu.excercise15springdatajpa.controller;


import com.miu.excercise15springdatajpa.jwtUtils.JwtTokenProvider;
import com.miu.excercise15springdatajpa.model.modelRequests.LoginRequest;
import com.miu.excercise15springdatajpa.model.modelResponses.CustomerResponse;
import com.miu.excercise15springdatajpa.model.modelResponses.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
//@CrossOrigin
@RequestMapping("/api/")
public class CustomerController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserDetailsService userDetailsService;

	
	@GetMapping("/ok")
	public String testing(){
		return "Am Ok";
	}
	

	@PostMapping("login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {

		//final ResponseEntity<String> response = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenProvider.generateToken(userDetails);
		System.out.println("token=="+token);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	


	private ResponseEntity<String> authenticate(String username, String password) throws Exception {
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {
			return new ResponseEntity<>("Please confirm your registration",HttpStatus.BAD_REQUEST);

		} catch (BadCredentialsException e) {
			return new ResponseEntity<>("Bad credantials",HttpStatus.BAD_REQUEST);
		} 
		return null;
	}
	


	
	private ResponseEntity<String> error(String message) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
	}
	


}
