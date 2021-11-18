package com.intelliatech.LibraryManagement.config;


import com.intelliatech.LibraryManagement.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("Inside JwtUserDetailsService in loadUserByUsername()");
		//Database Call
		//Get User by UserId
		com.intelliatech.LibraryManagement.model.User user= this.userRepository.findByUsernameOrEmailOrMobileNumber(username, username, username);

		//Check User Exists or not
		if (ObjectUtils.isEmpty(user)) {
			throw new UsernameNotFoundException("User not found with userName: " + username);
		}


		List<SimpleGrantedAuthority> list = new ArrayList<>();
		SimpleGrantedAuthority role = new SimpleGrantedAuthority(user.getRole());
		list.add(role);

		log.info("Leaving JwtUserDetailsService in loadUserByUsername()");
		return new User(user.getUsername(), user.getPassword(), list);
	}

}
