package com.onpier.task.dataimport;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.onpier.task.entity.Books;
import com.onpier.task.entity.Borrowed;
import com.onpier.task.entity.Users;
import com.onpier.task.repository.BooksRepository;
import com.onpier.task.repository.BorrowedRepository;
import com.onpier.task.repository.UserRepository;

/**
 * @author Udayshree
 * 
 *         CSV data write to the tables
 *
 * @param <T> : Data Object
 */
@Configuration
public class DatabaseWriter<T> implements ItemWriter<T> {
	private static final Logger Logger = LogManager.getLogger(DatabaseWriter.class);
	@Autowired
	UserRepository userRepository;

	@Autowired
	BooksRepository booksRepository;

	@Autowired
	BorrowedRepository borrowedRepository;

	List<Users> usersList = new ArrayList<>();
	List<Books> booksList = new ArrayList<>();
	List<Borrowed> borrowedList = new ArrayList<>();

	@Override
	public void write(List<? extends T> items) throws Exception {
		for (T item : items) {
			if (item instanceof Books) {
				Books book = (Books) item;
				if (!book.getTitle().isEmpty()) {
					booksList.add(book);
				}
			} else if (item instanceof Users) {
				usersList.add((Users) item);
			} else {
				getTheBorrowedList(item);
			}
		}
		userRepository.saveAll(usersList);
		booksRepository.saveAll(booksList);
		borrowedRepository.saveAll(borrowedList);
	}

	private void getTheBorrowedList(T item) {
		Borrowed borrowed = (Borrowed) item;
		String[] name = borrowed.getBorrower().split(",");
		Users user = userRepository.findByNameAndFirstName(name[0], name[1]).orElse(null);
		Books book = booksRepository.findByTitle(borrowed.getBookTitle()).orElse(null);
		if (user != null) {
			borrowed.setUser(user);
		} else {
			Logger.error("user details are null while making the user relation with borrowed");
		}
		if (book != null) {
			borrowed.setBook(book);
		} else {
			Logger.error("book details are null while making the book relation with borrowed");
		}

		borrowedList.add(borrowed);
	}
}