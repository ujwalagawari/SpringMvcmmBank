package com.moneymoney.pojo.exception;

import org.springframework.stereotype.Component;


public class AccountNotFoundException extends Exception {
	public AccountNotFoundException(String message) {
		super(message);
	}
}
