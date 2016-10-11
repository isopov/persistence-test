package ru.mail.park.java;

import java.util.Collection;

import ru.mail.park.java.dto.AuthorDTO;
import ru.mail.park.java.dto.BookDTO;

public interface LibraryService {
	Collection<BookDTO> getBooks();
	
	Collection<BookDTO> getBooks(long authorId);
	
	BookDTO getBook(long bookId);
	
	Collection<AuthorDTO> getAuthors();
	
	Collection<AuthorDTO> getAuthors(long bookId);
	
	AuthorDTO getAuthor(long authorId);
	
	long createBook(String title);
	
	void addAuthor(long bookId, long authorId);
	
	long createAuthor(String firstName, String secondName);

}
