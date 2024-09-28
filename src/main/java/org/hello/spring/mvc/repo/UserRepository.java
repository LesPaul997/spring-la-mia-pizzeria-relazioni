package org.hello.spring.mvc.repo;

import java.util.Optional;

import org.hello.spring.mvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	// In automatico ho tutto il necessario, ma posso aagiungere eventuali metodi e funzionalità
	
	public Optional<User> findByUsername(String username); 
}
