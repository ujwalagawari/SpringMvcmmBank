package com.moneymoney.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.moneymoney.pojo.account.SavingsAccount;
import com.moneymoney.pojo.exception.InsufficientFundsException;
import com.moneymoney.pojo.exception.InvalidInputException;

@Aspect
@Component
public class FundTransferValidationAspect {

	private Logger  logger = Logger.getLogger(FundTransferValidationAspect.class.getName());
	
	/*
	 * @Before("execution(* ccom.moneymoney.account.service.**(..))") public void
	 * log1() { logger.info("Before - logging the method"); }
	 */
	
	@Around("execution(* com.moneymoney.account.service.SavingsAccountServiceImpl.deposit(..))")
	public void depositLog(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Deposite method of saving account is initilized.");
		  Object[] params = pjp.getArgs();
		  if((Double)params[1] > 0) {
			  pjp.proceed();
			  logger.info(params[1]+"Amount deposit successfully");
		  }else {
			  logger.info("Amount should not be less than or equal to zero.");
			  throw new InvalidInputException("Invalid Input Amount!");
		  }
	}
	 
	@Around("execution(* com.moneymoney.account.service.SavingsAccountServiceImpl.withdraw(..))")
	public void withdrawLog(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Withdraw method of saving account is initilized.");
		  Object[] params = pjp.getArgs();
		  SavingsAccount account = (SavingsAccount) params[0];
		  if(account.getBankAccount().getAccountBalance() >= (Double)params[1]  && (Double)params[1] > 0) {
			  pjp.proceed();
			  logger.info("Amount withdraw successfully.");
		  }else if((Double)params[1] <= 0){
			  logger.info("Amount should not be less than or equal to zero.");
			  throw new InvalidInputException("Invalid Input Amount!");
		  }else {
			  logger.info("Invalid Input or Insufficient Funds!");
			  throw new InsufficientFundsException("Invalid Input or Insufficient Funds!");
		  }
	}
	
	@Around("execution(* com.moneymoney.account.service.SavingsAccountServiceImpl.fundTransfer(..))")
	public void fundTransferLog(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("fundTransfer method of saving account is initilized.");
			  pjp.proceed();
	    logger.info("fundTransfer done successfully.");	  
	}
}
