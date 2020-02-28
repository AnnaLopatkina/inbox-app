package com.inbox.app.controller;

import org.salespointframework.SalespointSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
public class SecurityConfig extends SalespointSecurityConfiguration{

	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/sign-up" , "/login").permitAll()
				.antMatchers("/users" , "/chat").hasRole("USER")
				.and()
			.formLogin()
				.loginPage("/login").loginProcessingUrl("/login").and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/");	
	}
}
