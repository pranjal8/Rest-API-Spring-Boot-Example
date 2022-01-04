package com.pranjal.restapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pranjal.restapi.dao.BookDao;
import com.pranjal.restapi.model.Book;

@Component
public class BookService {
	
	@Autowired
	private BookDao bookDao;
	
//	public static List<Book> list=new ArrayList<>();
//	
//	static {
//		list.add(new Book(1,"OCA Java SE 8", "Kathy Sierra") );
//		list.add(new Book(2,"The Complete Reference", "Herbert Schildt"));
//		list.add(new Book(3,"Head First Java", " Bert Bates"));
//	}
	
	public List<Book> getAllBook(){
	    List<Book> list= this.bookDao.findAll();
		System.out.println(list);    
		return list;	
	}
	
	public Book getBookById(int id) {
		Book book=null;
		//book=list.stream().filter(e -> e.getId() == id).findFirst().get();
		book= this.bookDao.findById(id);
		return book;
	}
	
	public Book addBook(Book book) {
//		list.add(book);
		Book res=this.bookDao.save(book);
		return res;	
	}

	public void deleteBook(int id) {
//	    list= list.stream().filter(e ->{
//			if(e.getId() != id) {
//				System.out.println(list);
//				return true;
//			}
//			return false;
//		}).collect(Collectors.toList());		
		
		this.bookDao.deleteById(id);	    
	}

	public void updateBook(Book book, int id) {		
//		list.stream().map(b ->{
//			if(b.getId() == id) {
//				b.setTitle(book.getTitle());
//				b.setAuthor(book.getTitle());
//			}
//			return b; 
//		}).collect(Collectors.toList());
		
		book.setId(id);
		this.bookDao.save(book);	
	}
	
}
