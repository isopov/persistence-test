package ru.mail.park.java.data;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ru.mail.park.java.LibraryService;
import ru.mail.park.java.dto.AuthorDTO;
import ru.mail.park.java.dto.BookDTO;
import ru.mail.park.java.jpa.AuthorEntity;
import ru.mail.park.java.jpa.BookEntity;

@Service
@Transactional
public class SpringDataLibraryService implements LibraryService {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;

	public SpringDataLibraryService(AuthorRepository authorRepository, BookRepository bookRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}

	@Override
	public Collection<BookDTO> getBooks() {
		return bookRepository.findAll().stream().map(BookEntity::toDto).collect(toList());
	}

	@Override
	public Collection<BookDTO> getBooks(long authorId) {
		return bookRepository.findByAuthors_Id(authorId).stream().map(BookEntity::toDto).collect(toList());
	}

	@Override
	public BookDTO getBook(long bookId) {
		return bookRepository.getOne(bookId).toDto();
	}

	@Override
	public Collection<AuthorDTO> getAuthors() {
		return authorRepository.findAll().stream().map(AuthorEntity::toDTO).collect(toList());
	}

	@Override
	public Collection<AuthorDTO> getAuthors(long bookId) {
		return authorRepository.findByBooks_Id(bookId).stream().map(AuthorEntity::toDTO).collect(toList());
	}

	@Override
	public AuthorDTO getAuthor(long authorId) {
		return authorRepository.getOne(authorId).toDTO();
	}

	@Override
	public long createBook(String title) {
		BookEntity book = new BookEntity();
		book.setTitle(title);
		return bookRepository.save(book).getId();
	}

	@Override
	public void addAuthor(long bookId, long authorId) {
		BookEntity book = bookRepository.getOne(bookId);
		AuthorEntity author = authorRepository.getOne(authorId);
		// book в нашем случае - управляющая сторона связи
		book.getAuthors().add(author);

	}

	@Override
	public long createAuthor(String firstName, String secondName) {
		AuthorEntity entity = new AuthorEntity();
		entity.setFirstName(firstName);
		entity.setSecondName(secondName);
		return authorRepository.save(entity).getId();
	}

}
