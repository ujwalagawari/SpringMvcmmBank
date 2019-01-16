package com.moneymoney.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.moneymoney.account.service.SavingsAccountService;
import com.moneymoney.pojo.account.Account;
import com.moneymoney.pojo.account.SavingsAccount;
import com.moneymoney.pojo.exception.AccountNotFoundException;
import com.moneymoney.validation.AccountValidation;

/**
 * @author ugawari
 *
 */
@Controller
@SessionAttributes("account")
public class AccountController {

	@Autowired
	AccountValidation accountValidation;

	@Autowired
	SavingsAccountService service;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/openAccount")
	public String openAccount() {
		return "OpenAccount";
	}

	@RequestMapping("/getAll")
	public String getAllSavingsAccount(Model model, SessionStatus status) throws ClassNotFoundException, SQLException {
		if (!status.isComplete()) {
			status.setComplete();
		}
		//List<SavingsAccount> accounts = service.getAllSavingsAccount();
		List<Account> accounts = service.getAllAccounts();
		System.out.println(accounts.get(1).toString());
		model.addAttribute("accounts", accounts);
		return "AccountDetails";
	}

	@RequestMapping("/addNewSAForm")
	public String addNewSAForm(Map map) {
		map.put("account", new SavingsAccount());
		return "addNewSAForm";
	}
	

	@RequestMapping("/addNewSA")
	public String addNewSA(@ModelAttribute("account") SavingsAccount savingAccount, Model model, BindingResult result)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		accountValidation.validate(savingAccount, result);

		if (result.hasErrors()) {
			return "addNewSAForm";
		}

		if (savingAccount.getBankAccount().getAccountNumber() != 0) {
			savingAccount = service.updateAccount(savingAccount, savingAccount.getBankAccount().getAccountNumber());
		} else {
			savingAccount = service.createNewAccount(savingAccount.getBankAccount().getAccountHolderName(),
					savingAccount.getBankAccount().getAccountBalance(), savingAccount.isSalary());
		}
		Account account = new Account(savingAccount.getBankAccount().getAccountNumber(), savingAccount.getBankAccount().getAccountHolderName(),
				savingAccount.getBankAccount().getAccountBalance(), savingAccount.isSalary(), 0.0, 
				savingAccount.getBankAccount().getType());
		model.addAttribute("account", account);
		// return "redirect:display";
		return "AccountDetails";
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public String display(SessionStatus status) {
		return "AccountDetails";
	}

	@RequestMapping("/closeAccount")
	public String closeAccount() {
		return "closeAccount";
	}

	@RequestMapping("/closeSaAccount")
	public String closeSaAccount(@RequestParam("accountNumber") int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		service.deleteAccount(accountNumber);
		return "redirect:getAll";
	}

	@RequestMapping(value = "/searchForm", method = RequestMethod.GET)
	public String searchForm() {
		return "SearchForm";
	}

	@RequestMapping(value = "/searchForm", method = RequestMethod.POST)
	public ModelAndView searchAccount(@RequestParam("txtAccountNumber") int accountNumber)
			throws AccountNotFoundException {
		SavingsAccount account = service.getAccountById(accountNumber);
		return new ModelAndView("AccountDetails", "account",account);
		//return "redirect:display";
	}

	@RequestMapping(value = "/updateSA", method = RequestMethod.GET)
	public String updateSA() {
		return "updateSA";
	}

	@RequestMapping(value = "/updateSA", method = RequestMethod.POST)
	public ModelAndView updateSAccount(@RequestParam("txtAccountNumber") int accountNumber)
			throws AccountNotFoundException {
		SavingsAccount account = service.getAccountById(accountNumber);
		return new ModelAndView("addNewSAForm", "account", account);
	}

	@RequestMapping(value = "/getCurrentBalance", method = RequestMethod.GET)
	public String getCurrentBalance() {
		return "getCurrentBalance";
	}

	@RequestMapping(value = "/getCurrentBalance", method = RequestMethod.POST)
	public ModelAndView getCurrentBalanceOfSa(@RequestParam("accountNumber") int accountNumber)
			throws AccountNotFoundException, ClassNotFoundException, SQLException {
		double currentBalance = service.getCurrentBalance(accountNumber);
		return new ModelAndView("currentBalance", "currentBalance", currentBalance);
	}

	@RequestMapping(value = "/deposit", method = RequestMethod.GET)
	public String deposit() {
		return "deposit";
	}

	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public String depositInSA(@RequestParam("accountNumber") int accountNumber, @RequestParam("amount") double amount,
			Model model) throws AccountNotFoundException {
		SavingsAccount savingsAccount = service.getAccountById(accountNumber);
		service.deposit(savingsAccount, amount);
		savingsAccount = service.getAccountById(accountNumber);
		model.addAttribute("account", savingsAccount);
		return "redirect:display";
		// return new ModelAndView("AccountDetails", "account", savingsAccount);
	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.GET)
	public String withdraw() {
		return "withdraw";
	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public String withdrawFromSa(@RequestParam("accountNumber") int accountNumber,
			@RequestParam("amount") double amount, Model model) throws AccountNotFoundException {
		SavingsAccount savingsAccount = service.getAccountById(accountNumber);
		service.withdraw(savingsAccount, amount);
		savingsAccount = service.getAccountById(accountNumber);
		// return new ModelAndView("AccountDetails", "account", savingsAccount);
		model.addAttribute("account", savingsAccount);
		return "redirect:display";
	}

	@RequestMapping(value = "/fundTransfer", method = RequestMethod.GET)
	public String fundTransfer() {
		return "fundTransfer";
	}

	@RequestMapping(value = "/fundTransfer", method = RequestMethod.POST)
	public String fundTransferInSA(@RequestParam("senderAccountNumber") int senderAccountNumber,
			@RequestParam("receiverAccountNumber") int receiverAccountNumber, @RequestParam("amount") double amount,
			Model model) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount senderSavingsAccount = service.getAccountById(senderAccountNumber);
		SavingsAccount receiverSavingsAccount = service.getAccountById(receiverAccountNumber);
		service.fundTransfer(senderSavingsAccount, receiverSavingsAccount, amount);
		SavingsAccount account = service.getAccountById(senderAccountNumber);
		model.addAttribute("account", account);
		// return "AccountDetails";
		return "redirect:display";
	}

}
