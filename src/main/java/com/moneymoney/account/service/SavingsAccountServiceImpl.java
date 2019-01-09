package com.moneymoney.account.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moneymoney.account.dao.SavingsAccountDAO;
import com.moneymoney.account.factory.AccountFactory;
import com.moneymoney.pojo.account.SavingsAccount;
import com.moneymoney.pojo.exception.AccountNotFoundException;
import com.moneymoney.pojo.exception.InsufficientFundsException;
import com.moneymoney.pojo.exception.InvalidInputException;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {

	@Autowired
	private AccountFactory factory;
	
	@Autowired
	private SavingsAccountDAO savingsAccountDAO;

	@Override
	public SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary)
			throws ClassNotFoundException, SQLException {
		SavingsAccount account = factory.createNewSavingsAccount(accountHolderName, accountBalance, salary);
		account =savingsAccountDAO.createNewAccount(account);
		return account;
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.getAllSavingsAccount();
	}

	@Override
	public void deposit(SavingsAccount account, double amount) {
			double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance += amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);		
	}
	
	@Override
	public void withdraw(SavingsAccount account, double amount) {
		double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance -= amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
	}

	@Transactional
	@Override
	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount) throws ClassNotFoundException, SQLException {		
		withdraw(sender, amount);
		deposit(receiver, amount);
	}

	@Override
	public SavingsAccount updateAccount(SavingsAccount account, int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return savingsAccountDAO.updateAccount(account, accountNumber);
	}

	@Override
	public SavingsAccount getAccountById(int accountNumber) throws AccountNotFoundException {
		return savingsAccountDAO.getAccountById(accountNumber);
	}

	@Override
	public SavingsAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return savingsAccountDAO.deleteAccount(accountNumber);
	}

	@Override
	public double getCurrentBalance(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return savingsAccountDAO.getCurrentBalance(accountNumber);
	}
	
	@Override
	public List<SavingsAccount> getAccountsByHolderName(String holderName) throws SQLException, ClassNotFoundException {
		return savingsAccountDAO.getAccountsByHolderName(holderName);
	}

	@Override
	public List<SavingsAccount> getAccountsBetweenMinMaxAccountBal(Double minBalance, Double maxBalance) throws SQLException, ClassNotFoundException {
		return savingsAccountDAO.getAccountsBetweenMinMaxAccountBal(minBalance, maxBalance);
	}
}
