package com.onlinebank.ctrl;

import com.onlinebank.model.accounts.PrimaryAccount;
import com.onlinebank.model.accounts.PrimaryTransaction;
import com.onlinebank.repo.PrimaryTransactionRepo;
import com.onlinebank.repo.specification.TransactionSpecification;
import com.onlinebank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
    public String primaryAccount(@RequestParam("id") int id,
                                 @RequestParam("page") int page,
                                 @RequestParam("filter") String filter,
                                 Model model,
                                 Principal principal) {
        model.addAttribute("title", "Primary account");
        PrimaryAccount primaryAccount = (PrimaryAccount) accountService.getPrimaryAccount(id, principal.getName());
        if (primaryAccount != null) {
            model.addAttribute("account", primaryAccount);
            if (StringUtils.isEmpty(filter)) {
                model.addAttribute("transactions", primaryTransactionRepo.findByAccount(primaryAccount, PageRequest.of(--page, itemsPerPage)));
                model.addAttribute("pages", IntStream.range(1, countPageNum(primaryAccount.getTransactionList().size()) + 1).toArray());
            } else {
                Page<PrimaryTransaction> pagable =  primaryTransactionRepo.findAll(TransactionSpecification.findAll(principal.getName(), filter), PageRequest.of(--page, itemsPerPage));
                model.addAttribute("transactions", pagable.getContent());
                model.addAttribute("pages", IntStream.range(1, pagable.getTotalPages() + 1).toArray());
                model.addAttribute("filter", filter);
            }
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
