package com.mdcorporation.serviceBookingSystem.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mdcorporation.serviceBookingSystem.jwt.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//interface 
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	//adding security filter to the apllication
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(Customizer.withDefaults()) // Enable CORS if needed
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/authenticate",
                    "/client/signup",
                    "company/signup",
                    "/api/verify-email",
                    "/verify**" // Allow all variants of /verify with query params
                ).permitAll()
                .requestMatchers("/api/**").authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



	
	
	
	//it is used to authenticate object coming from user side 
		//takes value from user and checks if its correct user
		@Bean
		public AuthenticationProvider authentication() {
			DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
			
			
			//the encoded password in the database decoded using this
			provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
			provider.setUserDetailsService(userDetailsService);
			return provider;
		}
		
		
		//authentication mannager handles the authentication provider automatically but now we are doing it mannually
		@Bean
		public AuthenticationManager authenticationManager (AuthenticationConfiguration config) throws Exception {
			return config.getAuthenticationManager();
		}
		
		
		@Bean
		public CorsConfigurationSource corsConfigurationSource() {
		    CorsConfiguration configuration = new CorsConfiguration();
		    configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Your Angular frontend URL
		    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		    configuration.setExposedHeaders(Arrays.asList("Authorization"));

		    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		    source.registerCorsConfiguration("/**", configuration);
		    return source;
		}

}
