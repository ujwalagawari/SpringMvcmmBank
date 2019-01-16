/**
 * 
 */
package com.moneymoney.account.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.moneymoney.pojo.account.Account;
import com.moneymoney.pojo.account.AccountMapper;
import com.moneymoney.pojo.account.SavingsAccount;
import com.moneymoney.pojo.account.SavingsAccountMapper;
import com.moneymoney.pojo.exception.AccountNotFoundException;
import com.mysql.jdbc.Statement;

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
		KeyHolder holder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				 PreparedStatement statement = con.prepareStatement("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		         statement.setInt(1, account.getBankAccount().getAccountNumber());
		         statement.setString(2, account.getBankAccount().getAccountHolderName());
		         statement.setDouble(3, account.getBankAccount().getAccountBalance());
		         statement.setBoolean(4, account.isSalary());
		         statement.setString(5, null);
		         statement.setString(6, "SA");
		         return statement;
			}
		},holder);
		
		account.getBankAccount().setAccountNumber(holder.getKey().intValue());
		account.getBankAccount().setType("SA");
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

	@Override
	public List<Account> getAllAccounts() {
		return template.query("SELECT * FROM ACCOUNT" , new AccountMapper());
	}

}
