package org.hello.spring.mvc.service;

import java.util.List;

import org.hello.spring.mvc.model.Discount;
import org.hello.spring.mvc.repo.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

	@Autowired
	private DiscountRepository repo;
	
	public List<Discount> findAllSortedByDiscountDate() {
		return repo.findAll(Sort.by("discountDate"));
	}
	
	public Discount create(Discount discount) {
		
		return repo.save(discount);
	}
 	
	public Discount getById(Integer id) {
		return repo.findById(id).get();
	}
}
