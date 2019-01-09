package com.moneymoney.pojo.exception;
import org.springframework.stereotype.Component;


public class InvalidInputException extends RuntimeException {

	public InvalidInputException(String message) {
		super(message);
	}

}
