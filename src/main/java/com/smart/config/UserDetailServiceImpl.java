package com.smart.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import com.smart.DAO.UserRepository;
import com.smart.Entities.User;

public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired 
	UserRepository userrepo;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 User user=(User) userrepo.findByEmail(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("Unable to find Email :"+username);
			
			
		}
		
		return ( new UserDetailsImpl(user.getEmail(), user.getPassword(), user.getRole()));
		
		
		
		
	}

}
