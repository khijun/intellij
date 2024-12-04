package edu.du.cursur1127.repository;

import edu.du.cursur1127.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
} 