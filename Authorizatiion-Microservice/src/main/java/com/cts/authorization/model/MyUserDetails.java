package com.cts.authorization.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 8932113486926657897L;
	private User user;

	public MyUserDetails(User user) {
		this.user = user;
	}

	public List<String> getRoles() {
		return List.of(user.getRoles().split(","));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		String[] roles = this.user.getRoles().split(",");
		List<SimpleGrantedAuthority> authorities = Arrays.stream(roles).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toUnmodifiableList());
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
