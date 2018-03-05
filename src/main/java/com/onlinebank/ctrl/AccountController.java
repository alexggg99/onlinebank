package com.onlinebank.ctrl;

import com.onlinebank.model.accounts.PrimaryAccount;
import com.onlinebank.model.accounts.PrimaryTransaction;
import com.onlinebank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/primaryAccount")
    public String primaryAccount(@RequestParam("id") int id, Model model) {
        model.addAttribute("title", "Primary account");
        PrimaryAccount primaryAccount = (PrimaryAccount) accountService.getPrimaryAccount(id);
        List<PrimaryTransaction> transactions = primaryAccount.getTransactionList();
        model.addAttribute("account", primaryAccount);
        model.addAttribute("primaryAccountsPages", transactions.size());
        model.addAttribute("transactions", primaryAccount.getTransactionList());
        return "primaryAccount";
    }

    @RequestMapping("/savingAccount")
    public String savingAccount() {
        return "savingAccount";
    }

}
