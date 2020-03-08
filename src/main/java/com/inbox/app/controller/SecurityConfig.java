package com.inbox.app.controller;

import org.salespointframework.SalespointSecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

@EnableWebSecurity
public class SecurityConfig extends SalespointSecurityConfiguration{

	@Autowired
	public SessionRegistry sessionRegistry;
	
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/sign-up" , "/login").permitAll()
				.antMatchers("/users" , "/chat" , "/edit/profile").hasRole("USER")
				.and()
			.formLogin()
				.loginPage("/login").loginProcessingUrl("/login").and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/");	
		
		http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());
	}
	
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    
}
