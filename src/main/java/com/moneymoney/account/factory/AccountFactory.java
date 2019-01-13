package com.moneymoney.account.factory;

import org.springframework.stereotype.Component;

import com.moneymoney.pojo.account.CurrentAccount;
import com.moneymoney.pojo.account.SavingsAccount;

public final class AccountFactory {
	
	private static AccountFactory factory = new AccountFactory();

	private AccountFactory() {
		
	}
	
	public static AccountFactory getInstance() {
		return factory;
	}

	public SavingsAccount createNewSavingsAccount(String accountHolderName, double accountBalance, boolean salary) {
		return new SavingsAccount(accountHolderName, accountBalance, salary);
	}
	
	public CurrentAccount createNewCurrentAccount(CurrentAccount account) {
		return new CurrentAccount(account.getBankAccount().getAccountHolderName(), account.getBankAccount().getAccountBalance(), account.getOdLimit());
	}
}
