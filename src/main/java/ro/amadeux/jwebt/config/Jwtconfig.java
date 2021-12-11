package ro.amadeux.jwebt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import ro.amadeux.jwebt.services.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class Jwtconfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	//Como deseamos gestionar nuestro proceso de autenticación
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customUserDetailService);
	}
	//Este método controlará que endpoints está permitido
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		super.configure(http);
	}
	
	

}
