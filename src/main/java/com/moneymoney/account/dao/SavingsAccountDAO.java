package com.moneymoney.account.dao;

import java.sql.SQLException;
import java.util.List;

import com.moneymoney.pojo.account.Account;
import com.moneymoney.pojo.account.SavingsAccount;
import com.moneymoney.pojo.exception.AccountNotFoundException;

public interface SavingsAccountDAO {
	
	SavingsAccount createNewAccount(SavingsAccount account);
	
	SavingsAccount updateAccount(SavingsAccount account, int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	
	SavingsAccount getAccountById(int accountNumber) throws AccountNotFoundException;
	
	SavingsAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	
	List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException;
	
	void updateBalance(int accountNumber, double currentBalance) ;

	
	double getCurrentBalance(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;

	List<SavingsAccount> getAccountsByHolderName(String holderName) throws SQLException, ClassNotFoundException;

	List<SavingsAccount> getAccountsBetweenMinMaxAccountBal(Double minBalance, Double maxBalance) throws SQLException, ClassNotFoundException;

	List<Account> getAllAccounts();
	
}
