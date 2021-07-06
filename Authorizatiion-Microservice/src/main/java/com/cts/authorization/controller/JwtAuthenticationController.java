package com.cts.authorization.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.authorization.Hook;
import com.cts.authorization.config.JwtTokenUtil;
import com.cts.authorization.exception.AuthorizationException;
import com.cts.authorization.model.JwtRequest;
import com.cts.authorization.model.JwtResponse;
import com.cts.authorization.model.MyUserDetails;
import com.cts.authorization.model.User;
import com.cts.authorization.repository.UserRespository;
import com.cts.authorization.service.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private UserRespository respository;

	@Autowired
	private Hook hook;

	@GetMapping("/hook")
	public void hook() {
		hook.insertData();
	}

	@GetMapping("/get-all")
	public ResponseEntity<?> getAll() {
		List<User> findAll = respository.findAll();
		return ResponseEntity.ok(Map.of("users", findAll));
	}

	/**
	 * @param authenticationRequest
	 * @return
	 * @throws AuthorizationException
	 * @throws Exception
	 */
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws AuthorizationException {
		Authentication auth = authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());
		final MyUserDetails userDetails = (MyUserDetails) userDetailsService
				.loadUserByUsername(authenticationRequest.getUserName());
		System.out.println(userDetails);
		final String token = jwtTokenUtil.generateToken(userDetails);
		JwtResponse jwtResponse = new JwtResponse(token);
		jwtResponse.setRoles(userDetails.getRoles());
		return ResponseEntity.ok(jwtResponse);
	}

	private Authentication authenticate(String userName, String password) throws AuthorizationException {
		try {
			System.out.println("Inside authenticate Method==================================");
			Authentication auth = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
			System.out.println("Authentication Successful.....");
			System.out.println(auth.getCredentials() + "+++++++++++++++++++++++++++++++++");
			return auth;

		} catch (DisabledException e) {
			throw new AuthorizationException("USER_DISABLED");
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new AuthorizationException("INVALID_CREDENTIALS");
		}

	}

	/**
	 * @param requestTokenHeader
	 * @return
	 */
	@PostMapping(value = "/authorize")
	public ResponseEntity<?> authorizeTheRequest(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) {
		System.out.println("Inside authorize ==============" + requestTokenHeader);
		String jwtToken = null;
		String userName = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			System.out.println("JWT Tocken =======================" + jwtToken);
			try {
				userName = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException | ExpiredJwtException | SignatureException e) {
				String err = e.getLocalizedMessage();
				log.error(err);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
			}
		}
		if (Objects.isNull(userName)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("userName can not be null");
		}

		return ResponseEntity.status(HttpStatus.OK).body(requestTokenHeader);
	}

	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("auth-Ok", HttpStatus.OK);
	}

}