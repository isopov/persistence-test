package ru.mail.park.java.jpa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.mail.park.java.LibraryController;
import ru.mail.park.java.LibraryService;


@RestController
@RequestMapping("/jpa")
public class JpaLibraryController extends LibraryController {

	private final JpaLibraryService service;

	public JpaLibraryController(JpaLibraryService service) {
		this.service = service;
	}

	@Override
	protected LibraryService getService() {
		return service;
	}

}
