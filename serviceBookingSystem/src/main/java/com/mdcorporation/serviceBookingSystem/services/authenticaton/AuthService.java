package com.mdcorporation.serviceBookingSystem.services.authenticaton;

import com.mdcorporation.serviceBookingSystem.dto.LoginRequestDto;
import com.mdcorporation.serviceBookingSystem.dto.SignupRequestDTO;
import com.mdcorporation.serviceBookingSystem.dto.UserDTO;
import com.mdcorporation.serviceBookingSystem.entity.User;

public interface AuthService {
	 UserDTO signupClient(User user,SignupRequestDTO signupRequestDTO);
	
	
	
	boolean presentByEmail(String email);

	String verify(LoginRequestDto loginRequestDto);

	UserDTO signupCompany(User user, SignupRequestDTO signupDto);
	
	void registerUser(String email);
}
