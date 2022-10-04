package com.onpier.task.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onpier.task.entity.Books;
import com.onpier.task.entity.Borrowed;
import com.onpier.task.entity.Users;
import com.onpier.task.repository.BooksRepository;
import com.onpier.task.repository.BorrowedRepository;
import com.onpier.task.repository.UserRepository;
import com.onpier.task.service.OnpierTaskService;
import com.onpier.task.util.OnpierDateFormatter;

/**
 * @author Udayshree
 * 
 *         Implementation for the TaskService methods
 *
 */
@Service
public class OnpierTaskServiceImpl implements OnpierTaskService {
	private static final Logger Logger = LogManager.getLogger(OnpierTaskServiceImpl.class);
	@Autowired
	UserRepository userRepository;
	@Autowired
	BooksRepository booksRepository;
	@Autowired
	BorrowedRepository borrowedRepository;

	@Override
	public List<Users> atleastOneBookborrowedUsers() {
		Logger.info("Start the borrowedUsers details");
		List<Users> users = borrowedRepository.findUsersByAtleastOneBook();
		Logger.info("borrowed users seize: {}", users.size());
		return users;
	}

	@Override
	public List<Users> nonTerminatedAndNonBorrowedUsers() {
		Logger.info("Start the nonBorrowedUser details");

		List<Users> nonTerminatedUsers = userRepository.findByMemberTillIsNull();
		List<Users> nonBorrowedUser = new ArrayList<>();
		nonTerminatedUsers.forEach(user -> {
			List<Borrowed> borrowed = borrowedRepository.findByUser(user);
			if (borrowed.isEmpty()) {
				nonBorrowedUser.add(user);
			}
		});
		Logger.info("nonBorrowed users seize: {}", nonBorrowedUser.size());
		return nonBorrowedUser;
	}

	@Override
	public List<Users> borrowedUsersByDate(String date) throws ParseException {
		Logger.info("Start the borrowedUsersByDate details for {}", date);
		Date fromDate = OnpierDateFormatter.getDateFormatYYYYMMDD(date);
		List<Users> borrowedUsers = borrowedRepository.findUserByBorrowedFrom(fromDate);
		Logger.info("borrowedUsersByDate date:{} , borrowedUser seize:{}", date, borrowedUsers.size());
		return borrowedUsers;
	}

	@Override
	public List<Books> booksByUserByDateRange(String user, String fromDate, String toDate) throws ParseException {
		Logger.info("Start the booksByUserByDateRange details with  {},{},{}", user, fromDate, toDate);
		Date from = OnpierDateFormatter.getDateFormatYYYYMMDD(fromDate);
		Date to = OnpierDateFormatter.getDateFormatYYYYMMDD(toDate);
		List<Books> borrowedBooks = borrowedRepository.findBookFromBorrowedFromAndBorrowedTo(user, from, to);
		Logger.info("booksByUserByDateRange details results: {}", borrowedBooks);
		return borrowedBooks;
	}

}
