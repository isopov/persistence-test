package ru.mail.park.java.data;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.mail.park.java.LibraryController;
import ru.mail.park.java.LibraryService;

@RestController
@RequestMapping("/data")
public class SpringDataLibraryController extends LibraryController {

	private final SpringDataLibraryService service;

	public SpringDataLibraryController(SpringDataLibraryService service) {
		this.service = service;
	}

	@Override
	protected LibraryService getService() {
		return service;
	}

}
