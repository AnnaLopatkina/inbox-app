package com.inbox.app.controller;

import org.salespointframework.SalespointSecurityConfiguration;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.session.HttpSessionEventPublisher;

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
		
		http.sessionManagement().maximumSessions(5).sessionRegistry(sessionRegistry());
	}
	
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }
    
}
