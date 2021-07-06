package com.cts.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.cts.authorization.model.User;
import com.cts.authorization.repository.UserRespository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class Hook {

	@Autowired
	private UserRespository userRespository;

	public void insertData() {
		log.debug("data inserted");
		System.err.println("data inserted");
		userRespository.save(new User(1, "user1", "user1", "ROLE_ADMIN"));
		userRespository.save(new User(2, "user2", "user2", "ROLE_USER"));
		userRespository.save(new User(3, "user3", "user3", "ROLE_USER"));
		userRespository.save(new User(4, "user4", "user4", "ROLE_USER"));
	}
}
