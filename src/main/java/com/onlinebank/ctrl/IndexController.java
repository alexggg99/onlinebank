package com.onlinebank.ctrl;

import com.onlinebank.model.accounts.SavingAccount;
import com.onlinebank.repo.PrimaryAccountRepo;
import com.onlinebank.repo.SavingAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private PrimaryAccountRepo primaryAccountRepo;

    @Autowired
    private SavingAccountRepo savingAccountRepo;

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String user(Principal principal, Model model) {
        model.addAttribute("primaryAccounts", primaryAccountRepo.findByUserUsernameOrderById(principal.getName()));
        List<SavingAccount> savingAccountList = savingAccountRepo.findByUserUsernameOrderById(principal.getName());
        if (savingAccountList.size() > 0) {
            model.addAttribute("savingAccount", savingAccountList.get(0));
        }
        return "index";
    }

    @GetMapping("/primaryAccounts")
    public String primaryAccounts(Principal principal, Model model) {
        model.addAttribute("accountBalance", "Primary balance");
        model.addAttribute("accounts", primaryAccountRepo.findByUserUsernameOrderById(principal.getName()));
        model.addAttribute("detailsURL", "/account/primaryAccount");
        model.addAttribute("referenceCssClass", "panel-info");
        return "accountsView";
    }

    @GetMapping("/savingAccounts")
    public String savingAccounts(Principal principal, Model model) {
        model.addAttribute("accountBalance", "Saving balance");
        model.addAttribute("accounts", primaryAccountRepo.findByUserUsernameOrderById(principal.getName()));
        model.addAttribute("detailsURL", "/account/savingAccount");
        model.addAttribute("referenceCssClass", "panel-success");
        return "accountsView";
    }

}
