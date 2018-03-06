package com.onlinebank.ctrl;

import com.onlinebank.model.accounts.PrimaryAccount;
import com.onlinebank.repo.PrimaryTransactionRepo;
import com.onlinebank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PrimaryTransactionRepo primaryTransactionRepo;

    @Value("${app.itemsPerPage}")
    private int itemsPerPage;

    @RequestMapping("/primaryAccount")
    public String primaryAccount(@RequestParam("id") int id, @RequestParam("page") int page, Model model, Principal principal) {
        model.addAttribute("title", "Primary account");
        PrimaryAccount primaryAccount = (PrimaryAccount) accountService.getPrimaryAccount(id, principal.getName());
        if (primaryAccount != null) {
            model.addAttribute("account", primaryAccount);
            model.addAttribute("pages", IntStream.range(1, countPageNum(primaryAccount.getTransactionList().size()) + 1).toArray());
            model.addAttribute("transactions", primaryTransactionRepo.findByAccountAndAccount_UserUsername(primaryAccount, principal.getName(), PageRequest.of(--page, itemsPerPage)));
        }
        return "account";
    }

    @RequestMapping("/savingAccount")
    public String savingAccount() {
        return "account";
    }

    private int countPageNum(int result) {
        if (result > 0 && result < itemsPerPage) {
            return 1;
        }
        if (result % itemsPerPage == 0) {
            result = result / itemsPerPage;
        } else {
            result = itemsPerPage / itemsPerPage + 1;
        }
        return result;
    }

}
