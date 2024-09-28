package org.hello.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	
	@SuppressWarnings("removal")
	@Bean
	SecurityFilterChain filterChai(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
//		.requestMatchers("/pizze/create", "/pizze/edit/*").hasAuthority("ADMIN")
//		.requestMatchers("/pizze", "pizze/show").hasAnyAuthority("ADMIN", "USER")
//		.requestMatchers("/ingredient/create", "/ingredient/edit/*").hasAuthority("ADMIN")
//		.requestMatchers("/ingredient", "ingredient/show").hasAnyAuthority("ADMIN", "USER")
//		.requestMatchers(HttpMethod.POST, "/pizze/**").hasAuthority("ADMIN")
//		.requestMatchers(HttpMethod.POST, "/ingredient/**").hasAuthority("ADMIN")
//		.requestMatchers("/user").hasAnyAuthority("ADMIN", "USER")
//		.requestMatchers("/admin").hasAuthority("ADMIN")
//		.requestMatchers("/**").permitAll()
//		.and().formLogin().and().logout()
//		.and().exceptionHandling()
//		.and().csrf().disable();
		.requestMatchers("/user").hasAuthority("USER")
		.requestMatchers("/admin").hasAuthority("ADMIN")
		.requestMatchers("/").permitAll()
		.and().formLogin()
		.and().logout();
		
		return http.build();
	}
	
	@Bean
	DatabaseUserDetailsService userDetailsService() {
		return new DatabaseUserDetailsService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService());
		
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
}
