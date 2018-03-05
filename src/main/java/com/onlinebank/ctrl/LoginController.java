package com.onlinebank.ctrl;

import com.onlinebank.model.security.User;
import com.onlinebank.repo.RoleRepo;
import com.onlinebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepo roleRepo;

    @GetMapping("login")
    private String login(Model model) {
        return "login";
    }

    @GetMapping("signup")
    private String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("signup")
    private String signupPost(@ModelAttribute("user") User user, Model model) {
        if(userService.checkUserExist(user.getUsername(), user.getEmail())) {
            if(userService.checkUsernameExist(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }
            if(userService.checkUsernameExist(user.getEmail())) {
                model.addAttribute("emailExists", true);
            }
            return "signup";
        } else {
            userService.createUser(user, roleRepo.findByName("ROLE_USER"));
        }
        return "redirect:/login";
    }
}
