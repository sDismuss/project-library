package com.netcracker.controller;

import com.netcracker.model.User;
import com.netcracker.service.MyUserDetailsService;
import com.netcracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private UserService userService;

    @GetMapping("/account")
    public String userForm(Model model) {
        String username = myUserDetailsService.getCurrentUsername();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "user";
    }
}
