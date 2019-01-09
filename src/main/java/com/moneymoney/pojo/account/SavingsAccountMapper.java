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
public class SavingsAccountMapper implements RowMapper<SavingsAccount> {

	
	@Override
	public SavingsAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		SavingsAccount savingsAccount = new SavingsAccount(rs.getInt("account_id"),
				rs.getString("account_hn"),
				rs.getDouble(3),
				rs.getBoolean("salary")
				);
		return savingsAccount;
	}

}
