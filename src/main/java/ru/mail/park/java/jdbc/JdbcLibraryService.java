package ru.mail.park.java.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.sql.DataSource;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import ru.mail.park.java.LibraryService;
import ru.mail.park.java.dto.AuthorDTO;
import ru.mail.park.java.dto.BookDTO;
import ru.mail.park.java.dto.BookDTO.BookAuthorDTO;

@Service
public class JdbcLibraryService implements LibraryService {

	private final DataSource ds;

	public JdbcLibraryService(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Collection<BookDTO> getBooks() {
		return getBooksInternal(null, null);
	}

	@Override
	public Collection<BookDTO> getBooks(long authorId) {
		return getBooksInternal(authorId, null);
	}

	@Override
	public BookDTO getBook(long bookId) {
		return getBooksInternal(null, bookId).get(1);
	}

	private List<BookDTO> getBooksInternal(Long authorId, Long bookId) {
		String sql = "select b.book_id, b.title, a.first_name, a.second_name from book b"
				+ " left join author_book ab on b.book_id=ab.book_id"
				+ " left join author a on a.author_id=ab.author_id";
		if (authorId != null) {
			sql += " where a.author_id=?";
		} else if (bookId != null) {
			sql += " where b.book_id=?";
		}

		sql += " order by b.book_id";
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			if (authorId != null) {
				pst.setLong(1, authorId);
			} else if (bookId != null) {
				pst.setLong(1, bookId);
			}
			try (ResultSet res = pst.executeQuery()) {
				List<BookDTO> result = new ArrayList<>();
				BookDTO prev = null;
				while (res.next()) {
					long currentBookId = res.getLong("book_id");
					BookAuthorDTO bookAuthor = new BookAuthorDTO(res.getString("first_name"),
							res.getString("first_name"));
					if (prev == null || currentBookId != prev.getId()) {
						prev = new BookDTO(currentBookId, res.getString("title"), new HashSet<>());
						result.add(prev);
					}
					if (bookAuthor.getFirstName() != null && bookAuthor.getSecondName() != null) {
						prev.getAuthors().add(bookAuthor);
					}
				}
				return result;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public long createBook(String title) {
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						"insert into book(title) values(?) returning book_id",
						Statement.RETURN_GENERATED_KEYS)) {
			pst.setString(1, title);
			pst.executeUpdate();

			try (ResultSet res = pst.getGeneratedKeys()) {
				Assert.isTrue(res.next());
				return res.getLong(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addAuthor(long bookId, long authorId) {
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						"insert into author_book(author_id, book_id) values(?,?)")) {
			pst.setLong(1, authorId);
			pst.setLong(2, bookId);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public long createAuthor(String firstName, String secondName) {
		try (Connection con = ds.getConnection();
				PreparedStatement pst = con.prepareStatement(
						"insert into author(first_name, second_name) values(?) returning author_id",
						Statement.RETURN_GENERATED_KEYS)) {
			pst.setString(1, firstName);
			pst.setString(2, secondName);
			pst.executeUpdate();

			try (ResultSet res = pst.getGeneratedKeys()) {
				Assert.isTrue(res.next());
				return res.getLong(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Collection<AuthorDTO> getAuthors() {
		throw new IllegalStateException("Too much code to write");
	}

	@Override
	public Collection<AuthorDTO> getAuthors(long bookId) {
		throw new IllegalStateException("Too much code to write");
	}

	@Override
	public AuthorDTO getAuthor(long authorId) {
		throw new IllegalStateException("Too much code to write");
	}

}
