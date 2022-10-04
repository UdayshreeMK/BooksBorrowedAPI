package com.onpier.task.controller;

import java.text.ParseException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onpier.task.entity.Books;
import com.onpier.task.entity.Users;
import com.onpier.task.exception.NoRecordFoundException;
import com.onpier.task.service.OnpierTaskService;

import io.swagger.annotations.ApiParam;

/**
 * @author Udayshree
 * 
 *         Controller to handle the common rest APIs for the application
 *
 */
@RestController
@RequestMapping(value = "/api/v1")
public class OnpierTaskController {
	private static final Logger Logger = LogManager.getLogger(OnpierTaskController.class);
	@Autowired
	OnpierTaskService taskService;

	@GetMapping(value = "/borrowed_users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Users>> borrowedUsers() {
		List<Users> users = taskService.atleastOneBookborrowedUsers();
		if (users == null) {
			Logger.error("At least one book borrowed users are null ");
			throw new NoRecordFoundException();
		}
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@GetMapping(value = "/nonborrowed_users")
	public ResponseEntity<List<Users>> nonBorrowedUsers() {
		List<Users> users = taskService.nonTerminatedAndNonBorrowedUsers();
		if (users == null) {
			Logger.error("non-terminated users who have not currently borrowed anything are null ");
			throw new NoRecordFoundException();
		}
		return ResponseEntity.status(HttpStatus.OK).body(users);

	}

	@GetMapping(value = "/borrowedusers_by_date")
	public ResponseEntity<List<Users>> borrowedUserByDate(
			@RequestParam @ApiParam(name = "fromDate", value = "borrowedFrom", example = "YYYYMMDD") String fromDate)
			throws ParseException {
		List<Users> users = taskService.borrowedUsersByDate(fromDate);
		if (users == null) {
			Logger.error("users who have borrowed a book on a given date: {} are null ", fromDate);
			throw new NoRecordFoundException();
		}
		return ResponseEntity.status(HttpStatus.OK).body(users);

	}

	@GetMapping(value = "/books_by_user_daterange")
	public ResponseEntity<List<Books>> booksByUserByDateRange(@RequestParam String user,
			@RequestParam @ApiParam(name = "fromDate", value = "borrowedFrom", example = "YYYYMMDD") String fromDate,
			@RequestParam @ApiParam(name = "toDate", value = "borrowedTo", example = "YYYYMMDD") String toDate)
			throws ParseException {
		List<Books> books = taskService.booksByUserByDateRange(user, fromDate, toDate);
		if (books == null) {
			Logger.error("books borrowed by a user:{} in a given date range {} to {} are null", user, fromDate, toDate);
			throw new NoRecordFoundException();
		}
		return ResponseEntity.status(HttpStatus.OK).body(books);

	}

}
