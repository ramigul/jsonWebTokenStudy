package ro.amadeux.jwebt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.amadeux.jwebt.models.JwtRequest;
import ro.amadeux.jwebt.models.JwtResponse;
import ro.amadeux.jwebt.services.CustomUserDetailService;
import ro.amadeux.jwebt.utils.JwtUtil;

@RestController
@RequestMapping("/api")
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/generateToken")
	public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest){
		UsernamePasswordAuthenticationToken userPassAuth = new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword());
		//Autenticar el usuario
		authenticationManager.authenticate(userPassAuth);
		
		UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtRequest.getUserName());
		String jwtToken = jwtUtil.generateToken(userDetails);
		
		JwtResponse jwtResponse = new JwtResponse(jwtToken);
		
		return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
		//return ResponseEntity.ok(jwtResponse);
		
	}
}
