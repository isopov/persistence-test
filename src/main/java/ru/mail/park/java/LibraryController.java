package ru.mail.park.java;

import java.util.Collection;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.mail.park.java.dto.AuthorDTO;
import ru.mail.park.java.dto.BookDTO;

public abstract class LibraryController {

	protected abstract LibraryService getService();
	
	
	@RequestMapping(path = "/book/all", method = RequestMethod.GET)
	public Collection<BookDTO> getAllBooks(){
		return getService().getBooks();
	}
	
	@RequestMapping(path = "/book/author/{authorId}", method = RequestMethod.GET)
	public Collection<BookDTO> getBooks(@PathVariable long authorId){
		return getService().getBooks(authorId);
	}
	
	@RequestMapping(path = "/book/{bookId}", method = RequestMethod.GET)
	public BookDTO getBook(@PathVariable long bookId){
		return getService().getBook(bookId);
	}
	
	
	@RequestMapping(path = "/author/all", method = RequestMethod.GET)
	public Collection<AuthorDTO> getAllAuthors(){
		return getService().getAuthors();
	}
	
	@RequestMapping(path = "/author/book/{bookId}", method = RequestMethod.GET)
	public Collection<AuthorDTO> getAuthors(@PathVariable long bookId){
		return getService().getAuthors(bookId);
	}
	
	@RequestMapping(path = "/author/{authorId}", method = RequestMethod.GET)
	public AuthorDTO getAuthor(@PathVariable long authorId){
		return getService().getAuthor(authorId);
	}
	
	@RequestMapping(path = "/author/create", method = RequestMethod.GET)
	public long createAuthor(@RequestParam String firstName, @RequestParam String secondName){
		return getService().createAuthor(firstName, secondName);
	}
	
	@RequestMapping(path = "/book/create", method = RequestMethod.GET)
	public long createBook(@RequestParam String title){
		return getService().createBook(title);
	}
	
	@RequestMapping(path = "/book/author/add", method = RequestMethod.GET)
	public void addAuthor(@RequestParam long bookId, @RequestParam long authorId){
		getService().addAuthor(bookId, authorId);;
	}
	
	
//	
//	long createBook(String title, long authorId);
//	
//	void addAuthor(long bookId, long authorId);
//	
//	long createAuthor(String firstName, String secondName);
	
	
}
