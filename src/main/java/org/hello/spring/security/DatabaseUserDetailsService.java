package org.hello.spring.security;

import java.util.Optional;

import org.hello.spring.mvc.model.User;
import org.hello.spring.mvc.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// Cerca nel databse se esiste un utente  che abbia lo username richiesto
		Optional<User> user = userRepository.findByUsername(username);
		
		// Se lo hai torvato, allora costruisci una nuova istanza di DatabaseUserDetails che riguardi l'utente con l'username inserito
		if(user.isPresent()) {
			return new DatabaseUserDetails(user.get());
			
		// In caso contrario lancia una nuova eccezione del tipo UsernameNotFoundException
		} else throw new UsernameNotFoundException("Username" + username + "not found!");
		
	}

	

}
