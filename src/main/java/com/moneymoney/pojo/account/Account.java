package com.moneymoney.pojo.account;

public class Account {

	private CurrentAccount currentAccount;
	private SavingsAccount savingsAccount;
	private BankAccount bankAccount;
	public Account(int accountNumber, String accountHolderName, double accountBalance,
			boolean salary, double odLimit, String type) {
		currentAccount = new CurrentAccount(odLimit);
		savingsAccount = new SavingsAccount(salary);
		bankAccount = new BankAccount(accountNumber, accountHolderName, accountBalance, type);
	}
	
	@Override
	public String toString() {
		return "Account [currentAccount=" + currentAccount + ", savingsAccount=" + savingsAccount + ", bankAccount="
				+ bankAccount + "]";
	}
	
	public CurrentAccount getCurrentAccount() {
		return currentAccount;
	}
	
	public void setCurrentAccount(CurrentAccount currentAccount) {
		this.currentAccount = currentAccount;
	}
	
	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}
	
	public void setSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
	}
	
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	
	
}
