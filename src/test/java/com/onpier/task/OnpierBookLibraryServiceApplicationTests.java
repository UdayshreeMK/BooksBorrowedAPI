package com.onpier.task;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.onpier.task.entity.Books;
import com.onpier.task.entity.Users;
import com.onpier.task.service.OnpierTaskService;

/**
 * @author Udayshree
 *
 *         Test Cases for OnpierBookLibrary application
 */
@SpringBootTest
class OnpierBookLibraryServiceApplicationTests {

	@Autowired
	OnpierTaskService taskService;

	@BeforeEach
	public void setup() {
		// check for the taskService
		assertNotNull(taskService);
	}

	@Test
	void testBorrowedUsers() {
		List<Users> users = taskService.atleastOneBookborrowedUsers();
		// check users is empty or not
		assertNotNull(users);

	}

	@Test
	void testNonBorrowedUsers() {
		List<Users> users = taskService.nonTerminatedAndNonBorrowedUsers();
		// check users is empty or not
		assertNotNull(users);

	}

	@Test
	void testBorrowedUsersByDate() throws ParseException {
		List<Users> borrows = taskService.borrowedUsersByDate("20210413");
		// check //check borrows is empty or not
		assertNotNull(borrows);

	}

	@Test
	void testBooksByUserByDateRange() throws ParseException {
		List<Books> books = taskService.booksByUserByDateRange("Aexi,Liam", "20080514", "20210413");
		// check //check books is empty or not
		assertNotNull(books);

	}

}
