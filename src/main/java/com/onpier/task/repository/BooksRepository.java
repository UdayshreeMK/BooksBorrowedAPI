package com.onpier.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onpier.task.entity.Books;

/**
 * @author Udayshree
 * 
 *         Repository for the Books table
 *
 */
@Repository
public interface BooksRepository extends JpaRepository<Books, String> {

	Optional<Books> findByTitle(String title);
}
