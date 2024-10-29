package com.mdcorporation.serviceBookingSystem.services.authenticaton;

import com.mdcorporation.serviceBookingSystem.dto.LoginRequestDto;
import com.mdcorporation.serviceBookingSystem.dto.SignupRequestDTO;
import com.mdcorporation.serviceBookingSystem.dto.UserDTO;

public interface AuthService {
	UserDTO signupClient(SignupRequestDTO signupRequestDTO);
	
	
	
	boolean presentByEmail(String email);

	String verify(LoginRequestDto loginRequestDto);

	UserDTO signupCompany(SignupRequestDTO signupDto);
}
