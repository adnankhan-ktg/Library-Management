package com.intelliatech.LibraryManagement.config;

import com.intelliatech.LibraryManagement.model.User;
import com.intelliatech.LibraryManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
 @Autowired
 private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
	
             User user = new User();
             
             user = this.userRepostitory.findByUsername(username);
             if(user == null)
             {
            	 throw new UsernameNotFoundException(username);
             }
             return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());

		 
		 
		 
		 
		 
		 
	}

	}
