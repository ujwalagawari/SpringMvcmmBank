package com.moneymoney.pojo.account;

import org.springframework.stereotype.Component;

/**
 * @author ugawari
 *
 */
@Component
public class CurrentAccount {

	private double odLimit;
	private BankAccount bankAccount;
	
	
	
	public CurrentAccount() {
	}

	public CurrentAccount(String accountHolderName, double accountBalance, double odLimit) {
		bankAccount = new BankAccount(accountHolderName, accountBalance);
		this.odLimit = odLimit;
	}
	
	public CurrentAccount(int accountNumber, String accountHolderName, double accountBalance, double odLimit, String type) {
		bankAccount = new BankAccount(accountNumber, accountHolderName, accountBalance, type);
		this.odLimit = odLimit;
	}
	
	public CurrentAccount(double odLimit) {
		this.odLimit=odLimit;
	}

	public double getOdLimit() {
		return odLimit;
	}
	public void setOdLimit(double odLimit) {
		this.odLimit = odLimit;
	}
	
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	@Override
	public String toString() {
		return "CurrentAccount [odLimit=" + odLimit + ", " + bankAccount + "]";
	}
	
	
	
}
