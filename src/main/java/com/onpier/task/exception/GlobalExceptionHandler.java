package com.onpier.task.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import com.onpier.task.model.ErrorResponse;

/**
 * @author Udayshree 
 * 
 *         It handle the all exception for application
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * method to handle exception for the records not found
	 * 
	 * @param ex:NoRecordFoundException
	 * @return errorResponse:
	 */
	@ExceptionHandler(NoRecordFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse handleNoRecordFoundException(NoRecordFoundException ex) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage("No Record Found");
		return errorResponse;
	}

	/**
	 * Method to handle the default exception
	 * 
	 * @param ex: Exception
	 * @return response:ErrorResponse
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse handleDefaultException(Exception ex) {
		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getMessage());
		return response;
	}
}
