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

import com.gcit.training.hibernatejpaapp.dao.AuthorDao;
import com.gcit.training.hibernatejpaapp.entity.Author;

@RestController
@RequestMapping("/lms")
public class AuthorController {

	@Autowired
	private AuthorDao authorDao;
	
	@GetMapping("/author")
	public List<Author> getAllAuthor() {
	    return authorDao.findAll();
	}
	
	@GetMapping("/author/{authorId}")
	public Author getAuthor(@PathVariable(value="authorId") int authorId) {
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(authorId);
		List<Author> authorById = authorDao.findAllById(idList);
		return authorById.get(0);
	}
	
	// Create a new Note
	@PostMapping("/author")
	public Author createNote(@Valid @RequestBody Author author) {
	    return authorDao.save(author);
	}
	
	@DeleteMapping("/author")
	public void deleteAuthor(@Valid @RequestBody Author author) {
		ArrayList<Author> authorList = new ArrayList<Author>();
	    authorList.add(author);
		authorDao.deleteInBatch(authorList);
	}
}
