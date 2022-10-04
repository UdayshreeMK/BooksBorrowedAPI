package com.onpier.task.util;

/**
 * @author Udayshree
 * 
 *         Constants for the application
 *
 */
public class Constants {
	private Constants() {
	}

	public static final String REGEX_MMSLASHDDSLASHYYYY = "[0-9]{2}/[0-9]{2}/[0-9]{4}$";
	public static final String REGEX_MMDASHDDDASHYYYY = "[0-9]{2}-[0-9]{2}-[0-9]{4}$";
	public static final String BORROWED_TO = "borrowed to";
	public static final String BORROWED_FROM = "borrowed from";
	public static final String BOOK = "Book";
	public static final String BORROWER = "Borrower";
	public static final String GENDER = "Gender";
	public static final String MEMBER_SINCE = "Member since";
	public static final String FIRST_NAME = "First name";
	public static final String NAME = "Name";
	public static final String MEMBER_TILL = "Member till";
	public static final String BOOKS = "books";
	public static final String USERS = "users";
	public static final String BORROWED = "borrowed";
	public static final String[] BOOKS_COLUMNS = { "Title", "Author", "Genre", "Publisher" };
	public static final String[] USERS_COLUMNS = { NAME, FIRST_NAME, MEMBER_SINCE, MEMBER_TILL, GENDER };
	public static final String[] BORROWED_COLUMNS = { BORROWER, BOOK, BORROWED_FROM, BORROWED_TO };

}
