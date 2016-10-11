package ru.mail.park.java.jpa;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ru.mail.park.java.dto.BookDTO;

@Entity
@Table(name = "book")
public class BookEntity {

	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String title;

	@ManyToMany
	@JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Collection<AuthorEntity> authors;

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Collection<AuthorEntity> getAuthors() {
		return authors;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthors(Collection<AuthorEntity> authors) {
		this.authors = authors;
	}

	public BookDTO toDto() {
		return new BookDTO(getId(), getTitle(),
				getAuthors().stream().map(AuthorEntity::toAuthorBookDTO).collect(Collectors.toSet()));
	}

}
