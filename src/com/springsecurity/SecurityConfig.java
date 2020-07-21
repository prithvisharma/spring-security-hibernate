package com.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.springsecurity")
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		//manually adding users
//		auth.inMemoryAuthentication()
//		.passwordEncoder(passwordEncoder)
//		.withUser("admin").password(passwordEncoder.encode("1234")).authorities("ADMIN")
//		.and()
//		.withUser("harry").password(passwordEncoder.encode("1234")).authorities("USER");
		
		auth.authenticationProvider(authenticationProvider());
	}
	
	
	
	private AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(passwordEncoder);
		dao.setUserDetailsService(authenticationService);
		return dao;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/show-report").hasAuthority("ADMIN")
		.antMatchers("/show-users").authenticated()	//can use hasAnyAuthority("...","...",..)
		.antMatchers("/**").permitAll()
		//adding inbuilt spring login page
		.and()
		.formLogin().permitAll()
		.and()
		.logout().permitAll()
		//changing 403 page
		.and()
		.exceptionHandling().accessDeniedPage("/forbidden.jsp")
		//disable csrf
		.and().csrf().disable();
	}
}
