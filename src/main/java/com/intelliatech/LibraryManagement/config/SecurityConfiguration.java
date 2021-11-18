package com.intelliatech.LibraryManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new JwtUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*");
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService);
		
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling()
		.authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().
		antMatchers("/captcha/get","/login/user/generate/token","/user/create")
				.permitAll()
				.antMatchers("/basic/**").hasRole("NORMAL")
				.antMatchers("/user/**","/student/**","/subject/**").hasRole("ADMIN")
				.anyRequest().authenticated();
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
