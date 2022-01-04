package com.pranjal.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.pranjal.restapi.model.Book;
import com.pranjal.restapi.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public List<Book> getBook() {
		return this.bookService.getAllBook();
	}
	
	@GetMapping("/books/{id}")
	public Book getBookById(@PathVariable("id") int id) {
		return this.bookService.getBookById(id);
	}
	
	@PostMapping("/books")
	public Book addBook(@RequestBody Book book) { 
		this.bookService.addBook(book);
		return book;		
	}
	
	@DeleteMapping("/books/{bookId}")
	public void deleteBook(@PathVariable("bookId") int id) {
		this.bookService.deleteBook(id);
	}
	
	@PutMapping("/books/{id}")
	public Book updateBook(@RequestBody Book book ,  @PathVariable("id") int id) {
		 this.bookService.updateBook(book, id);
		 return book;
	}
	
	
}
