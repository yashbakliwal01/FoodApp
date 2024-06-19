package com.example.FoodApp.config;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class SecurityConfigTest {

	
	 @Test
	    public void testAuthentication() {
	        SecurityConfig securityConfig = new SecurityConfig();
	        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);

	        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("FoodService", "Food123");
	        Authentication authentication = authenticationManager.authenticate(token);

	        Assertions.assertNull(authentication);
	    }
}
