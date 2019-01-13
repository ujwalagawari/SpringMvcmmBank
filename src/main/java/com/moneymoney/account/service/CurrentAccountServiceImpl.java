/**
 * 
 */
package com.moneymoney.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymoney.account.dao.CurrentAccountDao;
import com.moneymoney.account.factory.AccountFactory;
import com.moneymoney.pojo.account.CurrentAccount;
import com.moneymoney.pojo.account.SavingsAccount;

/**
 * @author ugawari
 *
 */
@Service
public class CurrentAccountServiceImpl implements CurrentAccountService {

	@Autowired
	CurrentAccountDao currentAccountDao; 
	
	@Autowired
	private AccountFactory factory;
	
	@Override
	public CurrentAccount createNewCurrentAccount(CurrentAccount account) {
		account = factory.createNewCurrentAccount(account);
		account = currentAccountDao.createNewCurrentAccount(account);
		return account;
	}

	@Override
	public CurrentAccount updateCurrentAccount(CurrentAccount account) {
		return currentAccountDao.updateCurrentAccount(account);
	}

}
