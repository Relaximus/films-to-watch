package com.ftwatch.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping
    public String home(Model model, Principal principal){
        Optional<Principal> p = Optional.ofNullable(principal);
        model.addAttribute("userName",p.map(op->op.getName()).orElse("N/A"));
        return "home";
    }
}
