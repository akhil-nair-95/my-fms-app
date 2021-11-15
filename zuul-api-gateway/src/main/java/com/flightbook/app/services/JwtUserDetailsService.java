package com.flightbook.app.services;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("admin".equals(username)) {
//			return new User("demo", "{noop}demo@123", new ArrayList<>());
//			return new User("demo", "{bcrypt}$2a$10$hxoW/ZjApcKGrZTaDtRyj.fRGA0VNS6sMm0RIP5RZILxOmSxqLfj6", new ArrayList<>());
			return new User("admin", "$2a$10$9MCjPPSPFEPBDLMEayagPurCNGYdjLC2mRSElCUJvYBzO0gYy5etS", new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}