/**
 * 
 */
package com.moneymoney.account.service;

import com.moneymoney.pojo.account.CurrentAccount;

/**
 * @author ugawari
 *
 */
public interface CurrentAccountService {

	CurrentAccount createNewCurrentAccount(CurrentAccount account);

	CurrentAccount updateCurrentAccount(CurrentAccount account);

}
