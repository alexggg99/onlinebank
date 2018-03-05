package com.onlinebank.ctrl;

import com.onlinebank.model.security.User;
import com.onlinebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    private String home() {
        return "redirect:/login";
    }

    @GetMapping("/index")
    private String user(Principal principal, Model model) {
        User currentUser = userService.findByUsername(principal.getName());
        model.addAttribute("primaryAccounts", currentUser.getPrimaryAccounts());
        model.addAttribute("savingAccount", currentUser.getSavingAccount());
        return "index";
    }
}
