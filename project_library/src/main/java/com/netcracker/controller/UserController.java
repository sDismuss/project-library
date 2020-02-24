package com.netcracker.controller;

import com.netcracker.model.User;
import com.netcracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/library/user/{id}")
    public String userForm(Model model, @PathVariable("id") String id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user";
    }
}
