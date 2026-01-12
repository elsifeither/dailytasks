package com.example.dailytasks.model.web;

import com.example.dailytasks.model.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/users")
public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userEntity", new UserEntity());
        return "login";
    }







}
