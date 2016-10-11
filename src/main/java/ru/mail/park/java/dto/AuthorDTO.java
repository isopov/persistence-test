package ru.mail.park.java.dto;

import java.util.Set;

public class AuthorDTO {
	private final long id;
	private final String firstName;
	private final String secondName;
	private final Set<String> books;
	
	public AuthorDTO(long id,String firstName, String secondName, Set<String> books) {
		this.id=id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.books = books;
	}
	

	public long getId() {
		return id;
	}


	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public Set<String> getBooks() {
		return books;
	}
}
