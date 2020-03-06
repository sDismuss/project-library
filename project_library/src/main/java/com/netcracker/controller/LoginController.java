package com.netcracker.controller;

import com.netcracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        Boolean logIn = userService.userLogIn();
        model.addAttribute("logIn", logIn);
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit() {
        return "mainPage";
    }
}
