package com.mdcorporation.serviceBookingSystem.services.authenticaton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mdcorporation.serviceBookingSystem.dto.LoginRequestDto;
import com.mdcorporation.serviceBookingSystem.dto.SignupRequestDTO;
import com.mdcorporation.serviceBookingSystem.dto.UserDTO;
import com.mdcorporation.serviceBookingSystem.entity.User;
import com.mdcorporation.serviceBookingSystem.enums.UserRole;
import com.mdcorporation.serviceBookingSystem.jwt.JWTService;
import com.mdcorporation.serviceBookingSystem.repositry.UserRepositery;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JWTService jwtService;
	
	public BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	@Autowired
	private UserRepositery userRepositery;
	
	@Autowired
    private EmailVerificationImpl emailVerificationService;


    public void registerUser(String email) {
        User user = new User();
        user.setEmail(email);
        user.setEmailVerified(false);
        userRepositery.save(user);
        emailVerificationService.sendVerificationEmail(user);
    }
	
	public UserDTO signupClient(User user,SignupRequestDTO signupRequestDTO) {
		
		user.setName(signupRequestDTO.getName());
		user.setLastname(signupRequestDTO.getLastname());
		user.setEmail(signupRequestDTO.getEmail());
		user.setPhone(signupRequestDTO.getPhone());
		user.setPassword(signupRequestDTO.getPassword());
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(UserRole.CLIENT);
		
		
		return userRepositery.save(user).getDto();
	}
	
	
	public UserDTO signupCompany(User user, SignupRequestDTO signupRequestDTO) {
		user.setName(signupRequestDTO.getName());
		user.setLastname(signupRequestDTO.getLastname());
		user.setEmail(signupRequestDTO.getEmail());
		user.setPhone(signupRequestDTO.getPhone());
		user.setPassword(signupRequestDTO.getPassword());
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(UserRole.COMPANY);
		
		
		return userRepositery.save(user).getDto();
	}
	
	public boolean presentByEmail(String email) {
		return userRepositery.findFirstByEmail(email) !=null;
		
	}
	


	@Override
	public String verify(LoginRequestDto loginRequestDto) {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.genrateToken(loginRequestDto.getEmail());
		} else {
			return "false value";
		}
	}

}
