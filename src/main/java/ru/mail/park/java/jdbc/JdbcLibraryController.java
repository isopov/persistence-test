package ru.mail.park.java.jdbc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.mail.park.java.LibraryController;
import ru.mail.park.java.LibraryService;


@RestController
@RequestMapping("/jdbc")
public class JdbcLibraryController extends LibraryController {

	private final JdbcLibraryService service;

	public JdbcLibraryController(JdbcLibraryService service) {
		this.service = service;
	}

	@Override
	protected LibraryService getService() {
		return service;
	}

}
