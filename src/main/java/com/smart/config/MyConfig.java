package com.smart.config;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class MyConfig {
	
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return  NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
		UserDetailsService userDetailService()
		{
			return new UserDetailServiceImpl();
		}
		
		
	
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests()
	            //.requestMatchers("/admin/**").hasRole("ADMIN") // Uncomment if needed
	            .requestMatchers("/user/**").hasRole("USER") // Requires USER role
	            .requestMatchers("/**").permitAll() // Allow all access to everything else
	            .and()
	        .formLogin()
	            .loginPage("/signin").loginProcessingUrl("/loginprocess")
	            .defaultSuccessUrl("/user/user-dash")// Custom login page
	            .and()
	        .csrf()
	        .disable();
	            

	    return http.build();
	}
	
	@Bean
	 AuthenticationManager authManager(HttpSecurity http) throws Exception {
	    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
	    authenticationManagerBuilder.userDetailsService(userDetailService()).passwordEncoder(passwordEncoder());
	    return authenticationManagerBuilder.build();
	}
	
	

	
	
	
}
