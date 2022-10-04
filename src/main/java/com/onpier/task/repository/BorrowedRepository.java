package com.onpier.task.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onpier.task.entity.Books;
import com.onpier.task.entity.Borrowed;
import com.onpier.task.entity.Users;

/**
 * @author Udayshree
 * 
 *         Repository for the Borrowed table
 *
 */
@Repository
public interface BorrowedRepository extends JpaRepository<Borrowed, String> {

	// get borrowedNames where book is not null
	@Query("SELECT DISTINCT b.user FROM Borrowed b where b.book IS NOT NULL")
	List<Users> findUsersByAtleastOneBook();

	// get borrowed details by user object
	List<Borrowed> findByUser(Users user);

	// get borrowed user details by BorrowedFrom
	@Query(" SELECT u FROM Borrowed b JOIN Users u ON b.user = u.id where b.borrowedFrom = :date")
	List<Users> findUserByBorrowedFrom(Date date);
	
	// get borrowed details by user between range of BorrowedFrom ,BorrowedTo
	@Query(" SELECT b FROM Borrowed br JOIN Books b ON br.book = b.id where br.borrower =:user and (br.borrowedFrom > :fromDate and br.borrowedTo < :toDate)")
	List<Books> findBookFromBorrowedFromAndBorrowedTo(String user, Date fromDate, Date toDate);
}
