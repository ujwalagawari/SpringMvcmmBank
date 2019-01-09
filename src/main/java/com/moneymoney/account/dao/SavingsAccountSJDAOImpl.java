/**
 * 
 */
package com.moneymoney.account.dao;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.moneymoney.pojo.account.SavingsAccount;
import com.moneymoney.pojo.account.SavingsAccountMapper;
import com.moneymoney.pojo.exception.AccountNotFoundException;

/**
 * @author ugawari
 *
 */
@Repository
@Primary
public class SavingsAccountSJDAOImpl implements SavingsAccountDAO{
	
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public SavingsAccount createNewAccount(SavingsAccount account) {
		template.update("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)", new Object[] {
				account.getBankAccount().getAccountNumber(),
				account.getBankAccount().getAccountHolderName(),
				account.getBankAccount().getAccountBalance(),
				account.isSalary(),
				null, "SA"
		});
		return account;
	}

	@Override
	public SavingsAccount updateAccount(SavingsAccount account, int accountNumber)throws ClassNotFoundException, SQLException, AccountNotFoundException {
		template.update("UPDATE ACCOUNT SET salary=?, account_hn=? where account_id=?",
				new Object[] {account.isSalary(),
						account.getBankAccount().getAccountHolderName(),
						accountNumber
				});
		return account;
	}

	@Override
	public SavingsAccount getAccountById(int accountNumber) {
		return template.queryForObject("SELECT * FROM account where account_id=?" ,
				new Object[] {accountNumber}, new SavingsAccountMapper());
	}

	@Override
	public SavingsAccount deleteAccount(int accountNumber)throws ClassNotFoundException, SQLException, AccountNotFoundException {
		template.update("DELETE FROM ACCOUNT WHERE ACCOUNT_ID=?", accountNumber);
		return getAccountById(accountNumber);
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() {
		return template.query("SELECT * FROM ACCOUNT" , new SavingsAccountMapper());
	}

	@Override
	public void updateBalance(int accountNumber, double currentBalance) {
		template.update("UPDATE ACCOUNT SET account_balance=? where account_id=?", 
				new Object[] {
						currentBalance, accountNumber
				});
	}

	

	@Override
	public double getCurrentBalance(int accountNumber) {
		return template.queryForObject("SELECT ACCOUNT_BALANCE FROM ACCOUNT WHERE ACCOUNT_ID=?", new Object[] {accountNumber }, Double.class); 
	}

	@Override
	public List<SavingsAccount> getAccountsByHolderName(String holderName) {
		String sql = "SELECT * FROM ACCOUNT WHERE account_hn=?";
		return template.query(sql, new Object[] {holderName}, new SavingsAccountMapper());
	}

	@Override
	public List<SavingsAccount> getAccountsBetweenMinMaxAccountBal(Double minBalance, Double maxBalance) {
		List<SavingsAccount> listOfAccounts= template.query("SELECT * FROM ACCOUNT WHERE account_balance between ? and ?", 
				new Object[] {minBalance, maxBalance},
				new SavingsAccountMapper()
		);
		return listOfAccounts;
	}

}
