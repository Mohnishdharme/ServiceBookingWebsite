package com.mdcorporation.serviceBookingSystem.jwt;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.mdcorporation.serviceBookingSystem.services.authenticaton.MyUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	JWTService jwtService;
	
	@Autowired
	ApplicationContext context;
	
	private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);


	 @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	            throws ServletException, IOException {

	        String authHeader = request.getHeader("Authorization");
	        String token = null;
	        String username = null;
	        
	        // Check if the Authorization header contains a Bearer token
	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            token = authHeader.substring(7);
	            try {
	                username = jwtService.extractUserName(token);  // Extract username from the token
	            } catch (Exception e) {
	                // If token extraction fails, handle the exception (e.g., token is invalid or expired)
	                logger.error("Failed to extract username from JWT", e);
	            }
	        }
	        
	        // If username is extracted and no authentication is set in the security context
	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = context.getBean(MyUserDetailService.class).loadUserByUsername(username);

	            // Validate the token using the JWT service and user details
	            if (jwtService.validateToken(token, userDetails)) {
	                UsernamePasswordAuthenticationToken authToken = 
	                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                
	                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                
	                // Set authentication in the security context
	                SecurityContextHolder.getContext().setAuthentication(authToken);
	            }
	        }
	        
	        // Proceed with the filter chain
	        filterChain.doFilter(request, response);
	    }   
}