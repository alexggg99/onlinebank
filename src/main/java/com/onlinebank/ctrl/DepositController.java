package com.onlinebank.ctrl;

import com.onlinebank.model.accounts.Account;
import com.onlinebank.model.accounts.PrimaryAccount;
import com.onlinebank.model.accounts.SavingAccount;
import com.onlinebank.service.AccountService;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("account")
public class DepositController {

    @Autowired
    private AccountService accountService;

    @InitBinder("command")
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.setLenient(false);
        binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, false));
    }

    @ModelAttribute("command")
    public FormCommand command() {
        return new FormCommand();
    }

    @ModelAttribute("accounts")
    public List<InnerAccount> accounts(Principal principal) {
        List<InnerAccount> accounts = new ArrayList<>();
        accountService.getAllAccounts(principal.getName()).stream().forEach(item -> {
            accounts.add(
                    new InnerAccount(item.getId(),
                            (item instanceof PrimaryAccount) ? "Primary" : "Saving",
                            item.getAccountBalance(),
                            item.getAccountNumber(),
                            (item instanceof SavingAccount) ? "RUR" : ((PrimaryAccount) item).getCurrency().name()
                    )
            );
        });
        return accounts;
    }

    @GetMapping("/deposit")
    public String deposit(Model model, Principal principal){
        model.addAttribute("url", "deposit");
        return "deposit";
    }

    @PostMapping("/deposit")
    public String depositPost(Model model, @Valid @ModelAttribute("command") FormCommand command, Errors result, Principal principal) {
        if (result.hasErrors() || command.getAmount().intValue() < 0) {
            model.addAttribute("url", "deposit");
            return "deposit";
        }
        accountService.manageAccount("deposit", command.accountId, command.getAmount(), principal.getName());
        return "redirect:/account/deposit?success";
    }

    @GetMapping("/withdraw")
    public String withdraw(Model model, Principal principal){
        model.addAttribute("url", "withdraw");
        return "deposit";
    }


    @PostMapping("/withdraw")
    public String withdrawPost(Model model, @Valid @ModelAttribute("command") FormCommand command, Errors result, Principal principal) {
        if (result.hasErrors() || command.getAmount().intValue() < 0) {
            model.addAttribute("url", "withdraw");
            return "deposit";
        }
        accountService.manageAccount("withdraw", command.accountId, command.getAmount(), principal.getName());
        return "redirect:/account/withdraw?success";
    }

    @Getter
    public static class InnerAccount extends Account {
        private String type;
        private String currency;

        public InnerAccount(long id, String type,  BigDecimal accountBalance, int accountNumber, String cur) {
            this.setId(id);
            this.setAccountBalance(accountBalance);
            this.setAccountNumber(accountNumber);
            this.type = type;
            this.currency = cur;
        }
    }

    @Data
    public static class FormCommand {
        private String accountId;
        @NotNull
        private BigDecimal amount;
    }

}
