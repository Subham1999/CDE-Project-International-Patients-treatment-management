package com.cts.authorization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.authorization.model.MyUserDetails;
import com.cts.authorization.model.User;
import com.cts.authorization.repository.UserRespository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRespository userRespository;

	@SuppressWarnings("unused")
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String userName) {

		User user = userRespository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + userName);
		}
		log.info("User found");
		log.info("user successfully located");

		return new MyUserDetails(user);		
	}

}