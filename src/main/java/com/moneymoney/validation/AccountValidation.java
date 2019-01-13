/**
 * 
 */
package com.moneymoney.validation;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.moneymoney.pojo.account.SavingsAccount;

/**
 * @author ugawari
 *
 */
@Component
public class AccountValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		SavingsAccount account = (SavingsAccount) target;
		if (account.getBankAccount().getAccountBalance() < 500) {
			errors.rejectValue("bankAccount.accountBalance", "bankAccount.accountBalance.minimum",
					"Balance should not be less then 500.");
		}

		if (!Pattern.matches("^[a-zA-Z]+$", account.getBankAccount().getAccountHolderName())) {
			errors.rejectValue("bankAccount.accountHolderName", "bankAccount.accountHolderName.alphanumeric",
					"Name should not contain special char and numbers.");
		}

	}

}
