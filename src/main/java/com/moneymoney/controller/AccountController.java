package com.moneymoney.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moneymoney.account.service.SavingsAccountService;
import com.moneymoney.pojo.account.SavingsAccount;
import com.moneymoney.pojo.exception.AccountNotFoundException;

/**
 * @author ugawari
 *
 */
@Controller
public class AccountController {
	
	@Autowired
	SavingsAccountService service; 
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/getAll")
	public String getAllSavingsAccount(Model model) throws ClassNotFoundException, SQLException{
		  List<SavingsAccount> accounts = service.getAllSavingsAccount();
		  model.addAttribute("accounts", accounts);
		return "AccountDetails";
	}
	
	@RequestMapping("/addNewSAForm")
	public String addNewSAForm() {
		return "addNewSAForm";
	}
	
	@RequestMapping("/addNewSA")
	public String addNewSA(HttpServletRequest request) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		String accountHolderName = request.getParameter("txtAccHN");
		double accountBalance = Double.parseDouble(request.getParameter("txtBalance"));
		boolean salary = request.getParameter("rdSalary").equalsIgnoreCase("yes")?true:false;
		if(request.getParameter("accountNumber") != ""){
			int accountNo = Integer.parseInt(request.getParameter("accountNumber"));
			String name = request.getParameter("txtAccHN");
			SavingsAccount account = service.getAccountById(accountNo);
			account.setSalary(salary);
			account.getBankAccount().setAccountHolderName(name);
			service.updateAccount(account, accountNo);
		}else{
			service.createNewAccount(accountHolderName, accountBalance, salary);
		}
		return "redirect:getAll";
	}
		
	@RequestMapping("/closeAccount")
	public String closeAccount() {
		return "closeAccount";
	}
	
	@RequestMapping("/closeSaAccount")
	public String closeSaAccount(HttpServletRequest request) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		service.deleteAccount(accountNumber);
		return "redirect:getAll";
	}
	  
	@RequestMapping("/searchForm")
	public String searchForm() {
		return "SearchForm";
	}
	  
	@RequestMapping("/searchAccount")
	public String searchAccount(HttpServletRequest request) throws AccountNotFoundException {
		int accountNumber = Integer.parseInt(request.getParameter("txtAccountNumber"));
		SavingsAccount account = service.getAccountById(accountNumber);
		request.setAttribute("account", account);
		return "AccountDetails";
	}
	
	@RequestMapping("/updateSA")
	public String updateSA() {
		return "updateSA";
	}
	  
	@RequestMapping("/updateSAccount")
	public String updateSAccount(HttpServletRequest request, Model model) throws AccountNotFoundException {
		int accountNumber = Integer.parseInt(request.getParameter("txtAccountNumber"));
		SavingsAccount account = service.getAccountById(accountNumber);
		model.addAttribute("account", account);
		return "addNewSAForm";
	}
	 	
	@RequestMapping("/getCurrentBalance")
	public String getCurrentBalance() {
		return "getCurrentBalance";
	}
	  
	@RequestMapping("/getCurrentBalanceOfSa")
	public String getCurrentBalanceOfSa(HttpServletRequest request, Model model) throws AccountNotFoundException, ClassNotFoundException, SQLException {
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		double currentBalance = service.getCurrentBalance(accountNumber);
		model.addAttribute("currentBalance", currentBalance);
		return "currentBalance";
	}
	
	@RequestMapping("/deposit")
	public String deposit() {
		return "deposit";
	}
	
	@RequestMapping("/depositInSA")
	public String depositInSA(HttpServletRequest request, Model model) throws AccountNotFoundException {
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		double amount = Double.parseDouble(request.getParameter("amount"));
		SavingsAccount savingsAccount = service.getAccountById(accountNumber);
		service.deposit(savingsAccount, amount);
		savingsAccount = service.getAccountById(accountNumber);
		model.addAttribute("account", savingsAccount);
		return "AccountDetails";
	}
	  
	
	@RequestMapping("/withdraw")
	public String withdraw() {
		return "withdraw";
	}
	
	@RequestMapping("/withdrawFromSa")
	public String withdrawFromSa(HttpServletRequest request, Model model) throws AccountNotFoundException {
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
	    double amount = Double.parseDouble(request.getParameter("amount"));
	    SavingsAccount savingsAccount = service.getAccountById(accountNumber);
	    service.withdraw(savingsAccount, amount);
	    savingsAccount = service.getAccountById(accountNumber);
		model.addAttribute("account", savingsAccount);
		return "AccountDetails";
	}
	  
	@RequestMapping("/fundTransfer")
	public String fundTransfer() {
		return "fundTransfer";
	}
	 
	@RequestMapping("/fundTransferInSA")
	public String fundTransferInSA(HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		int senderAccountNumber = Integer.parseInt(request.getParameter("senderAccountNumber"));
		int receiverAccountNumber = Integer.parseInt(request.getParameter("receiverAccountNumber"));
		double amount = Double.parseDouble(request.getParameter("amount"));
			SavingsAccount senderSavingsAccount = service.getAccountById(senderAccountNumber);
			SavingsAccount receiverSavingsAccount = service.getAccountById(receiverAccountNumber);
			service.fundTransfer(senderSavingsAccount, receiverSavingsAccount, amount);
		return "fundTransfer";
	}
	
}
