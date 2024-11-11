package com.mdcorporation.serviceBookingSystem.services.authenticaton;

import com.mdcorporation.serviceBookingSystem.entity.User;

public interface EmailVerification {
	

	
	void sendVerificationEmail(User user);
	
	boolean verifyEmail(String token);
	
	String generateVerificationToken();

}
