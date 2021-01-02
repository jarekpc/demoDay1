package com.example.demoDay1.controller;

import com.example.demoDay1.config.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class EbookStoreController {

    @Value("${spring.application.name}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @RequestMapping("/")
    public String index(final Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/403")
    public String accessDenied(Model model, Principal principal) {
        return "403";
    }

    @GetMapping("/accountInfo")
    public String getAccountInfo(Model model, Principal principal) {
        MyUserPrincipal loginedUser = (MyUserPrincipal) ((Authentication) principal).getPrincipal();
        model.addAttribute("person", loginedUser.getPerson());
        return "accountInfo";
    }


}
