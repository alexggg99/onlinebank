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
    private String home() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    private String user(Principal principal, Model model) {
        model.addAttribute("primaryAccounts", primaryAccountRepo.findByUserUsernameOrderById(principal.getName()));
        List<SavingAccount> savingAccountList = savingAccountRepo.findByUserUsernameOrderById(principal.getName());
        if (savingAccountList.size() > 0) {
            model.addAttribute("savingAccount", savingAccountList.get(0));
        }
        return "index";
    }
}
