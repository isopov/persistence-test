package ru.mail.park.java.dto;

import java.util.Set;

public class BookDTO {
	private final long id;
	private final String title;
	private final Set<BookAuthorDTO> authors;

	public BookDTO(long id, String title, Set<BookAuthorDTO> authors) {
		this.id = id;
		this.title = title;
		this.authors = authors;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Set<BookAuthorDTO> getAuthors() {
		return authors;
	}

	public static class BookAuthorDTO {
		private final String firstName;
		private final String secondName;

		public BookAuthorDTO(String firstName, String secondName) {
			this.firstName = firstName;
			this.secondName = secondName;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getSecondName() {
			return secondName;
		}

	}

}
