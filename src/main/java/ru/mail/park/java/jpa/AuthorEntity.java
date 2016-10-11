package ru.mail.park.java.jpa;

import static java.util.stream.Collectors.toSet;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ru.mail.park.java.dto.AuthorDTO;
import ru.mail.park.java.dto.BookDTO.BookAuthorDTO;

@Entity
@Table(name = "author")
public class AuthorEntity {

	@Id
	@Column(name = "author_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "second_name")
	private String secondName;

	@ManyToMany(mappedBy = "authors")
	private Collection<BookEntity> books;

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public Collection<BookEntity> getBooks() {
		return books;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public void setBooks(Collection<BookEntity> books) {
		this.books = books;
	}

	public AuthorDTO toDTO() {
		return new AuthorDTO(getId(), getFirstName(), getSecondName(),
				getBooks().stream().map(BookEntity::getTitle).collect(toSet()));
	}

	public BookAuthorDTO toAuthorBookDTO() {
		return new BookAuthorDTO(getFirstName(), getSecondName());
	}

}
