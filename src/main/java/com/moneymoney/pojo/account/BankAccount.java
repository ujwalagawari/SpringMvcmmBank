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
	private String type;

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

	public BankAccount(int accountNumber, String accountHolderName, double accountBalance, String type) {
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.accountBalance = accountBalance;
		this.type=type;
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
	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", accountBalance=" + accountBalance
				+ ", accountHolderName=" + accountHolderName + ", type=" + type + "]";
	}


	
}
