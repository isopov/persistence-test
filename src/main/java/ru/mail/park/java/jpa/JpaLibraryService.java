package ru.mail.park.java.jpa;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ru.mail.park.java.LibraryService;
import ru.mail.park.java.dto.AuthorDTO;
import ru.mail.park.java.dto.BookDTO;

@Service
@Transactional
public class JpaLibraryService implements LibraryService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Collection<BookDTO> getBooks() {
		return em.createQuery("select b from BookEntity b", BookEntity.class)
				.getResultList()
				.stream().map(BookEntity::toDto).collect(Collectors.toList());

	}

	@Override
	public Collection<BookDTO> getBooks(long authorId) {
		return em.createQuery("select a.books from AuthorEntity a where a.id=:id", BookEntity.class)
				.setParameter("id", authorId)
				.getResultList()
				.stream().map(BookEntity::toDto).collect(Collectors.toList());
	}

	@Override
	public BookDTO getBook(long bookId) {
		return em.createQuery("select b from BookEntity b where b.id=:id", BookEntity.class)
				.setParameter("id", bookId)
				.getSingleResult()
				.toDto();
	}

	@Override
	public Collection<AuthorDTO> getAuthors() {
		return em.createQuery("select a from AuthorEntity a", AuthorEntity.class)
				.getResultList()
				.stream().map(AuthorEntity::toDTO).collect(Collectors.toList());
	}

	@Override
	public Collection<AuthorDTO> getAuthors(long bookId) {
		return em.createQuery("select b.authors from BookEntity b where b.id=:id", AuthorEntity.class)
				.setParameter("id", bookId)
				.getResultList()
				.stream().map(AuthorEntity::toDTO).collect(Collectors.toList());
	}

	@Override
	public AuthorDTO getAuthor(long authorId) {
		return em.createQuery("select a from AuthorEntity a where a.id=:id", AuthorEntity.class)
				.setParameter("id", authorId)
				.getSingleResult()
				.toDTO();
	}

	@Override
	public long createBook(String title) {
		BookEntity entity = new BookEntity();
		entity.setTitle(title);
		em.persist(entity);
		return entity.getId();
	}

	@Override
	public long createAuthor(String firstName, String secondName) {
		AuthorEntity entity = new AuthorEntity();
		entity.setFirstName(firstName);
		entity.setSecondName(secondName);
		em.persist(entity);
		return entity.getId();
	}

	@Override
	public void addAuthor(long bookId, long authorId) {
		BookEntity book = em.find(BookEntity.class, bookId);
		AuthorEntity author = em.find(AuthorEntity.class, authorId);
		// book в нашем случае - управляющая сторона связи
		book.getAuthors().add(author);
	}
}
