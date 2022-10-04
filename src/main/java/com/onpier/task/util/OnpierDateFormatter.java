package com.onpier.task.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Udayshree
 * 
 *         It handle the all date formatter for application
 *
 */
public class OnpierDateFormatter {

	private OnpierDateFormatter() {

	}

	/**
	 * method to return Date Object from date string
	 * 
	 * @param date: it string date and the format is yyyyMMdd
	 * @return date: it is Date Object
	 * @throws ParseException
	 */
	public static Date getDateFormatYYYYMMDD(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.parse(date);
	}

	/**
	 * method to return Date Object from date string
	 * 
	 * @param date: it string date and the format is MM/dd/yyyy
	 * @return date: it is Date Object
	 * @throws ParseException
	 */
	public static Date getDateFormatddSlashMMSlashyyyy(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.parse(date);
	}

	/**
	 * method to return Date Object from date string
	 * 
	 * @param date: it string date and the format is MM-dd-yyyy
	 * @return date: it is Date Object
	 * @throws ParseException
	 */
	public static Date getDateFormatddDashMMDashyyyy(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		return sdf.parse(date);
	}
}
