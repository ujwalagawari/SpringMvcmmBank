package com.moneymoney.pojo.account;

/**
 * @author ugawari
 *
 */
public class CurrentAccount {

	private double odLimit;
	private BankAccount bankAccount;
	
	public CurrentAccount(String accountHolderName, double accountBalance, double odLimit) {
		bankAccount = new BankAccount(accountHolderName, accountBalance);
		this.odLimit = odLimit;
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
