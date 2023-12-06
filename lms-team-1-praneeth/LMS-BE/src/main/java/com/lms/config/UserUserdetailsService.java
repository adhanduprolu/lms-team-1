package com.lms.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lms.entity.User;
import com.lms.exception.details.EmailNotFoundException;
import com.lms.repository.UserRepo;

@Service
public class UserUserdetailsService implements UserDetailsService {

	@Autowired
	private UserRepo ur;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> findByemail = ur.findByemail(username);

		return findByemail.map(details -> new UserUserDetails(details))
				.orElseThrow(() -> new EmailNotFoundException("Email Not Found"));
	}

}
