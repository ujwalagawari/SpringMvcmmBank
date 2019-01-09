package com.moneymoney.pojo.exception;
import org.springframework.stereotype.Component;


public class InsufficientFundsException extends RuntimeException {

	public InsufficientFundsException(String message) {
		super(message);
	}

}
