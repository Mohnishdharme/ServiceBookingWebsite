package com.mdcorporation.serviceBookingSystem.repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdcorporation.serviceBookingSystem.entity.User;

public interface UserRepositery extends JpaRepository<User, Long>{

	Optional<User> findFirstByEmail(String email);

	User findByVerificationToken(String token);

	User findByEmail(String email);
	
	

}
