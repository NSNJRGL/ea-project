package com.miu.excercise15springdatajpa.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomerDetailsService implements UserDetailsService{


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
//		//Customer customer = customerRepository.findByEmail(username);
//
//
//		if(customer==null) {
//			throw new UsernameNotFoundException("Customer not found with username: " + username);
//
//		}
		
		boolean accountNonExpired = true; 
		boolean credentialsNonExpired = true; 
		boolean accountNonLocked = true;
		
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
				username, 
				"password",
				true, 
				accountNonExpired, 
				credentialsNonExpired, 
				accountNonLocked, 
				Arrays.asList(new SimpleGrantedAuthority("USER")));
		
		return userDetails;
	}
	

}