package com.example.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails normalUser = User
				.withUsername("kittu")
				.password(passwordEncoder().encode("priya"))
				.roles("NORMAL").build();
		
		UserDetails normalUser2 = User
				.withUsername("pintu")
				.password(passwordEncoder().encode("priya"))
				.roles("NORMAL").build();
		
		UserDetails adminUser = User
				.withUsername("priya")
				.password(passwordEncoder().encode("kittu"))
				.roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(normalUser,normalUser2,adminUser);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		security
		.csrf((csrf) -> csrf.disable())
		.securityMatcher("/user/**")
		.authorizeHttpRequests(auth -> {
				auth
				// we can define role on method controller level using preAuthrized()
				//.requestMatchers("/user/normal").hasRole("NORMAL") // defining role here
				//.requestMatchers("/user/admin").hasRole("ADMIN") // defining role here
				.requestMatchers("/user/admin").permitAll()			
				.anyRequest().authenticated();
			})
		        .httpBasic();
		return security.build();		
	}
}
