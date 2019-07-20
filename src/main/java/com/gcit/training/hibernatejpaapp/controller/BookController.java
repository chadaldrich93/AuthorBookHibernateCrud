package com.gcit.training.hibernatejpaapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.training.hibernatejpaapp.dao.BookDao;
import com.gcit.training.hibernatejpaapp.entity.Book;

@RestController
@RequestMapping("/lms")
public class BookController {

	@Autowired
	private BookDao bookDao;
	
	@GetMapping("/book")
	public List<Book> getAllBook() {
	    return bookDao.findAll();
	}
	
	@GetMapping("/book/{bookId}")
	public Book getBook(@PathVariable(value="bookId") int bookId) {
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(bookId);
		List<Book> bookById = bookDao.findAllById(idList);
		return bookById.get(0);
	}
	
	// Create a new Note
	@PostMapping("/book")
	public Book createNote(@Valid @RequestBody Book book) {
	    return bookDao.save(book);
	}
	
	@DeleteMapping("/book")
	public void deleteBook(@Valid @RequestBody Book book) {
		ArrayList<Book> bookList = new ArrayList<Book>();
	    bookList.add(book);
		bookDao.deleteInBatch(bookList);
	}
}