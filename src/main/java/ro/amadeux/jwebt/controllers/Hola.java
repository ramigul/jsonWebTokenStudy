package ro.amadeux.jwebt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hola {
	
	@GetMapping({"/hola"})
	public String hola() {
		return "Hola mundo ...";
	}

}
