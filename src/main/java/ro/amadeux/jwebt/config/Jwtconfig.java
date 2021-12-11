package ro.amadeux.jwebt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
		http
			.csrf()//cross site request forgery or xsrf (sea surf or session riding)
			.disable()//desactivar csrf???
			.cors()
			.disable()
			.authorizeRequests()
			.antMatchers("/generateToken").permitAll()//Este endpoint no tendrá restricciones (no necesita autenticación)
			.anyRequest().authenticated()//otras peticiones deberán autenticarse
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//cada petición deberá ser independiente de otra y el servidor no tiene que gestionar la sesión 
	}
	
	//Codificación de la contraseña
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	

}
