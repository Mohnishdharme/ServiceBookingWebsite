package com.mdcorporation.serviceBookingSystem.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdcorporation.serviceBookingSystem.entity.User;

public interface UserRepositery extends JpaRepository<User, Long>{

	User findFirstByEmail(String email);
	
	

}
