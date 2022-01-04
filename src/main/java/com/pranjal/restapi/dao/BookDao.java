package com.pranjal.restapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pranjal.restapi.model.Book;

public interface BookDao extends JpaRepository<Book, Integer> {

	public Book findById(int id);
	
	
}
