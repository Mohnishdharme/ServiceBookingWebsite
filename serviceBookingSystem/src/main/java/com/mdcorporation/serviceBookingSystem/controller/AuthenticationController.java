package com.mdcorporation.serviceBookingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.InvalidConfigDataPropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.mdcorporation.serviceBookingSystem.dto.LoginRequestDto;
import com.mdcorporation.serviceBookingSystem.dto.SignupRequestDTO;
import com.mdcorporation.serviceBookingSystem.dto.UserDTO;
import com.mdcorporation.serviceBookingSystem.entity.User;
import com.mdcorporation.serviceBookingSystem.repositry.UserRepositery;
import com.mdcorporation.serviceBookingSystem.services.authenticaton.AuthService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserRepositery userRepositery;
	
	
	
	@PostMapping("client/signup")
	public ResponseEntity<?> signUpUser(@RequestBody SignupRequestDTO signupDto) {
	try {
		
		// @mohanish
		// validate if email already exists
		if (authService.presentByEmail(signupDto.getEmail())) {
			return  ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body("client already exist with this email");
		}
		
		// @mohanish
		// calling the service to register new client
		UserDTO createdUser = authService.signupClient(signupDto);
		return  ResponseEntity.ok(createdUser);
		
		
	}catch (Exception e) {
		return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid Credentials" +e);
		}
	}
	
	@PostMapping("company/signup")
	public ResponseEntity<?> signUpCompany(@RequestBody SignupRequestDTO signupDto) {
	try {
		// @mohanish
		// check if company already exist with the email
		if (authService.presentByEmail(signupDto.getEmail())) {
			return  ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body("client already exits with this email");
		}
		
		//@mohanish
		// calling the service to register new company
		UserDTO createdUser = authService.signupCompany(signupDto);
		return new ResponseEntity<>(createdUser, HttpStatus.OK);
	} catch (Exception e) {
		return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid Credentials" +e);
	}
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
		try {
			//@mohanish
		    // Find if the user exist in database
		    User user = userRepositery.findFirstByEmail(loginRequestDto.getEmail());
		    if (user == null) {
		    	throw new RuntimeException("User not found");
		    }
		    
		    //@mohanish
		    //create a jwt token
		    String token = authService.verify(loginRequestDto);

		    // Create the response JSON
		    JsonObject jsonResponse = new JsonObject();
		    jsonResponse.addProperty("userId", user.getId());
		    jsonResponse.addProperty("userEmail", user.getEmail());
		    jsonResponse.addProperty("userRole", user.getRole().toString());
		
		    //@mohanish 
		    // Add the token to the Authorization header
		    return ResponseEntity.ok()
		            .header("Authorization", "Bearer " + token)
		            .body(jsonResponse.toString());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid credential"+ e);
		}

	}

	

}