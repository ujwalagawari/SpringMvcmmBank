/**
 * 
 */
package com.moneymoney;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.moneymoney.account.service.SavingsAccountService;
import com.moneymoney.account.service.SavingsAccountServiceImpl;
import com.moneymoney.pojo.exception.AccountNotFoundException;

/**
 * @author ugawari
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SavingsAccountServiceImpl.class,
	loader = AnnotationConfigContextLoader.class)
public class DepositeTest {

	@Autowired
	SavingsAccountService savingsAccountService;
	
	
	@Before
	public void setUp() {
		//savingsAccountService = new SavingsAccountServiceImpl();
	}
	
	@Test
	public void d() throws ClassNotFoundException, SQLException, AccountNotFoundException {
		System.out.println(savingsAccountService.getCurrentBalance(153));
		System.out.println(111);
	}

	
}
