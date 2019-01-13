/**
 * 
 */
package com.moneymoney.pojo.account;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author ugawari
 *
 */

public class AccountMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		Account savingsAccount = new Account(rs.getInt("account_id"), rs.getString("account_hn"), rs.getDouble(3),
				rs.getBoolean("salary"), rs.getDouble("odlimit"), rs.getString("type"));
		return savingsAccount;
	}

}
 
 
