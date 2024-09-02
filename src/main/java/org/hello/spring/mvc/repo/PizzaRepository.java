package org.hello.spring.mvc.repo;

import java.awt.print.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Book, Integer> {

	// In automatico ho tutto il necessario, ma posso aagiungere eventuali metodi e funzionalità
	
}
