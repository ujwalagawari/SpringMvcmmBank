/**
 * 
 */
package com.moneymoney.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.moneymoney.account.service.CurrentAccountService;
import com.moneymoney.pojo.account.CurrentAccount;
import com.moneymoney.pojo.exception.AccountNotFoundException;
import com.moneymoney.validation.CurrentAccountValidation;

/**
 * @author ugawari
 *
 */
@Controller
@SessionAttributes("account")
public class CurrentAccountController {

	@Autowired
	CurrentAccountValidation accountValidation;
	
	@Autowired
	CurrentAccountService currentAccountService;
	
	@RequestMapping("/addNewCAForm")
	public String addNewCAForm(Map map) {
		map.put("account", new CurrentAccount());
		return "addNewCAForm";
	}
	
	@RequestMapping("/addNewCA")
	public String addNewCA(@ModelAttribute("account") CurrentAccount account, Model model, BindingResult result)
			throws AccountNotFoundException {
		accountValidation.validate(account, result);

		if (result.hasErrors()) {
			return "addNewCAForm";
		}

		if (account.getBankAccount().getAccountNumber() != 0) {
			account = currentAccountService.updateCurrentAccount(account);
		} else {
			account = currentAccountService.createNewCurrentAccount(account);
		}
		model.addAttribute("account", account);
		return "AccountDetails";
	}
}
