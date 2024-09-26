package org.hello.spring.mvc.repo;

import org.hello.spring.mvc.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {

	// In automatico ho tutto il necessario, ma posso aagiungere eventuali metodi e funzionalità
	
	

	
}
