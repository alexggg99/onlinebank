package com.onlinebank.ctrl;

import com.onlinebank.model.accounts.*;
import com.onlinebank.repo.TransactionRepo;
import com.onlinebank.repo.specification.TransactionSpecification;
import com.onlinebank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionRepo transactionRepo;

    @Value("${app.itemsPerPage}")
    private int itemsPerPage;

    @RequestMapping("/primaryAccount")
    public String primaryAccount(@RequestParam("id") int id,
                                 @RequestParam("page") int page,
                                 @RequestParam("filter") String filter,
                                 Model model,
                                 Principal principal) {
        model.addAttribute("title", "Primary account");
        model.addAttribute("servlet", "primaryAccount");
        PrimaryAccount primaryAccount = (PrimaryAccount) accountService.getPrimaryAccount(id, principal.getName());
        complementResponce(model, primaryAccount, filter, principal, page);
        return "account";
    }

    @RequestMapping("/savingAccount")
    public String savingAccount(@RequestParam("id") int id,
                                @RequestParam("page") int page,
                                @RequestParam("filter") String filter,
                                Model model,
                                Principal principal) {
        model.addAttribute("title", "Saving account");
        model.addAttribute("servlet", "savingAccount");
        SavingAccount savingAccount = (SavingAccount) accountService.getSavingAccount(id, principal.getName());
        complementResponce(model, savingAccount, filter, principal, page);
        return "account";
    }

    @GetMapping("/add")
    public String addAccount(Principal principal) {
        List<Currency> currHoldByUser = accountService.getAllAccounts(principal.getName()).stream()
                .filter(elem -> elem instanceof PrimaryAccount)
                .map(elem -> ((PrimaryAccount) elem).getCurrency()).collect(Collectors.toList());
        List<Currency> filtered = Arrays.asList(Currency.values()).stream()
                .filter(elem ->  !currHoldByUser.contains(elem))
                .collect(Collectors.toList());
        if (filtered.size() > 0) {
            accountService.createPrimaryAccount(filtered.get(0), principal.getName());
        }
        return "redirect:/primaryAccounts";
    }

    private void complementResponce(Model model, Account account, String filter, Principal principal, int page){
        if (account != null) {
            model.addAttribute("account", account);
            if (StringUtils.isEmpty(filter)) {
                model.addAttribute("transactions", transactionRepo.findByAccountAndAccountUserUsername(account, principal.getName(), PageRequest.of(--page, itemsPerPage)));
                model.addAttribute("pages", IntStream.range(1, countPageNum(account.getTransactionList().size()) + 1).toArray());
            } else {
                Page<Transaction> pagable =  transactionRepo.findAll(TransactionSpecification.findAll(principal.getName(), account, filter), PageRequest.of(--page, itemsPerPage));
                model.addAttribute("transactions", pagable.getContent());
                model.addAttribute("pages", IntStream.range(1, pagable.getTotalPages() + 1).toArray());
                model.addAttribute("filter", filter);
            }
        }
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
