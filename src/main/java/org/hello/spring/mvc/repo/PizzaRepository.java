package org.hello.spring.mvc.repo;

import java.util.List;

import org.hello.spring.mvc.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

	// In automatico ho tutto il necessario, ma posso aagiungere eventuali metodi e funzionalità
	
	public List<Pizza> findByTitle(String title);
	
	public List<Pizza> findByTitleLike(String title);
	
}
