package com.mdcorporation.serviceBookingSystem.services.authenticaton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mdcorporation.serviceBookingSystem.entity.User;
import com.mdcorporation.serviceBookingSystem.entity.UserPrincipal;
import com.mdcorporation.serviceBookingSystem.repositry.UserRepositery;

@Service
public class MyUserDetailService implements UserDetailsService{
	
	@Autowired
	UserRepositery userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		User user = userRepo.findFirstByEmail(userEmail);
		if (user == null) {
			return (UserDetails) new UsernameNotFoundException("user not found");
		}
		return new UserPrincipal(user) ;
	}

}
