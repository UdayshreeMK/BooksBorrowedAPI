package com.onpier.task.dataimport;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.onpier.task.entity.Borrowed;
import com.onpier.task.entity.Users;
import com.onpier.task.util.Constants;
import com.onpier.task.util.OnpierDateFormatter;

/**
 * @author Udayshree
 * 
 *         Class to read the CSV fields and set to the java Objects
 *
 * @param <T> : data object
 */
public class CommonBeanWFSetMapper<T> extends BeanWrapperFieldSetMapper<T> {
	private static final String EXCEPTION_DURING_THE_PARSING_THE_DATE = "Exception , during the parsing the date {} for {}";
	private static final Logger Logger = LogManager.getLogger(CommonBeanWFSetMapper.class);
	private Class<T> targetType;

	public CommonBeanWFSetMapper(Class<T> targetType) {
		this.targetType = targetType;
		setTargetType(targetType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T mapFieldSet(FieldSet fs) throws BindException {
		if (targetType.getTypeName().equalsIgnoreCase(Users.class.getName())) {
			Users user = new Users();
			if (fs.readString(Constants.MEMBER_TILL).isEmpty()) {
				user.setMemberTill(null);
			} else {
				try {
					user.setMemberTill(regexMatchDate(fs.readString(Constants.MEMBER_TILL)));
				} catch (ParseException e) {
					Logger.error(EXCEPTION_DURING_THE_PARSING_THE_DATE, fs.readString(Constants.MEMBER_TILL),
							Constants.MEMBER_TILL, e);
				}
			}
			user.setName(fs.readString(Constants.NAME));
			user.setFirstName(fs.readString(Constants.FIRST_NAME));
			try {
				user.setMemberSince(regexMatchDate(fs.readString(Constants.MEMBER_SINCE)));
			} catch (ParseException e) {
				Logger.error(EXCEPTION_DURING_THE_PARSING_THE_DATE, fs.readString(Constants.MEMBER_SINCE),
						Constants.MEMBER_SINCE, e);
			}
			user.setGender(fs.readString(Constants.GENDER));

			return (T) user;
		} else if (targetType.getTypeName().equalsIgnoreCase(Borrowed.class.getName())) {
			Borrowed borrowed = new Borrowed();

			borrowed.setBorrower(fs.readString(Constants.BORROWER));
			borrowed.setBookTitle(fs.readString(Constants.BOOK));
			try {
				borrowed.setBorrowedFrom(regexMatchDate(fs.readString(Constants.BORROWED_FROM)));
			} catch (ParseException e) {
				Logger.error(EXCEPTION_DURING_THE_PARSING_THE_DATE, fs.readString(Constants.BORROWED_FROM),
						Constants.BORROWED_FROM, e);
			}
			try {
				borrowed.setBorrowedTo(regexMatchDate(fs.readString(Constants.BORROWED_TO)));
			} catch (ParseException e) {
				Logger.error(EXCEPTION_DURING_THE_PARSING_THE_DATE, fs.readString(Constants.BORROWED_TO),
						Constants.BORROWED_TO, e);
			}

			return (T) borrowed;
		}
		return super.mapFieldSet(fs);
	}

	private Date regexMatchDate(String textDate) throws ParseException {
		Pattern patternDash = Pattern.compile(Constants.REGEX_MMDASHDDDASHYYYY);
		Pattern patternSlash = Pattern.compile(Constants.REGEX_MMSLASHDDSLASHYYYY);
		if ((patternDash.matcher(textDate).matches())) {
			return OnpierDateFormatter.getDateFormatddDashMMDashyyyy(textDate);
		}
		return (patternSlash.matcher(textDate).matches())
				? OnpierDateFormatter.getDateFormatddSlashMMSlashyyyy(textDate)
				: null;

	}

}
