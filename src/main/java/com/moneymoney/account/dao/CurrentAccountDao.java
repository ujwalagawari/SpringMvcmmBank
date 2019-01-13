package com.moneymoney.account.dao;

import com.moneymoney.pojo.account.CurrentAccount;

/**
 * @author ugawari
 *
 */
public interface CurrentAccountDao {

	CurrentAccount createNewCurrentAccount(CurrentAccount account);

	CurrentAccount updateCurrentAccount(CurrentAccount account);
	
}
