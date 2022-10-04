package com.onpier.task.service;

import java.text.ParseException;
import java.util.List;

import com.onpier.task.entity.Books;
import com.onpier.task.entity.Users;

/**
 * @author Udayshree
 *
 */
public interface OnpierTaskService {
	// returns all users who have actually borrowed at least one book
	List<Users> atleastOneBookborrowedUsers();

	// returns all non-terminated users who have not currently borrowed anything
	List<Users> nonTerminatedAndNonBorrowedUsers();

	// returns all users who have borrowed a book on a given date
	List<Users> borrowedUsersByDate(String fromDate) throws ParseException;

	// returns all books borrowed by a given user in a given date range
	List<Books> booksByUserByDateRange(String user, String fromDate, String toDate) throws ParseException;

}
