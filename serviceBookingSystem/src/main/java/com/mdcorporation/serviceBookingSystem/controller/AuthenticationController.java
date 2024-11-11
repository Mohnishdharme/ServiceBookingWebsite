package com.mdcorporation.serviceBookingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.InvalidConfigDataPropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.mdcorporation.serviceBookingSystem.dto.LoginRequestDto;
import com.mdcorporation.serviceBookingSystem.dto.SignupRequestDTO;
import com.mdcorporation.serviceBookingSystem.dto.UserDTO;
import com.mdcorporation.serviceBookingSystem.entity.User;
import com.mdcorporation.serviceBookingSystem.repositry.UserRepositery;
import com.mdcorporation.serviceBookingSystem.services.authenticaton.AuthService;
import com.mdcorporation.serviceBookingSystem.services.authenticaton.EmailVerificationImpl;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserRepositery userRepositery;
	
	@Autowired
    private EmailVerificationImpl emailVerificationImpl;
	
	
	//@mohnish 
	//verify's the email 
	@PostMapping("api/verify-email")
	public ResponseEntity<?> initiateEmailVerification(@RequestBody SignupRequestDTO signupRequestDTO) {
	    try {
	        User user = userRepositery.findByEmail(signupRequestDTO.getEmail());
	        if (user == null) {
	            authService.registerUser(signupRequestDTO.getEmail());
	            return ResponseEntity.ok("Verification email sent to new user");
	        } else if (!user.isEmailVerified()) {
	            emailVerificationImpl.sendVerificationEmail(user);
	            return ResponseEntity.ok("Verification email sent");
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already verified");
	        }
	    } catch (Exception e) {
	        
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("An unexpected error occurred. Please try again later.");
	    }
	}
	


	@GetMapping("/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam String token) {
        boolean isVerified = emailVerificationImpl.verifyEmail(token);
        if (isVerified) {
            return ResponseEntity.ok().body("Email verified successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired token");
        }
    }
	
	
	
	@PostMapping("client/signup")
	public ResponseEntity<?> signUpUser(@RequestBody SignupRequestDTO signupDto) {
	try {
		
		// @mohanish
		// validate if email already exists
		User user = userRepositery.findByEmail(signupDto.getEmail());
		if (user==null) {
			return  ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body("client Does not exist plz verify email");
		}else if (user.getPassword()==null  && user.isEmailVerified()) {
			authService.signupClient(user, signupDto);
			return  ResponseEntity.ok("");
		} else {

		}
		
	
		return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid Credentials" );
		
		
		
		
	}catch (Exception e) {
		return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid Credentials" +e);
		}
	}
	
	@PostMapping("company/signup")
	public ResponseEntity<?> signUpCompany(@RequestBody SignupRequestDTO signupDto) {
		try {
			
			// @mohanish
			// validate if email already exists
			User user = userRepositery.findByEmail(signupDto.getEmail());
			if (user==null) {
				return  ResponseEntity
						.status(HttpStatus.CONFLICT)
						.body("client Does not exist plz verify email");
			}else if (user.getPassword()==null  && user.isEmailVerified()) {
				authService.signupCompany(user,signupDto);
				return  ResponseEntity.ok("");
			} else {

			}
			
		
			return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid Credentials" );
			
			
			
			
		}catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid Credentials" +e);
			}
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
		try {
			//@mohanish
		    // Find if the user exist in database
		    User user = userRepositery.findByEmail(loginRequestDto.getEmail());
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