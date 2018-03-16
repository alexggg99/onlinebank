package com.onlinebank.ctrl;

import com.onlinebank.ctrl.common.DTOaccount;
import com.onlinebank.exceptions.NotEnoughAccountBalance;
import com.onlinebank.model.accounts.PrimaryAccount;
import com.onlinebank.model.accounts.SavingAccount;
import com.onlinebank.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/transfer")
@SessionAttributes("command")
public class TransferController {

    @Autowired
    private AccountService accountService;

    @ModelAttribute("command")
    public FormCommand command(HttpSession httpSession) {
        return new FormCommand();
    }

    @ModelAttribute("accounts")
    public List<DTOaccount> accounts(Principal principal) {
        List<DTOaccount> accounts = new ArrayList<>();
        accountService.getAllAccounts(principal.getName()).stream().forEach(item -> {
            accounts.add(
                    new DTOaccount(item.getId(),
                            (item instanceof PrimaryAccount) ? "Primary" : "Saving",
                            item.getAccountBalance(),
                            item.getAccountNumber(),
                            (item instanceof SavingAccount) ? "RUR" : ((PrimaryAccount) item).getCurrency().name()
                    )
            );
        });
        return accounts;
    }

    @InitBinder("command")
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, false));
    }

    @GetMapping("/betweenAccounts")
    public String betweenAccounts() {
        return "betweenAccounts";
    }

    @PostMapping("/betweenAccounts")
    public String betweenAccounts(Model model, @Valid @ModelAttribute("command") FormCommand command, Errors result, Principal principal, HttpSession httpSession) {
        if (result.hasErrors() || command.getAmount().intValue() < 0) {
            return "betweenAccounts";
        }
        accountService.transferBetweenAccounts(command.accountIdFrom, command.getAccountIdTo(), command.getAmount(), principal.getName());
        return "redirect:/transfer/betweenAccounts?success";
    }

    @ExceptionHandler(NotEnoughAccountBalance.class)
    public String handleException() {
        return "redirect:/transfer/betweenAccounts?notEnoughBalance";
    }

    @Data
    public static class FormCommand {
        private String accountIdFrom;
        private String accountIdTo;
        @NotNull
        private BigDecimal amount;
    }

}
