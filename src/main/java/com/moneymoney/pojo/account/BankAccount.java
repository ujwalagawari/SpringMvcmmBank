package com.moneymoney.pojo.account;
import org.springframework.stereotype.Component;

/**
 * @author ugawari
 *
 */
@Component
public class BankAccount {
	private int accountNumber;
	private double accountBalance;
	private String accountHolderName;

	/**
	 * 
	 * @param accountHolderName
	 * @param accountBalance
	 */
	public BankAccount(String accountHolderName, double accountBalance) {
		this.accountHolderName = accountHolderName;
		this.accountBalance = accountBalance;
	}
	

	public BankAccount() {
		super();
	}


	/**
	 * 
	 * @param accountHolderName
	 */
	public BankAccount(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public BankAccount(int accountNumber, String accountHolderName, double accountBalance) {
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.accountBalance = accountBalance;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}
	
	@Override
	public String toString() {
		return "accountNumber=" + accountNumber + ", accountBalance=" + accountBalance
				+ ", accountHolderName=" + accountHolderName + "";
	}

}
