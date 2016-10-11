package ru.mail.park.java.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.mail.park.java.jpa.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
	List<BookEntity> findByAuthors_Id(long authorId);
}
