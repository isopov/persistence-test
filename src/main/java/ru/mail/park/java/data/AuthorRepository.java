package ru.mail.park.java.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.mail.park.java.jpa.AuthorEntity;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
	List<AuthorEntity> findByBooks_Id(long bookId);
}
