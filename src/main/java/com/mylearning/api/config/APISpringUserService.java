package com.mylearning.api.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Configuration
public class APISpringUserService implements UserDetailsService, EnvironmentAware {

	
	private ConfigurableEnvironment env;
	
	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		
	if(env!=null)
	{
		 		 
		 Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		 SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority("ROLE_USER");
		 authorities.add(userAuthority);
		
		 if(arg0.equals("apiuser"))
		  {
			 UserDetails user = new User("apiuser", "pass123", true, true, true, true, authorities);
			 return user;
		  }else
			  return null;
		
	}
		
	return null;		
		
	}

	@Override
	public void setEnvironment(Environment arg0) {
		this.env = (ConfigurableEnvironment) arg0;		
	}

}
