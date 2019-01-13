package com.moneymoney.pojo.account;

public class Account {

	private CurrentAccount currentAccount;
	private SavingsAccount savingsAccount;
	
	public Account(int accountNumber, String accountHolderName, double accountBalance,
			boolean salary, double odLimit, String type) {
		currentAccount = new CurrentAccount(accountNumber,accountHolderName, accountBalance, odLimit,type);
		savingsAccount = new SavingsAccount(accountNumber, accountHolderName, accountBalance, salary, type);
	}

	
}
