package com.moneymoney.account.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.moneymoney.pojo.account.CurrentAccount;
import com.mysql.jdbc.Statement;

/**
 * @author ugawari
 *
 */
@Repository
public class CurrentAccountDaoImpl implements CurrentAccountDao {

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public CurrentAccount createNewCurrentAccount(CurrentAccount account) {
		/*
		 * System.out.println(account.getBankAccount().getAccountHolderName());
		 * template.update("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)", new Object[] {
		 * account.getBankAccount().getAccountNumber(),
		 * account.getBankAccount().getAccountHolderName(),
		 * account.getBankAccount().getAccountNumber(), 0,account.getOdLimit(),"CA" });
		 */
		
		KeyHolder holder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement = con.prepareStatement("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, account.getBankAccount().getAccountNumber());
				statement.setString(2, account.getBankAccount().getAccountHolderName());
				statement.setDouble(3, account.getBankAccount().getAccountBalance());
				statement.setString(4, null);
				statement.setDouble(5, account.getOdLimit());
				statement.setString(6, "CA");
				return statement;
			}
		}, holder);

		account.getBankAccount().setAccountNumber(holder.getKey().intValue());
		account.getBankAccount().setType("CA");
		return account;
	}

	@Override
	public CurrentAccount updateCurrentAccount(CurrentAccount account) {
		template.update("UPDATE ACCOUNT SET account_hn=? where account_id=?",
				new Object[] { account.getBankAccount().getAccountHolderName(),
						account.getBankAccount().getAccountNumber()
				});
		return account;
	}

}
