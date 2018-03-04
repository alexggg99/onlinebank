package com.onlinebank.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    @GetMapping("/")
    private String home() {
        return "redirect:/login";
    }

    @GetMapping("/user")
    private String user() {
        return "user";
    }
}
