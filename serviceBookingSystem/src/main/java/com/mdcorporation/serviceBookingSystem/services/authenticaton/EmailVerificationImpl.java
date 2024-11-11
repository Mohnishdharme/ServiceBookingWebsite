package com.mdcorporation.serviceBookingSystem.services.authenticaton;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mdcorporation.serviceBookingSystem.entity.User;
import com.mdcorporation.serviceBookingSystem.repositry.UserRepositery;

@Service
public class EmailVerificationImpl implements EmailVerification{
	
	@Autowired
    private UserRepositery userRepository;

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(User user) {
    	
    	
        String token = generateVerificationToken();
        user.setVerificationToken(token);
        user.setTokenExpiryDate(LocalDateTime.now().plusHours(24)); // Token valid for 24 hours
        userRepository.save(user);

        String verificationLink = "http://localhost:8080/verify?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Email Verification");
        message.setText("Please click the link to verify your email: " + verificationLink);
        mailSender.send(message);
       
       
    }

    public boolean verifyEmail(String token) {
        User user = userRepository.findByVerificationToken(token);
        if (user != null && !user.isEmailVerified() && LocalDateTime.now().isBefore(user.getTokenExpiryDate())) {
            user.setEmailVerified(true);
            user.setVerificationToken(null);
            user.setTokenExpiryDate(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    //@mohanish 
    //this method generates the random token
    public String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }



}
