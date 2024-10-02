package com.smart.config;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private String role;

	
	
	public UserDetailsImpl(String email, String password, String role)
	{
		this.email=email;
		this.password=password;
		this.role=role;
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
	     Collection<GrantedAuthority> authorities=new ArrayList<>();
		 GrantedAuthority simplegrantedauthority=new SimpleGrantedAuthority(role);
		 authorities.add(simplegrantedauthority);
		 
		 
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return email;
	}

}
