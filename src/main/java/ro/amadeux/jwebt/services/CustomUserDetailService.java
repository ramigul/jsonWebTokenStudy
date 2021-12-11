package ro.amadeux.jwebt.services;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
	//Este método realmente hace la validación del usuario, buscando en la base de datos
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		if(userName.equals("tom")) {//Aqui podemos hacer una llamada a la BD con la ayuda del repository
			//y hacer la validación
			//User: security.core.userdetails.User
			return new User("tom", "secret", new ArrayList<>());//asumimos que lo retorna de la BD (esto es en crudo)
		}else {
			throw new UsernameNotFoundException("El usuario no existe");
		}
	}

}
