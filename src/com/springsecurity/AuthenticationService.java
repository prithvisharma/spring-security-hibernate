package com.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
//import ;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springsecurity.database.Database;
import com.springsecurity.model.User;
import com.springsecurity.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private Database database;
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//go to db and fetch user based upon their username
		//System.out.println(username);
		
		//User user = database.loadUserByUsername(username);
		
		
		// load user by username using hibernate
		
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Invalid");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.getAuthorities());
	}
	
}
